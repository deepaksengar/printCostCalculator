package service;

import java.io.IOException;
import java.util.List;
import model.PrintJob;

public interface IPrintJobBuilder {
    List<PrintJob> createPrintJobs(String csvFile) throws IOException;
}
