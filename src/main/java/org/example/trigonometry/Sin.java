package org.example.trigonometry;

public class Sin {

    public double calculateSin(double x, double eps){
        double term = x;
        double sum = term;
        int i = 1;
        while(Math.abs(term) > eps){
            term *= -x*x / ((2*i) * (2*i+1));
            sum += term;
            i++;
        }
        return sum;
    }
}
