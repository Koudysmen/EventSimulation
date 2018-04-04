/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulationCore;

import SimulationCore.Core;

/**
 *
 * @author Michal
 */
public interface IGuiViewr {
     void render(Core simulation);
     void renderAfterReplications(Core simulation);

}
