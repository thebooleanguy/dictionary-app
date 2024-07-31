package com.thebooleanguy.dictionary.controller;

import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;

@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    private static final Pattern VALID_PREFIX_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("prefix") String prefix, Model model, RedirectAttributes redirectAttributes) {
        if (!VALID_PREFIX_PATTERN.matcher(prefix).matches()) {
            redirectAttributes.addFlashAttribute("error", "Invalid input. Only letters are allowed.");
            return "redirect:/";
        }

        SearchResult result = dictionaryService.searchWords(prefix);
        model.addAttribute("prefix", prefix);
        model.addAttribute("exactMatches", result.getExactMatches());
        model.addAttribute("suggestions", result.getSuggestions());
        return "index";
    }
}
