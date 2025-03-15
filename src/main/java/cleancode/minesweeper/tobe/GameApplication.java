package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;

public class GameApplication {

    // 이 클래스는 딱 프로그램 실행에 진입점만 가지게 된다.
    // 이름도 MinesweeperGame 에서 GameApplication 으로 변경한다. -> 이렇게 변경하면 지뢰찾기게임(Minesweeper 뿐만이 아닌 다른 게임도 실행할 수 있게 된다.)
    // 게임 실행에 대한 책임과 지뢰찾기 도메인 자체, 지뢰찾기 게임을 담당하는 역할을 분리했다.
    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(new Beginner(), new ConsoleInputHandler(), new ConsoleOutputHandler());

        Minesweeper minesweeper = new Minesweeper(gameConfig);
        minesweeper.initialize();
        minesweeper.run();
    }
}
