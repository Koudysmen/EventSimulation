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
public class EndOfTermination1Event extends AirRentCarEvent {

    public EndOfTermination1Event(Core core, double eventTime, Customer customer,Bus bus) {
        super(core, eventTime, customer, bus);
    }

    @Override
    public void execute() throws Exception {
        if(this.getCore().getRadTerminal1().isEmpty() || this.getBus().getIsFull()){
            ArrivalBusTerminal2Event termination2 = new ArrivalBusTerminal2Event(this.getCore(), this.getEventTime(), null, this.getBus());
            this.getCore().planEvent(termination2);
        } else {
            //vybranie prveho zakaznika z frontu
            Customer customer = this.getCore().getRadTerminal2().peek();
            StartOfTermination1Event arival = new StartOfTermination1Event(this.getCore(), this.getEventTime(),customer,this.getBus());
            this.getCore().planEvent(arival);
            
        }
    }
    
}
