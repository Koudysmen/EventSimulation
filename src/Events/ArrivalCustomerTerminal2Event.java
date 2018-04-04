/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import SimulationCore.Core;

/**
 *
 * @author Michal
 */
public class ArrivalCustomerTerminal2Event extends AirRentCarEvent{
    
    public ArrivalCustomerTerminal2Event(Core core, double eventTime) {
        super(core, eventTime, new Customer(),null);
    }
    

    @Override
    public void execute() throws Exception {
        planArrival();
     //   planZacNastupu();
    }
    
    private void planArrival() throws Exception {
        double arrivalTime = this.getCore().getGens().getArrivalTimeTerminal2();
        double nextArrivalTime = arrivalTime + this.getEventTime();
        ArrivalCustomerTerminal2Event nextArrival = new ArrivalCustomerTerminal2Event(this.getCore(),nextArrivalTime);
        this.getCore().getRadTerminal2().add(this.getZakaznik());
        this.getZakaznik().setArrivalTime(nextArrivalTime);
        this.getCore().planEvent(nextArrival);
        
    }
    
}
