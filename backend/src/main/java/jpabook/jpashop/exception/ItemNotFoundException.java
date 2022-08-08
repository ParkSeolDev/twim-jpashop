package jpabook.jpashop.exception;

public class ItemNotFoundException extends RuntimeException {

    public static final String ITEM_NOT_FOUND_MESSAGE = "아이템이 존재하지 않습니다 : %s";

    public ItemNotFoundException(Long itemId) {
        super(String.format(ITEM_NOT_FOUND_MESSAGE, itemId));
    }

}
