var app = angular.module('myAppTrangChu',['ngRoute']);
app.config(function($routeProvider){
    $routeProvider
    .when('/', {
        templateUrl: '../layoutTrangChu/LayoutTrangChu.html'
    })
        .when('/Trang chủ', {
            templateUrl: '../layoutTrangChu/LayoutTrangChu.html'
        })
        .when('/Thông báo', {
            templateUrl: '../layoutTrangChu/ThongBao.html'
        })
        .when('/Giỏ hàng', {
            templateUrl: '../layoutTrangChu/gioHang.html'
        })
        .when('/Đăng nhập', {
            templateUrl: '../layout/dangNhap.html'
        })
        .when('/Đăng ký', {
            templateUrl: '../layout/dangKy.html'
        })
        .when('/Mua ngay', {
            templateUrl: '../layoutTrangChu/chiTietSanPham.html'
        })
});