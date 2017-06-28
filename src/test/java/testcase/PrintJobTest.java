package testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.JobStatus;
import model.PrintJob;

public class PrintJobTest {
	
	PrintJob job;
	
	@Before
	public void initialize(){
		job = new PrintJob("a4",10,0,false);
	}
	
	@Test
	public void testPrintJobCreation() {
		//Initial Cost will be set to 0
		assert( 0.0 == job.getCost());
		//Initial Status will be INITIALIZE
		assertEquals(job.getStatus(),JobStatus.INITIALIZE);
	}
	
	@Test
	public void testPrintJobNegative(){
		try{
			new PrintJob("a",10,0,false);
			fail("Should not create job for paper type \'a\'");
		
		}catch(Exception e){
			assert(e instanceof IllegalArgumentException);
		}
	}

}
