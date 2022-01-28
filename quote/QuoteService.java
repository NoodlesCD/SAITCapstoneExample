package com.mro.quotation.quote;

import com.mro.quotation.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public void addItem(Long id, Item item) {
        Quote quote = quoteRepository.getById(id);
        quote.addItemToList(item);
        quoteRepository.save(quote);
    }

    public void removeItem(Long id, Item item) {
        Quote quote = quoteRepository.getById(id);
        quote.removeItemFromList(item.getId());
        quoteRepository.save(quote);
    }
}
