package cleancode.studycafe.tobe2.model;

import cleancode.minesweeper.tobe.io.OutputHandler;
import cleancode.studycafe.tobe2.exception.AppException;
import cleancode.studycafe.tobe2.io.ConsoleInputHandler;
import cleancode.studycafe.tobe2.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe2.io.InputHandler;
import cleancode.studycafe.tobe2.io.StudyCafeLockerPassReader;
import cleancode.studycafe.tobe2.io.StudyCafePassReader;
import cleancode.studycafe.tobe2.io.file.StudyCafeLockerPassFileReader;
import cleancode.studycafe.tobe2.io.file.StudyCafePassFileReader;

import java.util.List;

public class LocalStudyCafeProcessorFactory implements StudyCafeProcessorFactory{
	private static final StudyCafePassReader studyCafePassReader = new StudyCafePassFileReader();
	private static final StudyCafeLockerPassReader studyCafeLockerPassReader = new StudyCafeLockerPassFileReader();
	private static final InputHandler inputHandler = new ConsoleInputHandler();
	private static final OutputHandler outputHandler = new ConsoleOutputHandler();

	private static final List<StudyCafeProcessor> processors = List.of(
		new HourlyStudyCafeProcessor(studyCafePassReader, inputHandler, outputHandler),
		new WeeklyStudyCafeProcessor(studyCafePassReader, inputHandler, outputHandler),
		new FixedStudyCafeProcessor(studyCafePassReader, studyCafeLockerPassReader, inputHandler, outputHandler)
	);

	public LocalStudyCafeProcessorFactory() {

	}

	@Override
	public StudyCafeProcessor createProcessor(StudyCafePassType passType) {
		return processors.stream()
			.filter(processor -> processor.satisfiedBy(passType))
			.findFirst()
			.orElseThrow(() -> new AppException("해당 이용권을 처리할 수 있는 프로세서가 없습니다."));
	}
}
