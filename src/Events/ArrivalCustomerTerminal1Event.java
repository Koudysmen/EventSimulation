/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import SimulationCore.Core;
import jdk.internal.dynalink.support.AutoDiscovery;

/**
 *
 * @author Michal
 */
public class ArrivalCustomerTerminal1Event extends AirRentCarEvent{
    
    public ArrivalCustomerTerminal1Event(Core core, double eventTime) {
        super(core, eventTime, new Customer(), null);
    }
    

    @Override
    public void execute() throws Exception {
        planArrival();
        
    }
    
    private void planArrival() throws Exception {
        // cas prichodu
        double arrivalTime = this.getCore().getGens().getArrivalTimeTerminal1();
        double nextArrivalTime = arrivalTime + this.getEventTime();
        //pridam ho do fronty
        this.getCore().getRadTerminal1().add(this.getZakaznik());
        this.getZakaznik().setArrivalTime(nextArrivalTime);
        //naplanujem nastup
        ArrivalCustomerTerminal1Event nextArrival = new ArrivalCustomerTerminal1Event(this.getCore(),nextArrivalTime);
        this.getCore().planEvent(nextArrival);
        
        this.getCore().setIdCustomer(this.getZakaznik());
           
    }
    


    
}
