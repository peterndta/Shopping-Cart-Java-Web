/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shopping.ProductDTO;
import shopping.ProductDAO;
import shopping.ProductError;
import user.UserDAO;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class CreateProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "addProduct.jsp";
    private static final String SUCCESS = "ViewController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {
            String productID = request.getParameter("productID");
            byte[] bytes = request.getParameter("productName").getBytes("ISO-8859-1");
            String productName = new String(bytes,"UTF-8");            
            String image = request.getParameter("image");
            int price = Integer.parseInt(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String categoryID = request.getParameter("categoryID");

            //check validation here: check ID, name, role, pass....
            boolean check = true;
            if (productID.trim().length() == 0 || DBUtils.checkString(productID, "^P-[A-Za-z0-9]+$") == false || productID.length() > 50) {
                productError.setProductID("Phải giống định dạng và nhỏ hơn 50");
                check = false;
            }
            if (productName.trim().length() == 0 || productName.trim().length() > 50) {
                productError.setProductName("Tên phải từ [1,50]");
                check = false;
            }
            if (image.trim().length() == 0) {
                productError.setImage("Không thể để trống");
                check = false;
            }

            //kiem tra ProductID đã tồn tại hay chưa
            ProductDAO dao = new ProductDAO();
            ProductDTO checkProduct = dao.getProInfor(productID);
            if (checkProduct != null) {
                productError.setProductID("ID đã tồn tại!");
                check = false;
            }
            if (check) {
                boolean checkInsert = dao.insertProduct(new ProductDTO(productID, productName, image, price, quantity, categoryID));
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR_PRODUCT", productError);
            }
        } catch (Exception e) {
            log("Error at CreateProductController" + e.toString());
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
