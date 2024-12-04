package com.dan.datn.Controller;

import com.dan.datn.Entity.ThongKe;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import com.dan.datn.Service.ThongKeService;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;
    @Autowired
    private UserServiceImpl userServiceImpl;



    @GetMapping("/thongke")
    public String getThongKe(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với vai trò admin để xem thống kê.");
            return "redirect:/dangnhapadmin";
        }

        // Nếu đã đăng nhập
        model.addAttribute("email", email);

        List<ThongKe> thongKes = thongKeService.getAllThongKe();

        // Lọc các sản phẩm trùng lặp
        Map<Long, ThongKe> uniqueProductsMap = new HashMap<>();
        for (ThongKe thongKe : thongKes) {
            Long productId = thongKe.getThanhToan().getSanPham().getID_san_pham();
            if (uniqueProductsMap.containsKey(productId)) {
                ThongKe existingThongKe = uniqueProductsMap.get(productId);
                existingThongKe.getThanhToan().setSoLuong(
                        existingThongKe.getThanhToan().getSoLuong() + thongKe.getThanhToan().getSoLuong()
                );
                existingThongKe.getThanhToan().setTongTien(
                        existingThongKe.getThanhToan().getTongTien() + thongKe.getThanhToan().getTongTien()
                );
            } else {
                uniqueProductsMap.put(productId, thongKe);
            }
        }

        List<ThongKe> uniqueThongKes = new ArrayList<>(uniqueProductsMap.values());
        double totalRevenue = uniqueThongKes.stream()
                .mapToDouble(tk -> tk.getThanhToan().getTongTien())
                .sum();

        model.addAttribute("thongKes", uniqueThongKes);
        model.addAttribute("totalRevenue", totalRevenue);

        return "index/ThongKeDoanhThu";
    }


    private final String exportPath = "D:/Excel";  // Đường dẫn mặc định là D:/Excel
    @GetMapping("/export/excel-to-path")
    public String exportToExcel(HttpSession session, Model model, RedirectAttributes redirectAttributes) throws IOException {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với vai trò admin để xem thống kê.");
            return "redirect:/dangnhapadmin";
        }

        // Nếu đã đăng nhập
        model.addAttribute("email", email);
        // Lấy dữ liệu từ cơ sở dữ liệu
        List<ThongKe> thongKes = thongKeService.getAllThongKe();

        // Lọc các sản phẩm trùng lặp
        Map<Long, ThongKe> uniqueProductsMap = new HashMap<>();
        for (ThongKe thongKe : thongKes) {
            Long productId = thongKe.getThanhToan().getSanPham().getID_san_pham();
            if (uniqueProductsMap.containsKey(productId)) {
                ThongKe existingThongKe = uniqueProductsMap.get(productId);
                existingThongKe.getThanhToan().setSoLuong(
                        existingThongKe.getThanhToan().getSoLuong() + thongKe.getThanhToan().getSoLuong()
                );
                existingThongKe.getThanhToan().setTongTien(
                        existingThongKe.getThanhToan().getTongTien() + thongKe.getThanhToan().getTongTien()
                );
            } else {
                uniqueProductsMap.put(productId, thongKe);
            }
        }

        List<ThongKe> uniqueThongKes = new ArrayList<>(uniqueProductsMap.values());

        // Tạo workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ThongKe");

        // Tạo header
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID Sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Tổng tiền"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        // Tạo CellStyle cho định dạng tiền tệ (VND)
        CellStyle currencyStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        currencyStyle.setDataFormat(format.getFormat("#,##0 ₫"));

        // Điền dữ liệu vào các dòng
        int rowIndex = 1;
        double totalRevenue = 0;
        for (ThongKe thongKe : uniqueThongKes) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(thongKe.getThanhToan().getSanPham().getID_san_pham());
            row.createCell(1).setCellValue(thongKe.getThanhToan().getSanPham().getTenSach());

            // Định dạng giá tiền
            Cell priceCell = row.createCell(2);
            priceCell.setCellValue(thongKe.getThanhToan().getSanPham().getGia());
            priceCell.setCellStyle(currencyStyle);

            row.createCell(3).setCellValue(thongKe.getThanhToan().getSoLuong());

            // Định dạng tổng tiền
            Cell totalCell = row.createCell(4);
            totalCell.setCellValue(thongKe.getThanhToan().getTongTien());
            totalCell.setCellStyle(currencyStyle);

            totalRevenue += thongKe.getTongDoanhThu();
        }

        // Tạo dòng "Tổng doanh thu" ở một hàng riêng dưới các sản phẩm
        Row totalRow = sheet.createRow(rowIndex);

        // Các cột khác để trống
        totalRow.createCell(0).setCellValue("");  // Cột "ID Sản phẩm"
        totalRow.createCell(1).setCellValue("");  // Cột "Tên sản phẩm"
        totalRow.createCell(2).setCellValue("");  // Cột "Giá"
        totalRow.createCell(3).setCellValue("Tổng doanh thu");  // Cột "Số lượng"

        // Tạo style cho chữ "Tổng doanh thu" để in đậm
        CellStyle boldStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);  // Đặt font chữ in đậm
        boldStyle.setFont(boldFont);

        // Áp dụng style cho ô chứa "Tổng doanh thu"
        totalRow.getCell(3).setCellStyle(boldStyle);

        // Định dạng tổng doanh thu
        Cell totalRevenueCell = totalRow.createCell(4);
        totalRevenueCell.setCellValue(totalRevenue);
        totalRevenueCell.setCellStyle(currencyStyle);

        // Tự động điều chỉnh kích thước cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Tạo thư mục nếu chưa tồn tại
        File directory = new File(exportPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Đường dẫn file
        // Lấy ngày giờ hiện tại và tạo tên file
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = dateFormat.format(new Date());
        String fileName = "ThongKe_" + formattedDate + ".xlsx";
        String filePath = exportPath + File.separator + fileName;

        try {
            // Ghi file Excel vào đường dẫn
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                workbook.close();
            }
            redirectAttributes.addFlashAttribute("success", "File Excel đã được lưu tại: " + filePath);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xuất file Excel: " + e.getMessage());
            return "redirect:/thongke";
        }

        // Trả về thông báo thành công
        return "redirect:/thongke";
    }
}




