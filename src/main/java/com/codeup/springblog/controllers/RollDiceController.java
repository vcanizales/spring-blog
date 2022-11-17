package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.relation.RelationNotFoundException;
import java.util.Random;

@Controller
@RequestMapping("/roll-dice")
public class RollDiceController {

    @GetMapping
    public String rollDiceGuess(){
        return "RollGuess";
    }

    @GetMapping("/{n}")
    public String rollDiceReturn(@PathVariable Integer n, Model model){
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1;
        model.addAttribute("roll", roll);
        model.addAttribute("n", n);
        return "RollGuess";
    }
}
