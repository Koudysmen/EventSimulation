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
public class ExitFromBusEvent extends AirRentCarEvent{

    public ExitFromBusEvent(Core core, double eventTime, Customer customer,Bus bus) {
        super(core, eventTime, customer, bus);
    }
    // dam este jeden if planovanie obsluhy ak neni front prazdny
    public void PlanGetOffBus() throws Exception{
        if(!this.getBus().getPeopleInTheBus().isEmpty()){
            //zaciatok cakania v rade pred pozicovnov
            this.getZakaznik().setStartWaitingTime(this.getEventTime());
            // odstranim ho z busu
            this.getBus().getPeopleInTheBus().remove(this.getZakaznik());
            this.getCore().getRadZaciatokObsluhy().add(this.getZakaznik());
            //naplanuje udalost vystup zakaznika ktory je prvy vo fronte
            Customer customer = this.getBus().getPeopleInTheBus().peek();
            StartOfExitFromBusEvent startExit = new StartOfExitFromBusEvent(this.getCore(), this.getEventTime(), customer, this.getBus());
            this.getCore().planEvent(startExit);
           
        } else{
            ArrivalBusTerminal1Event arrivalTime = new ArrivalBusTerminal1Event(this.getCore(), this.getEventTime(), this.getBus());
            this.getCore().planEvent(arrivalTime);
        }
        if((!this.getCore().getRadZaciatokObsluhy().isEmpty() && this.getCore().getObchodnici().canUse())){
            StartOfRentCar startRent = new StartOfRentCar(this.getCore(), this.getEventTime(), this.getZakaznik());
            this.getCore().planEvent(startRent);
        } else{
//            double time = this.getEventTime() - this.getCore().getLastUpdateZadanieObj();
//            this.getCore().getStats().getPriemDlzkaRaduPozicovna().addIteration(time, this.getCore().getRadZaciatokObsluhy().size());
//            this.getCore().setLastUpdateZadanieObj(this.getEventTime());
        }
    }
    
    

    @Override
    public void execute() throws Exception {
        PlanGetOffBus();
       // PlanRentCar();
        
    }
    
}
