package com.example.demo.lectureClass.testCode.order.entity;

import com.example.demo.lectureClass.testCode.account.entity.consumer.TestConsumerAccount;
import com.example.demo.lectureClass.testCode.product.entity.TestProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class TestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @Getter
    private TestConsumerAccount testConsumerAccount;

    @ManyToOne
    @Getter
    private TestProduct testProduct;

    public TestOrder(TestConsumerAccount testConsumerAccount, TestProduct testProduct) {
        this.testConsumerAccount = testConsumerAccount;
        this.testProduct = testProduct;
    }
}
