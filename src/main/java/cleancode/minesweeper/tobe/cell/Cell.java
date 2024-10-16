package cleancode.minesweeper.tobe.cell;

public abstract class Cell {

	// 하위 클래스에서도 사용할 수 있기 때문에 protected 로 변경
	protected static final String FLAG_SIGN = "⚑";
	protected static final String UNCHECKED_SIGN = "□";


	// 하위 클래스에서도 사용할 수 있도록 protected 로 변경
	protected boolean isFlagged;
	protected boolean isOpened;

	// Cell 이 가진 속성: 근처 지뢰 갯수, 지뢰 여부
	// Cell 의 상태: 깃발 유무, 열렸다/닫혔다, 사용자가 확인함

	// 정적 팩토리 메서드를 좋아하는 이유: 메서드에 이름을 줄 수 있다.
	// 정적 팩토리 메서드가 여러개가 된다면 그에 맞는 다른 이름들을 지어줄 수도 있고, 검증과 같은 로직을 추가할 수도 있다.
	// 생성자 하나인 객체라도 정적 팩토리 메서드를 만들어서 생성자를 대체해보자.

	// 지뢰와 관련된 기능
	// 그런데 구현하고 보니 LandMineCell 은 그자체로 landMine 이라는 의미를 갖고 있는데 turnOnLandMine() 으로 켜주는 것이 이상하다.
	// 그리고 해당 기능 때문에 다른 자식 클래스인 EmptyCell, NumberCell 에서는 UnsupportedOperationException 을 던지고 있다.
	// 따라서 해당 기능들을 지워야 한다.
//	public abstract void turnOnLandMine();

	// 이것도 특정 셀에서만 유효하다.
	// 이것도 위와 마찬가지로 NumberCell 에서는 그 자체로 count 를 필드로 갖고 있어야 하지 메서드로 조정할 것이 아니다.
	// 메서드로 조정하다보니 다른 자식 클래스에서 UnsupportedOperationException 을 던지고 있다.
	// 따라서 해당 기능들을 지워야 한다.
//	public abstract void updateNearbyLandMineCount(int count);


	// isOpened, isFlagged 는 Cell 의 공통 기능이므로 그대로 둔다.
	public void flag() {
		this.isFlagged = true;
	}

	public void open() {
		this.isOpened = true;
	}

	public boolean isChecked() {
		return isFlagged || isOpened;
	}

	// 이것도 특정 셀에서만 유효하다.
	public abstract boolean isLandMine();

	public boolean isOpened() {
		return isOpened;
	}

	public abstract boolean hasLandMineCount();

	public abstract String getSign();
}
