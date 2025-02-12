package edu.guilford;

/**
 * The Deck class represents a deck of playing cards.
 * It provides methods to build, shuffle, pick, and deal cards.
 */
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    private Random rand = new Random();

    /**
     * Constructor that initializes the deck by building it.
     */
    public Deck() {
        build();
    }

    /**
     * Gets the deck of cards.
     * 
     * @return the deck as an ArrayList of Card objects
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Clears the deck of all cards.
     */
    public void clear() {
        deck.clear();
    }

    /**
     * Builds a standard deck of 52 playing cards.
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck using the built-in shuffle method for efficiency.
     */
    public void shuffle() {
        // Using built-in shuffle method for better efficiency
        java.util.Collections.shuffle(deck, rand);
    }

    /**
     * Picks and removes a card at the specified index.
     * 
     * @param i the index of the card to pick
     * @return the picked card, or null if the index is out of bounds
     */
    public Card pick(int i) {
        if (i >= 0 && i < deck.size()) {
            return deck.remove(i);
        }
        return null; // Return null if index is out of bounds
    }

    /**
     * Deals the top card from the deck.
     * 
     * @return the dealt card, or null if the deck is empty
     */
    public Card deal() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        }
        return null; // Return null if deck is empty
    }

    /**
     * Gets the current size of the deck.
     * 
     * @return the number of cards remaining in the deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns a string representation of the deck.
     * 
     * @return a string listing all cards in the deck
     */
    public String toString() {
        StringBuilder deckString = new StringBuilder();
        for (Card card : deck) {
            deckString.append(card.toString()).append("\n");
        }
        return deckString.toString();
    }
}

// Original Code (Commented Out)

// public void shuffle() {
// ArrayList<Card> tempDeck = new ArrayList<Card>();
// while (deck.size() > 0) {
// int loc = rand.nextInt(deck.size());
// tempDeck.add(deck.get(loc));
// deck.remove(loc);
// }
// deck = tempDeck;
// }

// public Card pick(int i) {
// Card picked = deck.remove(i);
// return picked;
// }

// public Card deal() {
// return deck.remove(0);
// }
