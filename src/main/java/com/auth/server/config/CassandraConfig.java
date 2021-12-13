package com.auth.server.config;

import com.auth.server.repository.ClientDetailsCassandraDao;
import com.auth.server.repository.UserDetailsCassandraDao;
import com.auth.server.repository.UserDetailsCassandraMapper;
import com.auth.server.repository.UserDetailsCassandraMapperBuilder;
import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig {

    @Autowired
    CqlSession cqlSession;

    @Bean
    UserDetailsCassandraMapper userDetailsCassandraMapper() {
        return new UserDetailsCassandraMapperBuilder(cqlSession).build();
    }

    @Bean
    UserDetailsCassandraDao getUserDetailsRepositoryInstance(UserDetailsCassandraMapper userDetailsCassandraMapper) {
        return userDetailsCassandraMapper.userDetailsDao();
    }

    @Bean
    ClientDetailsCassandraDao getClientDetailsRepositoryInstance(UserDetailsCassandraMapper userDetailsCassandraMapper) {
        return userDetailsCassandraMapper.clientDetailsDao();
    }
}
