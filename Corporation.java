package DistributionSystem;

public class Corporation extends Subscriber{
private int bankCode;
private String bankName;
private int issueDay,issueMonth,issueYear;
private int accountNumber;
public Corporation(String name, String address) {
	super(name, address);
	this.bankCode=1;
	this.bankName="ABank";
	this.accountNumber=bankCode+7;
}
public String getBillingInformation() {
	String str="Bank name: "+bankName +" Bank code: "+bankCode +" Name: "+ super.getName()
			+" Account No: "+accountNumber; 
	return str;
}
}
