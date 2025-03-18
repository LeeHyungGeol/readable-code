package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class NumberCellTest {

	@Test
	@DisplayName("숫자 셀을 열면 셀의 상태가 주변에 있는 지뢰의 숫자를 표기한 NUMBER 상태로 변경된다.")
	void cellSnapshotIsNumberWhenCellStateIsOpened() {
		// given
		int nearByLandMineCount = 1;
		NumberCell numberCell = new NumberCell(nearByLandMineCount);

		// when
		numberCell.open();

		// then
		assertThat(numberCell.getSnapshot()).isEqualTo(CellSnapshot.ofNumber(nearByLandMineCount));
	}

	@Test
	@DisplayName("숫자 셀에 깃발을 꽂으면 셀의 상태가 깃발이 꽂힌 상태로 변경된다.")
	void cellSnapshotIsFlagWhenCellStateIsFlagged() {
		// given
		int nearByLandMineCount = 1;
		NumberCell numberCell = new NumberCell(nearByLandMineCount);

		// when
		numberCell.flag();

		// then
		assertThat(numberCell.getSnapshot()).isEqualTo(CellSnapshot.ofFlag());
	}

	@Test
	@DisplayName("숫자 셀을 초기화하면 셀의 상태가 아무것도 표시 안한 상태로 초기화된다.")
	void cellSnapshotIsUncheckedWhenJustInitialize() {
		// given
		int nearByLandMineCount = 1;
		NumberCell numberCell = new NumberCell(nearByLandMineCount);

		// when

		// then
		assertThat(numberCell.getSnapshot()).isEqualTo(CellSnapshot.ofUnchecked());
	}
}
