package org.example.function;

import org.example.logarithm.Log;

public class LogarithmFunction {
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    public LogarithmFunction(Log log2, Log log3, Log log5, Log log10){
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public double calculateLogFunc(double x, double accuracy){
        return (((log10.calculateLog(x, accuracy)*log10.calculateLog(x, accuracy) + log5.calculateLog(x, accuracy)) / log3.calculateLog(x, accuracy)
        - log2.calculateLog(x, accuracy)) - log3.calculateLog(x, accuracy));
    }
}
