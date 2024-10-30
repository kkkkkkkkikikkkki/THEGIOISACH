var app = angular.module('myAppAdmin',['ngRoute']);
app.config(function($routeProvider){
    $routeProvider

        .when('/', {
            templateUrl: '../layoutAdmin/Home.html'
        })
        .when('/Tạo quản lý mới', {
            templateUrl: '../layoutAdmin/TaoMoi.html'
        })
        .when('/Thống kê', {
            templateUrl: '../layoutAdmin/ThongKe.html'
        })
        .when('/Quản lý người dùng', {
            templateUrl: '../layoutAdmin/QuanLyNguoiDung.html'
        })
        .when('/Phân quyền', {
            templateUrl: '../layoutAdmin/PhanQuyen.html'
        })



});