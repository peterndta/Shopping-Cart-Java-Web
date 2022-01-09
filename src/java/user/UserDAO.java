/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import shopping.ProductDTO;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDAO {

    // Kiểm tra thông tin để User đăng nhập
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT fullName, roleID "
                        + " FROM tblUsers "
                        + " WHERE userID =? AND password =? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);
                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String fullName = resultSet.getString("fullName");
                    String roleID = resultSet.getString("roleID");
                    user = new UserDTO(userID, fullName, roleID);
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    // Lấy thông tin User từ db lên để kiểm tra tồn tại hay chưa
    public UserDTO getUserInfor(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT fullName, roleID "
                        + " FROM tblUsers "
                        + " WHERE userID= ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, userID);
                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String fullName = resultSet.getString("fullName");
                    String roleID = resultSet.getString("roleID");
                    user = new UserDTO(userID, fullName, "", "", "", roleID);
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    // Thêm thông tin user vào db sau khi Create User
    public boolean insertUser(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUsers(userID, fullName, address, phone, password, roleID) "
                        + "VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserId());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getAddress());
                stm.setString(4, user.getPhone());
                stm.setString(5, user.getPassword());
                stm.setString(6, user.getRoleId());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

//    public List<Product> getListProduct(){
//        Connection conn = null;
//        PreparedStatement pstm = null;
//        ResultSet resultSet = null;
//        List<Product> ListProduct = new ArrayList<>();
//        String sql = " SELECT productID, productName, image, price, quantity, categoryID "
//                        + " FROM tblProducts ";
//        try {
//            conn=new DBUtils().getConnection();
//            pstm = conn.prepareStatement(sql);
//            resultSet = pstm.executeQuery();
//            while (resultSet.next()) {
//                ListProduct.add(new ProductDTO(resultSet.getString(1),
//                resultSet.getString(2),
//                resultSet.getString(3),
//                resultSet.getFloat(4),
//                resultSet.getInt(5),
//                resultSet.getString(6)));
//            }
//        } catch (Exception e) {
//        }
//        return ListProduct;
//    }
//    
    //Test getListProduct

    
    //lấy thông tin search
    public List<ProductDTO> getSearchList(String searchFullName) throws SQLException {
        List<ProductDTO> ListProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT productID, productName, image, price, quantity, categoryID "
                        + " FROM tblProducts "
                        + " WHERE productName like ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + searchFullName + "%");
                resultSet = pstm.executeQuery();
                while (resultSet.next()) {
                    String productID = resultSet.getString("productID");
                    String productName = resultSet.getNString("productName");
                    String image = resultSet.getString("image");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String categoryID = resultSet.getString("categoryID");
                    ListProduct.add(new ProductDTO(productID, productName, image, price, quantity, categoryID));
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return ListProduct;
    }
}
