/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

/**
 *
 * @author Michal
 */
public class Statistics {
    private WeightedAverage casCakaniaTerminal1;
    private WeightedAverage casCakaniaTerminal2;
    private WeightedAverage casCakaniaPozicovna;
    
    private WeightedAverage casVSysteme;
    
    private WeightedAverage priemDlzkaRaduTerminal1;
    private WeightedAverage priemDlzkaRaduTerminal2;
    private WeightedAverage priemDlzkaRaduPozicovna;
    
    private WeightedAverage pocetObsObchodnikov;
    private WeightedAverage pocetObsBusov;

    

    public Statistics() {
        casCakaniaTerminal1 = new WeightedAverage();
        casCakaniaTerminal2 = new WeightedAverage();
        casVSysteme = new WeightedAverage();
        casCakaniaPozicovna = new WeightedAverage();
        priemDlzkaRaduTerminal1 = new WeightedAverage();
        priemDlzkaRaduTerminal2 = new WeightedAverage();
        priemDlzkaRaduPozicovna = new WeightedAverage();
        pocetObsObchodnikov = new WeightedAverage();
        pocetObsBusov = new WeightedAverage();
    }

    public WeightedAverage getPriemCasCakaniaTerminal1() {
        return casCakaniaTerminal1;
    }

    public WeightedAverage getPriemCasCakaniaTerminal2() {
        return casCakaniaTerminal2;
    }

    public WeightedAverage getPriemCasCakaniaPozicovna() {
        return casCakaniaPozicovna;
    }

    public WeightedAverage getPriemCasVSysteme() {
        return casVSysteme;
    }

    public WeightedAverage getPriemDlzkaRaduTerminal1() {
        return priemDlzkaRaduTerminal1;
    }

    public WeightedAverage getPriemDlzkaRaduTerminal2() {
        return priemDlzkaRaduTerminal2;
    }

    public WeightedAverage getPriemDlzkaRaduPozicovna() {
        return priemDlzkaRaduPozicovna;
    }

    public WeightedAverage getPriemPocetObsObchodnikov() {
        return pocetObsObchodnikov;
    }

    public WeightedAverage getPriemPocetObsBusov() {
        return pocetObsBusov;
    }
    
    
    
}
