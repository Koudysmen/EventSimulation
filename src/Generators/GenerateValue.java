/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generators;

/**
 *
 * @author Michal
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Michal
 */
public class GenerateValue {
    private Random randomSeed;
    private Exponential arrivalTimeTerminal1;
    private Exponential arrivalTimeTerminal2;
    private Uniform RentCarTime;
    private Uniform enterBusTime;
    private Uniform getOffBusTime;

    public GenerateValue() {
        randomSeed = new Random();
        arrivalTimeTerminal1 = new Exponential(new Random(randomSeed.nextInt()),83.6);
        arrivalTimeTerminal2 = new Exponential(new Random(randomSeed.nextInt()), 189);
        RentCarTime = new Uniform(new Random(randomSeed.nextInt()), 120, 600);
        enterBusTime = new Uniform(new Random(randomSeed.nextInt()), 10, 14);
        getOffBusTime = new Uniform(new Random(randomSeed.nextInt()), 4, 12);
        
    }
    public double getArrivalTimeTerminal1() {
        return arrivalTimeTerminal1.nextDouble();
    }

    public double getArrivalTimeTerminal2() {
        return arrivalTimeTerminal2.nextDouble();
    }

    public double getRentCarTime() {
        return RentCarTime.nextDouble();
    }
    
     public double getEnterBusTime() {
        return enterBusTime.nextDouble();
    }

    public double getGetOffBusTime() {
        return getOffBusTime.nextDouble();
    }
    
    
       
    
}

