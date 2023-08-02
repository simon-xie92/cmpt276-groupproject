package com.cmpt276.groupproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import com.cmpt276.groupproject.models.User;
import com.cmpt276.groupproject.models.Stock;
import com.cmpt276.groupproject.models.UserRepository;
import com.cmpt276.groupproject.models.StockRepository;

@Service
public class StockService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    @Autowired
    public StockService(UserRepository userRepository, StockRepository stockRepository) {
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void addStock(HttpSession session, String symbol, int quantity, double purchasePrice) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        session = attr.getRequest().getSession(false); 
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        Stock stock = new Stock();
        stock.setUser(user);
        stock.setSymbol(symbol);
        stock.setQuantity(quantity);
        stock.setPurchasePrice(purchasePrice);
        user.getStocks().add(stock);
        userRepository.save(user);
    }

    public List<Stock> getStocksForUser(User user) {
        return stockRepository.findByUser(user);
    }


}

    

