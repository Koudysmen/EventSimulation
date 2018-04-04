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
public class WeightedAverage {
    private double countOfWaiting = 0;
    private double allWaitingTime = 0;
    
    public void addIteration(double count, double valueTime) {
        countOfWaiting += count;
        allWaitingTime += (count * valueTime);
    }
    
    public double result() {
        return (double) allWaitingTime/countOfWaiting;
    }
}