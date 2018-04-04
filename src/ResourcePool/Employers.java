/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourcePool;

import Events.AirRentCarEvent;
import SimulationCore.AirRentCarSimCore;

/**
 *
 * @author Michal
 */
public class Employers {
    private int maximumResources;
    private int numOfResources;
    private int numOfPassengers;
    private final int capacityOfBus = 12;
    private AirRentCarSimCore instance;

    public Employers(int maximumResources) {
        this.maximumResources = maximumResources;
        this.numOfResources = maximumResources;
    }
    
    public void useResoulcePool() throws Exception {
        if ( numOfResources > 0 ) {
            numOfResources--;
        } else {
            throw new Exception("You dont have enought resources for delay.");
        }
    }
    
    public void releaseResourcePool() throws Exception {
        if ( numOfResources < maximumResources ) {
            numOfResources++;
        } else {
            throw new Exception("You cant have more resources for delay.");
        }
    }
    
    public void useResoulcePoolBus(int oneEnterCustomer) throws Exception {
        numOfPassengers += oneEnterCustomer;
        if ( numOfResources > 0 ) {
            if (numOfPassengers == capacityOfBus || instance.getRadTerminal1().isEmpty()){
                numOfResources--;
            } else {
                throw new Exception("You dont capacity of bus.");
            }
        } else {
            throw new Exception("You dont have enought resources for delay.");
        }
    }
    
    public void releaseResourcePoolBus(int coutOfEntered) throws Exception {
        if ( numOfResources < maximumResources ) {
            numOfResources++;
        } else {
            throw new Exception("You cant have more resources for delay.");
        }
    }
    
    public boolean isBusHere(int pom){
        if(pom == 1){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean canUse(){
        return numOfResources > 0;
    }
    
    public int numOfUsed() {
        return maximumResources-numOfResources;
    }

    public int getNumOfResources() {
        return numOfResources;
    }
    
    
    
}
