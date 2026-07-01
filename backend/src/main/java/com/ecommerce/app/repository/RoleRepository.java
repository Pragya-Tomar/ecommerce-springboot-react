package com.ecommerce.app.repository;

import com.ecommerce.app.entity.ERole;
import com.ecommerce.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
