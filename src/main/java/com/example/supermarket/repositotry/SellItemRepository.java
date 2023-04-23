package com.example.supermarket.repositotry;

import com.example.supermarket.model.SellEntity;
import com.example.supermarket.model.SellItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellItemRepository extends JpaRepository<SellItemEntity, Long> {

}
