package com.burakkocak.eventdrivenms.repository;

import com.burakkocak.eventdrivenms.entity.UserFollower;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableCassandraRepositories
public interface UserFollowerRepository extends CassandraRepository<UserFollower, UUID> {
    @AllowFiltering
    Optional<UserFollower>findById(UUID id);
}
