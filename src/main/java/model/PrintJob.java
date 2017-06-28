package model;

public class PrintJob {
	
	private int noOfTotalPages;
	private int noOfColorPages;
	private PaperType paperType;
	private PrintType printType;
	private double cost;
	private JobStatus status;
	
	/**
	 * 
	 * @param paperType : when there are multiple types of paper available for printing.
	 * @param printTotalPages
	 * @param printColorPages
	 * @param printType
	 */
	public PrintJob(String paperType, int printTotalPages, int printColorPages, boolean isDoubleSidePrint){
		createJob(printTotalPages, printColorPages, isDoubleSidePrint);
		this.paperType = PaperType.valueOf(paperType.toUpperCase());
	}
	
	public PrintJob(int printTotalPages, int printColorPages, boolean isDoubleSidePrint){
		this.paperType = PaperType.A4;
		createJob(printTotalPages, printColorPages, isDoubleSidePrint);
	}

	private void createJob(int printTotalPages, int printColorPages, boolean isDoubleSidePrint) {
		this.printType = isDoubleSidePrint ? PrintType.DOUBLE : PrintType.SINGLE;
		this.cost = 0;
		this.status = JobStatus.INITIALIZE;
		validateAndSetPages(this, printTotalPages, printColorPages);
	}
	
	private void validateAndSetPages(PrintJob printJob, int printTotalPages, int printColorPages) {
		if(printTotalPages < 0 || printColorPages < 0 || printTotalPages < printColorPages){
			throw new IllegalArgumentException("Illegal No of Pages.");
		}
		printJob.setNoOfTotalPages(printTotalPages);
		printJob.setNoOfColorPages(printColorPages);
	}

	
	public double getCost(){
		return cost;
	}
	
	public PaperType getPaperType() {
		return paperType;
	}

	public PrintType getPrintType() {
		return printType;
	}

	public int getNoOfTotalPages() {
		return noOfTotalPages;
	}

	public void setNoOfTotalPages(int noOfTotalPages) {
		this.noOfTotalPages = noOfTotalPages;
	}

	public int getNoOfColorPages() {
		return noOfColorPages;
	}

	public void setNoOfColorPages(int noOfColorPages) {
		this.noOfColorPages = noOfColorPages;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

}
