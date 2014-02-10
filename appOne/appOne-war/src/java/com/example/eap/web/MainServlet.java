/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.eap.web;

import com.example.ejb.remote.GreeterRemote;
import com.example.model.User;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lokesh
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet
{

    private Context ctx;
    
    private void createAppOneContext()
    {
        final Properties ejbClientContextProps = new Properties();
        ejbClientContextProps.put("endpoint.name", "appMain->appOne_endpoint");
        // --- Property to enable scoped EJB client context which will be tied
        // to the JNDI context ---
        ejbClientContextProps.put("org.jboss.ejb.client.scoped.context", true);
        // Property which will handle the ejb: namespace during JNDI lookup
        ejbClientContextProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        ejbClientContextProps.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        // add a property which lists the connections that we are configuring.
        // In this example, we are just configuring a single connection.
        final String connectionName = "appOneConnection";
        ejbClientContextProps.put("remote.connections", connectionName);
        // add the properties to connect the app-one host 
        ejbClientContextProps.put("remote.connection." + connectionName + ".host", "localhost");
        ejbClientContextProps.put("remote.connection." + connectionName + ".port", "4547");
        ejbClientContextProps.put("remote.connection." + connectionName + ".username", "lokesh");
        ejbClientContextProps.put("remote.connection." + connectionName + ".password", "P@ssw0rd");

        try
        {
            ctx = (Context) new InitialContext(ejbClientContextProps).lookup("ejb:");
        }
        catch (NamingException e)
        {
            Logger.getLogger("MainServlet").log(Level.SEVERE,"Could not create InitialContext('appOne')", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        Writer w = resp.getWriter();
        w.write("<h1>Hello Jboss EAP 6.2</h1>");
        
        if ( ctx == null )
        {
            createAppOneContext();
            w.write("<br/> Creating the context ....");
        }
        
        try
        {
            GreeterRemote remote = (GreeterRemote) ctx.lookup("appTwo/appTwo-ejb//GreeterBean!" + GreeterRemote.class.getName());
            w.write("<br/> Message from remote bean: " + remote.greet("This is a remote call test ......."));
            
            User user = new User("lokesh", "Lokesh Kumar Padhnavis", new Date() );
            w.write("<br/> Creating user: " + remote.createUser(user) );
        }
        catch( NamingException ne )
        {
            Logger.getLogger("MainServlet").log(Level.SEVERE, "No lookup", ne);
        }
        
        w.close();
    }
    
}
