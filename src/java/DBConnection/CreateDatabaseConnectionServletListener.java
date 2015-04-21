/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author NIC
 */
public class CreateDatabaseConnectionServletListener implements ServletContextListener {

    Connection con = null;
    ServletContext context = null;
    ConnectionPool connectionPool = null;

    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();
        context.log("Shillong College Office Automation Software Context Called");
        try {

            String dbUser = "";
            String dbPassword = "";
            String dbServer = "";
            String dbName = "";
            String dbJDBCDriver = "";
            String dbURL = "";

            //Retrieve the Connection Parameters from the web.xm file
            dbUser = context.getInitParameter("DBUser");
            dbPassword = context.getInitParameter("DBPassword");
            dbServer = context.getInitParameter("DBserver");
            dbName = context.getInitParameter("DBName");
            dbJDBCDriver = context.getInitParameter("JDBCDriver");
            dbURL = context.getInitParameter("DBURL");

            System.out.println("\n\n\n\n\tdbURL/" + dbURL);

            try {

                connectionPool = new ConnectionPool(dbJDBCDriver, dbURL, dbUser, dbPassword,
                        initialConnections(), maxConnections(), true);

                context.setAttribute("ConnectionPool", connectionPool);

                System.out.println("Connection Pool made with " + initialConnections() + " connections and " + maxConnections() + " maximum connections at " + new java.util.Date());

            } catch (SQLException sqle) {
                System.err.println("Error making pool: " + sqle);
                context.log("Error making pool: " + sqle);
                connectionPool = null;
            }

        } catch (Exception e) {
            context.log(this.getClass() + " Exception   : " + e);
        }
    }

    public void contextDestroyed(ServletContextEvent evt) {
        try {
            connectionPool.closeAllConnections();
            System.out.println("All Connections closed ");
            //con.close();
            //con = null;
        } catch (Exception e) {
            context.log(this.getClass() + " Exception   : " + e);
        }
    }

    protected int initialConnections() {
        return (5);
    }

    /** Override this in subclass to change maximum number of
     *  connections.
     */
    protected int maxConnections() {
        return (15);
    }
}
