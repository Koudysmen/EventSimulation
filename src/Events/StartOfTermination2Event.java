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
    public class StartOfTermination2Event extends AirRentCarEvent{

    public StartOfTermination2Event(Core core, double eventTime, Customer customer, Bus bus) {
        super(core, eventTime, customer , bus);
    }

    @Override
    public void execute() throws Exception {
        Customer custumer = this.getCore().getRadTerminal2().peek();
        if((!this.getCore().getRadTerminal2().isEmpty()) && (this.getBus().canUse())
                && this.getEventTime() >= custumer.getArrivalTime()){
            //pridam zakaznika ktorz je prvy vo fronte a odstranim ho z fronty
            Customer cust = this.getCore().getRadTerminal2().poll();
            //pridam ho do autobusu
            this.getBus().addCustomer(cust);     
            //gen cas nastupu
            double timeToEnter = getCore().getGens().getEnterBusTime();
            double timeToEnteredCustomer = getEventTime() + timeToEnter;
            // cas cakania
            double timeWaiting = this.getEventTime() - cust.getArrivalTime();
            //System.out.println("Cas cakania pred terminalom 2 : "+timeWaiting);
            //stats
             this.getCore().getStats().getPriemCasCakaniaTerminal2().addIteration(1, timeWaiting);
            //next events
            EndOfTermination2Event termination2 = new EndOfTermination2Event(this.getCore(), timeToEnteredCustomer, cust ,this.getBus());
            this.getCore().planEvent(termination2);
        } else {
            double lastChange = this.getEventTime() - this.getCore().getLastNumberOfPassager();
            this.getCore().getStats().getPriemPocetObsBusov().addIteration(lastChange, this.getBus().NumberOfPassager());
            this.getCore().setLastUpdateNumberOfPassager(this.getEventTime());
            //planning arrival bus to rent car
            ArrivalBusRentCarEvent arrivalTime = new ArrivalBusRentCarEvent(this.getCore(), this.getEventTime(), this.getBus());
            this.getCore().planEvent(arrivalTime);
        }
        
    }
    }
    

