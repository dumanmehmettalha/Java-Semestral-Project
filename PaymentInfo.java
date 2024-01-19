package DistributionSystem;

public class PaymentInfo {
private final double discountRatio=0;//değişebilir
private double receivedPayment=0;
public void increasePayment(double amount) {
	this.receivedPayment+=amount;
}
public double getReceivedPayment() {
	return receivedPayment;
}

}
