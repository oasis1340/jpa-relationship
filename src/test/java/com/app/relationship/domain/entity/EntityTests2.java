package com.app.relationship.domain.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j @Transactional @Commit
class EntityTests2 {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    void productInsertTest(){
        Member member = new Member();
        member.setMemberName("홍길동");
        member.setMemberAddress("서울시 강남구 역삼동");
        entityManager.persist(member);

        Product product = new Product();
        product.setProductName("아이폰");
        product.setProductPrice(10_000_000);
        product.setProductStock(100);
        product.setMember(member);
        entityManager.persist(product);
    }

    @Test
    void orderSaveTest(){
        Member member = new Member();
        member.setMemberName("홍길동");
        member.setMemberAddress("서울시 강남구 역삼동");
        entityManager.persist(member);

        Product product = new Product();
        product.setProductName("아이폰");
        product.setProductPrice(10_000_000);
        product.setProductStock(100);
        product.setMember(member);
        entityManager.persist(product);

        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
        order.setAmount(10);
        entityManager.persist(order);
    }

    @Test
    public void orderFindTest(){
        String query = "SELECT m FROM Member m JOIN m.orders o WHERE m.id = :id";
        List<Member> member = entityManager.createQuery(query, Member.class).setParameter("id", 1002L).getResultList();

//        그래프 탐색
        List<Product> orderedProductList = member.stream().flatMap((m) -> m.getOrders().stream()).map(Order::getProduct).toList();
        log.info("주문 된 상품: {}", orderedProductList.toString());
    }


}