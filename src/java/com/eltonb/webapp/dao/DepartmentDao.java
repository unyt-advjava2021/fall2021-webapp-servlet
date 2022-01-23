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

/**
 *
 * @author elton.ballhysa
 */
public class DepartmentDao extends BaseDao {
        
    public DepartmentDao() {
        super();
    }
    
    public List<Department> findAll() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "select * from departments";
        try (Connection conn = newConnection();
             PreparedStatement stat = conn.prepareCall(sql);
             ResultSet rs = stat.executeQuery()) {
            while (rs.next()) {
                Department d = newDepartment(rs);
                departments.add(d);
            }
        }
        return departments;
    }
    
    private Department newDepartment(ResultSet rs) throws SQLException {
        Department d = new Department();
        d.setCode(rs.getString("code"));
        d.setName(rs.getString("name"));
        d.setFacultyCode("faculty_code");
        d.setChairId(rs.getInt("chair_id"));
        return d;
    }
    
}
