package com.example.demo.exceptions;

public class ReportYearNotAvailable extends Exception{
    public ReportYearNotAvailable()
    {
        super("Report year is not available");
    }
}
