/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.apptwo.web;

import com.example.ejb.remote.GreeterLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lokesh
 */
@WebServlet("/greeter")
public class GreeterServlet extends HttpServlet
{
    @EJB(lookup = "java:global/appTwo/appTwo-ejb/GreeterBean!com.example.ejb.remote.GreeterLocal")
    private GreeterLocal ejbLocal;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter w = resp.getWriter();
        w.write("<h1>THIS IS A GREETER SERVLET</h1>");
        w.write("<br />");
        w.write(ejbLocal.greet("Lokesh"));
        w.close();
    }
    
}
