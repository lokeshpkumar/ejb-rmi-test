/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.eap.server;

import com.example.ejb.remote.GreeterRemote;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author lokesh
 */
@Startup
@Singleton
public class AppOneManager 
{
    private Logger log = Logger.getLogger("AppOneManager");
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
            log.log(Level.SEVERE,"Could not create InitialContext('appOne')", e);
        }
    }
    
    @PostConstruct
    public void startUp()
    {
        log.info("============== Chitti papa ===============");
        createAppOneContext();
        if ( ctx != null )
        {
            try
            {
                GreeterRemote remote = (GreeterRemote) ctx.lookup("appTwo/appTwo-ejb//GreeterBean!" + GreeterRemote.class.getName());
                log.info("FROM THE REMOTE BEAN: " + remote.greet(" I am Lokesh") );
            }
            catch (NamingException ex)
            {
                Logger.getLogger(AppOneManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Remove
    public void destroy()
    {
        log.info("======== Destroying ========");
    }
    
}
