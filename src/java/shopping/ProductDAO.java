/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    //Lấy danh sách sản phẩm từ db
    public List<ProductDTO> getListProduct() throws SQLException {
        List<ProductDTO> ListProduct = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ListProduct = new ArrayList<>();
                String sql = " SELECT productID, productName, image, price, quantity, categoryID "
                        + " FROM tblProducts ";
                pstm = conn.prepareStatement(sql);
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
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean deleteProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DELETE FROM tblProducts WHERE productID =? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, productID);
                check = pstm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public ProductDTO getProInfor(String productID) throws SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT productID, productName, image, price, quantity, categoryID "
                        + " FROM tblProducts "
                        + " WHERE productID =? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, productID);
                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String productName = resultSet.getString("productName");
                    String productImage = resultSet.getString("image");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String categoryID = resultSet.getString("categoryID");
                    product = new ProductDTO(productID, productName, productImage, price, quantity, categoryID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return product;
    }

    public boolean insertProduct(ProductDTO pro) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblProducts(productID, productName, image, price, quantity, categoryID) "
                        + "VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, pro.getProductID());
                stm.setString(2, pro.getProductName());
                stm.setString(3, pro.getImage());
                stm.setInt(4, pro.getPrice());
                stm.setInt(5, pro.getQuantity());
                stm.setString(6, pro.getCategoryID());
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

    public List<Category> getListCategory() throws SQLException {
        List<Category> ListCategory = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ListCategory = new ArrayList<>();
                String sql = " SELECT categoryID, categoryName "
                        + " FROM tblCategory ";
                pstm = conn.prepareStatement(sql);
                resultSet = pstm.executeQuery();
                while (resultSet.next()) {
                    String categoryID = resultSet.getString("categoryID");
                    String categoryName = resultSet.getNString("categoryName");
                    ListCategory.add(new Category(categoryID, categoryName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return ListCategory;
    }

    public List<ProductDTO> getProductByCateID(String categoryID) throws SQLException {
        List<ProductDTO> ListProduct = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ListProduct = new ArrayList<>();
                String sql = " SELECT productID, productName, image, price, quantity, categoryID "
                        + " FROM tblProducts "
                        + " WHERE categoryID = ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, categoryID);
                resultSet = pstm.executeQuery();
                while (resultSet.next()) {
                    String productID = resultSet.getString("productID");
                    String productName = resultSet.getNString("productName");
                    String productImage = resultSet.getString("image");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    ListProduct.add(new ProductDTO(productID, productName, productImage, price, quantity, categoryID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

//    public static void main(String[] args) throws SQLException {
//        ProductDAO dao = new ProductDAO();
//        String pro = "1";
//        List<ProductDTO> list = dao.getProductByCateID(pro);
//        for (ProductDTO o : list) {
//            System.out.println(o);
//        }
//    }
    public boolean updateProduct(ProductDTO pro) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblProducts SET productName =?, image =?, price =?, quantity =?, categoryID =? "
                        + " WHERE productID =? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, pro.getProductName());
                pstm.setString(2, pro.getImage());
                pstm.setInt(3, pro.getPrice());
                pstm.setInt(4, pro.getQuantity());
                pstm.setString(5, pro.getCategoryID());
                pstm.setString(6, pro.getProductID());
                check = pstm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public ProductDTO searchByProductID(String ID) throws SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productID, productName, image, price, quantity FROM tblProducts WHERE productName like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + ID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {

                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    product = new ProductDTO(productID, productName, image, price, quantity);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return product;

    }
    public boolean updateQuantity(ProductDTO pro) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblProducts set quantity=? "
                        + " WHERE productID=? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, pro.getQuantity());
                stm.setString(2, pro.getProductID());
                result = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
