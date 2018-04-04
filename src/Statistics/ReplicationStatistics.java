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
public class ReplicationStatistics extends Statistics{
    private IntervalConfidence  isCasVSysteme;
   
    public ReplicationStatistics() {
        this.isCasVSysteme = new IntervalConfidence();
    }

    public IntervalConfidence getIsCasVSysteme() {
        return isCasVSysteme;
    }
  
}