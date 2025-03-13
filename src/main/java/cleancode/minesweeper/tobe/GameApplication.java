package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.gamelevel.Middle;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;

public class GameApplication {

    // 이 클래스는 딱 프로그램 실행에 진입점만 가지게 된다.
    // 이름도 MinesweeperGame 에서 GameApplication 으로 변경한다. -> 이렇게 변경하면 지뢰찾기게임(Minesweeper 뿐만이 아닌 다른 게임도 실행할 수 있게 된다.)
    // 게임 실행에 대한 책임과 지뢰찾기 도메인 자체, 지뢰찾기 게임을 담당하는 역할을 분리했다.
    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        InputHandler inputHandler = new ConsoleInputHandler();
        ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();

        Minesweeper minesweeper = new Minesweeper(gameLevel, inputHandler, outputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }
}
