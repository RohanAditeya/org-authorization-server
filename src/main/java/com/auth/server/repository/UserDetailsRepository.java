package com.auth.server.repository;

import com.auth.server.model.UserDetailsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetailsModel, String> {
}
