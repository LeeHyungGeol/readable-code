package cleancode.minesweeper.tobe;

public class Cell {

	private static final String FLAG_SIGN = "⚑";
	private static final String LAND_MINE_SIGN = "☼";
	private static final String UNCHECKED_SIGN = "□";
	private static final String EMPTY_SIGN = "■";

	private int nearbyLandMineCount;
	private boolean isLandMine;
	private boolean isFlagged;
	private boolean isOpened;

	// Cell 이 가진 속성: 근처 지뢰 갯수, 지뢰 여부
	// Cell 의 상태: 깃발 유무, 열렸다/닫혔다, 사용자가 확인함

	private Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
		this.nearbyLandMineCount = nearbyLandMineCount;
		this.isLandMine = isLandMine;
		this.isFlagged = isFlagged;
		this.isOpened = isOpened;
	}

	// 정적 팩토리 메서드를 좋아하는 이유: 메서드에 이름을 줄 수 있다.
	// 정적 팩토리 메서드가 여러개가 된다면 그에 맞는 다른 이름들을 지어줄 수도 있고, 검증과 같은 로직을 추가할 수도 있다.
	// 생성자 하나인 객체라도 정적 팩토리 메서드를 만들어서 생성자를 대체해보자.
	public static Cell of(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
		return new Cell(nearbyLandMineCount, isLandMine, isFlagged, isOpened);
	}

	public static Cell create() {
		return of(0, false, false, false);
	}

	public void turnOnLandMine() {
		this.isLandMine = true;
	}

	public void updateNearbyLandMineCount(int count) {
		this.nearbyLandMineCount = count;
	}

	public void flag() {
		this.isFlagged = true;
	}

	public void open() {
		this.isOpened = true;
	}

	public boolean isChecked() {
		return isFlagged || isOpened;
	}

	public boolean isLandMine() {
		return isLandMine;
	}

	public boolean isOpened() {
		return isOpened;
	}

	public boolean hasLandMineCount() {
		return this.nearbyLandMineCount != 0;
	}

	public String getSign() {
		if (isOpened) {
			if (isLandMine) {
				return LAND_MINE_SIGN;
			}
			if (hasLandMineCount()) {
				return String.valueOf(nearbyLandMineCount);
			}
			return EMPTY_SIGN;
		}

		if (isFlagged) {
			return FLAG_SIGN;
		}

		return UNCHECKED_SIGN;
	}
}
