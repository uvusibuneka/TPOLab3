package org.example.logarithm;

public class Ln {

    public Ln(){
    }

    public double calculateLn(double x, double eps){
        if(x == 0){
            return Double.NEGATIVE_INFINITY;
        }

        if(x < 0){
            return Double.NaN;
        }

        double y = (x-1)/(x+1);
        double sum = 0;
        double term = y;
        int n = 1;

        while(Math.abs(term) > eps){
            sum += term / (2*n - 1);
            term *= y*y;
            n++;
        }
        return 2*sum;
    }
}
