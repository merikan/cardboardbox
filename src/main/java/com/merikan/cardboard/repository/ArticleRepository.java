package com.merikan.cardboard.repository;

import java.util.ArrayList;
import java.util.List;

import com.merikan.cardboard.model.Article;
import com.merikan.cardboard.model.support.Dimension;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

    private static List<Article> all = new ArrayList<>() {
    {
        add(new Article(1, new Dimension(1, 1)));
        add(new Article(2, new Dimension(1, 2)));
        add(new Article(3, new Dimension(1, 4)));
        add(new Article(4, new Dimension(1, 6)));
        add(new Article(5, new Dimension(1, 8)));
        add(new Article(6, new Dimension(1, 9)));
        add(new Article(7, new Dimension(1, 12)));
        add(new Article(8, new Dimension(1, 5)));
        add(new Article(9, new Dimension(1, 9)));
    }
};

    public List<Article> getAll() {
        return all;

    }
}
