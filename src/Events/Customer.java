/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

/**
 *
 * @author Michal
 */
public class Customer {
    private int IdCustomer;
    private double arrivalTime;
    private double customerTimeInSystem;
    private double startWaitingTime;

   

    

    public int getIdCustomer() {
        return IdCustomer;
    }

    public void setIdCustomer(int IdCustomer) {
        this.IdCustomer = IdCustomer;
    }

  
    
    
    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getCustomerTimeInSystem() {
        return customerTimeInSystem;
    }

    public double getStartWaitingTime() {
        return startWaitingTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setCustomerTimeInSystem(double CustomerTimeInSystem) {
        this.customerTimeInSystem = CustomerTimeInSystem;
    }

    public void setStartWaitingTime(double startWaitingTime) {
        this.startWaitingTime = startWaitingTime;
    }
    
    
    
}
