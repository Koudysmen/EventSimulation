/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import ResourcePool.Bus;
import SimulationCore.Core;

/**
 *
 * @author Michal
 */
public class ArrivalBusTerminal2Event extends AirRentCarEvent {

    public ArrivalBusTerminal2Event(Core core, double eventTime, Customer customer,Bus bus) {
        super(core, eventTime, null, bus);
    }

    @Override
    public void execute() throws Exception {
        double arrivalTime = 51.42;
        
        double nextArrivalTime = arrivalTime + this.getEventTime();
        StartOfTermination2Event nextArrival = new StartOfTermination2Event(this.getCore(), nextArrivalTime, null, this.getBus());
        this.getCore().planEvent(nextArrival);
//        System.out.println("--------------------------------------------------------------");
//        System.out.println("Prichod autobusu na terminal 2... ID BUS: " + this.getCore().getBus(this.getBus().getIDBus()) 
//                    +" v case: " + nextArrivalTime);
//        System.out.println("--------------------------------------------------------------");
    }
    
}
