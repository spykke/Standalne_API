package com.spykke.internal.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

}
