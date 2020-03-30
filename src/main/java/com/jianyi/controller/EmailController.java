package com.jianyi.controller;
import com.jianyi.mapper.JyUserMapper;
import com.jianyi.model.JyVercode;
import com.jianyi.service.EmailService;
import com.jianyi.service.JyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import static com.jianyi.util.VerCodeGenerateUtil.generateVerCode;
@RestController
@EnableAutoConfiguration
public class EmailController {
    @Resource
    private JyUserService jyUserService;
    @Resource
    private JyUserMapper jyUserMapper;

    @Autowired
    EmailService emailService;
    @GetMapping("/email")
    public Object sendEmail(@RequestParam("useremail") String useremail,String v_id) {
        try {
            String vercode = generateVerCode();
//            jyUserMapper.deletevercode();删除验证码
            JyVercode jyVercode = new JyVercode();
            jyVercode.setVercode(vercode);
            jyVercode.setV_id(v_id);
            jyUserService.addvercode(jyVercode);
            emailService.sendEmailVerCode(useremail,vercode);
            return "code:1" +
                    "date:null"+
                    "msg:发送成功";
        } catch (Exception e) {
            return "code:0"+
                    "date:"+e+
                    "msg:发送失败";
        }
    }
}
