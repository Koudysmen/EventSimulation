/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulationCore;

import ResourcePool.Employers;
import Events.ArrivalBusTerminal1Event;
import Events.ArrivalCustomerTerminal1Event;
import Events.ArrivalCustomerTerminal2Event;
import Events.Customer;
import Generators.GenerateValue;
import ResourcePool.Bus;
import Statistics.ReplicationStatistics;
import Statistics.Statistics;
import java.util.LinkedList;

/**
 *
 * @author Michal
 */
public class AirRentCarSimCore extends Core {
    private Generators.GenerateValue gens;
    private Statistics stats;
    private ReplicationStatistics replicationStats;
    
    
    
    // Model vars
    private int maxPocetObchodnikov;
    private int maxPocetBusov;
    
    private Employers obchodnici;
    private Bus bus;

    private LinkedList<Customer> radTerminal1;
    private LinkedList<Customer> radTerminal2;
    private LinkedList<Customer> radZaciatokObsluhy;
    private LinkedList<Bus> AllBus;
    
    private double lastUpdateZadanieObj;
    private double lastUpdateNumberOfPassager;
    private double lastUpdateZacatiePrepravy;
    
    // Variations
    private int obchodniciMin;
    private int obchodniciMax;
    private int minNumberOfBus;
    private int maxNumberOfBus;
    
    private int pocetVstupZakaznikov;
    private int IDCustomer;
    private int IDBus;

    public AirRentCarSimCore() {
        this.gens = new GenerateValue();
        this.stats = new Statistics();
        this.replicationStats = new ReplicationStatistics();
        this.pocetVstupZakaznikov = 0;
    }    
    
    public void init(int maxPocetObchodnikov, int maxPocetBusov) {
        this.maxPocetObchodnikov = maxPocetObchodnikov;
        this.maxPocetBusov = maxPocetBusov;
    }
    
    public void runVariationsBus(int busMin, int busMax) {
        this.minNumberOfBus = busMin;
        this.maxNumberOfBus = busMax;
        
        if (busMax != 0) {
            for (int i = busMin; i <= busMax; i++) {
                init(maxPocetObchodnikov, i);
                runReplication();
            }
        }
    }
    
    public void runVariationsEmployers(int obchodniciMin, int obchodniciMax) {
        this.obchodniciMin = obchodniciMin;
        this.obchodniciMax = obchodniciMax;
        if (obchodniciMax != 0) {
            for (int i = obchodniciMin; i <= obchodniciMax; i++) {
                init(i, maxPocetBusov);
                runReplication();
            }
        }
          
    }

    @Override
    public void beforeReplications() {
        this.replicationStats = new ReplicationStatistics();
    }

    @Override
    public void afterReplications() {
        
    }

    @Override
    public void beforeSimulation() {
        // Stats
        this.stats = new Statistics();
        
        
        // Init
        //this.bus = new Bus();
        this.obchodnici = new Employers(maxPocetObchodnikov);
        this.radTerminal1 = new LinkedList<Customer>();
        this.radTerminal2 = new LinkedList<Customer>();
        this.radZaciatokObsluhy = new LinkedList<Customer>();
        this.AllBus = new LinkedList<>();
        this.IDCustomer = 0;
        this.IDBus = 0;
        this.lastUpdateZadanieObj = 0;
        this.lastUpdateNumberOfPassager = 0;
        this.lastUpdateZacatiePrepravy = 0;
        
        // naplanujem prichod autobusu na terminal1
        try {
            for(int i = 1 ; i <= maxPocetBusov; i++){
                this.bus = new Bus(i);
                ArrivalBusTerminal1Event arrivalBus = new ArrivalBusTerminal1Event(this, this.initialTime, bus);
                this.planEvent(arrivalBus);
               // this.initialTime += 650;
            }
        } catch (Exception ex) {
            System.out.println("Error planovanie eventu");
        }
        this.initialTime = 0;
        
        // Naplanujem prvy prichod terminal1
        try {
            ArrivalCustomerTerminal1Event firstArrival1 = new ArrivalCustomerTerminal1Event(this, this.initialTime);
            this.planEvent(firstArrival1);
        } catch (Exception ex) {
            System.out.println("Error planovanie eventu");
        }
        // Naplanujem prvy prichod terminal2
        try {
            ArrivalCustomerTerminal2Event firstArrival2 = new ArrivalCustomerTerminal2Event(this, this.initialTime);
            this.planEvent(firstArrival2);
        } catch (Exception ex) {
            System.out.println("Error planovanie eventu");
        }
    }

