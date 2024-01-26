package DistributionSystem;

public abstract class Subscriber implements java.io.Serializable{
private static final long serialVersionUID = 1L;
private String name,address;
public Subscriber(String name,String address) {
	this.name=name;
	this.address=address;
}
public String getName() {
	return name;
}
public String getAddress() {
	return address;
}
public abstract String getBillingInformation();
}
