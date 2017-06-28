package service;


import java.util.List;

import model.PrintJob;

public interface JobProcessor {
	List<PrintJob> processPrintJobs(List<PrintJob> printJobs, boolean showJobDetails);
	double getTotalCost();
}
