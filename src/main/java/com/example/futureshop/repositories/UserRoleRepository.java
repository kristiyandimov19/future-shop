package com.example.futureshop.repositories;

import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.entities.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(UserRoleEnum role);

    @Query(
            value = "SELECT * FROM roles WHERE id NOT IN (select roles_id FROM users_roles where user_entity_id = ?1)",
            nativeQuery = true
    )
    List<UserRoleEntity> getUserNotRoles(Long userId);

}
