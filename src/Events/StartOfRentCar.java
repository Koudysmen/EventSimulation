/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import ResourcePool.Bus;
import SimulationCore.AirRentCarSimCore;
import SimulationCore.Core;

/**
 *
 * @author Michal
 */
public class StartOfRentCar extends AirRentCarEvent {

    public StartOfRentCar(Core core, double eventTime, Customer customer) {
        super(core, eventTime, customer, null);
    }

    @Override
    public void execute() throws Exception {
        
            this.getCore().getRadZaciatokObsluhy().remove(this.getZakaznik());
            this.getCore().getObchodnici().useResoulcePool();

            //stats
            double casCakania = this.getEventTime() - this.getZakaznik().getStartWaitingTime();
            this.getCore().getStats().getPriemCasCakaniaPozicovna().addIteration(1, casCakania);
            
            double time = this.getEventTime() -  this.getCore().getLastUpdateZadanieObj();
            this.getCore().getStats().getPriemPocetObsObchodnikov().addIteration(time, this.getCore().getObchodnici().getNumOfResources());
            this.getCore().setLastUpdateZadanieObj(this.getEventTime());
  
            
// plan end rent a car
            double timeOfRent = this.getCore().getGens().getRentCarTime();
            double timeToRentACar = this.getEventTime() + timeOfRent;
            EndOfRentCar endOfRent = new EndOfRentCar(this.getCore(), timeToRentACar, this.getZakaznik());
            this.getCore().planEvent(endOfRent);
    }
}
