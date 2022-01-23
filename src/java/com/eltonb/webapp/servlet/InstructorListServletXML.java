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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elton.ballhysa
 */
@WebServlet(name = "InstructorListServletXML", urlPatterns = {"/instructorsXML"})
public class InstructorListServletXML extends HttpServlet {

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
        response.setContentType("text/xml");
        String departmentCode = request.getParameter("department");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<instructors>");
            try {
                List<Instructor> instructors = retrieveInstructors(departmentCode);
                for (Instructor i : instructors) {
                    out.println("<instructor>");
                    out.println("<id>" + i.getId() + "</id>");
                    out.println("<name>" + i.getName() + "</name>");
                    out.println("<surname>" + i.getSurname() + "</surname>");
                    out.println("<departmentCode>" + i.getDepartmentCode() + "</departmentCode>");
                    out.println("</instructor>");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println("</instructors>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "List of Instructors XML";
    }// </editor-fold>

    private List<Instructor> retrieveInstructors(String departmentCode) throws SQLException {
        InstructorDao instructorDao = new InstructorDao();
        if (departmentCode == null || departmentCode.isEmpty()) 
            return instructorDao.findAll();
        else
            return instructorDao.findByDepartment(departmentCode);
    }
}
