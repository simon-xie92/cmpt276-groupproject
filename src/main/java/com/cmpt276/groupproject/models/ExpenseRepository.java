package com.cmpt276.groupproject.models;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findByUid(int uid);
}
