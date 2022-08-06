package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Service.MailService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    @Value("ink_book@qq.com")
    private String serverEmail;

    private final JavaMailSender mailSender;

    private final static String characters = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private final static Random random = new Random();

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setServerEmail(String serverEmail) {
        this.serverEmail = serverEmail;
    }

    @Override
    public Result<String> sendEmail(String userEmail) {
        String format = "\\w+@(\\w+.)+[a-z]{2,3}";
        if (!userEmail.matches(format))
            return Result.fail("Email Invalid");
        String code = generateCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(serverEmail);
        message.setTo(userEmail);
        message.setSubject("InkBook账号注册");
        message.setText("您的邮箱验证码为：\n" + code);
        mailSender.send(message);
        return Result.success("Mail Sent", code);
    }

    private String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 5; i++)
            code.append(characters.charAt(random.nextInt(36)));
        return code.toString().toUpperCase();
    }
}
