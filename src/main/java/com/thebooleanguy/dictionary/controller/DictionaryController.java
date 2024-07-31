package com.thebooleanguy.dictionary.controller;

import com.thebooleanguy.dictionary.service.DictionaryService;
import com.thebooleanguy.dictionary.model.Word;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("prefix") String prefix, Model model) {
        try {
            List<Word> results = dictionaryService.searchWords(prefix);
            model.addAttribute("prefix", prefix);
            model.addAttribute("results", results);
        } catch (Exception e) {
            // Log the error and add a user-friendly message
            System.err.println("Error occurred: " + e.getMessage());
            model.addAttribute("error", "An unexpected error occurred. Please try again.");
        }
        return "index"; // Template setup
    }
}

