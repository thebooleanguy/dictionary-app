package com.thebooleanguy.dictionary.controller;

import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
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
     * @param model The model to add attributes to be used in the view.
     * @return The name of the view to be rendered (index).
     */
    @RequestMapping("/")
    public String index(Model model) {
        // Add query history to the model to be displayed on the index page
        model.addAttribute("queryHistory", dictionaryService.getQueryHistory());
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

        // Add attributes to the model for the view
        model.addAttribute("prefix", prefix);
        model.addAttribute("exactMatches", result.getExactMatches());
        model.addAttribute("suggestions", result.getSuggestions());
        model.addAttribute("queryHistory", dictionaryService.getQueryHistory()); // Include query history

        return "index"; // Return the index view with the search results
    }

    /**
     * Fetches the query history as a JSON array.
     *
     * @return A list of search queries.
     */
    @GetMapping("/queryHistory")
    @ResponseBody
    public List<String> getQueryHistory() {
        return dictionaryService.getQueryHistory();
    }

    /**
     * Handles GET requests to the "/cachedResult/{query}" URL to retrieve cached search results.
     *
     * @param query The query string to look up.
     * @return A ModelAndView object containing the cached result view or an error view if no cached result is found.
     */
    @GetMapping("/cachedResult/{query}")
    public ModelAndView getCachedResult(@PathVariable String query) {
        // Process the query and fetch cached results
        SearchResult result = dictionaryService.getCachedResult(query);
        if (result == null) {
            // Handle the case where there is no cached result
            return new ModelAndView("error", "message", "No cached result found for the query.");
        }
        return new ModelAndView("cachedResultView", "result", result);
    }

    /**
     * Data class for handling incoming query data.
     */
    public static class QueryRequest {
        private String query;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }
    }
}
