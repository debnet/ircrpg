/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Model;

/**
 *
 * @author Marc
 */
public interface IEntity {

    public Long getId();

    public void setId(Long id);

    public Integer getVersion();

    public void setVersion(Integer version);
    
    public Model getModel();
}
