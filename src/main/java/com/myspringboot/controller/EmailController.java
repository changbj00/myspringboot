package com.myspringboot.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    TemplateEngine templateEngine;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("sendSimpleEmail")
    public String sendSimpleEmail(){
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo("1127701980@qq.com");
            mailMessage.setSubject("--email--");
            mailMessage.setText("send-spring-email");
            jms.send(mailMessage);
            return "发送成功";
        } catch (MailException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @RequestMapping("sendHtmlEmail")
    public String sendHtmlEmail(){
        MimeMessage message = null;
        message = jms.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("1127701980@qq.com");
            helper.setSubject("--email--");
            StringBuffer sb = new StringBuffer("<p style='color:#6db33f'>使用Spring Boot发送HTML格式邮件。</p>");
            helper.setText(sb.toString(),true);
            jms.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @RequestMapping("sendAttachmentsMail")
    public String sendAttachmentsMail(){
        MimeMessage mimeMessage=null;
        mimeMessage=jms.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo("1127701980@qq.com");
            helper.setSubject("--email--");
            StringBuffer sb = new StringBuffer("<p style='color:#6db33f'>详情参见附件内容！</p>");
            helper.setText(sb.toString(),true);
            //传入附件
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/file/logfile.txt"));
            helper.addAttachment("logfile.txt",file);
            jms.send(mimeMessage);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
    @RequestMapping("sendInlineMail")
    public String sendInlineMail(){
        MimeMessage mimeMessage=jms.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo("1127701980@qq.com");
            helper.setSubject("--email--");
            StringBuffer sb = new StringBuffer("<html><body>博客图：<img src='cid:img'/></body></html>");
            helper.setText(sb.toString(),true);
            //传入附件
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/img/img1.jpg"));
            helper.addInline("img",file);
            jms.send(mimeMessage);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @RequestMapping("sendTemplateEmail")
    public String sendTemplateEmail(String code){
        MimeMessage mimeMessage=jms.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo("1127701980@qq.com");
            helper.setSubject("--email--");
            //处理邮件模板
            Context context=new Context();
            context.setVariable("code",code);
            String template = templateEngine.process("emailTemplate", context);
            helper.setText(template,true);
            jms.send(mimeMessage);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
