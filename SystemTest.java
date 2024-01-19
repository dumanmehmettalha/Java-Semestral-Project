package DistributionSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SystemTest {

	@Test
	void addSubscriberIndividualTest() {
		Subscriber s= new Individual("Ahmet", "Konya");
		var dist= new Distributor();
		assertTrue(dist.addSubscriber(s));
	}
	@Test
	void addSubscriberCorporationTest() {
		var dist = new Distributor();
		Subscriber s= new Corporation("YTÜ","İstanbul");
		assertTrue(dist.addSubscriber(s));
	}
	@Test
	void addJournalTest() {
		var j=new Journal("AJournal","1234",12.2);
		var dist=new Distributor();
		assertTrue(dist.addJournal(j));
	}
	@Test
	void searchJournalTest() {
		var j=new Journal("AJournal","1234",12.2);
		var dist=new Distributor();
		dist.addJournal(j);
		assertTrue(j==dist.searchJournal("1234"));
	}
	@Test
	void searchJournalFalseTest() {
		var dist= new Distributor();
		var j=new Journal("BJournal","1222",21.3);
		String issn="1233";
		assertFalse(j==dist.searchJournal(issn));
	}
	@Test
	void searchSubscriberTrueTest() {
		Subscriber s= new Individual("İsa", "Bursa");
		var dist= new Distributor();
		dist.addSubscriber(s);
		assertTrue(dist.searchSubscriber("İsa")==s);
	}
	@Test
	void searchSubscriberFalseTest() {
		Subscriber s= new Individual("İsa", "Bursa");
		Subscriber s1= new Corporation("Ytü","Beşiktaş");
		var dist= new Distributor();
		dist.addSubscriber(s);
		dist.addSubscriber(s1);
		assertFalse(dist.searchSubscriber("Ytü")==s);
	}
	@Test
	void addSubscriptionDistributor() {
		var j=new Journal("AJournal","1234",12);
		var date= new DateInfo();
		var s= new Individual("Talha","Bursa");
		var subs=new Subscription(date,1,j,s);
		var dist= new Distributor();
		dist.addJournal(j);
		dist.addSubscriber(s);
		assertTrue(dist.addSubscription(j.getIssn(), s, subs));
	}
	@Test
	void addSubscriptionJournalTestgetSub() {
		var j=new Journal("AJournal","1234",12);
		var date= new DateInfo();
		var s= new Individual("Talha","İstanbul");
		var subs=new Subscription(date,1,j,s);
		j.addSubscription(subs);
		assertTrue(j.getSub()==subs);
		//assertTrue(freq1==j.getFrequency()-1);
	}
	@Test
	void addSubscriptionJournalTestFreq() {
		var j=new Journal("AJournal","1234",12);
		var date= new DateInfo();
		var s= new Individual("Talha","İstanbul");
		var subs=new Subscription(date,1,j,s);
		int freq1;
		freq1=j.getFrequency();
		j.addSubscription(subs);
		assertTrue(freq1==j.getFrequency()-1);
	}
	@Test
	void acceptPaymentTest() {
		var j=new Journal("AJournal","1234",12);
		var date= new DateInfo();
		var s= new Individual("Talha","İstanbul");
		var subs=new Subscription(date,1,j,s);
		subs.acceptPayment(13);
		assertTrue(subs.getPayment().getReceivedPayment()==13);
	}
	@Test 
	void canSendTrueTest() {
		var j=new Journal("AJournal","1234",12);
		var date= new DateInfo();
		date.setStartMonth(1);
		date.setStartYear(2024);
		var s= new Individual("Talha","İstanbul");
		var subs=new Subscription(date,1,j,s);
		subs.acceptPayment(13);
		assertTrue(subs.canSend(3));
	}
	@Test
	void canSendFalseTest() {
		var j=new Journal("AJournal","1234",12);
		var date= new DateInfo();
		date.setStartMonth(1);
		date.setStartYear(2024);
		var s= new Individual("Talha","İstanbul");
		var subs=new Subscription(date,1,j,s);
		subs.acceptPayment(11);
		assertFalse(subs.canSend(3));
	}
	@Test
	void increasePaymentTest() {
		var s=new Individual("Ahmet","Kars");
		var j=new Journal("AJournal","1234",12);
		var date= new DateInfo();
		var subs=new Subscription(date,1,j,s);
		subs.getPayment().increasePayment(15);
		assertTrue(subs.getPayment().getReceivedPayment()==15);
	}

	
}
