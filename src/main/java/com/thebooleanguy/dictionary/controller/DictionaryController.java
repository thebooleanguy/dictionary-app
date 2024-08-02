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

/**
 * Controller class responsible for handling HTTP requests related to dictionary operations.
 * It interacts with the DictionaryService to perform searches and returns the appropriate view.
 */
@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService; // Service for dictionary operations

    // Pattern to validate the prefix input: only alphabetic characters are allowed
    private static final Pattern VALID_PREFIX_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    /**
     * Handles requests to the root URL ("/") and returns the index view.
     *
     * @return The name of the view to be rendered (index).
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Handles POST requests to the "/search" URL to perform a dictionary search based on the provided prefix.
     *
     * @param prefix The prefix to search for in the dictionary.
     * @param model The model to add attributes to be used in the view.
     * @param redirectAttributes Used to pass error messages between requests.
     * @return The name of the view to be rendered (index) or a redirect to the root URL if invalid input is detected.
     */
    @PostMapping("/search")
    public String search(@RequestParam("prefix") String prefix, Model model, RedirectAttributes redirectAttributes) {
        // Validate the input prefix to ensure it only contains alphabetic characters
        if (!VALID_PREFIX_PATTERN.matcher(prefix).matches()) {
            redirectAttributes.addFlashAttribute("error", "Invalid input. Only letters are allowed.");
            return "redirect:/"; // Redirect to the root URL if input is invalid
        }

        // Perform the search operation using the DictionaryService
        SearchResult result = dictionaryService.searchWords(prefix);
        model.addAttribute("prefix", prefix);
        model.addAttribute("exactMatches", result.getExactMatches());
        model.addAttribute("suggestions", result.getSuggestions());
        return "index"; // Return the index view with the search results
    }
}
