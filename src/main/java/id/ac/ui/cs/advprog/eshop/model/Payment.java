package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import lombok.Getter;
import java.util.UUID;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

@Getter
public class Payment {
    String id;
    String method;
    String status;
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
        this.status = PaymentStatus.WAITING_PAYMENT.getValue();
        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data is null");
        }
        setPaymentData(paymentData);
        try {
            setPaymentData(paymentData);
            setStatus(PaymentStatus.SUCCESS.getValue());
        } catch (IllegalArgumentException e) {
            setStatus(PaymentStatus.REJECTED.getValue());
            throw new IllegalArgumentException();
        }
    }

    void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (method.equals(PaymentMethod.BANK.getValue())) {
            if (paymentData.get("bankName").isBlank() || paymentData.get("referenceCode").isBlank()) {
                throw new IllegalArgumentException("Bank name or reference code is blank");
            }
        } else if (method.equals(PaymentMethod.VOUCHER.getValue())) {
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
