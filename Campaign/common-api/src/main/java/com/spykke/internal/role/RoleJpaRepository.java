package com.spykke.internal.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {

}
