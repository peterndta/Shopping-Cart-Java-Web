/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserDAO;
import user.UserDTO;
import user.UserError;

/**
 *
 * @author Admin
 */
public class CreateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String roleID = "US";
            String confirm = request.getParameter("confirm");
            //check validation here: check ID, name, role, pass....
            boolean check = true;            
            if (userID.length() < 2 || userID.length() > 30) {
                userError.setUserID("Tên đăng nhập phải từ [2,30]");
                check = false;
            }

            if (fullName.length() < 5 || fullName.length() > 50) {
                userError.setFullName("Họ và Tên phải từ [5,50]");
                check = false;
            }
            if (address.length() < 2 || address.length() > 50) {
                userError.setFullName("Họ và Tên phải từ [2,50]");
                check = false;
            }
            if (phone.length() < 1 || phone.length() > 11) {
                userError.setFullName("Họ và Tên phải từ [1,11]");
                check = false;
            }

            if (password.length() < 1 || password.length() > 32) {
                userError.setPassword("Password must be in [1,32]");
                check = false;
            }
            if (!confirm.equals(password)) {
                userError.setConfirm("Password khong giong!");
                check = false;
            }
            //kiem tra UserId
            UserDAO dao = new UserDAO();
            UserDTO checkUser = dao.getUserInfor(userID);
            if (checkUser != null) {
                userError.setUserID("Tên đăng nhập đã tồn tại!");
                check = false;
            }
            if (check) {
                boolean checkInsert = dao.insertUser(new UserDTO(userID, fullName, address, phone, password, roleID));
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR_USER", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
