package cleancode.studycafe.tobe2.model;

import java.util.List;

public class StudyCafeLockerPasses {
	private final List<StudyCafeLockerPass> studyCafeLockerPasses;

	private StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
		this.studyCafeLockerPasses = studyCafeLockerPasses;
	}

	public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPasses) {
		return new StudyCafeLockerPasses(studyCafeLockerPasses);
	}

	public StudyCafeLockerPass getLockerPassByTypeAndDuration(StudyCafePass studyCafePass) {
		return studyCafeLockerPasses.stream()
			.filter(option ->
				option.getPassType() == studyCafePass.getPassType()
					&& option.getDuration() == studyCafePass.getDuration()
			)
			.findFirst()
			.orElse(null);
	}
}
