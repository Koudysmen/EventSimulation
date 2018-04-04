/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulationCore;

import static java.lang.Thread.sleep;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michal
 */
public abstract class Core {
    public abstract void beforeReplications();
    public abstract void afterReplications();
    public abstract void beforeSimulation();
    public abstract void afterSimulation();
    
    // Core vars
    private long period;
    private long sleep;
    private List<IGuiViewr> delegates;
    
    
    // Replications vars
    protected int numOfReplications;
    private boolean runningReplications; 
    private int indexReplication;
    
    // Simulation vars
    private double currTime;
    private double maxSimulationTime;
    private PriorityQueue<EventSim> timeLine;
    public  int initialTime = 0;
    private boolean runningSimulation;
    private boolean pausedSimulation;
    
    private boolean isShowingStatus;
    

    public Core() {
        this.runningReplications = false;
        this.indexReplication = 0;
        this.runningSimulation = false;
        this.pausedSimulation = false;
        this.delegates = new LinkedList();
        this.isShowingStatus = true;
    }
    
    public void runReplication(){
        runningReplications = true;
        beforeReplications();
        for (indexReplication = 1; indexReplication < numOfReplications+1; indexReplication++) {
            runSimulation();
            if (this.runningReplications == false ) {
                System.out.println("---- Stop replications ----");
                break;
            }
        }
        runningReplications = false;
        afterReplications();
        this.renderAfterReplications();
    }

    private void runSimulation() {
        this.runningSimulation = true;
        
        // Init simulation
        InitializeSimulation();
        beforeSimulation();
        EventSim currEvent;
        
        // Main loop
        while( (this.currTime <= maxSimulationTime
        || !this.timeLine.isEmpty())
        && this.runningSimulation ) {
            
            // Pausing
            while( this.pausedSimulation ) try {
            sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            currEvent = this.timeLine.poll();
            this.currTime = currEvent.getEventTime();
            try {
                currEvent.execute();
            } catch (Exception ex) {
                Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           if (this.isShowingStatus) {
                this.renderGUI();
            }
            
        }
        this.runningSimulation = false;
        // After simulation
        this.renderGUI();
        this.afterSimulation();
    }
    
    public boolean planEvent(EventSim event) throws Exception {
     if(this.currTime <= maxSimulationTime){
        if( this.currTime <= event.getEventTime() ) {
            this.timeLine.add(event);
        } else {
            throw new Exception("Event Time can not be bigger that simulation time");
        }
     }
        return true;
    }
        
    
    private void InitializeSimulation(){
        // Set curr time
        this.currTime = 0;
        
        // Init timeline;
        this.timeLine = new PriorityQueue<EventSim>(10, new EventComparator() );
        if (this.isShowingStatus) {
            this.timeLine.add(new EventCoreSim(this, this.currTime));
        }    
    }
    
    
    static class EventComparator implements Comparator<EventSim>
    {
        public int compare(EventSim e1, EventSim e2)
        {
            return Double.compare(e1.getEventTime(), e2.getEventTime());
        }
    }
    
     public void registerDelegate(IGuiViewr delegate) {
        delegates.add(delegate);
    }
    
      private void renderGUI() {
        for (IGuiViewr delegate : delegates) {
            delegate.render(this);
        }
    }
      
       private void renderAfterReplications() {
        for (IGuiViewr delegate : delegates) {
            delegate.renderAfterReplications(this);
        }
    }
     
    public void start() {
        this.pausedSimulation = false;
    }
        
    public void stop() {
        this.runningReplications = false;
    }
    
    public void pause() {
        this.pausedSimulation = true;
        
    }
    
    public void cancelReplications() {
        this.runningReplications = false;
    }

    public boolean isRunningSimulation() {
        return runningSimulation;
    }

    public boolean isPausedSimulation() {
        return pausedSimulation;
    }
    
    public double getSimulationTime() {
        return currTime;
    }


    public int getIndexReplication() {
        return indexReplication;
    }
    
    public int getCurrentRepp(){
        return (int) currTime / (int) maxSimulationTime;
    }

    public boolean isIsShowingStatus() {
        return isShowingStatus;
    }
    
    public void turnOnShowing() {
        this.isShowingStatus = true;
        this.timeLine.add(new EventCoreSim(this, this.currTime));
        
    }
    
    public void turnOffShowing() {
        this.isShowingStatus = false;
    }

    public void setTimer(long period, long sleep) {
        this.sleep = sleep;
        this.period = period;
    }

    public long getPeriod() {
        return period;
    }

    public long getSleep() {
        return sleep;
    }

    public int getNumOfReplications() {
        return numOfReplications;
    }

    
    
    public void setNumOfReplications(int numOfReplications) {
        this.numOfReplications = numOfReplications;
    }
    
    

    public void setMaxSimulationTime(double maxSimulationTime) {
        this.maxSimulationTime = maxSimulationTime;
    }    
}
