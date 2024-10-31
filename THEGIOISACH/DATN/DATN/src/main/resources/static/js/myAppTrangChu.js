var app = angular.module('myAppTrangChu', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'layoutTrangChu/layoutTrangChu.html',
        })
        .when('/Trang chủ', {
            templateUrl: 'layoutTrangChu/layoutTrangChu.html'
        })
        .when('/Thông báo', {
            templateUrl: 'layoutTrangChu/ThongBao.html'
        })
        .when('/Giỏ hàng', {
            templateUrl: 'layoutTrangChu/gioHang.html'
        })
        .when('/Mua ngay', {
            templateUrl: 'layoutTrangChu/chiTietSanPham.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});
