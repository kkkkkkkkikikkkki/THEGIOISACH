var app = angular.module('myAppTrangChu',['ngRoute']);
app.config(function($routeProvider){
    $routeProvider

        .when('/', {
            templateUrl: 'layoutTrangChu/layoutTrangChu.html',
        })
        .when('/Trang chủ', {
            templateUrl: 'layoutTrangChu/layoutTrangChu.html'
        })
        .when('/Thông báo', {
            templateUrl: 'layoutTrangChu/layoutTrangChu.html'
        })
        .when('/Giỏ hàng', {
            templateUrl: 'layoutAdmin/gioHang.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});