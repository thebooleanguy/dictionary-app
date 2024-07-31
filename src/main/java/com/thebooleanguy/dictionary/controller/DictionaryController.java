package com.thebooleanguy.dictionary.controller;

import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("prefix") String prefix, Model model) {
        SearchResult result = dictionaryService.searchWords(prefix);

        model.addAttribute("prefix", prefix);
        model.addAttribute("exactMatches", result.getExactMatches());
        model.addAttribute("suggestions", result.getSuggestions());

        return "index";
    }
}
