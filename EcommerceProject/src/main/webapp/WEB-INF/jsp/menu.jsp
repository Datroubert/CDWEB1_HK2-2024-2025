<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Site meta -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Free Bootstrap 4 Ecommerce Template</title>
<!-- CSS -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600"
	rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />



</head>
<script>
$(document).on('click', '.btn-add-cart', function(e) {
    e.preventDefault();
    var productId = $(this).data('product-id');
    $.ajax({
        url: '/cart/add',
        method: 'POST',
        data: { },
        success: function(res) {
            if(res.success) {
                $('#cart-count').text(res.cartCount); // Cập nhật badge số lượng
                // Có thể thêm hiệu ứng/thông báo ở đây
                alert('Đã thêm vào giỏ hàng!');
            } else {
                alert('Thêm vào giỏ hàng thất bại!');
            }
        },
        error: function() {
            alert('Có lỗi xảy ra!');
        }
    });
});
</script>
<body>
	<c:if test="${mailSuccess}">
		<script>
			alert("Đã gửi mail");
		</script>
	</c:if>
	<c:if test="${not empty mailError}">
		<script>
			alert("Gửi mail thất bại: ${mailError}");
		</script>
	</c:if>
	<!-- menu bar -->
	<div class="container-fluid">
		<div class="row bg-secondary py-1 px-xl-5"></div>
		<div
			class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex justify-content-between">
			<div class="col-lg-2">
				<a href="home" class="text-decoration-none"> <span
					class="h1 text-uppercase text-primary bg-dark px-2">Phone</span> <span
					class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Store</span>
				</a>
			</div>
			<div class="col-lg-auto d-none d-lg-block bg-light ">
				<div class="d-inline-flex align-items-center h-100 ">
					<a style="text-decoration: none;" class="text-body mr-3 h5"
						href="shop">Danh mục sản phẩm</a>
					<c:if test="${sessionScope.acc.getRole()==1}">
						<a style="text-decoration: none;" class="text-body mr-3 h5"
							href="admin">Admin</a>
					</c:if>




				</div>
			</div>

			<!-- SEARCH AJAX -->
			<div class="col-lg-auto col-6  text-left">
				<form action="search" method="get">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Tìm sản phẩm..." name="search">
						<div class="input-group-append">

							<button class="input-group-text bg-transparent text-primary"
								type="submit">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			
			<div class="col-lg-auto  col-6 text-right d-flex">

				<div class="cart px-3">
					<a href="/cart" class="btn border">
    <i class="fas fa-shopping-cart text-dark"></i>
    <span class="badge" id="cart-count">${cartCount}</span>
</a>

				</div>
				<div class="cart">


					<div>
						<div class="dropdown">
							<a class="btn btn-secondary dropdown-toggle" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">
								${sessionScope.acc.getUsername()} </a>

							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="profile">Thông tin
										tài khoản</a></li>
								<li><a class="dropdown-item"
									href="cart?username=${sessionScope.acc.getUsername()}">Giỏ
										hàng</a></li>
								<li><a class="dropdown-item" href="logoutPerfome">Đăng
										xuất</a></li>
							</ul>
						</div>

					</div>

				</div>


			</div>

		</div>
	</div>
	<!-- Topbar End -->

	<!-- ajax -->

	<script>
	$(function() {
	    $("#searchInput").on("input", function() {
	        var query = $(this).val();
	        if (query === "") {
	            // Có thể load lại toàn bộ sản phẩm nếu muốn
	            $("#product-list").empty();
	            return;
	        }
	        $.ajax({
	            url : "api/searchProduct", // hoặc "/api/searchProduct" nếu controller mapping như vậy
	            method : "GET",
	            data : { keyword : query },
	            success : function(data) {
	                var html = "";
	                if(data.length === 0) {
	                    html = "<div>Không tìm thấy sản phẩm nào.</div>";
	                } else {
	                    data.forEach(function(product) {
	                        html += `
	                        <div class="product-card">
	                            <img src="${product.imgPath}" alt="${product.productName}" style="width:100px;height:100px;">
	                            <div><b>${product.productName}</b></div>
	                            <div>Giá: ${product.price} ₫</div>
	                        </div>
	                        `;
	                    });
	                }
	                $("#product-list").html(html);
	            }
	        });
	    });
	});
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>

</html>