package com.burakkocak.eventdrivenms.repository;

import com.burakkocak.eventdrivenms.entity.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableCassandraRepositories
public interface UserRepository extends CassandraRepository<User, UUID> {
    @AllowFiltering
    Optional<User> findById(UUID id);
}
