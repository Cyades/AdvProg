package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import lombok.Getter;
import java.util.UUID;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this(order, method, paymentData);
        this.id = id;
    }

    public Payment(Order order, String method, Map<String, String> paymentData) {
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.order = order;
        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data is null");
        }
        setPaymentData(paymentData);
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (method.equals("BANK")) {
            if (paymentData.get("bankName").isBlank() || paymentData.get("referenceCode").isBlank()) {
                throw new IllegalArgumentException("Bank name or reference code is blank");
            }
        } else if (method.equals("VOUCHER")) {
            int count = 0;
            for (int i = 0; i < paymentData.get("voucher").length(); i++) {
                if (Character.isDigit(paymentData.get("voucher").charAt(i))) {
                    count++;
                }
            }
            if (paymentData.get("voucher").length() != 16 || !paymentData.get("voucher").startsWith("ESHOP") || count != 8) {
                throw new IllegalArgumentException("Voucher not supported");
            }
        }
        this.paymentData = paymentData;
    }
}
