package jpabook.jpashop.controller.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 엔티티 조회
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;
    private final EntityManager em;

    // 엔티티 그대로 반환
    @GetMapping("/api/v1/simple-order")
    public List<Order> orderV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();        // 강제 초기화
            order.getDelivery().getAddress();   // 강제 초기화
        }
        return all;
    }

    // Dto로 변환해서 반환
    @GetMapping("/api/v2/simple-order")
    public List<SimpleOrderDto> orderV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream().map(SimpleOrderDto::new).collect(Collectors.toList());

        return result;
    }

    // Dto로 변환 + 페치 조인
    @GetMapping("/api/v3/simple-order")
    public List<SimpleOrderDto> orderV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream().map(SimpleOrderDto::new).collect(Collectors.toList());

        return result;
    }

    // 페치 조인 하는데 dto로 바로 조회
    @GetMapping("/api/v4/simple-order")
    public List<OrderSimpleQueryDto> orderv4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate; //주문시간
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }

}
