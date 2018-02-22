package edu.nc.servicebus.datagrid.dao;

import edu.nc.servicebus.datagrid.model.Request;
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
import java.util.*;

@Repository
@Transactional
public class RequestDao {

    @Autowired
    private Ignite ignite;

    private CacheConfiguration<Integer, Request> requestCacheCfg;

    public static final String REQUEST_CACHE_NAME = RequestDao.class.getSimpleName() + "Request";

    public void add(int id, String reason, Date time, Date endTime) throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Request> requestCache = ignite.getOrCreateCache(requestCacheCfg);
        Request request = new Request(id, reason, time, endTime);
        requestCache.put(request.getRequestId(), request);
    }


    public String getAll() throws IgniteException {
        getCacheCfg();
        IgniteCache<Integer, Request> requestCache = ignite.getOrCreateCache(requestCacheCfg);

        String result = "[";

        QueryCursor<Cache.Entry<Integer, Request>> requests = requestCache.query(new SqlQuery(
                Request.class,
                "from \""  + RequestDao.REQUEST_CACHE_NAME + "\".Request "));
        for(Cache.Entry<Integer, Request> request : requests){

            result +=  request.toString();
        }
        result += "]";

        return result;
    }

    public void delete(int id) {
        getCacheCfg();
        IgniteCache<Integer, Request> requestCache = ignite.getOrCreateCache(requestCacheCfg);
        requestCache.getAndRemove(id);
    }

    public void clear(){
        getCacheCfg();
        IgniteCache<Integer, Request> requestCache = ignite.getOrCreateCache(requestCacheCfg);
        requestCache.clear();
    }

    private void  getCacheCfg(){
        if(requestCacheCfg == null) {
            requestCacheCfg = new CacheConfiguration<>(RequestDao.REQUEST_CACHE_NAME);
            requestCacheCfg.setCacheMode(CacheMode.PARTITIONED);
            requestCacheCfg.setIndexedTypes(Integer.class, Request.class);
        }
    }

    private int findId(){
        int max = 0;
        getCacheCfg();
        IgniteCache<Integer, Request> requestCache = ignite.getOrCreateCache(requestCacheCfg);

        QueryCursor<Cache.Entry<Integer, Request>> requests = requestCache.query(new SqlQuery(
                Request.class,
                "from \""  + RequestDao.REQUEST_CACHE_NAME + "\".Request "));
        for(Cache.Entry<Integer, Request> request : requests){
            if(max < request.getValue().getRequestId())
                max = request.getValue().getRequestId();
        }
        return ++max;
    }

    public Request findById(int id){
        getCacheCfg();
        IgniteCache<Integer, Request> requestCache = ignite.getOrCreateCache(requestCacheCfg);

        QueryCursor<Cache.Entry<Integer, Request>> requests = requestCache.query(new SqlQuery(
                Request.class,
                "from \""  + RequestDao.REQUEST_CACHE_NAME + "\".Request "));

        Request result = null;
        for (Cache.Entry<Integer, Request> request : requests){
            if (request.getValue().getRequestId() == id){
                result = request.getValue();
            }
        }

        return result;
    }

    public List<Request> getRequestList(){
        getCacheCfg();
        IgniteCache<Integer, Request> requestCache = ignite.getOrCreateCache(requestCacheCfg);

        QueryCursor<Cache.Entry<Integer, Request>> requests = requestCache.query(new SqlQuery(
                Request.class,
                "from \""  + RequestDao.REQUEST_CACHE_NAME + "\".Request "));

        List<Request> requestList = new ArrayList<>();
        for (Cache.Entry<Integer, Request> request: requests){
            requestList.add(request.getValue());
        }

        Collections.sort(requestList);

        return requestList;
    }
}
