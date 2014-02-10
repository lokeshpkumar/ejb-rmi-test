/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lokesh
 */
public class User implements Serializable
{
    private String id;
    private String name;
    private Date birthDay;

    public User(String id, String name, Date birthDay)
    {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
    }

    public User()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getBirthDay()
    {
        return birthDay;
    }

    public void setBirthDay(Date birthDay)
    {
        this.birthDay = birthDay;
    }
    
    
}
