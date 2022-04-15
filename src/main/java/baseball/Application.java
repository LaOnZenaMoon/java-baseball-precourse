package baseball;

import baseball.answer.User;
import baseball.game.BaseballGame;
import baseball.game.Game;
import baseball.question.Computer;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        Game game = new BaseballGame(new Computer(), new User());
        do {
            playTheGame(game);
        } while (game.restart(Console.readLine()));
    }

    private static void playTheGame(Game game) {
        game.start();
        do {
            game.play();
        } while (!game.end());
    }
}
