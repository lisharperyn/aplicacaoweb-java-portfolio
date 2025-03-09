package com.senai.quartaaplicacaoweb.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class CookieService {

    public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) {
        Cookie cookie = new Cookie(key, URLEncoder.encode(valor, StandardCharsets.UTF_8));
        cookie.setMaxAge(segundos); // Tempo de vida do cookie em segundos
        cookie.setHttpOnly(true); // Protege o cookie contra acesso via JavaScript
        cookie.setPath("/"); // Define o caminho do cookie como raiz
        response.addCookie(cookie); // Adiciona o cookie à resposta
    }

    public static String getCookie(HttpServletRequest request, String key) {
        try {
            // Verifica se há cookies na requisição
            if (request.getCookies() == null) {
                return null; // Retorna null se não houver cookies
            }

            // Procura o cookie com o nome especificado
            Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies())
                    .filter(cookie -> key.equals(cookie.getName()))
                    .findAny();

            // Se o cookie for encontrado, decodifica e retorna o valor
            if (cookieOptional.isPresent()) {
                String valor = cookieOptional.get().getValue();
                System.out.println("Cookie encontrado: " + key + " = " + valor); // Log
                return URLDecoder.decode(valor, StandardCharsets.UTF_8);
            } else {
                System.out.println("Cookie não encontrado: " + key); // Log
                return null; // Retorna null se o cookie não for encontrado
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro (opcional)
            return null; // Retorna null em caso de exceção
        }
    }
}
