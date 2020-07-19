package com.amazon.model;

public class Product {

    String title;
    Integer price;

    /**
     * Get title of the product
     * @return title of the product
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of the product
     * @param title title of the product
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get price of the product
     * @return price of the product
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Set price of the product
     * @param price price of the product
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
}
