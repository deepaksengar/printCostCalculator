package testcase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import helper.PrintJobCost;
import model.PrintJob;

public class PrintJobCostTest {
	
	PrintJob job;
	
	@Before
	public void initialize() throws Exception {
		job = new PrintJob("a4",10,10,false);
		PrintJobCost.status = PrintJobCost.FeedStatus.WAITING;
		PrintJobCost.paperTypeToPaperCost.clear();
	}
	
	@Test
	public void testCostMap() {
		assertEquals("Initially Print Cost map should be empty" , 0 , PrintJobCost.paperTypeToPaperCost.size());
		double cost = PrintJobCost.getCost(job);
		assertTrue("Print Cost should be initialized after first call.", PrintJobCost.paperTypeToPaperCost.size() > 0);
		//Print Cost should be equal to 2.5
		assert( 2.5 == cost);
	}

}
