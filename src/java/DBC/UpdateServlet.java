/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBC;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import EntityDB.DBController;
import EntityDB.Student;
import java.util.List;
/**
 *
 * @author eiwte
 */
public class UpdateServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String Oldname = request.getParameter("OlderName1");
            String name = request.getParameter("Name1");
            double gpa = Double.parseDouble(request.getParameter("Gpa1"));
            synchronized(getServletContext()){ //*********************
            DBController DB = new DBController();
            Student st;
            List ar = DB.findByName(Oldname);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            
            try {
            st = (Student)ar.get(0);
            getServletContext().setAttribute("ID Check", st.getId()); //*********************
            Thread.sleep(5000);
            st.setName(name);
            st.setGpa(gpa); 
            DB.EditData(st);
            out.println("<head>");
            out.println("<title>Servlet SesstionControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Update Successful </h1>");
            } catch(Exception e){
                out.print("<h1> You cannot Edit this record now </h1>");
            }
            out.println("</body>");
            out.println("</html>");
            }
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

    /**
     * Handles the HTTP <code>POST</code> method.
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
