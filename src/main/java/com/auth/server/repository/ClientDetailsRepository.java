package com.auth.server.repository;

import com.auth.server.model.ClientDetailsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends CrudRepository<ClientDetailsModel, String> {
}
