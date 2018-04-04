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
public class ArrivalBusRentCarEvent extends AirRentCarEvent{

    public ArrivalBusRentCarEvent(Core core, double eventTime,Bus bus) {
        super(core, eventTime, null, bus);
    }

    
    
    @Override
    public void execute() throws Exception {
        double arrivalTime = 257.1429;
        double nextArrivalTime = arrivalTime + this.getEventTime();
        StartOfExitFromBusEvent nextArrival = new StartOfExitFromBusEvent(this.getCore(), nextArrivalTime, null, this.getBus());
        this.getCore().planEvent(nextArrival);
//         System.out.println("--------------------------------------------------------------");
//            System.out.println("Prichod autobusu k POZICOVNI... ID BUS: " + this.getBus().getIDBus() 
//                    +" v case: " + nextArrivalTime);
//            System.out.println("--------------------------------------------------------------");
        
    }
}
