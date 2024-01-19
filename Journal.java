package DistributionSystem;

import java.util.ArrayList;

public class Journal implements java.io.Serializable{
private static final long serialVersionUID = 1L;
private String name;
private String issn;
private int frequency=0;
private double issuePrice;
private ArrayList<Subscription> subscriptions=new ArrayList<>(); //Dikkat et

public Journal(String name,String issn,double issuePrice) {
	this.name=name;
	this.issn=issn;
	this.issuePrice=issuePrice;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getIssn() {
	return issn;
}
public void setIssn(String issn) {
	this.issn = issn;
}
public double getIssuePrice() {
	return issuePrice;
}
public void setIssuePrice(double issuePrice) {
	this.issuePrice = issuePrice;
}

public Subscription getSub() {
	if(frequency<=subscriptions.size()&& frequency>0) {
		return subscriptions.get(frequency-1);
	}else if(frequency==0) {
		System.out.println("Frequency is zero");
		return null;
	}else {
		System.out.println("Error occurred.");
		return null;
	}
}
public int getFrequency() {
	return frequency;
}
public void addSubscription(Subscription sub) {
	if(sub!=null && !subscriptions.contains(sub)) { // eğer abonelik henüz yoksa ve abonelik null değilse gir
		System.out.println("Subscription added1"+sub.getSubscriber().getName());
		subscriptions.add(sub);
		frequency++;
		 // her bir abonelikte frekans artıyor
	}
}
}
