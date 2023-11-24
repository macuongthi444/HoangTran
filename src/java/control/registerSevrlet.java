/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;

/**
 *
 * @author hoang
 */
@WebServlet(name="registerSevrlet", urlPatterns={"/register"})
public class registerSevrlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String personID = request.getParameter("personID");
                      String fname = request.getParameter("fname");
                      String lname = request.getParameter("lname");
                      String login = request.getParameter("loginID");
                      String password = request.getParameter("password");
                      String repassword = request.getParameter("repassword");
                      String birthDate= request.getParameter("dob");
                      PrintWriter out = response.getWriter();
                      if(personID.equals("") ||fname.equals("")||lname.equals("")||login.equals("")||password.equals("")||repassword.equals("")||birthDate.equals("")){
                          request.setAttribute("error0", "Không được để trống");
                          response.sendRedirect("register.jsp");
                      }
                      else if(!password.equals(repassword)){
                           request.setAttribute("error1", "Lỗi mật khẩu không trùng nhau");
                          response.sendRedirect("register.jsp");
                      }else{                        
                          DAO dao = new DAO();
                          Person a= dao.checkregister(personID, login);
                          if(a==null){
                              //register
                              dao.register(personID, fname, lname, login, password, birthDate);
                              response.sendRedirect("home");
                          }else{
                              //loi
                              request.setAttribute("error2", "Lỗi trùng ID hoặc loginID của tài khoảng khác");
                              response.sendRedirect("register.jsp");
                          }
                          
                      }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
