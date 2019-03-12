package com.keablog.demo.Controllers;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Objects.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute (name="loginForm") User user, Model model, Database database) {

        if (user.getUsername().equals("h") && user.getPassword().equals("h")) {
            return "admin";
        } else {
            model.addAttribute("invalid", true);
            return "login";
        }


    }


}