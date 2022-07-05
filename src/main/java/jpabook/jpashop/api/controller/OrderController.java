package jpabook.jpashop.api.controller;

import io.swagger.annotations.Api;
import jpabook.jpashop.api.service.ItemService;
import jpabook.jpashop.api.service.JwtService;
import jpabook.jpashop.api.service.OrderService;
import jpabook.jpashop.api.service.UserService;
import jpabook.jpashop.db.entity.Order;
import jpabook.jpashop.db.entity.User;
import jpabook.jpashop.db.entity.item.Item;
import jpabook.jpashop.db.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "주문 API", tags = {"Order"})
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    @Autowired
    private JwtService jwtService;

    @PostMapping()
    public ResponseEntity<String> order(@RequestParam("userId") String userId,
                                        @RequestParam("itemId") Long itemId,
                                        @RequestParam("count") int count, HttpServletRequest request) {
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
                orderService.order(userId, itemId, count);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                return new ResponseEntity<String>(FAIL, status);
            }
        }

        return new ResponseEntity<String>(SUCCESS, status);
    }

    @PostMapping("order-list")
    public ResponseEntity<List<Order>> orderList(@RequestBody OrderSearch orderSearch, HttpServletRequest request) {

        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
                List<Order> orders = orderService.findOrders(orderSearch);
                status = HttpStatus.ACCEPTED;
                return new ResponseEntity<>(orders, status);
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                return new ResponseEntity<>(null, status);
            }
        }
        return new ResponseEntity<>(null, status);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}