package com.example.lms.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Controller
public class HomeController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/home")
    public String homePage(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");

        model.addAttribute("fname", name);
        model.addAttribute("fage", age);
        model.addAttribute("fgender", gender);
        return "home1";
    }

    @GetMapping("/showBooks")
    public String showBooks(Model model){
        try{
            Connection con = dataSource.getConnection();
            Statement stmt = con.createStatement();

            String sql = "Select id, title, description, edition from book limit 1";

            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                model.addAttribute("id",resultSet.getInt("id"));
                model.addAttribute("title",resultSet.getString("title"));
                model.addAttribute("desc",resultSet.getString("description"));
                model.addAttribute("edition",resultSet.getString("edition"));

            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "books";
    }
}
