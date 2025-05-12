package com.merikan.cardboard.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.merikan.cardboard.model.Article;
import com.merikan.cardboard.model.Cardboard;
import com.merikan.cardboard.repository.ArticleRepository;
import com.merikan.cardboard.repository.CardboardRepository;

@Service
public class CardboardService {

    private final ArticleRepository articleRepository;
    private final CardboardRepository cardboardRepository;
    private List<Cardboard> cardboards = new ArrayList<>();
    private Map<Integer, Article> articlesMap = new HashMap<>();


    public CardboardService(ArticleRepository articleRepository, CardboardRepository cardboardRepository) {
        this.articleRepository = articleRepository;
        this.cardboardRepository = cardboardRepository;
        // initialize list and map
        cardboards.addAll(cardboardRepository.getAll());
        for (Article a : articleRepository.getAll()) { // build hashmap for easy access
            articlesMap.put(a.getId(), a);
        }

    }
    public Cardboard findMatchingCardboardBox(List<Item> items) {

        List<Integer> itemLengths = new ArrayList<>();
        for (Item item : items) {
            if (!articlesMap.containsKey(item.getArticleId())) {
                throw new RuntimeException("Invalid article id %s".formatted(item.getArticleId()));
            }
            int quantity = item.getQuantity();
            int length = articlesMap.get(item.getArticleId()).getDimension().getLength();
            for (int i = 0; i < quantity; i++) {
                itemLengths.add(length);
            }
        }


        return bestMatch(itemLengths);
    }

    /**
     * Try to fit items in a cardboard box.
     * From smallest to largest box.
     *
     * @param itemLengths A list of lengths for each item
     * @return a {@link Cardboard} if items fit in box else return null
     */
    private Cardboard bestMatch(List<Integer> itemLengths) {
        // sort by area size, smallest first
        List<Cardboard> sortedCardboards = cardboards.stream().sorted(Comparator.comparing(b -> b.getDimension().getArea())).toList();
        for (Cardboard box : sortedCardboards) {
            int boxWidth = box.getDimension().getWidth();
            int boxLength = box.getDimension().getLength();
            int boxArea = boxWidth * boxLength;

            // is it even possible?
            int totalLength = itemLengths.stream().mapToInt(Integer::intValue).sum();
            if (totalLength > boxArea) continue; // does not fit?

            // for each row in the box
            List<List<Integer>> rows = new ArrayList<>();
            for (int i = 0; i < boxWidth; i++) {
                rows.add(new ArrayList<>());
            }

            itemLengths.sort(Comparator.reverseOrder()); // sort item by length, longest first
            boolean allFit = true;
            for (int item : itemLengths) {
                boolean doesFit = false;
                for (List<Integer> row : rows) {
                    int rowSum = row.stream().mapToInt(Integer::intValue).sum();
                    if (rowSum + item <= boxLength) {
                        row.add(item);
                        doesFit = true;
                        break;
                    }
                }
                if (!doesFit) {
                    allFit = false;
                    break;
                }
            }

            if (allFit) {
                return box;
            }
        }

        return null;
    }
}
