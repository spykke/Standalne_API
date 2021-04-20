package com.spykke.internal.sysuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.SysUser;

@Repository
public interface SysUserJpaRepository extends JpaRepository<SysUser, Long> {

}
