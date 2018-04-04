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
public class EndOfTermination2Event extends AirRentCarEvent {

    public EndOfTermination2Event(Core core, double eventTime,Customer customer, Bus bus) {
        super(core, eventTime, customer, bus);
    }

    @Override
    public void execute() throws Exception {
        if(this.getCore().getRadTerminal2().isEmpty() || this.getBus().getIsFull()){
            ArrivalBusRentCarEvent termination2 = new ArrivalBusRentCarEvent(this.getCore(), this.getEventTime(), this.getBus());
            this.getCore().planEvent(termination2);
//            System.out.println("Pocet Ludi v rade : " +this.getCore().getRadTerminal2().size() ); 
//            System.out.println("ODCHOD autobusu z terminal 2... ID BUS: " 
//            + this.getBus().getIDBus() + " z poctom passazierov :" + this.getBus().NumberOfPassager());
           
        } else {
            Customer customer = this.getCore().getRadTerminal2().peek();
            StartOfTermination2Event arival = new StartOfTermination2Event(this.getCore(), this.getEventTime(),customer , this.getBus());
            this.getCore().planEvent(arival);
        
        }
    }
}
