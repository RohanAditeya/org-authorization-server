package com.auth.server.repository;

import com.auth.server.model.ClientResourceModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientResourceRepository extends CrudRepository<ClientResourceModel, String> {
    Optional<List<ClientResourceModel>> findByClientId(String clientId);
}
