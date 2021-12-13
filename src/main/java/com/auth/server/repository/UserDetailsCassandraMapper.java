package com.auth.server.repository;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface UserDetailsCassandraMapper {

    @DaoFactory
    UserDetailsCassandraDao userDetailsDao();

    @DaoFactory
    ClientDetailsCassandraDao clientDetailsDao();

}
