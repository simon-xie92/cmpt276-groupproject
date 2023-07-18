package com.cmpt276.groupproject.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByUid(int uid);
}
