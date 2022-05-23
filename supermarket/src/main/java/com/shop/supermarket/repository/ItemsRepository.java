package com.shop.supermarket.repository;

import com.shop.supermarket.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ItemsRepository extends JpaRepository<Items,Integer> {

}
