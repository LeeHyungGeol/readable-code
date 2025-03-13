package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.positoion.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

public interface InputHandler {
	UserAction getUserActionFromUser();

	CellPosition getCellPositionFromUser();
}
