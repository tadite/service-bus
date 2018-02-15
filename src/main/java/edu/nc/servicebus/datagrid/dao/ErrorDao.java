package edu.nc.servicebus.datagrid.dao;

import edu.nc.servicebus.datagrid.model.Error;
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
public class ErrorDao {

    @Autowired
    private Ignite ignite;

    private CacheConfiguration<Integer, Error> errorCacheCfg;

    public static final String ERROR_CACHE_NAME = ErrorDao.class.getSimpleName() + "Error";

    public void add(String  reason) throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Error> errorCache = ignite.getOrCreateCache(errorCacheCfg);

        Error error = new Error(findId(),reason,new Date());
        errorCache.put(error.getErrorId(), error);
    }

    public String getAll() throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Error> errorCache = ignite.getOrCreateCache(errorCacheCfg);

        String result = "[";

        QueryCursor<Cache.Entry<Integer, Error>> errors = errorCache.query(new SqlQuery(
                Error.class,
                "from \""  + ErrorDao.ERROR_CACHE_NAME + "\".Error "));
        for(Cache.Entry<Integer, Error> error : errors){

            result +=  error.toString();
        }
        result += "]";

        return result;
    }

    public void delete(int id) {
            getCacheCfg();
            IgniteCache<Integer, Error> errorCache = ignite.getOrCreateCache(errorCacheCfg);
            errorCache.getAndRemove(id);
    }

    private void  getCacheCfg(){
        if(errorCacheCfg == null) {
            errorCacheCfg = new CacheConfiguration<>(ErrorDao.ERROR_CACHE_NAME);
            errorCacheCfg.setCacheMode(CacheMode.PARTITIONED);
            errorCacheCfg.setIndexedTypes(Integer.class, Error.class);
        }
    }

    private int findId(){
        int max = 0;
        getCacheCfg();
        IgniteCache<Integer, Error> errorCache = ignite.getOrCreateCache(errorCacheCfg);
        QueryCursor<Cache.Entry<Integer, Error>> errors = errorCache.query(new SqlQuery(
                Error.class,
                "from \""  + ErrorDao.ERROR_CACHE_NAME + "\".Error "));
        for(Cache.Entry<Integer, Error> error : errors){
            if(max < error.getValue().getErrorId())
                max = error.getValue().getErrorId();
        }
        return ++max;
    }
}