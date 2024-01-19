package DistributionSystem;

public class DateInfo implements java.io.Serializable {
private static final long serialVersionUID = 1L;
private int startMonth;
private int endMonth;
private int startYear;
public DateInfo() {
	//
}
public DateInfo(int startMonth,int startYear){
	this.startMonth=startMonth;
	this.startYear=startYear;
	if(startMonth==1) {
		endMonth=12;
	}
	endMonth=startMonth-1;

}
public int getStartMonth() {
	return startMonth;
}
public void setStartMonth(int startMonth) {
	this.startMonth = startMonth;
	if(startMonth==1) {
		endMonth=12;
	}else {
		endMonth=startMonth-1;
	}
}
public int getEndMonth() {
	return endMonth;
}
public int getStartYear() {
	return startYear;
}
public void setStartYear(int startYear) {
	this.startYear = startYear;
}

}
