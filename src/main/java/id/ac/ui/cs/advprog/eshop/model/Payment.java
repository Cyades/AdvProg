package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import lombok.Getter;
import java.util.UUID;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        // Constructor implementation
    }

    public Payment(Order order, String method, Map<String, String> paymentData) {
        // Constructor implementation
    }

    void setStatus(String status) {
        // Method implementation
    }

    public void setPaymentData(Map<String, String> paymentData) {
        // Method implementation
    }
}
