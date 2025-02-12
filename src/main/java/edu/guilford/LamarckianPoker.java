package edu.guilford;


/**
 * Represents a game of Lamarckian Poker.
 * This class manages the hands of two players, a pool of cards, a deck, and a discard pile.
 * The game follows a turn-based mechanism where players draw and compare cards.
 */
import java.util.ArrayList;
import java.util.Random;

public class LamarckianPoker {
    private Hand player1Hand;
    private Hand player2Hand;
    private Hand pool;
    private Deck discard;
    private Deck deck;
    private Random rand = new Random();
    private int iTurn;

    /**
     * Constructs a new game and resets the state with a new deck.
     */
    public LamarckianPoker() {
        reset(true);
    }

    /**
     * @return The hand of Player 1.
     */
    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    /**
     * @return The hand of Player 2.
     */
    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    /**
     * @return The current pool of shared cards.
     */
    public Hand getPool() {
        return pool;
    }

    /**
     * Resets the game state.
     * @param newDeck If true, creates a new deck and shuffles it.
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            discard = new Deck();
            discard.clear();
            deck.shuffle();
        }
        iTurn = 0;
    }

    /**
     * Deals four cards to each player at the beginning of the game.
     */
    public void deal() {
        player1Hand = new Hand();
        player2Hand = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            if (deck.size() > 0) { // Ensures deck has enough cards
                player1Hand.addCard(deck.deal());
            }
            if (deck.size() > 0) { // Ensures deck has enough cards
                player2Hand.addCard(deck.deal());
            }
        }
    }

    /**
     * Creates a pool of four cards from the deck.
     */
    public void makePool() {
        pool = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            if (deck.size() > 0) { // Ensures deck has enough cards
                pool.addCard(deck.deal());
            }
        }
    }

    /**
     * Executes a turn in the game, where both players compare a randomly selected card.
     * The winner adds matching cards from the pool to their hand.
     * @return True if the turn was executed successfully, False if the game has ended.
     */
    public boolean turn() {
        if (player1Hand.size() < 7 || player2Hand.size() < 7) {
            makePool();
            
            if (player1Hand.size() == 0 || player2Hand.size() == 0) {
                return false; // Prevents IllegalArgumentException
            }

            // Original code
            // Card player1Card = player1Hand.getCard(rand.nextInt(player1Hand.size()));
            // Card player2Card = player2Hand.getCard(rand.nextInt(player2Hand.size()));

            // Selecting a random card from each player's hand (Updated for safety)
            Card player1Card = player1Hand.getCard(rand.nextInt(Math.max(1, player1Hand.size())));
            Card player2Card = player2Hand.getCard(rand.nextInt(Math.max(1, player2Hand.size())));
            
            Hand firstHand, secondHand;
            Card firstCard, secondCard;
            if (player1Card.getRank().ordinal() > player2Card.getRank().ordinal()) {
                firstHand = player1Hand;
                secondHand = player2Hand;
                firstCard = player1Card;
                secondCard = player2Card;
            } else if (player1Card.getRank().ordinal() < player2Card.getRank().ordinal()) {
                firstHand = player2Hand;
                secondHand = player1Hand;
                firstCard = player2Card;
                secondCard = player1Card;
            } else {
                if (player1Card.getSuit().ordinal() > player2Card.getSuit().ordinal()) {
                    firstHand = player1Hand;
                    secondHand = player2Hand;
                    firstCard = player1Card;
                    secondCard = player2Card;
                } else {
                    firstHand = player2Hand;
                    secondHand = player1Hand;
                    firstCard = player2Card;
                    secondCard = player1Card;
                }
            }

            ArrayList<Card> poolRemove = new ArrayList<>();
            
            for (Card poolCard : pool.getHand()) {
                if (firstCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        firstCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    firstHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            poolRemove.clear();
            pool.addCard(firstCard);
            firstHand.removeCard(firstCard);
            
            for (Card poolCard : pool.getHand()) {
                if (secondCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        secondCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    secondHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            pool.addCard(secondCard);
            secondHand.removeCard(secondCard);
            
            for (Card poolCard : pool.getHand()) {
                discard.getDeck().add(poolCard);
            }
            pool.getHand().clear();

            if (deck.size() < 4) {
                for (Card card : discard.getDeck()) {
                    deck.getDeck().add(card);
                }
                discard.clear();
            }
            iTurn++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Provides a string representation of the game state.
     * @return A formatted string containing the hands of both players and the pool.
     */
    @Override
    public String toString() {
        return "\nPlayer 1: \n" + player1Hand + "\nPlayer 2: \n" + player2Hand + "\nPool: " + pool + "\n";
    }
}
