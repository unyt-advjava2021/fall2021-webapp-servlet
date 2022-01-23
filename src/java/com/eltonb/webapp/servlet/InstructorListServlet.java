/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.servlet;

import com.eltonb.webapp.dao.InstructorDao;
import com.eltonb.webapp.model.Instructor;
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
@WebServlet(name = "InstructorListServlet", urlPatterns = {"/instructors"})
public class InstructorListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String departmentCode = request.getParameter("department");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Instructors</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>List of Instructors: " + departmentCode + "</h1>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<td>Id</td>");
            out.println("<td>Name</td>");
            out.println("<td>Surname</td>");
            out.println("<td>Department</td>");
            out.println("</thead>");
            
            try {
                List<Instructor> instructors = retrieveInstructors(departmentCode);
                for (Instructor i : instructors) {
                    out.println("<tr>");
                    out.println("<td>" + i.getId() + "</td>");
                    out.println("<td>" + i.getName() + "</td>");
                    out.println("<td>" + i.getSurname() + "</td>");
                    out.println("<td>" + i.getDepartmentCode() + "</td>");
                    out.println("</tr>");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "List of Instructors";
    }// </editor-fold>

    private List<Instructor> retrieveInstructors(String departmentCode) throws SQLException {
        InstructorDao instructorDao = new InstructorDao();
        if (departmentCode == null || departmentCode.isEmpty()) 
            return instructorDao.findAll();
        else
            return instructorDao.findByDepartment(departmentCode);
    }

}
