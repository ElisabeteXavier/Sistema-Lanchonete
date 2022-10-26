package br.com.elisabete.lanchonete.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    @GetMapping("/oi") // caminho para chegar no método
    @ResponseBody // indica o que vai ser devolvido
    public String hello() {
        return "Oi, tudo bem?";
    }
}