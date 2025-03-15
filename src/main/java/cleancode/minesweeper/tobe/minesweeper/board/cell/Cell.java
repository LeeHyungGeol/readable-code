package cleancode.minesweeper.tobe.minesweeper.board.cell;

public interface Cell {



	boolean isLandMine();
	boolean hasLandMineCount();

	CellSnapshot getSnapshot();

	// isOpened, isFlagged 는 Cell 의 공통 기능이므로 그대로 둔다.
	void flag();

	void open();

	boolean isChecked();

	boolean isOpened();
}
