package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class CellState {
	private boolean isFlagged;
	private boolean isOpened;

	private CellState(boolean isFlagged, boolean isOpened) {
		this.isFlagged = isFlagged;
		this.isOpened = isOpened;
	}

	public static CellState initialize() {
		return new CellState(false, false);
	}

	// isOpened, isFlagged 는 Cell 의 공통 기능이므로 그대로 둔다.
	public void flag() {
		this.isFlagged = true;
	}

	public void open() {
		this.isOpened = true;
	}

	public boolean isOpened() {
		return isOpened;
	}

	public boolean isFlagged() {
		return isFlagged;
	}
}
