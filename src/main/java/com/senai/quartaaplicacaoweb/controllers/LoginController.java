package com.senai.quartaaplicacaoweb.controllers;

import com.senai.quartaaplicacaoweb.models.UserModel;
import com.senai.quartaaplicacaoweb.services.CookieService;
import com.senai.quartaaplicacaoweb.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String indexLogin(){
        return "login/login";
    }

    @GetMapping(value="/logout")
    public String logout(HttpServletResponse response){
        CookieService.setCookie(response,"usuarioId","", 0); //Remove o cookie de autenticação
        return "redirect:/login";
    }

    @PostMapping  (value = "/logar")
    public String logOn(UserModel userModel, HttpServletResponse response){
        UserModel user = loginService.logar(userModel);

        if (user != null){
            //Cria um cookie pra manter o usuário logado
            CookieService.setCookie(response, "usuarioId", String.valueOf(user.getId()), 3600);
            return "redirect:/";
        } else {
            return "login/login";
        }
    }
}
