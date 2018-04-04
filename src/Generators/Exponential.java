/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generators;

import java.util.Random;

/**
 *
 * @author Michal
 */
public class Exponential {
    private Random rand;
    private double lambda;

    public Exponential(Random rnd, double median) {
        this.rand = rnd;
        this.lambda = (double) 1/median;
    }

    public double nextDouble() {
        return  Math.log(1-this.rand.nextDouble())/(-lambda);
    }
}
