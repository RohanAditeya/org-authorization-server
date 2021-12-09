package com.auth.server.repository;

import com.auth.server.model.ClientGrantType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientGrantTypeRepository extends CrudRepository<ClientGrantType, String> {
    Optional<List<ClientGrantType>> findByClientId(String clientId);
}
