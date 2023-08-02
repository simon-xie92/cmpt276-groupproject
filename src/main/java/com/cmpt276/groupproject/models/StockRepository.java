package com.cmpt276.groupproject.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cmpt276.groupproject.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByUser(User user);
    void deleteByIdAndSymbol(Integer id, String symbol);

}
