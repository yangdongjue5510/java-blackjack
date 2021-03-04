package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {
    private static final int DEFAULT_CARD_DRAW_COUNTS = 2;

    private final Deque<Card> cardDeck;

    public CardDeck() {
        List<Card> cards = Arrays.stream(Symbol.values())
                .flatMap(this::generateCard)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        this.cardDeck = new ArrayDeque<>(cards);
    }

    private Stream<Card> generateCard(Symbol symbol) {
        return Arrays.stream(Shape.values())
                .map(shape -> new Card(symbol, shape));
    }

    public Cards drawDefaultCards() {
        List<Card> cards = Stream.generate(this::draw)
                .limit(DEFAULT_CARD_DRAW_COUNTS)
                .collect(Collectors.toList());
        return new Cards(cards);
    }

    public Card draw() {
        return cardDeck.pop();
    }

    int size() {
        return cardDeck.size();
    }
}
