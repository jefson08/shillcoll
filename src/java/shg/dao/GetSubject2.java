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
import shg.util.SubjectAndPaper2;

/**
 *
 * @author B Mukhim
 */
public class GetSubject2 extends HttpServlet {

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

        String stream = request.getParameter("v1").trim().toString();
        String course = request.getParameter("v2").trim().toString();
        String rollno = request.getParameter("v3").trim().toString();
        String examid = request.getParameter("v4").trim().toString();
       
         System.out.println("teobnor   "+examid);
        String str = new SubjectAndPaper2().getSubjectAndPaper(getServletContext(), rollno, examid, null, null);
        if (str == null) {
            str = "NO MATCH COMBINATION FOUND FROM THE SELECTED ROLL NO...1 !";
            System.out.println("Error at " + this.getClass());
        }
        out.print(str);

    }
}
