<%-- 
    Document   : addproduct
    Created on : Oct 29, 2021, 12:11:47 PM
    Author     : Admin
--%>

<%@page import="user.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm Sản Phẩm</title>
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
    </head><!--/head-->
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleId())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <header id="header"><!--header-->		
            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 clearfix">
                            <div class="logo pull-left">
                                <a href="ViewController"><img src="images/home/Logo1.jpg" alt="" /></a>
                            </div>					
                        </div>
                        <div class="col-md-8 clearfix">
                            <div class="shop-menu clearfix pull-right">
                                <ul class="nav navbar-nav">                                 
                                    <li><a href="ViewController"><i class="fa fa-user"></i>${sessionScope.LOGIN_USER.fullName}</a></li>
                                    <li><a href="addProduct.jsp"><i class="fa fa-shopping-cart"></i> Thêm sản phẩm mới</a></li>
                                        <c:url var="logoutLink" value="MainController">
                                            <c:param name="action" value="Logout"></c:param>
                                        </c:url>
                                        <c:if test="${sessionScope.LOGIN_USER ne null}">
                                        <li><a href="${logoutLink}"><i class="fa fa-lock"></i> Đăng xuất</a></li>
                                        </c:if>
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
                                    <li><a href="ViewController" class="active">Trang chủ</a></li>								
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


        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block">Thông tin sản phẩm</h2>
                            </div>
                        </div>
                        <div class="row tm-edit-product-row">
                            <form action="MainController" method="POST" class="tm-edit-product-form">
                                <div class="col-xl-12 col-lg-12 col-md-12">
                                    <div class="form-group mb-3">
                                        <label>ID Sản phẩm</label>
                                        <input name="productID" type="text" class="form-control validate" required="" placeholder="ID buộc phải bắt đầu với P-..."/>
                                        ${requestScope.ERROR_PRODUCT.productID}</br>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Tên sản phẩm</label>
                                        <input name="productName" type="text" class="form-control validate" required="" placeholder="Tên..."/>
                                        ${requestScope.ERROR_PRODUCT.productName}</br>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>URL của ảnh</label>
                                        <input name="image" type="text" class="form-control validate" required="" placeholder="Bắt đầu với https:// HOẶC http://"/>
                                        ${requestScope.ERROR_PRODUCT.image}</br>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Loại sản phẩm</label>
                                        <select class="custom-select tm-select-accounts" name="categoryID">
                                            <option selected>Chọn loại sản phẩm</option>
                                            <option value="1">Rau</option>
                                            <option value="2">Củ</option>
                                            <option value="3">Quả</option>
                                        </select>
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label>Giá sản phẩm</label>
                                            <input value="1" name="price" type="number" min="1" class="form-control validate" required=""/>
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label>Số lượng có trong kho</label>
                                            <input value="1" name="quantity" type="number" min="1" class="form-control validate" required=""/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <input type="submit" name="action" class="btn btn-primary btn-block text-uppercase" value="Add Product Now">
                                </div>
                                    
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>   




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