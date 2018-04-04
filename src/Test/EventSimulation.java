/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Generators.GenerateValue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Michal
 */
public class EventSimulation {
  private static BufferedWriter bf;
   
    public static void main(String[] args) throws IOException {
       bf = new BufferedWriter(new FileWriter("TEST.txt"));
        for (int i = 0; i < 1000000; i++) {
            GenerateValue gen = new GenerateValue();
            bf.write(gen.getArrivalTimeTerminal1()+"");
            bf.newLine();
//            System.out.println(gen.getArrivalTimeTerminal1());
        }
        bf.close();
    }
}