    @Override
    public void afterSimulation() {
        this.replicationStats.getPriemCasCakaniaTerminal1().addIteration(1, this.stats.getPriemCasCakaniaTerminal1().result());
        this.replicationStats.getPriemCasCakaniaTerminal2().addIteration(1, this.stats.getPriemCasCakaniaTerminal2().result());
        this.replicationStats.getPriemCasCakaniaPozicovna().addIteration(1, this.stats.getPriemCasCakaniaPozicovna().result());
        
        this.replicationStats.getPriemCasVSysteme().addIteration(1, this.stats.getPriemCasVSysteme().result());
        
        
        this.replicationStats.getPriemDlzkaRaduTerminal1().addIteration(1, this.stats.getPriemDlzkaRaduTerminal1().result());
        this.replicationStats.getPriemDlzkaRaduTerminal2().addIteration(1, this.stats.getPriemDlzkaRaduTerminal2().result());
        this.replicationStats.getPriemDlzkaRaduPozicovna().addIteration(1, this.stats.getPriemDlzkaRaduPozicovna().result());
        // with confidence value
        this.replicationStats.getIsCasVSysteme().addIteration(this.getStats().getPriemCasVSysteme().result());
        
        
        this.replicationStats.getPriemPocetObsObchodnikov().addIteration(1, this.stats.getPriemPocetObsObchodnikov().result());
        this.replicationStats.getPriemPocetObsBusov().addIteration(1, this.stats.getPriemPocetObsBusov().result());
    }

    @Override
    public void setMaxSimulationTime(double maxSimulationTime) {
        super.setMaxSimulationTime(maxSimulationTime); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public GenerateValue getGens() {
        return gens;
    }

    public Employers getObchodnici() {
        return obchodnici;
    }

    public void setMaxPocetBusov(int maxPocetBusov) {
        this.maxPocetBusov = maxPocetBusov;
    }

    public Bus getBus(int ID) {
        return bus;
    }

    public LinkedList<Customer> getRadTerminal1() {
        return radTerminal1;
    }
  

    public LinkedList<Customer> getRadTerminal2() {
        return radTerminal2;
    }

    public LinkedList<Customer> getRadZaciatokObsluhy() {
        return radZaciatokObsluhy;
    }

    public Statistics getStats() {
        return stats;
    }

    public ReplicationStatistics getReplicationStats() {
        return replicationStats;
    }

    public int getMaxPocetObchodnikov() {
        return maxPocetObchodnikov;
    }

    public int getMaxPocetBusov() {
        return maxPocetBusov;
    }

    public double getLastUpdateZadanieObj() {
        return lastUpdateZadanieObj;
    }

    public double getLastNumberOfPassager() {
        return lastUpdateNumberOfPassager;
    }

    public double getLastUpdateZacatiePrepravy() {
        return lastUpdateZacatiePrepravy;
    }

    public void setPocetVstupZakaznikov() {
        this.pocetVstupZakaznikov++;
    }
    
    
    
    

    public void setLastUpdateZadanieObj(double lastUpdateZadanieObj) {
        this.lastUpdateZadanieObj = lastUpdateZadanieObj;
    }

    public void setLastUpdateNumberOfPassager(double lastUpdateZacatieOpravy) {
        this.lastUpdateNumberOfPassager = lastUpdateZacatieOpravy;
    }

    public void setLastUpdateZacatiePrepravy(double lastUpdateZacatiePrepravy) {
        this.lastUpdateZacatiePrepravy = lastUpdateZacatiePrepravy;
    }

    public void setIdCustomer(Customer cust) {
        IDCustomer += 1;
        cust.setIdCustomer(IDCustomer);
    }
    
    public void setIdBuss(Bus bus) {
        IDBus += 1;
        bus.setIDBus(IDBus);
    }

    
     
    
    
}
