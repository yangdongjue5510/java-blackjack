package domain.rule;

import java.util.function.BiFunction;

import domain.result.WinningResult;
import domain.user.User;

public enum Rule {

    FIRST_USER_BUST((first, second) -> first.isBust(), WinningResult.LOSE),
    SECOND_USER_BUST((first, second) -> second.isBust(), WinningResult.WIN),
    FIRST_USER_GREATER((first, second) -> first.calculatePoint() > second.calculatePoint(), WinningResult.WIN),
    FIRST_USER_ONLY_BLACK_JACK((first, second) -> first.isBlackJack() && !second.isBlackJack(), WinningResult.WIN),
    SECOND_USER_ONLY_BLACK_JACK((first, second) -> !first.isBlackJack() && second.isBlackJack(), WinningResult.LOSE),
    EQUALS((first, second) -> first.calculatePoint() == second.calculatePoint(), WinningResult.DRAW);

    private final BiFunction<User, User, Boolean> condition;
    private final WinningResult winningResult;

    Rule(BiFunction<User, User, Boolean> condition, WinningResult winningResult) {
        this.condition = condition;
        this.winningResult = winningResult;
    }

    public static WinningResult decideWinningResult(User first, User second) {
        for (Rule rule : Rule.values()) {
            if (rule.condition.apply(first, second)) {
                return rule.winningResult;
            }
        }
        return WinningResult.LOSE;
    }
}
