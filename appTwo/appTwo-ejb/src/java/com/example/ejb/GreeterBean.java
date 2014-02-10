/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.ejb;

import com.example.ejb.remote.GreeterLocal;
import com.example.ejb.remote.GreeterRemote;
import com.example.model.User;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author lokesh
 */
@Stateless
@Remote(GreeterRemote.class)
@Local(GreeterLocal.class)
public class GreeterBean implements GreeterRemote, GreeterLocal
{
    private Logger log = Logger.getLogger("GreeterBean");

    @Override
    public String greet(String message)
    {
        log.info("Incoming message: " + message);
        return "Hello !! " + message;
    }
    
    @Override
    public String createUser(User user)
    {
        if ( user == null )
            return "Null user";
        log.info("Received user: " + user.getName() );
        return user.getName();
    }
    
}
