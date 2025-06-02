package org.example.function;

import org.example.trigonometry.Csc;
import org.example.trigonometry.Tan;

public class TrigonometricFunction {
    private final Tan tan;
    private final Csc csc;

    public TrigonometricFunction(Tan tan, Csc csc){
        this.tan = tan;
        this.csc = csc;
    }

    public double calculateTrigFunc(double x, double eps){
        return (tan.calculateTan(x, eps) - csc.calculateCosec(x, eps));
    }

}
