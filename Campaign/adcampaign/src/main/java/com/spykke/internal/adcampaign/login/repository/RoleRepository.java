package com.spykke.internal.adcampaign.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spykke.internal.adcampaign.login.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
