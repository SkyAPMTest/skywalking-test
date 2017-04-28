package com.a.eye.skywalking.test.persistence;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by xin on 2016/12/13.
 */
@Path("/persistence")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public interface PersistenceService {

    @Path("/query")
    @POST
    CacheItem query(String key);
}
