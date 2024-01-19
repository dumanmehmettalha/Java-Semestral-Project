package DistributionSystem;

public class Subscription implements java.io.Serializable {
private static final long serialVersionUID = 1L;
private final DateInfo dates;
private final Journal journal;
private final Subscriber subscriber;
private PaymentInfo payment=new PaymentInfo();
private int copies=0;

public Subscription(DateInfo dates,int copies,Journal journal,Subscriber subscriber)  {
	this.dates=dates;
	this.copies=copies;
	this.journal=journal;
	this.subscriber=subscriber;
}
public void acceptPayment(double amount) {
	System.out.println("Payment accepted "+ amount);
	payment.increasePayment(amount);
}
public boolean canSend(int issueMonth) {
	if(dates.getEndMonth()>=issueMonth) {
		if(payment.getReceivedPayment()>=(journal.getIssuePrice()*copies)) {
			return true;
		}else {
			return false;
		}
	}
	return false;
}
public Subscriber getSubscriber() {
	return subscriber;
}
public int getCopies() {// otomatik oluşturuldu belki de silinmeli
	return copies;
}
public void setCopies(int copies) {
	this.copies = copies;
}
public Journal getJournal() {
	return journal;
}
public DateInfo getDates() { //yeni eklendi
	return dates;
}
public PaymentInfo getPayment() { //yeni eklendi
	return payment;
}
}
