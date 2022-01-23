/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.dao;

import com.eltonb.webapp.model.Department;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author elton.ballhysa
 */
public class BaseDao {
    
    private Properties props;
    
    public BaseDao() {
        props = new Properties();
        try (InputStream is = BaseDao.class.getResourceAsStream("application.properties")) {
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Connection newConnection() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.pass")
        );
    }
    
}
