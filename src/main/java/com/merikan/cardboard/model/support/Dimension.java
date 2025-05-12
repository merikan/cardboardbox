package com.merikan.cardboard.model.support;

public class Dimension {

    private int width;
    private int length;

    public Dimension(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Integer getArea() {
        return length * width;
    }
}
