package org.example.function;

public class MainFunction {
    private final LogarithmFunction logarithmFunction;
    private final TrigonometricFunction trigonometricFunction;

    public MainFunction(LogarithmFunction logarithmFunction, TrigonometricFunction trigonometricFunction){
        this.logarithmFunction = logarithmFunction;
        this.trigonometricFunction = trigonometricFunction;
    }

    public double calculateFunction(double x, double accuracy){
        if(x > 0){
            return logarithmFunction.calculateLogFunc(x, accuracy);
        }else{
            return trigonometricFunction.calculateTrigFunc(x, accuracy);
        }
    }
}
