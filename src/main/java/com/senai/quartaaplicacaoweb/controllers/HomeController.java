package com.senai.quartaaplicacaoweb.controllers;

import com.senai.quartaaplicacaoweb.services.CookieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        String usuarioId = CookieService.getCookie(request, "usuarioId");

        if (usuarioId != null && !usuarioId.isEmpty()) {
            System.out.println("Usuário autenticado. Exibindo página inicial."); // Log
            model.addAttribute("nome", CookieService.getCookie(request, "nome"));
            return "home/index"; // Página inicial
        } else {
            System.out.println("Usuário não autenticado. Redirecionando para o login."); // Log
            return "redirect:/login"; // Redireciona para a página de login
        }
    }
}
