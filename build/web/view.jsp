<%-- 
    Document   : index
    Created on : Oct 20, 2021, 1:04:25 AM
    Author     : Admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Rau Nhà Làm ngon như nhà làm</title>
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
                                    <!--tài khoản-->
                                    <c:if test="${sessionScope.LOGIN_USER ne null}">
                                        <li><a href=""><i class="fa fa-user"></i>${sessionScope.LOGIN_USER.fullName}</a></li>
                                            </c:if>
                                            <c:if test="${sessionScope.LOGIN_USER eq null}">
                                        <li><a href="login.jsp"><i class="fa fa-user"></i> Tài khoản</a></li>
                                        </c:if>
                                    <!--giỏ hàng-->    
                                    <c:if test="${sessionScope.LOGIN_USER ne null}">
                                        <li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Giỏ hàng</a></li>
                                        </c:if>                                            
                                        <c:if test="${sessionScope.LOGIN_USER eq null}">
                                        <li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Giỏ hàng</a></li>
                                        </c:if>
                                    <!--login/out-->
                                    <c:url var="logoutLink" value="MainController">
                                        <c:param name="action" value="Logout"></c:param>
                                    </c:url>
                                    <c:if test="${sessionScope.LOGIN_USER ne null}">
                                        <li><a href="${logoutLink}"><i class="fa fa-lock"></i> Đăng xuất</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.LOGIN_USER eq null}">
                                        <li><a href="login.jsp"><i class="fa fa-lock"></i> Đăng nhập</a></li>
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
                                <!--nút trang chủ-->
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="ViewController" class="active">Trang chủ</a></li>								
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <!--form tìm kiếm-->
                            <form action="MainController">
                                <div class="search_box pull-right">
                                    <input class="click_form" type="text" name="search" value="${param.search}" placeholder="Tìm kiếm"/>
                                    <button class="click_button" type="submit" name="action" value="Search">
                                        <i class="fa fa-search"></i>
                                    </button>                                   
                                </div>
                            </form>
                        </div>
                        <div class="vegeimg">
                            <img src="images/vegetables-fresh.jpg" alt=""/>
                        </div>
                    </div>
                </div>
            </div><!--/header-bottom-->
        </header><!--/header-->

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Category</h2>
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <c:forEach items="${sessionScope.LIST_CATE}" var="cate">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title"><a href="CategoryController?categoryID=${cate.categoryID}">${cate.categoryName}</a></h4>
                                        </div>
                                    </div>				
                                </c:forEach>
                            </div><!--/category-products-->
                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">

                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">Sản Phẩm</h2>
                            <div class="row">
                                <c:forEach items="${sessionScope.LIST_ITEM}" var="pro">
                                    <form action="MainController" method="POST">
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <input type="hidden" name="productId" value="${pro.productID}"/>
                                                        <img src="${pro.image}" alt="product" />
                                                        <input type="hidden" name="Image" value="${pro.image}"/>
                                                        <h2>${pro.price} VND</h2>
                                                        <input type="hidden" name="Price" value="${pro.price}"/>
                                                        <input type="hidden" name="Quantity" value="${pro.quantity}"/>
                                                        <p>${pro.productName}</p>
                                                        <input type="hidden" name="Name" value="${pro.productName}"/>
                                                        <input class="btn btn-default add-to-cart" type="submit" name="action" value="Add"/>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </c:forEach>
                            </div>

                        </div><!--features_items-->				
                    </div>
                </div>
            </div>
        </section>

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