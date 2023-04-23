package com.example.supermarket.repositotry;

import com.example.supermarket.model.PurchaseEntity;
import com.example.supermarket.model.SellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository  extends JpaRepository<SellEntity,Long> {
}
