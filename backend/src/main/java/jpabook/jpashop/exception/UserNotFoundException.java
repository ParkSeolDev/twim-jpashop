package jpabook.jpashop.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String USER_NOT_FOUND_MESSAGE = "회원이 존재하지 않습니다 : %s";

    public UserNotFoundException(String userId) {
        super(String.format(USER_NOT_FOUND_MESSAGE, userId));
    }

}
