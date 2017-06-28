package testcase;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import client.PrintCalculatorExecutor;
import service.JobProcessor;

public class PrintCostCalculatorTest {
	
	final String CSV_FILE = "src/test/resources/printjobs.csv";
	
	PrintCalculatorExecutor executor;
	
	@Before
	public void initialize() throws Exception {
		executor = new PrintCalculatorExecutor();
	}
	
	@Test
	public void testJobProcessing(){
		try {
			executor.executePrintCalculator(CSV_FILE);
		} catch (Exception e) {
			fail("Not Able to execute given csv file. : " + CSV_FILE);
		}
	}
	
	@Test
	public void testJobProcessingForTotalAmount() throws Exception {
		JobProcessor processedJobs = executor.processJobs(CSV_FILE, false);
		assert(64.10 == processedJobs.getTotalCost());
	}
}
