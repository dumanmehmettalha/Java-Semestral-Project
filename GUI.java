
package DistributionSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.Color;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	//private JTextField commandInput;
	private Subscriber s;
	private Subscription subs;
	private Journal j;
	private Distributor dist=new Distributor();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setTitle("Distribution System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.BOLD, 20));
		textArea.setBounds(10, 119, 671, 127);
		contentPane.add(textArea);
		
		JLabel lblLtfenncelikleIlk = new JLabel("Please define the journal, subscriber, and subscription in order.");
		lblLtfenncelikleIlk.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblLtfenncelikleIlk.setHorizontalAlignment(SwingConstants.CENTER);
		lblLtfenncelikleIlk.setBounds(0, 46, 681, 63);
		contentPane.add(lblLtfenncelikleIlk);
		JLabel commandLabel = new JLabel("");
		commandLabel.setHorizontalAlignment(SwingConstants.CENTER);
		commandLabel.setBounds(10, 96, 576, 13);
		contentPane.add(commandLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 128, 64));
		panel_1.setBounds(0, 256, 681, 286);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton createJournalBtn = new JButton("Create Journal");
		createJournalBtn.setBackground(new Color(0, 255, 255));
		panel_1.add(createJournalBtn);
		
		JButton createSubscriberBtn = new JButton("Create Subscriber");
		createSubscriberBtn.setBackground(new Color(0, 255, 255));
		panel_1.add(createSubscriberBtn);
		
		JButton createSubscriptionBtn = new JButton("Create Subscription");
		createSubscriptionBtn.setBackground(new Color(0, 255, 255));
		createSubscriptionBtn.addActionListener(new ActionListener() {
		    private DateInfo dates = new DateInfo();
		    private int copies;
		    private Journal j;
		    private Subscriber s;

		    public void actionPerformed(ActionEvent e) {
		        try {
		            commandLabel.setText("Please enter the start month");
		            dates.setStartMonth(Integer.parseInt(JOptionPane.showInputDialog("Please enter the start month")));

		            commandLabel.setText("Please enter the start year");
		            dates.setStartYear(Integer.parseInt(JOptionPane.showInputDialog("Please enter the start year")));

		            commandLabel.setText("Please enter copies you want to purchase");
		            copies = Integer.parseInt(JOptionPane.showInputDialog("Please enter copies you want to purchase"));

		            commandLabel.setText("Please enter issn of the journal you want to purchase");
		            String issn = JOptionPane.showInputDialog("Please enter issn of the journal you want to purchase");
		            j = dist.searchJournal(issn);

		            if (j != null) {
		                commandLabel.setText("Please enter subscriber you want to assign");
		                String subscriberName = JOptionPane.showInputDialog("Please enter subscriber's name you want to assign");
		                s = dist.searchSubscriber(subscriberName);

		                if (s != null) {
		                    subs = new Subscription(dates, copies, j, s);
		                    dist.addSubscription(j.getIssn(), s, subs);
		                    commandLabel.setText("Subscription created and added successfully!");
		                    textArea.setText("Subscription created and added successfully!");
		                } else {
		                    commandLabel.setText("Subscriber not found. Please try again.");
		                }
		            } else {
		                commandLabel.setText("Journal not found. Please try again."); 
		            }
		        } catch (NumberFormatException ex) {
		            commandLabel.setText("Invalid input. Please enter a valid number.");
		        }
		    }
		});
		panel_1.add(createSubscriptionBtn);
		
		JButton IncreasePaymentBtn = new JButton("Increase Payment");
		IncreasePaymentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount;
				commandLabel.setText("Please enter the amount you want to increase.");
				amount=Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount you want to increase:"));
				subs.getPayment().increasePayment(amount);
				textArea.setText("Payment increased by "+amount+".");
			}
		});
		panel_1.add(IncreasePaymentBtn);
		
		JButton ListSubscriptionNameBtn = new JButton("List Subscriptions by Name");
		ListSubscriptionNameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String subscriberName = JOptionPane.showInputDialog("Please enter subscriber name:");
		        dist.listSubscriptions(subscriberName);
		        textArea.setText(dist.listSubscriptions(subscriberName));
			}
		});
		panel_1.add(ListSubscriptionNameBtn);
		
		JButton loadStateBtn = new JButton("Load State");
		loadStateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				commandLabel.setText("Enter filename: ");
				dist.loadState(JOptionPane.showInputDialog("Enter filename: "));
				textArea.setText("State loaded.");
				}catch(Exception ex) {
					commandLabel.setText("File not found.");
				}
			}
		});
		panel_1.add(loadStateBtn);
		
		JButton saveStateBtn = new JButton("Save State");
		saveStateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandLabel.setText("Enter filename: ");
				
				dist.saveState(JOptionPane.showInputDialog("Enter filename: "));
				textArea.setText("State saved.");
			}
		});
		panel_1.add(saveStateBtn);
		
		JButton listAllSubscriptions = new JButton("List All Subscriptions");
		listAllSubscriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month = Integer.parseInt(JOptionPane.showInputDialog("Please enter month:"));
				int year = Integer.parseInt(JOptionPane.showInputDialog("Please enter year:"));
		        textArea.setText(dist.listAllSendingOrders(month, year));
			}
		});
		panel_1.add(listAllSubscriptions);
		
		JButton listSubscriptionIssn = new JButton("List Subscription by Issn");
		listSubscriptionIssn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn = JOptionPane.showInputDialog("Please enter issn:");
		        textArea.setText(dist.listSubscriptionsByIssn(issn));
			}
		});
		panel_1.add(listSubscriptionIssn);
		
		JButton listIncompleteSubs = new JButton("List Incomplete Subscriptions");
		listIncompleteSubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        textArea.setText(dist.listIncompletePayments());
			}
		});
		panel_1.add(listIncompleteSubs);
		
		JButton billingInfoBtn = new JButton("Get Billing Info");
		billingInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Please enter subscriber name:");
		        textArea.setText(dist.searchSubscriber(name).getBillingInformation());
			}
		});
		panel_1.add(billingInfoBtn);
		
		JButton generateReportBtn = new JButton("Generate Report");
		generateReportBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            DistributorReporter reporter=new DistributorReporter(dist);
		            Thread aThread=new Thread(reporter);
		            aThread.start();
		            textArea.setText(reporter.getReportOutput());
		            System.out.println(reporter.getReportOutput());
		    		//textArea.setText("Information related to subscriber "+subscriberName+" displayed on the console.\nAlso, information given from Month:"+reportMonth+" Year:"+reportYear);
		    		
		        } catch (NumberFormatException ex) {
		            commandLabel.setText("Invalid input. Please enter a valid number.");
		        }
		    }
		});
		panel_1.add(generateReportBtn);
		
		JLabel label = new JLabel("");
		panel_1.add(label);
		
		createSubscriberBtn.addActionListener(new ActionListener() {
		    private String sName;
		    private String sAddress;
		    private int control; 

		    public void actionPerformed(ActionEvent e) {
		        commandLabel.setText("Please enter subscriber name:");
		        sName = JOptionPane.showInputDialog("Enter subscriber name:");

		        if (sName != null && !sName.isEmpty()) {
		            commandLabel.setText("Please enter subscriber address:");
		            sAddress = JOptionPane.showInputDialog("Enter subscriber address:");

		            if (sAddress != null && !sAddress.isEmpty()) {
		                
		                try {
		                    control = Integer.parseInt(JOptionPane.showInputDialog("Enter subscriber type (0 for Corporation, 1 for Individual):"));

		                    if (control == 0 || control == 1) {
		                        s = (control == 1) ? new Individual(sName,sAddress) : new Corporation(sName,sAddress);
		                        
		                        System.out.println("Subscriber created: " + s);
		                        dist.addSubscriber(s);
		                        textArea.setText("Subscriber successfully created.");
		                        
		                    } else {
		                        commandLabel.setText("Invalid input for subscriber type. Please enter 0 or 1.");
		                    }
		                } catch (NumberFormatException ex) {
		                    commandLabel.setText("Invalid input for subscriber type. Please enter a valid number.");
		                }
		            } else {
		                commandLabel.setText("Invalid input for subscriber address. Please try again.");
		            }
		        } else {
		            commandLabel.setText("Invalid input for subscriber name. Please try again.");
		        }
		    }
		});
		createJournalBtn.addActionListener(new ActionListener() {
		    private String jName;
		    private String jIssn;
		    private double jIssuePrice;

		    public void actionPerformed(ActionEvent e) {
		        commandLabel.setText("Please enter journal name:");
		        jName = JOptionPane.showInputDialog("Enter journal name:");
		        
		        if (jName != null && !jName.isEmpty()) {
		            commandLabel.setText("Please enter journal issn:");
		            jIssn = JOptionPane.showInputDialog("Enter journal ISSN:");

		            if (jIssn != null && !jIssn.isEmpty()) {
		                commandLabel.setText("Please enter journal issue price:");
		                String priceInput = JOptionPane.showInputDialog("Enter journal issue price:");

		                try {
		                    if (priceInput != null && !priceInput.isEmpty()) {
		                        jIssuePrice = Double.parseDouble(priceInput);
		                        j = new Journal(jName, jIssn, jIssuePrice);
		                        // Now, you can use the created Journal object as needed.
		                        System.out.println("Journal created: " + j);
		                        dist.addJournal(j);
		                        textArea.setText("Journal successfully created");
		                    } else {
		                        commandLabel.setText("Invalid input for issue price. Please try again.");
		                    }
		                } catch (NumberFormatException ex) {
		                    commandLabel.setText("Invalid input for issue price. Please enter a valid number.");
		                }
		            } else {
		                commandLabel.setText("Invalid input for ISSN. Please try again.");
		            }
		        } else {
		            commandLabel.setText("Invalid input for journal name. Please try again.");
		        }
		    }
		});
		
		JLabel labelWelcome = new JLabel("Welcome to the Journal Distribution System");
		labelWelcome.setForeground(new Color(255, 255, 255));
		labelWelcome.setFont(new Font("Times New Roman", Font.BOLD, 24));
		labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		labelWelcome.setBounds(10, 0, 671, 44);
		contentPane.add(labelWelcome);
		
		textField = new JTextField();
		textField.setColumns(10);
		

		

	}
}
