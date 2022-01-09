<%-- 
    Document   : cart
    Created on : Oct 20, 2021, 1:08:33 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giỏ hàng của bạn</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet"> 
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

    <body>

        <header id="header"><!--header-->		
            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 clearfix">
                            <div class="logo pull-left">
                                <a href="view.jsp"><img src="images/home/Logo1.jpg" alt="" /></a>
                            </div>					
                        </div>
                        <div class="col-md-8 clearfix">
                            <div class="shop-menu clearfix pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href="view.jsp"><i class="fa fa-user"></i>${sessionScope.LOGIN_USER.fullName}</a></li>   
                                    <li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Giỏ hàng</a></li>
                                    <li>       
                                        <c:url var="logoutLink" value="MainController">
                                            <c:param name="action" value="Logout"></c:param>
                                        </c:url>
                                        <a href="${logoutLink}"><i class="fa fa-lock"></i> Đăng xuất</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->

            <div class="header-bottom"><!--header-bottom-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="view.jsp" class="active">Trang chủ</a></li>								
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <form action="MainController">
                                <div class="search_box pull-right">
                                    <input class="click_form" type="text" name="search" value="${param.search}" placeholder="Tìm kiếm"/>
                                    <button class="click_button" type="submit" name="action" value="Search">
                                        <i class="fa fa-search"></i>
                                    </button>                                   
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div><!--/header-bottom-->
        </header><!--/header-->
        <section id="cart_items">
            <div class="container">
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">Sản phẩm</td>
                                <td class="description"></td>
                                <td class="price">Giá</td>
                                <td class="quantity">Số lượng</td>
                                <td class="total">Tổng</td>
                                <td></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                                <c:set var="total" value="${0}"/>
                            <c:forEach items="${sessionScope.CART.getCart().values()}" var="pro">
                            <form class="quantity-product" method="POST" action="MainController">
                                <tr>
                                    <td class="cart_product">
                                        <img src="${pro.image}" alt="image">
                                    </td>
                                    <td class="cart_description">
                                        <h4><a href="">${pro.getProductName()}</a></h4>
                                        <p>Pro ID: ${pro.productID}</p>
                                        <input type="hidden" name="productID" value="${pro.productID}"/>
                                    </td>
                                    <td class="cart_price">
                                        <p>${pro.price} VND</p>
                                    </td>
                                    <td class="cart_quantity">
                                        <div class="cart_quantity_button">
                                            <input class="cart_quantity_input" type="number" name="quantity" min="1" value="${pro.quantity}">

                                        </div>
                                    </td>
                                    <td class="cart_total">
                                        <p class="cart_total_price">${pro.price * pro.quantity} VND</p>
                                        <c:set var="total" value="${total + pro.price * pro.quantity}" />
                                    </td>
                                    <td class="cart_delete">
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="action" value="Remove"></c:param>
                                            <c:param name="productID" value="${pro.productID}"></c:param>
                                        </c:url>
                                        <a class="cart_quantity_delete" href="${deleteLink}"><i class="fa fa-times"></i></a>
                                    </td>
                                    <td>
                                        <input class="btn btn-success btn-sm rounded-0 update" type="submit" name="action" value="Edit">
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section> <!--/#cart_items-->
        <section id="do_action">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="total_area">
                            <ul>
                                <li>Tổng tiền tất cả sản phẩm <span>${total} VND</span></li>
                            </ul>
                            
                            <form action="MainController">                              
                                <input type="hidden" value="${total}" name="total">
                                <input class="btn btn-default check_out" type="submit" value="Check Out" name="action">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section><!--/#do_action-->

        <footer id="footer"><!--Footer-->
            <div class="footer-top">
                <div class="container">
                    <div class="row">
                        <p>Liên hệ số: 028.3622.9900 (7:00 - 21:30)</p>
                    </div>
                </div>	
            </div>		

            <div class="footer-widget">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="single-widget">

                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="#">Trung Tâm Trợ Giúp</a></li>
                                    <li><a href="#">Blo Blo</a></li>
                                    <li><a href="#">Something</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="single-widget">
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="#">Điều Khoản</a></li>
                                    <li><a href="#">Chính Sách Bảo Hành</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="single-widget">
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Copyright</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="single-widget">
                                <h2>Về Rau Nhà Làm</h2>
                                <p>Email: RauNhaLam@gmail.com</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </footer><!--/Footer-->



        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
