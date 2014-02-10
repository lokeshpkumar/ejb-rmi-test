/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.ejb.remote;

import com.example.model.User;
import javax.ejb.Remote;

/**
 *
 * @author lokesh
 */
@Remote
public interface GreeterRemote
{
    public String greet(String message);
    
    public String createUser(User user);
}
