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
public class EndOfRentCar extends AirRentCarEvent{

    public EndOfRentCar(Core core, double eventTime, Customer customer) {
        super(core, eventTime, customer, null);
    }

    @Override
    public void execute() throws Exception {
    //uvolnenie resource pool
        this.getCore().getObchodnici().releaseResourcePool();
    // Stat cas v systeme
         this.getCore().getStats().getPriemCasVSysteme().addIteration(1, this.getEventTime() - this.getZakaznik().getArrivalTime());
    // po uvolneni zamestnanca ci nie je volne miesto
        Customer cust = this.getCore().getRadZaciatokObsluhy().peek();
         if(!this.getCore().getRadZaciatokObsluhy().isEmpty() &&
            this.getCore().getObchodnici().canUse() 
                 /*&& this.getEventTime() >= cust.getStartWaitingTime()*/){
            StartOfRentCar startRent = new StartOfRentCar(this.getCore(), this.getEventTime(), cust);
            this.getCore().planEvent(startRent);
         }
         double time = this.getEventTime() - this.getCore().getLastUpdateZadanieObj();
         this.getCore().getStats().getPriemDlzkaRaduPozicovna().addIteration(time, this.getCore().getRadZaciatokObsluhy().size());
         this.getCore().setLastUpdateZadanieObj(this.getEventTime());
        
    }
    
}
