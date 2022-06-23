package com.example.openpayd_client.repository;

import com.example.openpayd_client.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserModel, UUID> {
    Optional<UserModel> findFirstByEmail(String email);
}
