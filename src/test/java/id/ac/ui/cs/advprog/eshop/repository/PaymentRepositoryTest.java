package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private List<Payment> payments;
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();
        orders = new ArrayList<>();
        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("a7d3f4e2-5b8c-42f1-9e6d-c1e29b7a8c55");
        product1.setProductQuantity(2);
        product1.setProductName("WiWiWi");
        products.add(product1);

        Order order1 = new Order("f9a47c88-3d6e-4e21-95b2-7c5d8e1a2b34", products, 1708560000L, "MAWMAW");
        orders.add(order1);

        Map<String, String> emptyPaymentData = new HashMap<>();
        Payment payment1 = new Payment("b7f6d2c1-4e9a-48b3-85d7-3c2e8f5a9d12", orders.get(0), "", emptyPaymentData);
        Payment payment2 = new Payment("c4d8f9b2-6e3a-41d5-97c1-8b5e2a7f3d64", orders.get(0), "", emptyPaymentData);
        payments.add(payment1);
        payments.add(payment2);

        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucher", "ESHOP87654321UIA");
        Payment paymentWithVoucher = new Payment("d5c9b8a3-7e6f-42d1-85c4-9f2e7a3b6d81", orders.get(0), "VOUCHER", paymentDataVoucher);
        payments.add(paymentWithVoucher);

        Map<String, String> paymentDataBank = new HashMap<>();
        paymentDataBank.put("bankName", "BCA");
        paymentDataBank.put("referenceCode", "0");
        Payment paymentWithBank = new Payment("f3b8c8c8-1b2e-4d5f-83a1-dfc638382dd3", orders.get(0), "BANK", paymentDataBank);
        payments.add(paymentWithBank);
    }

    @Test
    void testAddPaymentVoucherSuccess() {
        Payment payment = payments.get(2);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payments.get(2).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentSuccess() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentDuplicatedId() {
        Payment payment1 = payments.get(1);
        Payment result = paymentRepository.save(payment1);
        Payment payment2 = new Payment(payment1.getId(), payment1.getOrder(), payment1.getMethod(), payment1.getPaymentData());
        assertThrows(IllegalStateException.class, () -> {
            paymentRepository.save(payment2);
        });
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertSame(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        assertNull(paymentRepository.findById("UIIA"));
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        List<Payment> result = paymentRepository.getAllPayments();
        assertEquals(4, result.size());
    }
}