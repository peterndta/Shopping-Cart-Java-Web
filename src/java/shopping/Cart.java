/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Cart {

    private Map<String, ProductDTO> cart;
    private int totalPrice;

    public Cart() {
        totalPrice = 0;
    }

    public Cart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public int getSize() {
        return cart.size();
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean add(ProductDTO pro) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(pro.getProductID())) {
                int currentQuantity = this.cart.get(pro.getProductID()).getQuantity();
                pro.setQuantity(currentQuantity + pro.getQuantity());
            }
            this.cart.put(pro.getProductID(), pro);
            totalPrice += (pro.getPrice() * pro.getQuantity());
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean delete(ProductDTO pro) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(pro.getProductID())) {
                    this.cart.remove(pro.getProductID());
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    //Giống delete chỉ khác replace [ghi đè vô]
    public boolean update(String id, ProductDTO pro) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.replace(id, pro);
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
