    // async function loadUsers() {
    //     try {
    //         const response = await fetch('/api/taikhoan');
    //         const users = await response.json();
    //
    //         const tbody = document.getElementById('userTableBody');
    //         tbody.innerHTML = ''; // Xóa dữ liệu cũ
    //
    //         users.forEach(user => {
    //             const row = `
    //                     <tr>
    //                         <td class="text-center">${user.id || user.idKhachHang || user.idNguoiBan}</td>
    //                         <td>${user.ten || user.hoTen}</td>
    //                         <td>${user.email}</td>
    //                         <td class="text-center">${user.ngayDangKi}</td>
    //                         <td class="text-center">
    //                             <button class="btn btn-info btn-sm" onclick="viewUserDetail(${user.id || user.idKhachHang || user.idNguoiBan})" data-bs-toggle="modal" data-bs-target="#userDetailModal">
    //                                 <i class="bi bi-eye-fill"></i> Xem Chi Tiết
    //                             </button>
    //                         </td>
    //                     </tr>
    //                 `;
    //             tbody.innerHTML += row;
    //         });
    //     } catch (error) {
    //         console.error('Lỗi khi tải danh sách người dùng:', error);
    //     }
    // }
    //
    // async function viewUserDetail(userId) {
    //     try {
    //         const response = await fetch(`/api/taikhoan/${userId}`);
    //         const user = await response.json();
    //
    //         const modalBody = document.getElementById('userDetailBody');
    //         modalBody.innerHTML = `
    //                 <p><strong>ID:</strong> ${user.id || user.idKhachHang || user.idNguoiBan}</p>
    //                 <p><strong>Tên Người Dùng:</strong> ${user.ten || user.hoTen}</p>
    //                 <p><strong>Email:</strong> ${user.email}</p>
    //                 <p><strong>Số Điện Thoại:</strong> ${user.sdt}</p>
    //                 <p><strong>Ngày Đăng Ký:</strong> ${user.ngayDangKi}</p>
    //                 <p><strong>Địa Chỉ:</strong> ${user.diaChi}</p>
    //             `;
    //     } catch (error) {
    //         console.error('Lỗi khi tải thông tin chi tiết người dùng:', error);
    //     }
    // }
    //
    // window.onload = loadUsers;// Global variable to store users
    // let users = [];
    //
    // // Function to load users from the API
    // async function loadUsers() {
    //     try {
    //         const response = await fetch('/api/taikhoan');
    //         if (!response.ok) {
    //             throw new Error('Network response was not ok');
    //         }
    //         users = await response.json(); // Store users in the global variable
    //
    //         updateUserTable();
    //     } catch (error) {
    //         console.error('Lỗi khi tải danh sách người dùng:', error);
    //     }
    // }
    //
    // // Function to update the user table
    // function updateUserTable() {
    //     const tbody = document.getElementById('userTableBody');
    //     tbody.innerHTML = ''; // Clear old data
    //
    //     users.forEach(user => {
    //         const row = `
    //             <tr>
    //                 <td class="text-center">${user.id || user.idKhachHang || user.idNguoiBan}</td>
    //                 <td>${user.ten || user.hoTen}</td>
    //                 <td>${user.email}</td>
    //                 <td class="text-center">${user.ngayDangKi}</td>
    //                 <td class="text-center">
    //                     <button class="btn btn-info btn-sm" onclick="viewUserDetail(${user.id || user.idKhachHang || user.idNguoiBan})" data-bs-toggle="modal" data-bs-target="#userDetailModal">
    //                         <i class="bi bi-eye-fill"></i> Xem Chi Tiết
    //                     </button>
    //                 </td>
    //             </tr>
    //         `;
    //         tbody.innerHTML += row;
    //     });
    // }
    //
    // // Function to view user detail in a modal
    // async function viewUserDetail(userId) {
    //     const user = users.find(u => u.id === userId || u.idKhachHang === userId || u.idNguoiBan === userId);
    //
    //     if (user) {
    //         const modalBody = document.getElementById('userDetailBody');
    //         modalBody.innerHTML = `
    //             <p><strong>ID:</strong> ${user.id || user.idKhachHang || user.idNguoiBan}</p>
    //             <p><strong>Tên Người Dùng:</strong> ${user.ten || user.hoTen}</p>
    //             <p><strong>Email:</strong> ${user.email}</p>
    //             <p><strong>Số Điện Thoại:</strong> ${user.sdt}</p>
    //             <p><strong>Ngày Đăng Ký:</strong> ${user.ngayDangKi}</p>
    //             <p><strong>Địa Chỉ:</strong> ${user.diaChi}</p>
    //         `;
    //     } else {
    //         console.error('Người dùng không tìm thấy!');
    //     }
    // }
    //
    // // Call loadUsers only when navigating to the user management page
    // // Gọi hàm loadUsers khi nhấn vào "Quản lý tài khoản"
    // document.getElementById('manageAccounts').addEventListener('click', function () {
    //     loadUsers();
    // });
