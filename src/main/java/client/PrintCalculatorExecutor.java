package client;
import java.io.IOException;
import java.util.List;

import model.PrintJob;
import service.CsvPrintJobBuilder;
import service.JobProcessor;
import service.JobProcessorImpl;

public class PrintCalculatorExecutor {
	
	public void executePrintCalculator(String fileName){
		System.out.println("Executing print Jobs from file: " + fileName);
        try {
        	JobProcessor processJobs = processJobs(fileName);
            System.out.println("Total cost:  $" + processJobs.getTotalCost());
        } catch (Exception e) {
            System.out.println("Error occured during Print Cost Calculation : " + e.getMessage());
        } 
		
	}

	public JobProcessor processJobs(String fileName) throws IOException {
		return processJobs(fileName, true);
	}
	
	public JobProcessor processJobs(String fileName, boolean showEachJobResult) throws IOException {
		List<PrintJob> printJobs = CsvPrintJobBuilder.getInstance().createPrintJobs(fileName);
		
		JobProcessor processJobs = new JobProcessorImpl();
		processJobs.processPrintJobs(printJobs,showEachJobResult);
		return processJobs;
	}
	
	public static void main(String[] args) {
		String CSV_FILE = "src/main/resources/printjobs.csv";
		PrintCalculatorExecutor executor = new PrintCalculatorExecutor();
		executor.executePrintCalculator(CSV_FILE);
		
    }
}
