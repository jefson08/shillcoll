/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shg.util.SubjectAndPaper1;

/**
 *
 * @author B Mukhim
 */
public class GetSubject1 extends HttpServlet {


    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        
        String rollno = request.getParameter("v1").trim().toString();
        String stat = request.getParameter("v2").trim().toString();
        String yos = request.getParameter("v3").trim().toString();
       // System.out.println("teobnor   "+yos);
        String str = new SubjectAndPaper1().getSubjectAndPaper(getServletContext(), rollno, stat,yos, null, null);
        if (str == null) {
            str = "NO MATCH COMBINATION FOUND FROM THE SELECTED ROLL NO... ABORT!";
            System.out.println("Error at " + this.getClass());
        }
        out.print(str);
        
    }
}
