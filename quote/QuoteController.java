package com.mro.quotation.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/quotes")
@CrossOrigin("*")
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<Quote> getQuotes() {
        return quoteService.getQuotes();
    }

    @PostMapping("/addquote")
    public void addQuote(@RequestBody Quote quote) {
        quoteService.saveQuote(quote);
    }
}
