/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.dao;

import com.eltonb.webapp.model.Department;
import com.eltonb.webapp.model.Instructor;
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
public class InstructorDao extends BaseDao {
    
    public InstructorDao() {
        super();
    }
    
    public List<Instructor> findAll() throws SQLException {
        List<Instructor> instructors = new ArrayList<>();
        String sql = "select * from instructors i";
        try (Connection conn = newConnection();
             PreparedStatement stat = conn.prepareCall(sql);
             ResultSet rs = stat.executeQuery()) 
        {
            while (rs.next()) {
                Instructor i = newInstructor(rs);
                instructors.add(i);
            }
        }
        return instructors;
    }

    public List<Instructor> findByDepartment(String departmentCode) throws SQLException {
        List<Instructor> instructors = new ArrayList<>();
        String sql = "select * from instructors i where i.department_code = ?";
        try (Connection conn = newConnection();
             PreparedStatement stat = conn.prepareCall(sql)) {
            stat.setString(1, departmentCode);
            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    Instructor i = newInstructor(rs);
                    instructors.add(i);
                }
            }
        }
        return instructors;
    }
    
    private Instructor newInstructor(ResultSet rs) throws SQLException {
        Instructor i = new Instructor();
        i.setId(rs.getInt("id"));
        i.setName(rs.getString("name"));
        i.setSurname(rs.getString("surname"));
        i.setDepartmentCode(rs.getString("department_code"));
        return i;
    }}
