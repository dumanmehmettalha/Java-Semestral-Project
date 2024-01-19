package DistributionSystem;

public class DistributorReporter implements Runnable {
	private Distributor d;
	String reportOutput;
	public DistributorReporter(Distributor dist) {
		this.d=dist;
	}
	@Override
	public synchronized void run() {
		reportOutput=d.report();
	}
	public String getReportOutput() {
		return reportOutput;
	}
	

}
