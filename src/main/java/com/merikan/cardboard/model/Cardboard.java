package com.merikan.cardboard.model;

import com.merikan.cardboard.model.support.Dimension;

public class Cardboard {

    private Integer id;
    private Dimension dimension;

    public Cardboard(Integer id, Dimension dimension) {
        this.id = id;
        this.dimension = dimension;
    }

    public Integer getId() {
        return id;
    }

    public Dimension getDimension() {
        return dimension;
    }

}
