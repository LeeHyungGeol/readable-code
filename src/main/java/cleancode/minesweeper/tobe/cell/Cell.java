package cleancode.minesweeper.tobe.cell;

public interface Cell {

	// 하위 클래스에서도 사용할 수 있기 때문에 protected 로 변경
	String FLAG_SIGN = "⚑";
	String UNCHECKED_SIGN = "□";

	boolean isLandMine();
	boolean hasLandMineCount();
	String getSign();

	// isOpened, isFlagged 는 Cell 의 공통 기능이므로 그대로 둔다.
	void flag();

	void open();

	boolean isChecked();

	boolean isOpened();
}
