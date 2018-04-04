/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulationCore;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michal
 */
public class EventCoreSim extends EventSim {
    public EventCoreSim(Core core, double eventTime) {
        super(core, eventTime);
    }

    @Override
    public void execute() {
        try {
            sleep( this.getCore().getSleep() );
        } catch (InterruptedException ex) {
            Logger.getLogger(EventCoreSim.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
            if (this.getCore().isIsShowingStatus()) {
                EventCoreSim newEvent = new EventCoreSim(this.getCore(), this.getEventTime()+this.getCore().getPeriod());
                this.getCore().planEvent(newEvent);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(EventCoreSim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
