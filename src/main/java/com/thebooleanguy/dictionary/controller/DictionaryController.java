package com.thebooleanguy.dictionary.controller;

import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String prefix, Model model) {
        List<Word> results = dictionaryService.searchWords(prefix);
        model.addAttribute("results", results);
        model.addAttribute("prefix", prefix);
        return "search";
    }
}
