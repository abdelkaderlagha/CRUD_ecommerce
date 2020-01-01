package com.formalab.ecommerce.dao;

import java.util.Optional;

import com.formalab.ecommerce.model.Role;
import com.formalab.ecommerce.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}