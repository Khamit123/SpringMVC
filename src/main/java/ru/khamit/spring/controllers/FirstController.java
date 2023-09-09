package ru.khamit.spring.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        model.addAttribute("mes","Hello "+name+" "+surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surame", required = false) String surname) {
        System.out.println(name + " " + surname);
        return "first/goodbye";
    }
    @GetMapping("/Calculator")
    public String calcPage(@RequestParam(value = "a", required = false) int a,
                           @RequestParam(value = "b", required = false) int b,
                           @RequestParam(value = "action", required = false) String action,
                           Model model) {
        double answer=0;

        if(b!=0) {
            switch (action) {
                case "multiplication":
                    answer = a * b;
                    break;
                case "addition":
                    answer = a + b;
                    break;
                case "division":
                    answer = (double) a/ b;
                    break;
                case "substraction":
                    answer = a - b;
            }
            model.addAttribute("answer",answer);
        }
        else {
            model.addAttribute("answer","Division on 0");
        }


        return "first/Calculator";
    }

}
