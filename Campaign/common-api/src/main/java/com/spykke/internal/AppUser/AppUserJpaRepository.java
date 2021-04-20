package com.spykke.internal.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.AppUser;

@Repository
public interface AppUserJpaRepository extends JpaRepository<AppUser, Long> {

}
