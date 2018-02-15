package edu.nc.servicebus.datagrid.dao;

import edu.nc.servicebus.datagrid.model.User;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.cache.Cache;
import java.util.Date;

@Repository
@Transactional
public class UserDao {

    @Autowired
    private Ignite ignite;

    private CacheConfiguration<Integer, User> userCacheCfg;

    public static final String USER_CACHE_NAME = UserDao.class.getSimpleName() + "User";

    public void add(String login, String password,String email) throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, User> userCache = ignite.getOrCreateCache(userCacheCfg);
        User user = new User(findId(),login, password, new Date(),email);
        userCache.put(user.getUserId(), user);
    }


    public String getAll() throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, User> userCache = ignite.getOrCreateCache(userCacheCfg);

        String result = "[";

        QueryCursor<Cache.Entry<Integer, User>> users = userCache.query(new SqlQuery(
                User.class,
                "from \""  + UserDao.USER_CACHE_NAME + "\".User "));
        for(Cache.Entry<Integer, User> user : users){

            result +=  user.toString();
        }
        result += "]";

        return result;
    }

    public void delete(int id) {
        getCacheCfg();
        IgniteCache<Integer, User> userCache = ignite.getOrCreateCache(userCacheCfg);
        userCache.getAndRemove(id);
    }

    private  void  getCacheCfg(){
        if(userCacheCfg == null) {
            userCacheCfg = new CacheConfiguration<>(UserDao.USER_CACHE_NAME);
            userCacheCfg.setCacheMode(CacheMode.PARTITIONED);
            userCacheCfg.setIndexedTypes(Integer.class, User.class);
        }
    }

    private int findId(){
        int max = 0;
        IgniteCache<Integer, User> userCache = ignite.getOrCreateCache(userCacheCfg);

        QueryCursor<Cache.Entry<Integer, User>> users = userCache.query(new SqlQuery(
                User.class,
                "from \""  + UserDao.USER_CACHE_NAME + "\".User "));
        for(Cache.Entry<Integer, User> user : users){
            if(max < user.getValue().getUserId())
                max = user.getValue().getUserId();
        }

        return ++max;
    }
}
