package com.example.supermarket.repositotry;

import com.example.supermarket.model.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    BranchEntity findByName(String name);
}
