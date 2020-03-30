package com.jianyi.service;
import com.jianyi.config.MailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
MailConfiguration mailConfiguration = new MailConfiguration();

    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 从配置文件中获取发件人
     */
    @Value("${spring.mail.username}")
    private String sender;
    /**
     * 邮件发送
     * @param receiver 收件人
     * @param verCode 验证码
     * @throws MailSendException 邮件发送错误
     */
    @Async
    public void sendEmailVerCode(String receiver, String verCode) throws MailSendException {
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("验证码");
        message.setText("尊敬的简易用户,您好:\n"
                + "\n欢迎您注册本网站本次请求的邮件验证码为:" + verCode + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");	//设置邮件正文
        //设置收件人
        message.setTo(receiver);
        //设置发件人
        message.setFrom(sender);
        //发送邮件
        mailConfiguration.JavaMailSender().send(message);
    }

}


