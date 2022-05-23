package com.shop.supermarket.repository;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,String> {
    @Query("select user.items from Users user where user.username=:username")
    public List<Items> getOrdersList(@Param("username") String username);

    @Modifying(clearAutomatically = true)
    @Query("update Users user set user.password =:password,user.email=:email,user.phoneNumber=:phoneNumber,user.address=:address where user.username =:username")
    public void updateData(@Param("username")String username,@Param("password") String password,@Param("email") String email,@Param("phoneNumber") String phoneNumber,@Param("address") String address);

    public Users findByUsername(String username);
}
