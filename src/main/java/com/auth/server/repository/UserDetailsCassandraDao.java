package com.auth.server.repository;

import com.auth.server.model.UserDetailsModel;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface UserDetailsCassandraDao {

    @Select
    UserDetailsModel findByUser(String username);

    @Insert(ifNotExists = true)
    ResultSet save(UserDetailsModel record);

}
