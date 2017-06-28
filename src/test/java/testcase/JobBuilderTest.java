package testcase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.PrintJob;
import service.CsvPrintJobBuilder;

public class JobBuilderTest {

	@Test
	public void testJobBuilder() throws Exception {
		String fileName = "src/test/resources/printjobs.csv";
		List<PrintJob> printJobs = CsvPrintJobBuilder.getInstance().createPrintJobs(fileName);
		assertEquals("Should Create 4 printJobs.", 4, printJobs.size());
	}
}
