package com.example.user_management.repository;

import com.example.user_management.dto.UserDTO;
import com.example.user_management.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM tbl_user WHERE " +
            "(:name IS NULL OR LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:surname IS NULL OR LOWER(surname) LIKE LOWER(CONCAT('%', :surname, '%'))) AND " +
            "(:identityNumber IS NULL OR LOWER(identity_number) LIKE LOWER(CONCAT('%', :identityNumber, '%'))) AND " +
            "(:birthDate IS NULL OR CAST(birth_date AS text) LIKE CONCAT('%', :birthDate, '%')) AND " +
            "(:salary IS NULL OR CAST(salary AS text) LIKE CONCAT('%', CAST(:salary AS text), '%')) AND " +
            "(:email IS NULL OR LOWER(email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:username IS NULL OR LOWER(username) LIKE LOWER(CONCAT('%', :username, '%')))",
            countQuery = "SELECT COUNT(*) FROM tbl_user WHERE " +
                    "(:name IS NULL OR LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
                    "(:surname IS NULL OR LOWER(surname) LIKE LOWER(CONCAT('%', :surname, '%'))) AND " +
                    "(:identityNumber IS NULL OR LOWER(identity_number) LIKE LOWER(CONCAT('%', :identityNumber, '%'))) AND " +
                    "(:birthDate IS NULL OR CAST(birth_date AS text) LIKE CONCAT('%', :birthDate, '%')) AND " +
                    "(:salary IS NULL OR CAST(salary AS text) LIKE CONCAT('%', CAST(:salary AS text), '%')) AND " +
                    "(:email IS NULL OR LOWER(email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
                    "(:username IS NULL OR LOWER(username) LIKE LOWER(CONCAT('%', :username, '%')))",
            nativeQuery = true)
    Page<User> findByCriteria(@Param("name") String name,
                              @Param("surname") String surname,
                              @Param("identityNumber") String identityNumber,
                              @Param("birthDate") String birthDate,
                              @Param("salary") Float salary,
                              @Param("email") String email,
                              @Param("username") String username,
                              Pageable pageable);
}
