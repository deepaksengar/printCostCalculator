package service;

import java.awt.print.Paper;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.PrintJob;

public class CsvPrintJobBuilder implements IPrintJobBuilder {
	
	private static IPrintJobBuilder instance;

    /**
     * Constructor should be private for a singleton class
     */
    private CsvPrintJobBuilder() {

    }

    /**
     * Create a unique instance of the CSVPrintJobReader.
     *
     * @return a single unique instance.
     */
    public static IPrintJobBuilder getInstance() {
        if (instance == null) {
            instance = new CsvPrintJobBuilder();
        }
        return instance;
    }

    @Override
    public List<PrintJob> createPrintJobs(String csvFile) {
        
        List<PrintJob> printJobs = new ArrayList<>();;
        
        try (Reader in = new FileReader(csvFile)) {
            
        	Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            printJobs = new ArrayList<>();
            for (CSVRecord record : records) {
                int printTotalPages = getPageCount(record.get(0));

                //If empty found then set 0
                int printColorPages = getPageCount(record.get(1));
                
                boolean doubleSided = Boolean.parseBoolean(record.get(2).trim().isEmpty() ? "false" : record.get(2).trim());
                //Default Page is A4 size for print job
                PrintJob job = new PrintJob(printTotalPages, printColorPages, doubleSided);
                printJobs.add(job);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Not able to find the file: " + csvFile);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Error reading the csv file: " + csvFile);
        }
        
        return printJobs;
    }
    
    private static int getPageCount(String value) {
        if (value == null) {
            throw new IllegalArgumentException("CSV record is invalid.");
        }
        value = value.trim();
        
        if (value.isEmpty()) {
            return 0;
        }
        
        return Integer.parseInt(value);
    }
}
