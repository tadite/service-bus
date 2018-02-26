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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ResponseDao {
    @Autowired
    private Ignite ignite;

    private CacheConfiguration<Integer, Response> responseCacheCfg;

    public static final String RESPONSE_CACHE_NAME = ResponseDao.class.getSimpleName() + "Response";

    public void add(int id, String reason, Date time, Date endTime) throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);
        Response response = new Response(id, reason, time, endTime);
        responseCache.put(response.getResponseId(), response);
    }


    public String getAll() throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);

        String result = "[";

        QueryCursor<Cache.Entry<Integer, Response>> responses = responseCache.query(new SqlQuery(
                Response.class,
                "from \""  + ResponseDao.RESPONSE_CACHE_NAME + "\".Response "));

        List<Response> responseList = new ArrayList<>();
        for(Cache.Entry<Integer, Response> response : responses){
            responseList.add(response.getValue());
        }
        Collections.sort(responseList);

        for (Response response : responseList){
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

    public void clear(){
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);
        responseCache.clear();
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

    public Response findById(int id){
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);

        QueryCursor<Cache.Entry<Integer, Response>> responses = responseCache.query(new SqlQuery(
                Response.class,
                "from \""  + ResponseDao.RESPONSE_CACHE_NAME + "\".Response "));

        Response result = null;
        for (Cache.Entry<Integer, Response> response : responses){
            if (response.getValue().getResponseId() == id){
                result = response.getValue();
            }
        }

        return result;
    }

    public List<Response> getResponseList(){
        getCacheCfg();
        IgniteCache<Integer, Response> responseCache = ignite.getOrCreateCache(responseCacheCfg);

        QueryCursor<Cache.Entry<Integer, Response>> responses = responseCache.query(new SqlQuery(
                Response.class,
                "from \""  + ResponseDao.RESPONSE_CACHE_NAME + "\".Response "));

        List<Response> responseList = new ArrayList<>();
        for (Cache.Entry<Integer, Response> response : responses){
            responseList.add(response.getValue());
        }

        Collections.sort(responseList);

        return responseList;
    }
}
