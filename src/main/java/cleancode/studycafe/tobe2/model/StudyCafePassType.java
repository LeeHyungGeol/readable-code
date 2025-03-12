package cleancode.studycafe.tobe2.model;

import cleancode.studycafe.tobe2.exception.AppException;

public enum StudyCafePassType {


    HOURLY("1", "시간 단위 이용권"),
    WEEKLY("2", "주 단위 이용권"),
    FIXED("3", "1인 고정석");


    private final String userAction;
    private final String description;


    StudyCafePassType(String userAction, String description) {
        this.userAction = userAction;
        this.description = description;
    }


    public static StudyCafePassType findByUserAction(String userAction) {
        for (StudyCafePassType passType : values()) {
            if (passType.userAction.equals(userAction)) {
                return passType;
            }
        }
        throw new AppException("잘못된 입력입니다.");
    }
}
