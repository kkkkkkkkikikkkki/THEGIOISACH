package com.dan.datn.Service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        // Thay bằng logic gửi email thực tế
        System.out.println("Gửi email đến: " + to);
        System.out.println("Tiêu đề: " + subject);
        System.out.println("Nội dung: " + body);
    }
}
