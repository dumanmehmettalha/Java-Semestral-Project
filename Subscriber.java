package DistributionSystem;

public abstract class Subscriber {
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
