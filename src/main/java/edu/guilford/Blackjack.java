package edu.guilford;

/**
 * The Blackjack class represents a simplified version of the Blackjack card
 * game.
 * It manages the player's and dealer's hands, the deck, and the game logic.
 */
public class Blackjack {
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;

    /**
     * Constructs a new Blackjack game and initializes it with a shuffled deck.
     */
    public Blackjack() {
        reset(true);
    }

    /**
     * Gets the player's hand.
     * 
     * @return the player's hand
     */
    public Hand getPlayerHand() {
        return playerHand;
    }

    /**
     * Gets the dealer's hand.
     * 
     * @return the dealer's hand
     */
    public Hand getDealerHand() {
        return dealerHand;
    }

    /**
     * Gets the deck used in the game.
     * 
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    // public void reset(boolean newDeck) {
    // if (newDeck) {
    // deck = new Deck();
    // deck.shuffle();
    // }
    // }

    /**
     * Resets the game by optionally creating a new shuffled deck and clearing
     * hands.
     * 
     * @param newDeck true if a new deck should be created, false to keep the
     *                existing deck
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            deck.shuffle();
        }
        playerHand = new Hand(); // Ensure hands are reset
        dealerHand = new Hand();
    }

    // public void deal() {
    // playerHand = new Hand();
    // dealerHand = new Hand();
    // playerHand.addCard(deck.deal());
    // dealerHand.addCard(deck.deal());
    // playerHand.addCard(deck.deal());
    // dealerHand.addCard(deck.deal());
    // }

    /**
     * Deals two cards each to the player and dealer. If there aren't enough cards,
     * the deck is reshuffled.
     */
    public void deal() {
        if (deck.size() < 4) {
            reset(true); // Reshuffle if not enough cards
        }
        playerHand = new Hand();
        dealerHand = new Hand();
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }

    /**
     * Executes the player's turn. The player will draw cards until the total hand
     * value is at least 16.
     * 
     * @return true if the player has not exceeded 21 (busted), false otherwise
     */
    public boolean playerTurn() {
        while (playerHand.getTotalValue() < 16) {
            playerHand.addCard(deck.deal());
        }
        return playerHand.getTotalValue() <= 21;
    }

    /**
     * Executes the dealer's turn. The dealer will draw cards until the total hand
     * value is at least 17.
     * 
     * @return true if the dealer has not exceeded 21 (busted), false otherwise
     */
    public boolean dealerTurn() {
        while (dealerHand.getTotalValue() < 17) {
            dealerHand.addCard(deck.deal());
        }
        return dealerHand.getTotalValue() <= 21;
    }

    // public String toString() {
    // String result = "Player's Hand:\n";
    // for (int i = 0; i < playerHand.size(); i++) {
    // result += playerHand.getCard(i) + "\n";
    // }
    // result += "Player's Total: " + playerHand.getTotalValue() + "\n\n";
    // result += "Dealer's Hand:\n";
    // for (int i = 0; i < dealerHand.size(); i++) {
    // result += dealerHand.getCard(i) + "\n";
    // }
    // result += "Dealer's Total: " + dealerHand.getTotalValue() + "\n\n";
    // return result;
    // }

    /**
     * Returns a string representation of the current game state, including the
     * hands and their total values.
     * 
     * @return a formatted string representing the player's and dealer's hands
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Player's Hand:\n");
        for (int i = 0; i < playerHand.size(); i++) {
            result.append(playerHand.getCard(i)).append("\n");
        }
        result.append("Player's Total: ").append(playerHand.getTotalValue()).append("\n\n");
        result.append("Dealer's Hand:\n");
        for (int i = 0; i < dealerHand.size(); i++) {
            result.append(dealerHand.getCard(i)).append("\n");
        }
        result.append("Dealer's Total: ").append(dealerHand.getTotalValue()).append("\n\n");
        return result.toString();
    }
}
