package service;

import java.util.List;
import java.util.stream.Collectors;

import helper.CurrencyHelper;
import helper.PrintJobCost;
import model.JobStatus;
import model.PrintJob;

public class JobProcessorImpl implements JobProcessor {
	
	private double totalCost;
	
	@Override
	public List<PrintJob> processPrintJobs(List<PrintJob> printJobs, boolean showJobDetails){
		this.totalCost = 0.0 ;
		
		if(showJobDetails)
			System.out.println("Print Job details:");
		
		printJobs.stream().forEach(job -> {
			double cost = PrintJobCost.getCost(job);
			if(cost > 0){
				job.setCost(CurrencyHelper.getRoundedValue(cost));
				job.setStatus(JobStatus.DONE);
				this.totalCost += cost;
				if(showJobDetails){showJobDetails(job);};
			}
		});
		
		if(showJobDetails)
			showJobsNotCompleted(printJobs);

		return printJobs;
	}
	
	private void showJobsNotCompleted(List<PrintJob> printJobs) {
		List<PrintJob> notCompeltedJobs = printJobs.stream().filter(job -> job.getStatus() != JobStatus.DONE).collect(Collectors.toList());
		if(notCompeltedJobs.size() > 0){
			System.out.println("Print Jobs not completed");
			notCompeltedJobs.forEach(job -> showJobDetails(job));
		}
	}

	private void showJobDetails(PrintJob job) {
		StringBuilder jobDetail = new StringBuilder();
		
		jobDetail.append("Paper: ").append(job.getPaperType()).append("\tTotalPages: ")
		.append(job.getNoOfTotalPages()).append("\tColorPages: ").append(job.getNoOfColorPages())
		.append("\t Cost: $").append(job.getCost());
		
		System.out.println(jobDetail.toString());
	}

	@Override
	public double getTotalCost(){
		return CurrencyHelper.getRoundedValue(totalCost);
	}

}
