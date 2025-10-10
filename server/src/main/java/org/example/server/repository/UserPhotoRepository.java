package org.example.server.repository;

import org.example.server.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserPhotoRepository extends JpaRepository<UserPhoto, UUID> {

    Optional<UserPhoto> findByUserId(UUID userId);
}
