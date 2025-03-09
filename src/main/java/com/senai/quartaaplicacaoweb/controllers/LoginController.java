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


    @PostMapping(value = "/logar")
    public String logOn(UserModel userModel, HttpServletResponse response, Model model){
        System.out.println("Tentativa de login com e-mail: " + userModel.getEmail()); // Log
        UserModel user = loginService.logar(userModel);

        if (user != null){
            System.out.println("Usuário encontrado: ID = " + user.getId()); // Log
            // Cria um cookie para manter o usuário logado
            CookieService.setCookie(response, "usuarioId", String.valueOf(user.getId()), 3600);
            return "redirect:/"; // Redireciona para a página inicial
        } else {
            System.out.println("Usuário não encontrado ou credenciais inválidas"); // Log
            model.addAttribute("erro", "E-mail ou senha inválidos"); // Mensagem de erro
            return "login/login"; // Credenciais inválidas: recarrega a página de login
        }
    }
}
