package jpabook.jpashop.api.service;

import jpabook.jpashop.db.entity.*;
import jpabook.jpashop.db.entity.item.Item;
import jpabook.jpashop.db.repository.OrderSearch;
import jpabook.jpashop.exception.ItemNotFoundException;
import jpabook.jpashop.exception.UserNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    Long order(String userId, Long itemId, int count);
    void cancelOrder(Long orderId);

    List<Order> findOrders(OrderSearch orderSearch);

    List<Order> findAllOrders();
}
