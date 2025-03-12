package cleancode.studycafe.tobe2.io;

import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.studycafe.tobe2.model.StudyCafePass;
import cleancode.studycafe.tobe2.model.StudyCafePassType;
import cleancode.studycafe.tobe2.model.StudyCafePasses;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
	private static final Scanner SCANNER = new Scanner(System.in);

	public StudyCafePassType getPassTypeSelectingUserAction() {
		String userInput = SCANNER.nextLine();
		return StudyCafePassType.findByUserAction(userInput);
	}

	public StudyCafePass getSelectPass(StudyCafePasses studyCafePasses) {
		String userInput = SCANNER.nextLine();
		int selectedIndex = Integer.parseInt(userInput) - 1;
		return studyCafePasses.get(selectedIndex);
	}

	public boolean getLockerSelection() {
		String userInput = SCANNER.nextLine();
		return "1".equals(userInput);
	}
}
