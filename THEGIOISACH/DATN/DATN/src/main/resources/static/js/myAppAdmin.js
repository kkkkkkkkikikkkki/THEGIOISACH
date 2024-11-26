var app = angular.module('myAppAdmin', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'layoutAdmin/Home.html',
        })
        .when('/Home', {
            templateUrl: 'layoutAdmin/Home.html'
        })
        .when('/Tạo quản lý mới', {
            templateUrl: 'layoutAdmin/TaoMoi.html'
        })
        .when('/Thống kê', {
            templateUrl: 'layoutAdmin/ThongKe.html'
        })
        .when('/Quản lý người dùng', {
            templateUrl: 'layoutAdmin/QuanLyNguoiDung.html',
            controller: 'NguoiDungController'
        })
        .when('/Quản lý sản phẩm', {
            templateUrl: 'layoutAdmin/Quanlysanpham.html'
        })
        .when('/Phân quyền', {
            templateUrl: 'layoutAdmin/PhanQuyen.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.controller('NguoiDungController', function($scope, $http) {
    // Hàm tải dữ liệu người dùng
    $scope.loadUsers = async function() {
        try {
            const response = await $http.get('/api/nguoidung'); // Thay đổi endpoint API của bạn ở đây
            $scope.users = response.data;
        } catch (error) {
            console.error('Lỗi khi tải danh sách người dùng:', error);
        }
    };

    // Hàm xóa người dùng
    $scope.xoaNguoiDung = async function(userId) {
        try {
            // Gọi API xóa người dùng
            await $http.delete('/api/nguoidung/' + userId); // Thay đổi endpoint xóa người dùng của bạn ở đây

            // Cập nhật lại danh sách người dùng sau khi xóa
            $scope.users = $scope.users.filter(function(user) {
                return user.id_nguoi_dung !== userId;
            });

            alert('Người dùng đã được xóa thành công!');
            $scope.loadUsers();
        } catch (error) {
            console.error('Lỗi khi xóa người dùng:', error);
            alert('Có lỗi xảy ra khi xóa người dùng!');
        }
    };

    // Gọi hàm loadUsers khi controller được khởi tạo
    $scope.loadUsers();
});
