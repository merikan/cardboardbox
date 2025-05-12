package com.merikan.cardboard.services;

public class Item {

    private int quantity;
    private Integer articleId;

    public Item(int quantity, Integer articleId) {
        this.quantity = quantity;
        this.articleId = articleId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public int getQuantity() {
        return quantity;
    }

}
