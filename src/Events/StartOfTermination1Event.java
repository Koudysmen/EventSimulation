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
public class StartOfTermination1Event extends AirRentCarEvent {

    public StartOfTermination1Event(Core core, double eventTime,Customer cust, Bus bus) {
        super(core, eventTime, cust, bus);
    }
    
    

    @Override
    //canUse() nw ci dobre
    public void execute() throws Exception {
        Customer custumer = this.getCore().getRadTerminal1().peek();
        if(!this.getCore().getRadTerminal1().isEmpty() && this.getBus().canUse() 
            && this.getEventTime() >= custumer.getArrivalTime()){
            //pridam zakaznika ktorz je prvy vo fronte a odstranim 
            Customer cust = this.getCore().getRadTerminal1().poll();
            //this.getCore().getBus(getBus().getIDBus()).addCustomer(cust);
            this.getBus().addCustomer(cust);
            //gen cas nastupu
            double timeToEnter = getCore().getGens().getEnterBusTime();
            double timeToEnteredCustomer = getEventTime() + timeToEnter;
            // cas cakania
            double timeWaiting = this.getEventTime() - cust.getArrivalTime();
           // System.out.println("Cas cakania pred terminalom 1 : "+timeWaiting);
            //stats
            this.getCore().getStats().getPriemCasCakaniaTerminal1().addIteration(1, timeWaiting);
            //next events
            EndOfTermination1Event termination1 = new EndOfTermination1Event(this.getCore(), timeToEnteredCustomer, cust, this.getBus());
            this.getCore().planEvent(termination1);
        } else {
            ArrivalBusTerminal2Event arrivalTime = new ArrivalBusTerminal2Event(this.getCore(), this.getEventTime(),null ,this.getBus());
            this.getCore().planEvent(arrivalTime);
        }
        
    }
    
}
