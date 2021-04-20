package com.spykke.internal.userpackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.UserPackage;

@Repository
public interface UserPackageJpaRepository extends JpaRepository<UserPackage, Long> {

}
