package com.ctl.eureka_client_provider.controller;

import com.ctl.eureka_client_provider.entity.emp;
import com.ctl.eureka_client_provider.mapper.EmpMapper;
import com.ctl.eureka_client_provider.service.EmpService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    @Autowired
    EmpService empService;
    @Autowired
    RabbitTemplate rabbitTemplate;
      @Autowired
    JavaMailSenderImpl javaMailSender;

    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("hello");
        return "hello";
    }
    @GetMapping("/aa")
    public String getAa(String aa){
        return aa;
    }

    @GetMapping("/getEmp")
    public emp getEmpList(Integer id){
        emp empList = empService.getEmp(id);
        return empList;
    }
    /*
    单播（点对点）
     */
    @GetMapping("/send")
    public void sendMessage(String aa){
        //message 需要自己构造一个；定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routeKey,message);
        //只需要传入要发送的对象，自动序列化发送给rabbtimq
//        rabbitTemplate.convertAndSend(exchange,routeKey,message);
//        Map<String,Object> map=new HashMap<>();
//        map.put("msg","这是第一个消息");
//        map.put("data", Arrays.asList("h","e","l","l","w"));
//        emp empList = empService.getEmp(1);
        emp emp=new emp();
        emp.setId(1);
        emp.setName("猪八戒");
        emp.setDeptId(1);
        emp.setSalary(Float.valueOf("1000.0"));
        this.rabbitTemplate.convertAndSend("exchange.direct","ctl.news",
                emp);
    }
    @GetMapping("receive")
    public  void receive(){
        Object o = rabbitTemplate.receiveAndConvert("ctl.news");
        System.out.printf(o.toString());
    }

    @GetMapping("sendMail")
    public void sendMail(){
        //简单邮件
        SimpleMailMessage impleMailMessages = new SimpleMailMessage();
        //邮件标题
        impleMailMessages.setSubject("通知-java测试");
        //邮件内容
        impleMailMessages.setText("测试内容。。。");
        //发送人
        impleMailMessages.setTo("1223240795@qq.com");
        impleMailMessages.setFrom("1223240795@qq.com");
        javaMailSender.send(impleMailMessages);
    }

    @GetMapping("sendMail2")
    public void sendMail2(){
        //复杂邮件
        MimeMessage mimeMessages =javaMailSender.createMimeMessage() ;
            try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessages,true);
            //邮件标题
            helper.setSubject("通知-java测试2");
            //邮件内容
            helper.setText("<b style='color:red'>测试内容2。。。</b>",true);
            //发送人
            helper.setTo("1223240795@qq.com");
            helper.setFrom("1223240795@qq.com");
            //上传文件
//            FileSystemResource file=new FileSystemResource(new File("C:\\Users\\CTL\\Pictures\\Saved Pictures\\timg.jfif"));
//            helper.addInline("picture",file);
            helper.addAttachment("1.jpg",new File("C:\\Users\\CTL\\Pictures\\Saved Pictures\\timg.jfif"));
            javaMailSender.send(mimeMessages);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }




}
