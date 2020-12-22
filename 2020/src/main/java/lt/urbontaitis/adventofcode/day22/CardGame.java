package lt.urbontaitis.adventofcode.day22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toList;

public class CardGame {

  private final Deck deck1;
  private final Deck deck2;

  public CardGame(String inputData) {
    var data = inputData.split(",,");

    deck1 = transform(data[0]);
    deck2 = transform(data[1]);
  }

  private Deck transform(String s) {
    var cards = Arrays.stream(s.split(",")).skip(1).map(Integer::parseInt).collect(toList());

    return new Deck(false, new LinkedList<>(cards));
  }

  record Deck(boolean isWon, Deque<Integer> cards) {

    boolean hasCards() {
      return !cards.isEmpty();
    }

    int getFirstCard() {
      return cards.pop();
    }

    int cardsCount() {
      return cards.size();
    }

    void takeCards(int card1, int card2) {
      cards.addLast(card1);
      cards.addLast(card2);
    }

    int calculateScore() {
      var clone = new LinkedList<>(cards);
      var scores = IntStream.range(1, clone.size() + 1).boxed().sorted(reverseOrder()).collect(toList());
      return scores.stream().mapToInt(score -> score * clone.pop()).sum();
    }

    Deck winner() {
      return new Deck(true, cards);
    }
  }

  private Deck playRecursive(Deck deck1, Deck deck2) {
    var state = new HashSet<Deck>();
    while(deck1.hasCards() && deck2.hasCards()) {
      if(!state.add(deck1)) {
        return deck1.winner();
      }

      var card1 = deck1.getFirstCard();
      var card2 = deck2.getFirstCard();

      if(deck1.cardsCount() < card1 || deck2.cardsCount() < card2) {
        if(card1 > card2) {
          deck1.takeCards(card1, card2);
        } else {
          deck2.takeCards(card2, card1);
        }
      } else {
        var isFirstPlayerWinner = playRecursive(getSubGameDeck(deck1, card1), getSubGameDeck(deck2, card2)).isWon();
        if(isFirstPlayerWinner) {
          deck1.takeCards(card1, card2);
        } else {
          deck2.takeCards(card2, card1);
        }
      }
    }

    return deck1.hasCards() ? deck1.winner() : deck2;
  }

  private Deck getSubGameDeck(Deck deck, int card) {
    return new Deck(false, new LinkedList<>(new ArrayList<>(deck.cards()).subList(0, card)));
  }

  Integer playCombat() {
    while (deck1.hasCards() && deck2.hasCards()) {
      var player1Card = deck1.getFirstCard();
      var player2Card = deck2.getFirstCard();
      if (player1Card > player2Card) {
        deck1.takeCards(player1Card, player2Card);
      } else {
        deck2.takeCards(player2Card, player1Card);
      }
    }

    if (deck1.hasCards()) {
      return deck1.calculateScore();
    } else {
      return deck2.calculateScore();
    }
  }

  Integer playRecursiveCombat() {
    var winner = playRecursive(deck1, deck2);

    return winner.calculateScore();
  }
}
