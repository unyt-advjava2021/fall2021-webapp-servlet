/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.servlet;

import com.eltonb.webapp.dao.DepartmentDao;
import com.eltonb.webapp.model.Department;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elton.ballhysa
 */
@WebServlet(name = "DepartmentListServlet", urlPatterns = {"/departments"})
public class DepartmentListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Department List</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"instructors\" method=\"POST\">");
            out.println("Select Department: ");
            out.println("<select name=\"department\">");
            out.println("<option value=\"\"></option>");
            DepartmentDao departmentDao = new DepartmentDao();
            try {
                List<Department> departments = departmentDao.findAll();
                for (Department d : departments) {
                    out.println("<option value=\"" + d.getCode() + "\">");
                    out.println(d.getName());
                    out.println("</option>");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Submit\" />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "List of Departments";
    }// </editor-fold>

}
