package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmptyCellTest {

	@Test
	@DisplayName("빈 셀을 열면 셀의 상태가 열린 상태로 변경된다.")
	void cellSnapshotIsEmptyWhenCellStateIsOpened() {
		// given
		EmptyCell emptyCell = new EmptyCell();

		// when
		emptyCell.open();

		// then
		assertThat(emptyCell.getSnapshot()).isEqualTo(CellSnapshot.ofEmpty());
	}

	@Test
	@DisplayName("빈 셀에 깃발을 꽂으면 셀의 상태가 깃발이 꽂힌 상태로 변경된다.")
	void cellSnapshotIsFlagWhenCellStateIsFlagged() {
		// given
		EmptyCell emptyCell = new EmptyCell();

		// when
		emptyCell.flag();

		// then
		assertThat(emptyCell.getSnapshot()).isEqualTo(CellSnapshot.ofFlag());
	}

	@Test
	@DisplayName("빈 셀을 초기화하면 셀의 상태가 아무것도 표시 안한 상태로 초기화된다.")
	void cellSnapshotIsUncheckedWhenJustInitialize() {
		// given
		EmptyCell emptyCell = new EmptyCell();

		// when

		// then
		assertThat(emptyCell.getSnapshot()).isEqualTo(CellSnapshot.ofUnchecked());
	}
}
