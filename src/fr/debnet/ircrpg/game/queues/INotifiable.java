/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game.queues;

import fr.debnet.ircrpg.models.Result;

/**
 *
 * @author Marc
 */
public interface INotifiable {
    
    void notify(Result result);
}
