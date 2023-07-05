package com.cmpt276.groupproject.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.cmpt276.groupproject.models.UserRepository;
import com.cmpt276.groupproject.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
    

    @Autowired
    private UserRepository userRepo;

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
            return "users/homepage";
        }
    }

    @PostMapping("/users/signup")
    public String addUser(@RequestParam Map<String,String> newuser, HttpServletResponse response){
        String newName = newuser.get("name");
        String newPassword = newuser.get("password");
        double balance = 0;
        userRepo.save(new User(newName,newPassword,balance));
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
            request.getSession().setAttribute("session_user", user);
            model.addAttribute("user", user);
            return "users/homepage";
        }
    }
    @PostMapping("/Income")
    public String Income(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        double Income = Double.parseDouble(formData.get("incomeAmount"));
        User user = (User) session.getAttribute("session_user");

        if (user == null){
            return "users/login";
        }
        else {
            //success
            model.addAttribute("user", user);
            double updatedBalance = user.getBalance() + Income;
            user.setBalance(updatedBalance);
         
            return "users/balanceUpdate";
        }
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "users/login";
    }
}