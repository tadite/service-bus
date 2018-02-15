package edu.nc.servicebus.datagrid.dao;

import edu.nc.servicebus.datagrid.model.Log;
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

@Repository
@Transactional
public class LogDao {

    @Autowired
    private Ignite ignite;

    private CacheConfiguration<Integer, Log> logCacheCfg;

    public static final String LOG_CACHE_NAME = LogDao.class.getSimpleName() + "Log";

    public void add(int requestId, int responseId, int errorId) throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Log> logCache = ignite.getOrCreateCache(logCacheCfg);
        Log log = new Log(findId(),requestId, responseId, errorId);
        logCache.put(log.getLogId(),log);

    }
    public String getAll() throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Log> logCache = ignite.getOrCreateCache(logCacheCfg);

        String result = "[";

        QueryCursor<Cache.Entry<Integer, Log>> logs = logCache.query(new SqlQuery(
                Log.class,
                "from \""  + LogDao.LOG_CACHE_NAME + "\".Log "));
        for(Cache.Entry<Integer, Log> log : logs){

            result +=  log.toString();
        }
        result += "]";

        return result;
    }

    public void delete(int id) {
        getCacheCfg();
        IgniteCache<Integer, Log> logCache = ignite.getOrCreateCache(logCacheCfg);
        logCache.getAndRemove(id);
    }

    private void  getCacheCfg(){
        if(logCacheCfg == null) {
            logCacheCfg = new CacheConfiguration<>(LogDao.LOG_CACHE_NAME);
            logCacheCfg.setCacheMode(CacheMode.PARTITIONED);
            logCacheCfg.setIndexedTypes(Integer.class, Log.class);
        }
    }

    private int findId(){
        int max = 0;
        getCacheCfg();
        IgniteCache<Integer, Log> logCache = ignite.getOrCreateCache(logCacheCfg);

        QueryCursor<Cache.Entry<Integer, Log>> logs = logCache.query(new SqlQuery(
                Log.class,
                "from \""  + LogDao.LOG_CACHE_NAME + "\".Log "));
        for(Cache.Entry<Integer, Log> log : logs){

            if(max < log.getValue().getLogId())
                max = log.getValue().getLogId();
        }
        return ++max;
    }
}
