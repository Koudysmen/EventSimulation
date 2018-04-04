/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import ResourcePool.Bus;
import SimulationCore.AirRentCarSimCore;
import SimulationCore.Core;
import SimulationCore.EventSim;

/**
 *
 * @author Michal
 */
public abstract class AirRentCarEvent extends EventSim{
    private Customer customer;
    private Bus bus;
    
    public AirRentCarEvent(Core core, double eventTime, Customer customer, Bus bus) {
        super(core, eventTime);
        this.customer = customer;
        this.bus = bus;
    }

    public Customer getZakaznik() {
        return customer;
    }

    public Bus getBus() {
        return bus;
    }
    
    
    
    

    @Override
    public AirRentCarSimCore getCore() {
        return (AirRentCarSimCore) super.getCore(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
