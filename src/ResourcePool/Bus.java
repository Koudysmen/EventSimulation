/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResourcePool;

import Events.Customer;
import SimulationCore.AirRentCarSimCore;
import java.util.LinkedList;

/**
 *
 * @author Michal
 */
public class Bus {
    private int IDBus;
    private LinkedList<Customer> peopleInTheBus;
    private Customer customer;
    private int numOfResources;
    private int numOfPassengers;
    private final int capacityOfBus = 12;
    private AirRentCarSimCore instance;

    public Bus(int ID) {
        this.peopleInTheBus = new LinkedList<Customer>();
        this.IDBus = ID;
        //this.numOfResources = instance.getMaxPocetBusov();
    }

    public void canUseBus() throws Exception{
        if (peopleInTheBus.size() == capacityOfBus) {
                numOfResources--;
            } else {
                throw new Exception("You dont have capacity of bus.");
            }
    }
    
     public boolean canUse(){
        if ( peopleInTheBus.size() < capacityOfBus ) {
            return true;
        }
        else{
            return false;        
        }
        
    }
     
     public int numberOfFreeSpot(){
         return capacityOfBus - peopleInTheBus.size();
     }
     
     public boolean getIsFull(){
     if ( peopleInTheBus.size() == capacityOfBus ) {
            return true;
        } else {
         return false;
        }    
     }
     
     public void addCustomer(Customer customer){
         peopleInTheBus.add(customer);
     }
     
      public void removeCustomer(){
         peopleInTheBus.remove(customer);
     }
     
     public int NumberOfPassager(){
         return peopleInTheBus.size();
     }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
     
     
    
    public LinkedList<Customer> getPeopleInTheBus() {
        return peopleInTheBus;
    }

    public int getNumOfResources() {
        return numOfResources;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setPeopleInTheBus(LinkedList<Customer> peopleInTheBus) {
        this.peopleInTheBus = peopleInTheBus;
    }

    public void setNumOfResources(int numOfResources) {
        this.numOfResources = numOfResources;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public int getIDBus() {
        return IDBus;
    }

    public void setIDBus(int IDbus) {
        this.IDBus = IDBus;
    }
    
    
    
}
