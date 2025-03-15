package cleancode.minesweeper.tobe.minesweeper.gamelevel;

// 추상화를 정말 바로 보여주는 구조이다. 인터페이스가 갖고 있는 스펙들 즉, 선언된 메서드 선언부들이 이 객체가 어떠한 역할을 갖는지 설명을 해준다.
// 이 GameLevel 인터페이스를 MineSweeper 안에 넣어줄 것이다.
// Minesweeper 객체는 GameLevel 을 받을 것이지만, 인터페이스여서 런타임 시점에 어떤 GameLevel 구현체가 들어오는지는 모른다. 하지만 GameLevel 인터페이스의 스펙은 알고 있다.
// Minesweeper 는 GameLevel 의 스펙을 통해 구현하면 된다.
public interface GameLevel {
	int getRowSize();
	int getColSize();
	int getLandMineCount();
}
