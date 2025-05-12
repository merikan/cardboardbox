package com.merikan.cardboard.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.merikan.cardboard.model.Cardboard;

@SpringBootTest
public class CardboardServiceTest {

    @Autowired
    private CardboardService uut;

    @Nested
    class findMatchingCardboardBox {

        @Test
        void test1() {
            // 6 st artikel 7, 2 st artikel 4, 4 st artikel 1 => kartong nr 2
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(6, 7));
                    add(new Item(2, 4));
                    add(new Item(4, 1));
                }
            };

            Cardboard result = uut.findMatchingCardboardBox(items);
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(2);

        }

        @Test
        void test2() {
            // 3 st artikel 3, 1 st artikel 1, 1 st artikel 2 => kartong nr 1
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(3, 3));
                    add(new Item(1, 1));
                    add(new Item(1, 2));
                }
            };

            Cardboard result = uut.findMatchingCardboardBox(items);
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1);

        }

        @Test
        void test3() {
            // 1 st artikel 5, 3 st artikel 4 => kartong nr 2
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(1, 5));
                    add(new Item(3, 4));
                }
            };

            Cardboard result = uut.findMatchingCardboardBox(items);
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(2);

        }

        @Test
        void test4() {
            // 12 st artikel 7, 100 st artikel 1 => "Upphämtning krävs"
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(12, 7));
                    add(new Item(100, 1));
                }
            };

            Cardboard result = uut.findMatchingCardboardBox(items);
            assertThat(result).isNull();

        }

        @Test
        void test5() {
            // 4 st artikel 8 => kartong nr 1
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(4, 8));
                }
            };

            Cardboard result = uut.findMatchingCardboardBox(items);
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1);

        }

        @Test
        void shouldThrowErrorWhenInvalidArticleId() {
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(4, 99));
                }
            };

            RuntimeException result = assertThrows(RuntimeException.class, () -> uut.findMatchingCardboardBox(items));
            assertThat(result.getMessage()).isEqualTo("Invalid article id 99");

        }
    }

}
