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
public class ArrivalBusTerminal1Event extends AirRentCarEvent{

    public ArrivalBusTerminal1Event(Core core, double eventTime, Bus bus) {
        super(core, eventTime, null, bus);
    }

    
    
    @Override
    public void execute() throws Exception {
        double arrivalTime = 658.2856;
        double nextArrivalTime = arrivalTime + this.getEventTime();
        StartOfTermination1Event nextArrival = new StartOfTermination1Event(this.getCore(),nextArrivalTime,null, this.getBus());
        this.getCore().planEvent(nextArrival);
        
    }
    
}
