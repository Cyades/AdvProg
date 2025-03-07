package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class PaymentTest {
    private List<Product> products;
    private Map<String, String> paymentData;
    private Order order;

    @BeforeEach
    void setUP(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("DanDan");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("WiWiWi");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "WumWum");

        paymentData = new HashMap<>();
    }


    @Test
    void testCreatePaymentVoucher16Char(){
        paymentData.put("voucher", "ESHOP1234567AA1234");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", order , "VOUCHER", paymentData);
        });
    }


    @Test
    void testCreatePaymentVoucherESHOPFirst(){
        paymentData.put("voucher", "1234567AAESHOP");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", order , "VOUCHER", paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucher8NumChar(){
        paymentData.put("voucher", "ESHOP1234567AA");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "VOUCHER", paymentData);
        });
    }

    @Test
    void testCreatePaymentBankName(){
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "1234567890");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "BANK", paymentData);
        });
    }

    @Test
    void testCreatePaymentBankReferenceCode(){
        paymentData.put("bankName", "BNI");
        paymentData.put("referenceCode", "");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "BANK", paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherSuccess(){
        paymentData.put("voucher", "ESHOP00000000AAA");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "VOUCHER", paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }

    @Test
    void testCreatePaymentBankSuccess(){
        paymentData.put("bankName", "BNI");
        paymentData.put("referenceCode", "1234567890");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "BANK", paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("BANK", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }

    @Test
    void testCreatePaymentNullData(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", order, "BANK", null);
        });
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        Payment payment = new Payment(order.getId(), order, "CANCELLED", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MAWMAW"));
    }

    @Test
    void testPaymentMethodContains() {
        // Test for existing payment methods
        assertTrue(PaymentMethod.contains("BANK"));
        assertTrue(PaymentMethod.contains("VOUCHER"));
        
        // Test for non-existing payment method
        assertFalse(PaymentMethod.contains("CASH"));
        assertFalse(PaymentMethod.contains("CREDIT_CARD"));
        assertFalse(PaymentMethod.contains(null));
        assertFalse(PaymentMethod.contains(""));
    }
}
