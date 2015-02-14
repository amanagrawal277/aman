package com.rboneshop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {

    static Vector connections = null;
    static ConnectionPool instance = null;

    public synchronized void removeAllConnections() {
        try {
            if (connections == null) {
                return;
            }
            int sz = connections.size();
            for (int i = 0; i < sz; i++) {
                Connection c = (Connection) connections.elementAt(i);
                c.close();
            }
            connections.removeAllElements();
            connections = null;
        } catch (SQLException sqlE) {
            System.out.println(sqlE);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized void initialize() {
        if (connections == null) {
            try {
                Properties pro = new Properties();
                InputStream in = getClass().getResourceAsStream("Message.properties");
                pro.load(in);
                String userName = pro.getProperty("UserName");
                String password = pro.getProperty("Password");

                String url = pro.getProperty("url");
                int ic = Integer.parseInt(pro.getProperty("MAX_CONNECTIONS"));
                Class.forName(pro.getProperty("drivername")).newInstance();

                connections = new Vector();
                int count = 0;
                while (count < ic) {

                    Connection c = DriverManager.getConnection(url, userName, password);
                    connections.addElement(c);
                    count++;
                }

            } catch (Exception e) {
                System.err.println("Cannot connect to database server");
            }

            System.out.println("over");
        }
    }

    public synchronized Connection getConnection() {
        Connection c = null;
        if (connections == null) {
            return null;
        }
        if (connections.size() > 0) {
            c = (Connection) connections.elementAt(0);
            connections.removeElementAt(0);
        }
        return c;
    }

    public synchronized void putConnection(Connection c) {
        connections.addElement(c);
        notifyAll();
    }

    public  String getPath() {
         String path=new String();
        try {
            Properties pro = new Properties();
            InputStream in = getClass().getResourceAsStream("Message.properties");
            pro.load(in);
            path=pro.getProperty("Path");
            return path;
        } catch (IOException ex) {
        }
        return path;
    }
}
