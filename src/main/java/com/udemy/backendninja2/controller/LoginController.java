package com.udemy.backendninja2.controller;

import com.udemy.backendninja2.constant.ViewConstant;
import com.udemy.backendninja2.model.UserCredential;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    private static final Log LOGGER = LogFactory.getLog(LoginController.class);

    @GetMapping("/")
    public String redirectToLoging(){
        LOGGER.info("METHOD: redirectToLogin()");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name="error", required = false)String error,
                                             @RequestParam(name="logout", required = false)String logout){

        LOGGER.info("METHOD: showLoginForm() -- Params: error=" + error + "logout" + logout);
        model.addAttribute("logout", logout);
        model.addAttribute("error", error);
        model.addAttribute("userCredentials", new UserCredential());
        LOGGER.info("Returning to login view");
        return ViewConstant.LOGIN;
    }

    @PostMapping("/logincheck")
    public String loginCheck(@ModelAttribute(name="userCredentials")UserCredential userCredential){
        LOGGER.info("METHOD: loginCheck() -- Params: " + userCredential.toString() );
        if(userCredential.getUserName().equals("user") && userCredential.getPassword().equals("user")){
            LOGGER.info("Returning to contacts view");
            return "redirect:/contacts/showcontacts";
        }
        LOGGER.info("Redirect to login?error");
        return "redirect:/login?error";
    }
}
