package com.cmpt276.groupproject.controllers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.dialect.function.LocatePositionEmulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PathVariable;

import com.cmpt276.groupproject.models.UserRepository;
import com.cmpt276.groupproject.models.quote;
import com.cmpt276.groupproject.service.FinnhubService;
import com.cmpt276.groupproject.service.NewsService;
import com.cmpt276.groupproject.service.StockService;
import com.cmpt276.groupproject.service.UserService;
import com.cmpt276.groupproject.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.cmpt276.groupproject.controllers.NewsController;

import com.cmpt276.groupproject.models.TransactionRepository;
import com.cmpt276.groupproject.models.Article;
import com.cmpt276.groupproject.models.Expense;
import com.cmpt276.groupproject.models.ExpenseRepository;
import com.cmpt276.groupproject.models.Goal;
import com.cmpt276.groupproject.models.GoalRepository;
import com.cmpt276.groupproject.models.News;
import com.cmpt276.groupproject.models.NewsApiResponse;
import com.cmpt276.groupproject.models.PortfolioItem;
import com.cmpt276.groupproject.models.RecommendationTrend;
import com.cmpt276.groupproject.models.Stock;
import com.cmpt276.groupproject.models.Transaction;
import com.cmpt276.groupproject.models.StockRepository;



import java.time.LocalDate;

@Controller
public class UsersController {
    

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
private UserService userService;

@Autowired
private StockService stockService;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private GoalRepository goalRepo;

    @Autowired
    private NewsService newsService;

    @Autowired
    private FinnhubService finnhubService;

    @GetMapping("/")
    public RedirectView process(){
        return new RedirectView("login");
    }
    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        if (user == null){
            return "users/login";
        } else {
            model.addAttribute("user", user);
            return home(model, request, session);
        }
    }

    @PostMapping("/users/signup")
    public String addUser(@RequestParam Map<String,String> newuser, HttpServletResponse response){
        String newName = newuser.get("name");
        String newPassword = newuser.get("password");
        double balance = 0;
        double monthlyIncome = 0;
        double monthlyExpenses = 0;
        double monthlySavings = monthlyIncome - monthlyExpenses;
        userRepo.save(new User(newName,newPassword,balance,monthlyIncome, monthlyExpenses, monthlySavings));
        response.setStatus(201);
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){

        String name = formData.get("uname");
        String password = formData.get("upassword");
        List<User> userlist = userRepo.findByNameAndPassword(name, password);
        if (userlist.isEmpty()){
            return "users/login";
        } else {
            //success
            User user = userlist.get(0);
            request.getSession(false).setAttribute("session_user", user);
            model.addAttribute("user", user);
            return home(model, request, session);
        }
    }
    @GetMapping("/checkBalance")
    public String getUserBalance(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        int userId = user.getUid();
        List<Transaction> transaction = transactionRepo.findByUid(userId);
        List<Expense> expense = expenseRepo.findByUid(userId);
        List<Goal> goals = goalRepo.findByUid(userId);
        model.addAttribute("es", expense);
        model.addAttribute("us", user);
        model.addAttribute("ts", transaction);
        model.addAttribute("gs", goals);
        return "users/account";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        int userId = user.getUid();
        if (user == null){
            return "users/login";

        }
        else{
            List<Transaction> transaction = transactionRepo.findByUid(userId);
            List<Expense> expense = expenseRepo.findByUid(userId);
            List<Goal> goals = goalRepo.findByUid(userId);
            List<Article> headlines = newsService.getNews("Stocks").getArticles();

            
    List<Stock> stocks = stockService.getStocksForUser(user);
    List<PortfolioItem> portfolioItems = new ArrayList<>();
    for (Stock stock : stocks) {
        quote quote = finnhubService.getQuote(stock.getSymbol());
        RecommendationTrend[] recommendations = finnhubService.getRecommendationTrends(stock.getSymbol());
        News[] news = finnhubService.getNews(stock.getSymbol(), "2023-01-01", "2023-12-31");
        PortfolioItem item = new PortfolioItem();
        item.setStock(stock);
        item.setQuote(quote);
        item.setRecommendations(recommendations);
        item.setNews(news);
        portfolioItems.add(item);
    }
            model.addAttribute("es", expense);
            model.addAttribute("ts", transaction);
            model.addAttribute("gs", goals);
            model.addAttribute("headlines", headlines);
            model.addAttribute("portfolio", portfolioItems);


            return "users/homepage";
        }
    }

    @PostMapping("/monthlyIncome")
    public String monthlyIncome(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        double monthlyIncome = Double.parseDouble(formData.get("monthlyIncomeAmount"));
        User user = (User) session.getAttribute("session_user");

        if (user == null){
            return "users/login";
        }
        else {
            //success
          
            double monthlyExpenses = user.getMonthlyexpenses();
            double monthlySavings = monthlyIncome - monthlyExpenses;
            user.setMonthlyincome(monthlyIncome);
            user.setMonthlysavings(monthlySavings);
            userRepo.save(user);
            model.addAttribute("user", user);
            return getUserBalance(model, request, session);
        }
    }
    @PostMapping("/monthlyExpenses")
    public String monthlyExpenses(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        double monthlyExpense = Double.parseDouble(formData.get("monthlyExpenseAmount"));
        String testMonthlyExpense = formData.get("monthlyExpenseAmount");
        if(testMonthlyExpense == null){
            return getUserBalance(model, request, session);
        }
        String reason = formData.get("monthlyExpense");
        User user = (User) session.getAttribute("session_user");

        if (user == null){
            return "users/login";
        }
        else {
            //success
            double monthlyExpenses = user.getMonthlyexpenses() + monthlyExpense;
            double monthlySavings = user.getMonthlyincome() - monthlyExpenses;
            int userId = user.getUid();
            user.setMonthlyexpenses(monthlyExpenses);
            user.setMonthlysavings(monthlySavings);
            userRepo.save(user);
            expenseRepo.save(new Expense(userId, reason, monthlyExpense));
            model.addAttribute("user", user);
            return getUserBalance(model, request, session);
        }
    }

    @GetMapping("/deleteExpense/{eid}")
    public String deleteExpense (Model model, HttpServletRequest request, HttpSession session, @PathVariable String eid){
        User user = (User) session.getAttribute("session_user");
        int id=Integer.parseInt(eid);
        Expense exp=expenseRepo.findByEid(id).get(0);
        double expenseVal = exp.getAmount();
        double monthlyExpenses = user.getMonthlyexpenses() - expenseVal;
        double monthlySavings = user.getMonthlyincome() - monthlyExpenses;
        user.setMonthlyexpenses(monthlyExpenses);
        user.setMonthlysavings(monthlySavings);
        userRepo.save(user);
        expenseRepo.delete(exp);
        List<Expense> expense = expenseRepo.findAll();
        model.addAttribute("ex", expense);
        return getUserBalance(model, request, session);
    }

    @GetMapping("/deleteGoal/{gid}")
    public String deleteGoal (Model model, HttpServletRequest request, HttpSession session, @PathVariable String gid){
        User user = (User) session.getAttribute("session_user");
        if(user == null){
            return login(null, model, request, session);
        }
        int id=Integer.parseInt(gid);
        Goal goal = goalRepo.findByGid(id).get(0);
        goalRepo.delete(goal);
        List<Goal> findgoal = goalRepo.findAll();
        model.addAttribute("go", findgoal);
        return getLogin(model, request, session);
    }

    @PostMapping("/Income")
    public String Income(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        double Income = Double.parseDouble(formData.get("incomeAmount"));
        String reason = formData.get("income");
        User user = (User) session.getAttribute("session_user");
        

        if (user == null){
            return "users/login";
        }
        else {
            //success
            
            double updatedBalance = user.getBalance() + Income;
            System.out.println(updatedBalance);
            LocalDate date = LocalDate.now();
            user.setBalance(updatedBalance);
            userRepo.save(user);
            int userId = user.getUid();
            transactionRepo.save(new Transaction(userId, date, reason, Income, "Deposit"));
            model.addAttribute("user", user);
            return getUserBalance(model, request, session);
        }
    }

    @PostMapping("/expenses")
    public String Expenses(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        double Expense = Double.parseDouble(formData.get("expenseAmount"));
        String reason = formData.get("expense");
        User user = (User) session.getAttribute("session_user");

        if (user == null){
            return "users/login";
        }
        else {
            //success
        
            double updatedBalance = user.getBalance() - Expense;
            user.setBalance(updatedBalance);
            userRepo.save(user);
            
            LocalDate date = LocalDate.now();
            int userId = user.getUid();
            
            transactionRepo.save(new Transaction(userId, date, reason , Expense, "Withdrawl"));

            model.addAttribute("user", user);
            return getUserBalance(model, request, session);
        }
    }

    @PostMapping("/goals")
    public String Goals(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        double cost = Double.parseDouble(formData.get("cost"));
        String goal  = formData.get("goal");
        User user = (User) session.getAttribute("session_user");

        if (user == null){
            return "users/login";
        }
        else {
            //success

            double monthlySavings = user.getMonthlysavings();
            double balance = user.getBalance();
            double time = (cost-balance)/monthlySavings;
            int newTime = (int)time;
            int userId = user.getUid();
            goalRepo.save(new Goal(userId, goal, cost, newTime));
            model.addAttribute("user", user);
            return home(model, request, session);
        }
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession(false).invalidate();
        return "users/login";
    }

    @PostMapping("/AddStock")
