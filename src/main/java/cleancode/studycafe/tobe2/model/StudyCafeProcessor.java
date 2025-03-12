package cleancode.studycafe.tobe2.model;

public interface StudyCafeProcessor {
	boolean satisfiedBy(StudyCafePassType passType);
	void process();
}
