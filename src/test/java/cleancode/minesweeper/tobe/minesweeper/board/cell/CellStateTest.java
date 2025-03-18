package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellStateTest {

	@Test
	@DisplayName("셀 상태를 초기화하면 오픈되지 않고, 깃발이 꽂히지 않은 상태로 초기화된다.")
	void initialize() {
		// given
		 CellState cellState = CellState.initialize();

		// when

		// then
		assertFalse(cellState.isOpened());
		assertFalse(cellState.isFlagged());
	}

	@Test
	@DisplayName("셀 상태를 깃발 꽂은 상태로 바꿀 수 있다.")
	void modifyCellStateFlagged() {
		// given
		CellState cellState = CellState.initialize();

		// when
		cellState.flag();

		// then
		assertTrue(cellState.isFlagged());
	}

	@Test
	@DisplayName("셀 상태를 연 상태로 바꿀 수 있다.")
	void modifyCellStateOpened() {
		// given
		CellState cellState = CellState.initialize();

		// when
		cellState.open();

		// then
		assertTrue(cellState.isOpened());
	}
}
