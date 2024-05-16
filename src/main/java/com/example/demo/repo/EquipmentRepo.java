package com.example.demo.repo;

import com.example.demo.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByAccountId(String accountId);
}
