package ch7.item45;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PokerCartesianProduct {
    public static void main(String[] args) {
        List<Card> cardList = newDeck();
        for (Card card : cardList) {
            System.out.println(card.suit + " " + card.rank);

        }
    }
    // Iterative Cartesian product computation
    private static List<Card> newDeck() {
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values()) for (Rank rank : Rank.values()) result.add(new Card(suit, rank));
        return result;
    }

    // Stream-based Cartesian product computation
    private static List<Card> newDeckStream() {
        return Stream.of(Suit.values())
                .flatMap(suit ->
                        Stream.of(Rank.values())
                              .map(rank -> new Card(suit, rank))
                ).collect(toList());
    }

    private static class Card {
        private Suit suit;
        private Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
    }

    private enum Suit {
        HEART, DIAMOND, CLUB, SPADE;

    }

    private enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, J, Q, K, A

    }
}
