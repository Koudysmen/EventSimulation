/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

/**
 *
 * @author Michal
 */
public class IntervalConfidence {
     private double sumX;
    private double sumX2;
    
    private double num;

    public IntervalConfidence() {
        sumX = 0;
        sumX2 = 0;
        num = 0;
    }
    
    
    public void addIteration(double value) {
        num++;
        sumX += value;
        sumX2 += value*value;
    }
    
    public double getLeftPart() {
        return getAritmeticAverage() - getIntervalPart() ;
    }
    
    public double getRightPart() {
        return getAritmeticAverage() + getIntervalPart() ;
    }
    
    private double getIntervalPart(){
        return (double)(1.645*getStDev())/Math.sqrt(num-1);
    }
    
    private double getStDev() {
        return Math.sqrt( ((double) sumX2/num) - Math.pow(((double) sumX/num),2) );
    }
    
    private double getAritmeticAverage() {
        return (double) sumX/num;
    }
}
