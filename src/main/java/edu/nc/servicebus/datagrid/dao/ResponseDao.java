package edu.nc.servicebus.datagrid.dao;

import edu.nc.servicebus.datagrid.model.Response;
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
public class ResponseDao {
    @Autowired
    private Ignite ignite;

    private CacheConfiguration<Integer, Response> responseCacheCfg;

    public static final String RESPONSE_CACHE_NAME = ResponseDao.class.getSimpleName() + "Response";

    public void add(String  reason) throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);
        Response response = new Response(findId(),reason,new Date());
        responseCache.put(response.getResponseId(), response);
    }


    public String getAll() throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);

        String result = "[";

        QueryCursor<Cache.Entry<Integer, Response>> responses = responseCache.query(new SqlQuery(
                Response.class,
                "from \""  + ResponseDao.RESPONSE_CACHE_NAME + "\".Response "));
        for(Cache.Entry<Integer, Response> response : responses){

            result +=  response.toString();
        }
        result += "]";

        return result;
    }

    public void delete(int id) {
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);
        responseCache.getAndRemove(id);
    }

    private  void  getCacheCfg(){
        if(responseCacheCfg == null) {
            responseCacheCfg = new CacheConfiguration<>(ResponseDao.RESPONSE_CACHE_NAME);
            responseCacheCfg.setCacheMode(CacheMode.PARTITIONED);
            responseCacheCfg.setIndexedTypes(Integer.class, Response.class);
        }
    }

    private int findId(){
        int max = 0;
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);

        QueryCursor<Cache.Entry<Integer, Response>> responses = responseCache.query(new SqlQuery(
                Response.class,
                "from \""  + ResponseDao.RESPONSE_CACHE_NAME + "\".Response "));
        for(Cache.Entry<Integer, Response> response : responses){
            if(max < response.getValue().getResponseId())
                max = response.getValue().getResponseId();
        }
        return ++max;
    }
}
