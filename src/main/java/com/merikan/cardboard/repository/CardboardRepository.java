package com.merikan.cardboard.repository;

import java.util.ArrayList;
import java.util.List;

import com.merikan.cardboard.model.Cardboard;
import com.merikan.cardboard.model.support.Dimension;
import org.springframework.stereotype.Repository;

@Repository
public class CardboardRepository {

    private static List<Cardboard> all = new ArrayList<>() {
        {
            add(new Cardboard(1, new Dimension(4, 5)));
            add(new Cardboard(2, new Dimension(8, 12)));
            add(new Cardboard(3, new Dimension(12, 20)));
        }
    };

    public List<Cardboard> getAll() {
        return all;

    }
}
