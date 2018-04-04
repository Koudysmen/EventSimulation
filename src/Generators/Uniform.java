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
public class Uniform {
    private Random rand;
    private int min;
    private int max;

    public Uniform(Random rand, int min, int max) {
        this.rand = rand;
        this.min = min;
        this.max = max;
    }
    
    public double nextDouble() {
        return min + (max - min) * rand.nextDouble();
  }
}
