/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulationCore;

/**
 *
 * @author Michal
 */
public abstract class EventSim {
    
    public abstract void execute() throws Exception;
    
   
    private Core core;
    private double eventTime;

    public EventSim(Core core, double eventTime) {
        this.core = core;
        this.eventTime = eventTime;
    }

    public double getEventTime() {
        return eventTime;
    }

    public Core getCore() {
        return core;
    }
}
