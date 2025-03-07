package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData;

    public Payment save(Payment payment) {
        // Implementasi penyimpanan
    }

    public Payment findById(String id) {
        // Implementasi pencarian berdasarkan ID
    }

    public List<Payment> getAllPayments() {
        // Implementasi mendapatkan semua pembayaran
    }
}