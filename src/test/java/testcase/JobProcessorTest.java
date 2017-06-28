package testcase;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.JobStatus;
import model.PrintJob;
import service.JobProcessorImpl;

public class JobProcessorTest {
	
	List<PrintJob> printJobs;
	JobProcessorImpl processor;
	
	@Before
	public void initialize() throws Exception {
		processor = new JobProcessorImpl();
		printJobs = new ArrayList<>();
		PrintJob job = new PrintJob("a4",10,10,false);
		printJobs.add(job);
	}
	
	@Test
	public void testJobProcessing(){
		processor.processPrintJobs(printJobs, false);
		//One Side Printing of 10 color pages will cost $2.5
		assert(2.5 == processor.getTotalCost());
	}
	
	@Test
	public void testJobProcessingAddingOneMoreJob(){
		PrintJob job = new PrintJob("a4",10,0,false);
		printJobs.add(job);
		processor.processPrintJobs(printJobs, false);
		//One Side Printing of 10 color pages & 10 BalcknWhite Pages will cost $4
		assert( 4.0 == processor.getTotalCost());
	}
	
	@Test
	public void testProcessPrintJobStatusUpdate(){
		List<PrintJob> processedJobs = processor.processPrintJobs(printJobs, false);
		//After Processing cost has been updated in PrintJob
		assert(2.5 == processedJobs.get(0).getCost());
		//After Processing Status has been updated to Done in PrintJob
		assertEquals("Staus should be updated to Done.",processedJobs.get(0).getStatus(), JobStatus.DONE);
	}

}