public String addStock(@RequestParam("symbol") String symbol, @RequestParam("quantity") int quantity, @RequestParam("purchasePrice") double purchasePrice, HttpSession session) {
    User user = (User) session.getAttribute("session_user");
    if (user == null) {
        return "redirect:/login";
    }
    LocalDateTime time = LocalDateTime.now();
    stockService.addStock(user.getUid(), symbol, quantity, purchasePrice, time);
    return "redirect:/portfolio";
}


@GetMapping("/portfolio")
public ModelAndView portfolio(HttpSession session) {
    User user = userService.getCurrentUser(session);
    List<Stock> stocks = stockService.getStocksForUser(user);
    List<PortfolioItem> portfolioItems = new ArrayList<>();
    for (Stock stock : stocks) {
        quote quote = finnhubService.getQuote(stock.getSymbol());
        RecommendationTrend[] recommendations = finnhubService.getRecommendationTrends(stock.getSymbol());
        News[] news = finnhubService.getNews(stock.getSymbol(), "2023-01-01", "2023-12-31");
        PortfolioItem item = new PortfolioItem();
        item.setStock(stock);
        item.setQuote(quote);
        item.setRecommendations(recommendations);
        item.setNews(news);
        portfolioItems.add(item);
    }
    ModelAndView modelAndView = new ModelAndView("portfolio");
    modelAndView.addObject("portfolioItems", portfolioItems);
    return modelAndView;
}


@PostMapping("/{id}/{symbol}")
public String deleteStock(@PathVariable Integer id, @PathVariable String symbol) {
    stockService.removeStock(id, symbol);
    return "redirect:/portfolio";
}



}