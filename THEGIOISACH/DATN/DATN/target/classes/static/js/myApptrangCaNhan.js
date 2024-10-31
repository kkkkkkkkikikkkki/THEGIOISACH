


var app = angular.module('myApptrangCaNhan',['ngRoute']);
app.config(function($routeProvider){
    $routeProvider

        .when('/Thông tin cá nhân', {
            templateUrl: 'layout/Thongtincanhan.html'
        })
        .when('/Đơn hàng của tôi', {
            templateUrl: 'layout/Donhangcuatoi.html'
        })
        .when('/Trở thành người bán', {
            templateUrl: 'layout/Trothanhnguoiban.html'
        })
        .when('/Quản lý sản phẩm', {
            templateUrl: 'layout/Quanlysanpham.html'
        })
        .when('/Phân quyền', {
            templateUrl: 'layout/Setting.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});