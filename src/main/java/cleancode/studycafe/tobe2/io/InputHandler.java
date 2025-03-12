package cleancode.studycafe.tobe2.io;

import cleancode.studycafe.tobe2.model.StudyCafePass;
import cleancode.studycafe.tobe2.model.StudyCafePassType;
import cleancode.studycafe.tobe2.model.StudyCafePasses;

public interface InputHandler {
	StudyCafePassType getPassTypeSelectingUserAction();

	StudyCafePass getSelectPass(StudyCafePasses studyCafePasses);

	boolean getLockerSelection();
}
