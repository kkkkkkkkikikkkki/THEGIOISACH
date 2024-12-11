package com.dan.datn.Controller;

import com.dan.datn.Entity.ThongKe;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import com.dan.datn.Service.ThongKeService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ThongKeController {

    @Autowired
    private ThongKeService thongKeService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    private final String exportPath = "D:/Excel";  // Đường dẫn mặc định là D:/Excel

    // Lấy thống kê doanh thu
    @GetMapping("/thongke")
    public String getThongKe(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với vai trò admin để xem thống kê.");
            return "redirect:/dangnhapadmin";
        }

        // Nếu đã đăng nhập
        model.addAttribute("email", email);
        // Lấy dữ liệu thống kê
        List<ThongKe> thongKes = thongKeService.getAllThongKe();
        // Lọc các sản phẩm trùng lặp và tính tổng
        List<ThongKe> uniqueThongKes = getUniqueThongKes(thongKes);
        double totalRevenue = 0;
        for (ThongKe tk : uniqueThongKes) {
            totalRevenue += tk.getThanhToan().getTongTien();
        }
        Map<String, Double> revenueByMonth = thongKes.stream()
                .filter(thongKe -> thongKe.getThanhToan() != null && thongKe.getThanhToan().getNgayDatHang() != null) // Kiểm tra null
                .collect(Collectors.groupingBy(
                        thongKe -> {
                            LocalDate date = thongKe.getThanhToan().getNgayDatHang().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                            // Lấy tên tháng (loại bỏ năm)
                            return date.getMonth().getDisplayName(TextStyle.FULL, new Locale("vi", "VN")) + " " + date.getYear();
                            },
                        Collectors.summingDouble(thongKe -> thongKe.getThanhToan().getTongTien())
                ));

        for (Map.Entry<String, Double> entry : revenueByMonth.entrySet()) {
            System.out.println("Tháng: " + entry.getKey() + ", Tổng doanh thu: " + entry.getValue());
        }
        Map<Integer, Double> revenueByYear = thongKes.stream()
                .filter(thongKe -> thongKe.getThanhToan() != null && thongKe.getThanhToan().getNgayDatHang() != null) // Kiểm tra null
                .collect(Collectors.groupingBy(
                        thongKe -> {
                            LocalDate date = thongKe.getThanhToan().getNgayDatHang().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                            return date.getYear();  // Lấy năm
                        },
                        Collectors.summingDouble(thongKe -> thongKe.getThanhToan().getTongTien())
                ));

        for (Map.Entry<Integer, Double> entry : revenueByYear.entrySet()) {
            System.out.println("Năm: " + entry.getKey() + ", Tổng doanh thu: " + entry.getValue());
        }


        int totalQuantitySold = thongKes.stream()
                .filter(thongKe -> thongKe.getThanhToan() != null)  // Kiểm tra null trước khi lấy số lượng
                .mapToInt(thongKe -> thongKe.getThanhToan().getSoLuong())
                .sum();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String revenueJson = objectMapper.writeValueAsString(revenueByMonth);
            model.addAttribute("revenueByMonthJson", revenueJson);
            String revenueByYearJson = objectMapper.writeValueAsString(revenueByYear);
            objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
            model.addAttribute("revenueByYearJson", revenueByYearJson); // Truyền vào model// Truyền vào model
        } catch (Exception e) {
            e.printStackTrace();
        }


        model.addAttribute("totalQuantitySold", totalQuantitySold);
//        model.addAttribute("revenueByMonth", revenueByMonth);
        model.addAttribute("totalQuantitySold", totalQuantitySold); // Truyền giá trị vào model
        model.addAttribute("thongKes", uniqueThongKes);
        model.addAttribute("totalRevenue", totalRevenue);
        session.setAttribute("email", email);
        return "index/ThongKeDoanhThu";
    }

    // Phương thức để lọc và gộp các sản phẩm trùng lặp
    private List<ThongKe> getUniqueThongKes(List<ThongKe> thongKes) {
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
        return new ArrayList<>(uniqueProductsMap.values());
    }

    // Xuất Excel
    @GetMapping("/export/excel-to-path")
    public String exportToExcel(HttpSession session, Model model, RedirectAttributes redirectAttributes) throws IOException {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với vai trò admin để xem thống kê.");
            return "redirect:/dangnhapadmin";
        }

        // Nếu đã đăng nhập
        model.addAttribute("email", email);

        // Lấy dữ liệu thống kê
        List<ThongKe> thongKes = thongKeService.getAllThongKe();
        List<ThongKe> uniqueThongKes = getUniqueThongKes(thongKes);

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

            totalRevenue += thongKe.getThanhToan().getTongTien();
        }

        // Tạo dòng "Tổng doanh thu" ở một hàng riêng dưới các sản phẩm
        Row totalRow = sheet.createRow(rowIndex);
        totalRow.createCell(0).setCellValue("");  // Cột "ID Sản phẩm"
        totalRow.createCell(1).setCellValue("");  // Cột "Tên sản phẩm"
        totalRow.createCell(2).setCellValue("");  // Cột "Giá"
        totalRow.createCell(3).setCellValue("Tổng doanh thu");  // Cột "Số lượng"

        // Tạo style cho chữ "Tổng doanh thu" để in đậm
        CellStyle boldStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);  // Đặt font chữ in đậm
        boldStyle.setFont(boldFont);
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

        // Lấy ngày giờ hiện tại và tạo tên file
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = dateFormat.format(new Date());
        String fileName = "ThongKe_" + formattedDate + ".xlsx";
        String filePath = exportPath + File.separator + fileName;

        // Ghi file Excel vào đường dẫn
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            redirectAttributes.addFlashAttribute("success", "File Excel đã được lưu tại: " + filePath);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xuất file Excel: " + e.getMessage());
            return "redirect:/thongke";
        }

        // Trả về thông báo thành công
        return "redirect:/thongke";
    }
}
