package id.ac.ui.cs.advprog.eshop.model;


import java.util.Map;

public class PaymentVoucher extends  Payment{
    public PaymentVoucher(Order order, String method, Map<String, String>paymentData){
        super(order, method,paymentData);
    }
    public PaymentVoucher(String id, Order order, String method, Map<String, String>paymentData){
        super(id, order, method,paymentData);
    }
    @Override
        public void setPaymentData(Map<String, String> paymentData) {
            int numOfNumerics = 0;
            for (int i=0; i<paymentData.get("voucher").length(); i++){
                if (Character.isDigit(paymentData.get("voucher").charAt(i))){
                    numOfNumerics+=1;
                }
            }
            if (paymentData.get("voucher").length()!=16 ||
                    !paymentData.get("voucher").startsWith("ESHOP") ||
                    numOfNumerics!=8){
                throw new IllegalArgumentException();
            }
            this.paymentData=paymentData;
        }
}