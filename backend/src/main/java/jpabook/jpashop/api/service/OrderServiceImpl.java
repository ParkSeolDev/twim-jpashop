package jpabook.jpashop.api.service;

import jpabook.jpashop.api.exception.UserNotFoundException;
import jpabook.jpashop.db.entity.*;
import jpabook.jpashop.db.entity.item.Item;
import jpabook.jpashop.db.repository.ItemRepository;
import jpabook.jpashop.db.repository.OrderRepository;
import jpabook.jpashop.db.repository.OrderRepositorySupport;
import jpabook.jpashop.db.repository.OrderSearch;
import jpabook.jpashop.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositorySupport orderRepositorySupport;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(String userId, Long itemId, int count) {

        //엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Item item = itemRepository.findById(itemId).get();

        //배송정보 생성
        Delivery delivery = Delivery.create(user.getAddress(), DeliveryStatus.READY);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(user, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId).get();
        //주문 취소
        order.cancel();
    }

    //검색
    @Transactional(readOnly = true)
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepositorySupport.findAllByString(orderSearch);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders(){
        return orderRepositorySupport.findAll();
    }
}

