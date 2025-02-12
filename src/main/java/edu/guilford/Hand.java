package edu.guilford;

import java.util.ArrayList;

/**
 * Represents a hand of cards in a card game.
 * Provides methods to add, remove, and evaluate the total value of the hand.
 */
public class Hand {
    private ArrayList<Card> hand;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        hand = new ArrayList<>();
    }

    /**
     * Adds a card to the hand.
     * 
     * @param card the card to add
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes a card from the hand.
     * 
     * @param card the card to remove
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Clears all cards from the hand.
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Gets the number of cards in the hand.
     * 
     * @return the size of the hand
     */
    public int size() {
        return hand.size();
    }

    /**
     * Retrieves a card from the hand by index.
     * 
     * @param index the index of the card
     * @return the card at the specified index, or null if out of bounds
     */
    public Card getCard(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.get(index);
        }
        return null; // Return null if index is out of bounds
    }

    /**
     * Calculates the total value of the hand, considering Ace values dynamically.
     * 
     * @return the total value of the hand
     */
    public int getTotalValue() {
        int value = 0;
        int aces = 0;
        int[] cardValues = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 }; // Values for ranks

        for (Card card : hand) {
            int rankIndex = card.getRank().ordinal();
            if (rankIndex == 12) { // ACE
                aces++;
            } else {
                value += cardValues[rankIndex];
            }
        }

        for (int i = 0; i < aces; i++) {
            value += (value + 11 <= 21) ? 11 : 1;
        }
        return value;
    }

    /**
     * Returns a string representation of the hand.
     * 
     * @return a formatted string listing the cards in the hand
     */
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            handString.append(card.toString()).append("\n");
        }
        return handString.toString();
    }

    /**
     * Retrieves the list of cards in the hand.
     * 
     * @return an ArrayList of Card objects
     */
    public ArrayList<Card> getHand() {
        return hand;
    }
}

// **Original Code (Commented Out)**

// public int getTotalValue() {
// int value = 0;
// int aces = 0;
// for (Card card : hand) {
// switch (card.getRank()) {
// case TWO:
// value += 2;
// break;
// case THREE:
// value += 3;
// break;
// case FOUR:
// value += 4;
// break;
// case FIVE:
// value += 5;
// break;
// case SIX:
// value += 6;
// break;
// case SEVEN:
// value += 7;
// break;
// case EIGHT:
// value += 8;
// break;
// case NINE:
// value += 9;
// break;
// case TEN:
// case JACK:
// case QUEEN:
// case KING:
// value += 10;
// break;
// case ACE:
// aces++;
// break;
// }
// }
// for (int i = 0; i < aces; i++) {
// if (value + 11 <= 21) {
// value += 11;
// } else {
// value += 1;
// }
// }
// return value;
// }
