package cleancode.studycafe.tobe2.model;

import cleancode.studycafe.tobe2.io.InputHandler;
import cleancode.studycafe.tobe2.io.OutputHandler;
import cleancode.studycafe.tobe2.io.StudyCafePassReader;

public class WeeklyStudyCafeProcessor implements StudyCafeProcessor{
	private final StudyCafePassReader studyCafePassReader;
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;

	public WeeklyStudyCafeProcessor(StudyCafePassReader studyCafePassReader, InputHandler inputHandler, OutputHandler outputHandler) {
		this.studyCafePassReader = studyCafePassReader;
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
	}

	@Override
	public boolean satisfiedBy(StudyCafePassType passType) {
		return passType == StudyCafePassType.WEEKLY;
	}

	@Override
	public void process() {
		StudyCafePasses studyCafePasses = studyCafePassReader.readStudyCafePasses();
		StudyCafePasses weeklyPasses = studyCafePasses.findByType(StudyCafePassType.WEEKLY);
		outputHandler.showPassListForSelection(weeklyPasses);
		StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
		outputHandler.showPassOrderSummary(selectedPass, null);
	}
}
