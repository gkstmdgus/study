package jpabook.jpasjop.service;

import jpabook.jpasjop.domain.Delivery;
import jpabook.jpasjop.domain.Member;
import jpabook.jpasjop.domain.Order;
import jpabook.jpasjop.domain.OrderItem;
import jpabook.jpasjop.domain.item.Item;
import jpabook.jpasjop.repository.ItemRepository;
import jpabook.jpasjop.repository.MemberRepository;
import jpabook.jpasjop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderitem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderitem);

        // 주문 저장
        // delivery, orderitem을 cascade해서 따로 영속안해도 order랑 같이 영속화 됨
        // 지금같이 order말고 다른 관계가 없는 경우만 cascade 사용
        orderRepository.save(order);

        return order.getId();
    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    // 검색
/*
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
    */
}
