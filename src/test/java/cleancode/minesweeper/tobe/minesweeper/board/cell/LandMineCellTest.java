package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LandMineCellTest {

	@Test
	@DisplayName("지뢰 셀을 열면 셀의 상태가 주변에 있는 지뢰 셀 상태로 변경된다.")
	void cellSnapshotIsNumberWhenCellStateIsOpened() {
		// given
		LandMineCell landMineCell = new LandMineCell();

		// when
		landMineCell.open();

		// then
		assertThat(landMineCell.getSnapshot()).isEqualTo(CellSnapshot.ofLandMine());
	}

	@Test
	@DisplayName("지뢰 셀에 깃발을 꽂으면 셀의 상태가 깃발이 꽂힌 상태로 변경된다.")
	void cellSnapshotIsFlagWhenCellStateIsFlagged() {
		// given
		LandMineCell landMineCell = new LandMineCell();

		// when
		landMineCell.flag();

		// then
		assertThat(landMineCell.getSnapshot()).isEqualTo(CellSnapshot.ofFlag());
	}

	@Test
	@DisplayName("지뢰 셀을 초기화하면 셀의 상태가 아무것도 표시 안한 상태로 초기화된다.")
	void cellSnapshotIsUncheckedWhenJustInitialize() {
		// given
		LandMineCell landMineCell = new LandMineCell();

		// when

		// then
		assertThat(landMineCell.getSnapshot()).isEqualTo(CellSnapshot.ofUnchecked());
	}
}
