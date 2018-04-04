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
public class StartOfExitFromBusEvent extends AirRentCarEvent {

    public StartOfExitFromBusEvent(Core core, double eventTime, Customer customer, Bus bus) {
        super(core, eventTime, customer, bus);
    }

    @Override
    public void execute() throws Exception {
        if(!this.getBus().getPeopleInTheBus().isEmpty()){
            Customer cust = this.getBus().getPeopleInTheBus().peek();
            //vygeneruje mu dobu vychadzania
            double time = this.getCore().getGens().getGetOffBusTime();
            double getOffTime = getEventTime() + time;
            //this.getCore().getRadZaciatokObsluhy().add(cust);
            ExitFromBusEvent nextExit = new ExitFromBusEvent(this.getCore(), getOffTime, cust ,this.getBus());
            this.getCore().planEvent(nextExit);
            
     } else{
            ArrivalBusTerminal1Event arrivalTime = new ArrivalBusTerminal1Event(this.getCore(), this.getEventTime(), this.getBus());
            this.getCore().planEvent(arrivalTime);
        }
    }
    
}   
