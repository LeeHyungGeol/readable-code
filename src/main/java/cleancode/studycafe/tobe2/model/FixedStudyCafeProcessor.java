package cleancode.studycafe.tobe2.model;

import cleancode.studycafe.tobe2.io.InputHandler;
import cleancode.studycafe.tobe2.io.OutputHandler;
import cleancode.studycafe.tobe2.io.StudyCafeLockerPassReader;
import cleancode.studycafe.tobe2.io.StudyCafePassReader;

public class FixedStudyCafeProcessor implements StudyCafeProcessor {
	private final StudyCafePassReader studyCafePassReader;
	private final StudyCafeLockerPassReader studyCafeLockerPassReader;
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;

	public FixedStudyCafeProcessor(StudyCafePassReader studyCafePassReader, StudyCafeLockerPassReader studyCafeLockerPassReader, InputHandler inputHandler, OutputHandler outputHandler) {
		this.studyCafePassReader = studyCafePassReader;
		this.studyCafeLockerPassReader = studyCafeLockerPassReader;
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
	}

	private static boolean isExists(StudyCafeLockerPass lockerPass) {
		return lockerPass != null;
	}

	@Override
	public boolean satisfiedBy(StudyCafePassType passType) {
		return passType == StudyCafePassType.FIXED;
	}

	@Override
	public void process() {
		StudyCafePasses studyCafePasses = studyCafePassReader.readStudyCafePasses();
		StudyCafePasses fixedStudyCafePasses = studyCafePasses.findByType(StudyCafePassType.FIXED);

		outputHandler.showPassListForSelection(fixedStudyCafePasses);
		StudyCafePass selectedPass = inputHandler.getSelectPass(fixedStudyCafePasses);

		StudyCafeLockerPasses lockerPasses = studyCafeLockerPassReader.readLockerPasses();
		StudyCafeLockerPass lockerPass = lockerPasses.getLockerPassByTypeAndDuration(selectedPass);

		boolean lockerSelection = false;
		if (isExists(lockerPass)) {
			outputHandler.askLockerPass(lockerPass);
			lockerSelection = inputHandler.getLockerSelection();
		}

		if (lockerSelection) {
			outputHandler.showPassOrderSummary(selectedPass, lockerPass);
		} else {
			outputHandler.showPassOrderSummary(selectedPass, null);
		}
	}
}
