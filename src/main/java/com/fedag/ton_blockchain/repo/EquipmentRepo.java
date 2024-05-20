package com.fedag.ton_blockchain.repo;

import com.fedag.ton_blockchain.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByAccountId(String accountId);
}
