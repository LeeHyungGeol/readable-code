package cleancode.studycafe.tobe2.model;

import java.util.List;

public class StudyCafePasses {

	private final List<StudyCafePass> studyCafePasses;

	private StudyCafePasses(List<StudyCafePass> studyCafePasses) {
		this.studyCafePasses = studyCafePasses;
	}

	public static StudyCafePasses of(List<StudyCafePass> studyCafePasses) {
		return new StudyCafePasses(studyCafePasses);
	}

	public StudyCafePasses findByType(StudyCafePassType passType) {
		return StudyCafePasses.of(studyCafePasses.stream()
			.filter(studyCafePass -> studyCafePass.getPassType() == passType)
			.toList());
	}

	public int getSize() {
		return studyCafePasses.size();
	}

	public StudyCafePass get(int index) {
		return studyCafePasses.get(index);
	}
}
