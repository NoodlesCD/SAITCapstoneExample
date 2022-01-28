package com.mro.quotation.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mro.quotation.item.Item;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @SequenceGenerator(
            name = "quote_sequence",
            sequenceName = "quote_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quote_sequence"
    )
    private Long quoteNumber;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("targetPricing")
    private double targetPricing;

    @JsonProperty("quantityRequired")
    private double quantityRequired;

    @JsonProperty("itemList")
    @OneToMany(mappedBy = "quote")
    private List<Item> itemList;

    private Date dateQuoted;
    private Date dateRevised;

    public Quote() {}

    public Quote(String customerName, List<Item> itemList) {
        this.customerName = customerName;
        this.itemList = itemList;
    }

    public Quote(String customerName, double targetPricing, double quantityRequired, List<Item> itemList) {
        this.customerName = customerName;
        this.targetPricing = targetPricing;
        this.quantityRequired = quantityRequired;
        this.itemList = itemList;
    }

    public Long getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(Long quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTargetPricing() {
        return targetPricing;
    }

    public void setTargetPricing(double targetPricing) {
        this.targetPricing = targetPricing;
    }

    public double getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(double quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public void addItemToList(Item item) {
        itemList.add(item);
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void removeItemFromList(Long id) {
        for (Item i : itemList) {
            if (i.getId() == id) {
                itemList.remove(i);
                break;
            }
        }
    }

    public Date getDateQuoted() {
        return dateQuoted;
    }

    public void setDateQuoted(Date dateQuoted) {
        this.dateQuoted = dateQuoted;
    }

    public Date getDateRevised() {
        return dateRevised;
    }

    public void setDateRevised(Date dateRevised) {
        this.dateRevised = dateRevised;
    }
}
