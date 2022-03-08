package model;

import java.util.List;

public class Dealer extends Participator {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    @Override
    public List<Card> getCards() {
        return List.of(cards.getFirstCard(1));
    }

    @Override
    public boolean canReceiveCard() {
        return cards.canReceiveCardForDealer();
    }
}
