package DistributionSystem;

public class PaymentInfo implements java.io.Serializable{
private static final long serialVersionUID = 1L;
private final double discountRatio=0;//değişebilir
private double receivedPayment=0;
public void increasePayment(double amount) {
	this.receivedPayment+=amount;
}
public double getReceivedPayment() {
	return receivedPayment;
}

}
