package com.spykke.internal.adcampaign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaign.dao.CouponUser;
import com.spykke.internal.commondb.entity.AppUser;
import com.spykke.internal.commondb.entity.CodeUse;
import com.spykke.internal.commondb.entity.CouponCode;
import com.spykke.internal.commondb.entity.CouponCodeChild;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
	@Query("select new com.spykke.internal.adcampaign.dao.CouponUser( cc.title, ccc.code, au.id, au.nickname, au.leaseFrequency )"
			+ " from tb_coupon_code cc, tb_coupon_code_child ccc, tb_code_use cu, tb_app_user au  "
			+ " where "
			+ " cc.id = ccc.couponCode and "
			+ " ccc.id = cu.couponCodeChild and "
			+ " cu.appUser = au.id and "
			+ " cc.title like 'Elite%' group by  au.id")
	public List<CouponUser> getCouponUsers();

	
}
