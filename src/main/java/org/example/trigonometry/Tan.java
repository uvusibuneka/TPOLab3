package org.example.trigonometry;

public class Tan{
    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    public double calculateTan(double x, double eps){
        double sinX = sin.calculateSin(x, eps);
        double cosX = cos.calculateCos(x, eps);

        if(Math.abs(cosX) < 1e-10 || Math.abs((x % Math.PI) - Math.PI/2) < 1e-10){
            return Double.NaN;
        }

        return sinX/cosX;
    }
}