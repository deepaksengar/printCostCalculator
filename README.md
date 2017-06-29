# PrintCostCalculator
[![Build Status](https://travis-ci.org/deepaksengar/printCostCalculator.svg?branch=master)](https://travis-ci.org/deepaksengar/printCostCalculator)

The application is a simulation of helps the system administrator to calculate the print costs. It takes a list of A4 print jobs and calculates the cost of each job, given the total number of pages, number of colour pages and whether printing is double-sided.

The application:
Read print jobs from a file.
Output the job details and job cost for each job to the console
Output the total cost of all jobs to the console

Execution Instruction:
---------------------

	To remove all older builds :
	-	mvn clean
	
	To build the code and run tests :
	-	mvn clean install
	
	To create a package which will create executable Jar, execute :
	-	mvn clean package

Dependency:
-----------
	1) Java 8
	2) JUnit 4 : for test execution
	

Design:
------
	1) Model :
		PrintJob :
			This class implements PrintJob model which will hold object of job which needs to be executed.
		JobStatus :
			Enum to represent different status of PrintJob.
		PrintColor : 
			Enum to represent different type of Print color options such as Black&White or Color.
		PrintType : 
			Enum to represent different type of Print options such as Single side or Double Side.
		PaperType : 
			Enum to represent different type of Print Paper such as A4.
		Cost :
			This class has cost for different PrintColor.
		PaperCost :
			This class holds cost for different PrintType and PrintColor.
		
	2) Service :
			This package has classes which will provide services such as JobProcessing and JobBuilder from Csv file.
		JobProcessor :
			This interface provides method to execute job list and totalCost for all jobs executed.
		JobProcessorImpl :
			This is implementation class for JobProcessor interface.
		IPrintJobBuilder :
			This interface provides method for job creation.
		CsvPrintJobBuilder :
			This is implementation class for IPrintJobBuilder interface. 
			It provides single instance which can be used to create PrintJob list.				 
	3) Helper :
		Currency Helper :
			This class provides rounding method for currency.
		PrintJobCost :
			This class provides cost of each print job, it also holds costs of each PaperType.
		PaperCostFeed :
			This class reads json feed from file to initialize PaperType costs.	
	
	4) Executor Class :
		PrintCalculatorExecutor
			This is entry point for Application.

