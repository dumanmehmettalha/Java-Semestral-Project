package DistributionSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Vector;

public class Distributor implements java.io.Serializable{ 
private static final long serialVersionUID = 1L;
private Hashtable<String,Journal> journals= new Hashtable<>();
private Vector<Subscriber> subscribers=new Vector<>();
public boolean addJournal(Journal j) {
	if(!journals.containsValue(j)) {
		journals.put(j.getIssn(), j); 
		System.out.println("Add Journal Return true");
		return true;
	}
	else {
		return false;
	}
}
public Journal searchJournal(String issn) {
	for(Journal j: journals.values()) {
		if(issn.equals(j.getIssn())) { 
			return j;
		}
	}
	return null;
	
}
public boolean addSubscriber(Subscriber s) {
	if(!subscribers.contains(s)) {
		subscribers.add(s);
		System.out.println("addSubscriber returns true");
		return true;
	}
	return false;
}
public Subscriber searchSubscriber(String name) {
	for(Subscriber s:subscribers) {
		if(name.equals(s.getName())) {
			return s;
		}
	}
	return null;
}
public boolean addSubscription(String issn,Subscriber s,Subscription sub) {
	Journal j= searchJournal(issn);
	if(sub.getSubscriber()==s && sub.getJournal()==j) {// verilen s ve verilen j Subscription içinde tanımlı ise 
		sub.setCopies(sub.getCopies()+1);
		System.out.println("copy incremented for "+s.getName());
		j.addSubscription(sub);
		return true;
	}
	else if(j!=null && searchSubscriber(s.getName())!=null) {
		if(sub.getSubscriber()==s) {
			j.addSubscription(sub); // may be incorrect !!!!!!!
			System.out.println("subscription added2"+s.getName());
			return true;
		}
	}	return false;
}
public String listAllSendingOrders(int month,int year) {
	int endM,endY;
	String str="-----------------------List All Sending Orders-----------------------------\n";
	for(Journal j:journals.values()) {
		endM=j.getSub().getDates().getEndMonth();
		endY=j.getSub().getDates().getStartYear()+1;
		if(month<endM && year<=endY) {
			System.out.println(j.getSub().getSubscriber().getName()+" has ordered "+j.getName()+".");
			str+=j.getSub().getSubscriber().getName()+" has ordered "+j.getName()+".";
		}
	}
	return str;
}
public String listSendingOrders(String issn,int month,int year) {
	String str="---------------------List Sending Orders by Issn, Month and Year-----------------------\n";
	Journal j=searchJournal(issn);
	Subscription subs=j.getSub(); 
	if(subs!=null && subs.canSend(month)) {
		System.out.println(subs.getSubscriber().getName()+" has ordered "+subs.getCopies()+" copy/copies of "+j.getName()+".");
		str+=subs.getSubscriber().getName()+" has ordered "+subs.getCopies()+" copy/copies of "+j.getName()+".";
	}
	return str;
}
public String listIncompletePayments() {
	String str="------------------List Incomplete Payments-------------------\n";
	for(Journal j:journals.values()) {
		if(j.getSub()!=null) {
			if(!(j.getSub().canSend(j.getSub().getDates().getStartMonth()))) { //eğer canSend false döndürürse gir
			System.out.println("Subscriber "+j.getSub().getSubscriber().getName()+" did not complete payment of Journal "+j.getName());
			str+="Subscriber "+j.getSub().getSubscriber().getName()+" did not complete payment of Journal "+j.getName()+".\n";
			}
		}	
	}
	return str;
}
public String listSubscriptions(String subscriberName) {
	Subscriber s=searchSubscriber(subscriberName);
	String str="-------List Subscriptions by Name--------\n";
	for(Journal j:journals.values()) {
		if(j.getSub().getSubscriber()==s) {
			System.out.println(s.getName()+" subscribed to "+j.getName());
			str+=s.getName()+" subscribed to "+j.getName()+".\n";
		}
	}
	return str;
}

public String listSubscriptionsByIssn(String issn) {
	String str="----------List Subscriptions by Issn--------\n";
	int flag=0;
    for (Journal j : journals.values()) {
        if (issn.equals(j.getIssn()) && j.getSub()!=null) {
        	if(j.getSub().getPayment().getReceivedPayment()>=j.getIssuePrice()){
        		str+=j.getSub().getSubscriber().getName() + " subscribed to " + j.getName()+".\n";
        		System.out.println(j.getSub().getSubscriber().getName() + " subscribed to " + j.getName()+".\n");
        		flag=1;
        	}
        }
    }
    if(flag==0) {
    	str+="If you don't see the subscriptions you have created,\nyou may forget to increase payment.";
    	}
    return str;
}
	

public void saveState(String filename) {
    try {
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));
        writer.writeObject(journals);
        System.out.println("Writing process is completed.");
        writer.close();
    } catch (FileNotFoundException e) {
        System.out.println("File Not Found.");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("IO exception occurred.");
        e.printStackTrace();
    }
}

@SuppressWarnings("unchecked")
public void loadState(String filename) {
    try {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filename));
		Hashtable<String,Journal> journals= (Hashtable<String,Journal>)reader.readObject();
        System.out.println("Reading process is completed.");
        reader.close();
    } catch (ClassNotFoundException c) {
        System.out.println("Journal class not found");
        c.printStackTrace();
    } catch (FileNotFoundException e) {
        System.out.println("File Not Found.");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("IO exception occurred.");
        e.printStackTrace();
    }
}

public String report() {
	String str = "Deneme \n";
	for(Journal j:journals.values()) {
		if(j.getSub()!=null) {
		str+=j.getSub().getSubscriber().getName()+" has ordered "+j.getSub().getCopies()+" of "+j.getName()
		+" which will available till "+j.getSub().getDates().getEndMonth()+"th month of "+j.getSub().getDates().getStartYear()+1+"\n";	
		}
	}
	return str;

}

}
