package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @Spy
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp(){
        orders = new ArrayList<>();
        payments = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("f7a3d6c2-8e4b-49f1-92d5-bc1e73a8f5d9");
        product1.setProductName("AWOOGA");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order = new Order("e9b4c7d2-5a3f-48e1-86d5-7c1f92a6b3d8", products, 1708560000L, "UwaUwa");
        orders.add(order);

        payments = new ArrayList<>();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucher","ESHOP11111111ZZZ");
        Payment payment1 = new Payment( order, "VOUCHER", paymentData );
        payments.add(payment1);
        paymentData = new HashMap<>();
        paymentData.put("bankName","BRI");
        paymentData.put("referenceCode","7897");
        Payment payment2 = new Payment(order, "BANK", paymentData);
        payments.add(payment2);
    }

    @Test
    void testAddPayment(){
        Payment payment1 = payments.get(0);
        doReturn(payment1).when(paymentRepository).save(any(Payment.class));
        payment1 = paymentService.addPayment(payment1.getOrder(), "VOUCHER", payment1.getPaymentData());

        Payment payment2 = payments.get(1);
        doReturn(payment2).when(paymentRepository).save(any(Payment.class));
        payment2 = paymentService.addPayment(payment2.getOrder(), "BANK", payment2.getPaymentData());

        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment findResult = paymentService.getPayment(payment1.getId());

        assertEquals(payment1.getId(),findResult.getId() );
        assertEquals(payment1.getMethod(), findResult.getMethod() );
        assertEquals(payment1.getStatus(), findResult.getStatus() );

        doReturn(payment2).when(paymentRepository).findById(payment2.getId());
        findResult = paymentService.getPayment(payment2.getId());

        assertEquals(payment2.getId(),findResult.getId() );
        assertEquals(payment2.getMethod(), findResult.getMethod() );
        assertEquals(payment2.getStatus(), findResult.getStatus() );
        verify(paymentService, times(1)).createPaymentVoucher(any(Order.class), any(String.class), anyMap());
        verify(paymentService, times(1)).createPaymentBank(any(Order.class), any(String.class), anyMap());
    }

    @Test
    void testGetPaymentIfFound(){
        Payment payment1 = payments.get(0);
        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment paymentFound = paymentService.getPayment(payment1.getId());
        assertEquals(payment1.getId(), paymentFound.getId());
        assertEquals("VOUCHER",paymentFound.getMethod());
        assertEquals(payment1.getStatus(), paymentFound.getStatus());
    }

    @Test
    void testGetPaymentIfNotFound(){
        doReturn(null).when(paymentRepository).findById("UIIA");
        Payment payment = paymentService.getPayment("UIIA");
        assertNull(payment);
    }

    @Test
    void testGetAllPayment(){
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> payment = paymentService.getAllPayment();
        assertNotNull(payment);
        assertEquals(payments.size(), payment.size());
        assertEquals(payments, payment);
    }

    @Test
    void testSetStatusSuccessful(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucher","ESHOP00000000AAA");
        Payment payment1 = new Payment(orders.get(0), "", paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(),payment1.getStatus());
        paymentService.setStatus(payment1, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payment1.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment1.getOrder().getStatus());

        paymentService.setStatus(payment1, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(),payment1.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment1.getOrder().getStatus());
    }

    @Test
    void testSetStatusFail(){
        Payment payment1 = payments.get(0);
        assertThrows(IllegalArgumentException.class, ()->
                paymentService.setStatus(payment1, "MIAW")
        );
    }


}