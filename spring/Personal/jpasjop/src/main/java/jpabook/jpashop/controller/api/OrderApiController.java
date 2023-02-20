package jpabook.jpashop.controller.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 컬렉션 조회
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    // V1 - 엔티티 직접 노출
    // Hibernate5Module 등록 필요 -> LAZY null 처리
    // @JsonIgnore 필요
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();        // LAZY 강제 초기화
            order.getDelivery().getAddress();   // LAZY 강제 초기화
            List<OrderItem> orderItems = order.getOrderItems();             // LAZY 강제 초기화
            orderItems.stream().forEach(o -> o.getItem().getName());        // LAZY 강제 초기화
        }
        return all;
    }

    // V2 - 엔티티를 Dto로 변환
    // member, delivery, orderItems, item 모두 지연로딩
    // 1 + N + N + N (최악, 모두 다른 경우)
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> result = orders.stream().map(OrderDto::new).collect(Collectors.toList());

        return result;
    }

    @Data
    private static class OrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItemDtos;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItemDtos = order.getOrderItems().stream()
                    .map(OrderItemDto::new).collect(Collectors.toList());
        }
    }

    @Data
    private static class OrderItemDto {

        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

    // V3 - 엔티티를 Dto로 변환 + 페치 조인
    // 퀴리문 한 번만 발생, distinct로 중복 제거 (DB에서는 중복 그대로 전송, 메모리에서 제거)
    // 페이징 불가능 (DB에서는 중복 상태로 있기 때문)
    // 메모리에서 하기는 하나 매우 위험 (OutOfMemory 발생 가능성)
    // 컬렉션 페치 조인은 1개만 사용하자 (2개 이상은 부정합하게 조합될 가능성 있음)
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllwithItem();
        List<OrderDto> result = orders.stream().map(OrderDto::new).collect(Collectors.toList());

        return result;
    }

    // V3.1 - 엔티티를 Dto로 변환 + 페치 조인 + 페이징과 한계돌파
    // ToOne 관계는 페치 조인 + ToMany는 지연로딩 + BatchSize
    // BatchSize를 적용하면 in 구문을 사용해서 size만큼의 쿼리를 한번에 가져옴
    // 페치 조인 1번 + 오더 아이템 1번 (A,B) + 아이템 1번 (bookA,bookB,bookA,bookB)
    // DB 단에서 중복이 없으므로 페이징 가능
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orders_page(@RequestParam(value = "offset", defaultValue = "0") int offset, @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        List<OrderDto> result = orders.stream().map(OrderDto::new).collect(Collectors.toList());

        return result;
    }

    // V4 - JPA에서 Dto 직접 조회
    // new Dto로 member,delivery 직접 조회 + new Dto로 OrderItems 직접 조회
    // member,delivery 조회 1 번 + OrderItem 하나씩 조회 2 번 = 3 번
    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    // V5 - JPA에서 Dto 직접 조회 + 컬렉션 조회 최적화
    // new Dto로 member,delivery 직접 조회 + orderId 리스트 만들어서 OrderItems 조회 시 in 조건에 입력
    // member,delivery 조회 1 번 + OrderItem 조회 1 번 = 2 번
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllDto_optimization();
    }

    // V6 -  JPA에서 DTO로 직접 조회 + 플랫 데이터 최적화
    // 한방에 하는 건데 지금 쓸일은 없을거같다. 나중에 필요하면 찾아보기
    // 중복 데이터를 한번에 들고와서 new Dto에 넣은다음 직접 중복 제거하기
    // 중복 데이터이므로 페이징 불가능
}
