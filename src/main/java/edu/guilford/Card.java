package edu.guilford;

/**
 * The Card class represents a playing card with a suit and rank.
 * It implements Comparable to allow sorting of cards based on their rank and suit.
 */
import java.util.Random;

public class Card implements Comparable<Card> {
    // Enum for the suits
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    // Enum for the ranks
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    // Instance variables
    private Suit suit;
    private Rank rank;

    /**
     * Constructor to initialize a card with a specific suit and rank.
     * 
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Constructor to generate a random card.
     * The suit and rank are selected randomly from their respective enums.
     */
    public Card() {
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    /**
     * Gets the suit of the card.
     * 
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the rank of the card.
     * 
     * @return the rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns a string representation of the card.
     * 
     * @return a string in the format "Rank of Suit"
     */
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Compares this card with another card based on rank and suit.
     * 
     * @param otherCard the card to be compared
     * @return a negative integer, zero, or a positive integer as this card is less
     *         than,
     *         equal to, or greater than the specified card
     */
    @Override
    public int compareTo(Card otherCard) {
        // Updated to use Integer.compare for better readability and performance
        int rankComparison = Integer.compare(this.rank.ordinal(), otherCard.rank.ordinal());
        return (rankComparison != 0) ? rankComparison : Integer.compare(this.suit.ordinal(), otherCard.suit.ordinal());
    }

    // Original compareTo method (commented out)
    /*
     * @Override
     * public int compareTo(Card otherCard) {
     * if (this.rank.ordinal() > otherCard.rank.ordinal()) {
     * return 1;
     * } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
     * return -1;
     * } else {
     * if (this.suit.ordinal() > otherCard.suit.ordinal()) {
     * return 1;
     * } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
     * return -1;
     * }
     * }
     * return 0;
     * }
     */
}
