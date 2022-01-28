package com.mro.quotation.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mro.quotation.quote.Quote;

import javax.persistence.*;

@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long id;

    @JsonProperty("itemSku")
    @Column(nullable = false)
    private String sku;

    @JsonProperty("itemName")
    @Column(nullable = false)
    private String name;

    @JsonProperty("itemCost")
    @Column(nullable = false)
    private double cost;

    @JsonProperty("complexityFactor")
    private double complexityFactor;

    @Column(length = 512)
    @JsonProperty("complexityDescription")
    private String complexityDescription;

    @ManyToOne
    @JoinColumn(name = "quote_quote_number")
    private Quote quote;

    public Quote getQuote() {
        return quote;
    }

    public Item() {}

    public Item(String sku, String name, double cost) {
        this.sku = sku;
        this.name = name;
        this.cost = cost;
    }

    public Item(String sku, String name, double cost, double complexityFactor, String complexityDescription) {
        this.sku = sku;
        this.name = name;
        this.cost = cost;
        this.complexityFactor = complexityFactor;
        this.complexityDescription = complexityDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getComplexityFactor() {
        return complexityFactor;
    }

    public void setComplexityFactor(double complexityFactor) {
        this.complexityFactor = complexityFactor;
    }

    public String getComplexityDescription() {
        return complexityDescription;
    }

    public void setComplexityDescription(String complexityDescription) {
        this.complexityDescription = complexityDescription;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", complexityFactor=" + complexityFactor +
                ", complexityDescription='" + complexityDescription + '\'' +
                '}';
    }
}
