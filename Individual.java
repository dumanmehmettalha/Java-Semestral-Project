package DistributionSystem;

public class Individual extends Subscriber implements java.io.Serializable {
private static final long serialVersionUID = 1L;
private String creditCardNr;
private int expiremonth, expireYear;
private int CCV;
public Individual(String name, String address) {
	super(name, address);
	this.creditCardNr="2000 1000 0000 9000";
}
public String getBillingInformation() {
	String str;
	str="Name: "+ super.getName()+ " Address: "+ super.getAddress()+" Credit Card No: "+ creditCardNr;
	return str;
}
}
