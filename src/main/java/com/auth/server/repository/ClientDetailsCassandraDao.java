package com.auth.server.repository;

import com.auth.server.model.ClientDetailsModel;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface ClientDetailsCassandraDao {

    @Select
    ClientDetailsModel findByClientId(String clientId);

    @Insert(ifNotExists = true)
    ResultSet save(ClientDetailsModel record);

}
