package com.mro.quotation.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository) {
        return args -> {
            Item tinyCable = new Item("TNYC", "Tiny cable", 2.99, 1.0, "Tiny cable, not complex at all");
            Item bigCable = new Item("BIGC", "Big cable", 9.99, 1.2, "Slightly complex");
            Item box = new Item("BOKS", "Box", 4.55, 5, "Not complex at all");
            Item hdmi = new Item("HDMI3", "HDMI", 99.99, 4, "The most complex");

            itemRepository.saveAll(List.of(tinyCable, bigCable, box, hdmi));
        };
    }
}
