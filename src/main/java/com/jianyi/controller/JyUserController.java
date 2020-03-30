package com.jianyi.controller;
import com.alibaba.fastjson.JSONObject;
import com.jianyi.mapper.JyUserMapper;
import com.jianyi.service.EmailService;
import com.jianyi.service.JyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**声明Rest风格控制器*/
@RestController
/**自动配置。相当于spring配置文件*/
@EnableAutoConfiguration
@RequestMapping(value = "user",method = RequestMethod.GET )
public class JyUserController {
    @Resource
    private JyUserService jyUserService;
    @Resource
    private JyUserMapper jyUserMapper;
    @Autowired
    EmailService emailService;


    /**实现登录*/
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public JSONObject login(String _id, String password, HttpSession session){
        //验证是否存在该用户并验证该账号密码是否正确
        return jyUserService.Verification(_id, password,session);
    }


    /**实时查询id*/
    @RequestMapping(value = "register/id",method = RequestMethod.GET)
        public JSONObject registe( String _id){
        JSONObject jsonObject = new JSONObject();
        List s = jyUserService.getAllid(_id);
        if (s.size()!=0)
        {
            jsonObject.put("code", -1);
            jsonObject.put("data", null);
            jsonObject.put("message", "账号已存在注册失败");
            return jsonObject;
        }
        return jsonObject;
    }

    /**实时查询name*/
    @RequestMapping(value = "register/name",method = RequestMethod.GET)
        public JSONObject register( String username){
        JSONObject jsonObject = new JSONObject();
        List s = jyUserService.getAllname(username);
        if (s.size()!=0)
    {
        jsonObject.put("code", -2);
        jsonObject.put("data", null);
        jsonObject.put("message", "用户名已存在注册失败");
        return jsonObject;
    }
    return jsonObject;
}

    /**实现注册*/
    @RequestMapping(value = "register",method = RequestMethod.GET)
        public JSONObject register(String username, String password, String _id, String user_email, String vercode,String v_id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("data",null);
        jsonObject.put("message","注册失败");
        //验证邮箱
        int code1 =  jyUserService.emailvercode(vercode,v_id);
        if ( code1 == 0 )
        {
            jsonObject.put("code",2);
            jsonObject.put("data",null);
            jsonObject.put("message","验证码错误");
            return jsonObject;
        }
        //判断用户名和账号和邮箱是否为空
        int code2 = jyUserService.isempty(username,password,_id,user_email);
        if ( code2 == 1 )
        {
            jsonObject.put("code",3);
            jsonObject.put("data",null);
            jsonObject.put("message","用户名为空注册失败");
            return jsonObject;
        }else if ( code2 == 2 )
        {
            jsonObject.put("code",4);
            jsonObject.put("data",null);
            jsonObject.put("message","账号为空注册失败");
            return jsonObject;
        }else if ( code2 == 3 )
        {
            jsonObject.put("code",5);
            jsonObject.put("data",null);
            jsonObject.put("message","邮箱为空注册失败");
            return jsonObject;
        }
        //将用户数据写入数据库并查询
        int code3 = jyUserService.isequally(username,password,_id,user_email);

        if ( code3 == 3 )
        {
            jsonObject.put("code", 1);
            jsonObject.put("data", null);
            jsonObject.put("message", "注册成功");
            return jsonObject;
        }
            return jsonObject;
    }


      /**注销登录*/
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public JSONObject logout(HttpServletRequest request)
    {
        JSONObject jsonObject = new JSONObject();
        request.getSession().removeAttribute("_id");
        jsonObject.put("code", 1);
        jsonObject.put("data", null);
        jsonObject.put("message", "注销成功");
        return jsonObject;
    }


    /**实现修改密码*/
    @RequestMapping(value = "changepassword",method = RequestMethod.GET)//需要提交邮箱验证码和id和新密码
    public JSONObject changepassword(String user_oldpassword ,String password,String _id){
        JSONObject jsonObject=new JSONObject();
        int code4 = jyUserService.isture(user_oldpassword,password,_id);
        if ( code4 == 1)
        {
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            jsonObject.put("message","新密码为空");
            return jsonObject;
        }else if ( code4 == 2)
        {
            jsonObject.put("code",2);
            jsonObject.put("data",null);
            jsonObject.put("message","账号为空");
            return jsonObject;
        }else if ( code4 == 3)
        {
            jsonObject.put("code",3);
            jsonObject.put("data",null);
            jsonObject.put("message","原密码为空");
            return jsonObject;
        }else if ( code4 == 4)
        {
            jsonObject.put("code",1);
            jsonObject.put("data",null);
            jsonObject.put("message","修改成功");
            return jsonObject;
        }else if ( code4 == 5){
            jsonObject.put("code",4);
            jsonObject.put("data",null);
            jsonObject.put("message","原密码不正确");
            return jsonObject;
        }
        jsonObject.put("code",5);
        jsonObject.put("data",null);
        jsonObject.put("message","修改失败");
        return jsonObject;
    }


    /**实现忘记密码修改*/
    @RequestMapping(value = "forget",method = RequestMethod.GET)
    public JSONObject changepassword(String _id,String email,String vercode,String v_id)throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (_id==null){
            jsonObject.put("code",-5);
            jsonObject.put("data",null);
            jsonObject.put("message","错误！请返回上一步");
            return jsonObject;
        }
        //验证邮箱与id是否存在
        int code1 = jyUserService.idemail(_id,email);
        if (code1==0){
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            jsonObject.put("message","邮箱或账号错误");
            return jsonObject;
        }
        //验证邮箱
        int number1 =  jyUserService.emailvercode(vercode,v_id);
        if ( number1==0 )
        {
            jsonObject.put("code",-1);
            jsonObject.put("data",null);
            jsonObject.put("message","邮箱验证失败");
            return jsonObject;
        }
        if (_id == null){
            jsonObject.put("code",-2);
            jsonObject.put("data",null);
            jsonObject.put("message","账号不能为空");
            return jsonObject;
        }
        List idlist = new ArrayList();
        idlist.add(_id);
        jsonObject.put("code",1);
        jsonObject.put("data",idlist);
        jsonObject.put("message","验证成功");
        return jsonObject;
    }

    @RequestMapping(value = "forgetchange",method = RequestMethod.GET)
    public JSONObject change(String _id,String password){
        JSONObject jsonObject = new JSONObject();

        int number2 = jyUserService.change(password,_id);
        if (number2 == 1)
        {
            jsonObject.put("code",-1);
            jsonObject.put("data",null);
            jsonObject.put("message","跟旧密码相同");
            return jsonObject;
        }else if (number2 == 2)
        {
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            jsonObject.put("message","修改失败");
        }
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }


    /**实现个人信息的完善*/
    @RequestMapping(value = "/information",method = RequestMethod.POST)//user_birth的值必须为标准date才能存入
    public JSONObject information(String _id, Integer user_sex, Integer user_phone, String user_address, Date user_birth,String user_hobby,String user_autograph)throws Exception{
        JSONObject jsonObject = new JSONObject();
        int code  = jyUserService.information(_id,user_sex,user_phone,user_address,user_birth,user_hobby,user_autograph);
        if ( code == 0 )
        {
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            jsonObject.put("message","ID错误修改失败");
            return jsonObject;
        }else if (code == 1) {
            jsonObject.put("code", 1);
            jsonObject.put("data", null);
            jsonObject.put("message", "修改成功");
        }
        return jsonObject;
    }
    @RequestMapping(value = "/informationname",method = RequestMethod.POST)
    public JSONObject informationname(String _id,String username)
    {
        JSONObject jsonObject = new JSONObject();
        jyUserMapper.changeusername(_id,username);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }
    @RequestMapping(value = "/informationsex",method = RequestMethod.POST)
    public JSONObject informationsex(String _id,Integer user_sex)
    {
        JSONObject jsonObject = new JSONObject();
        jyUserMapper.changeusersex(_id,user_sex);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }
    @RequestMapping(value = "/informationphone",method = RequestMethod.POST)
    public JSONObject informationphone(String _id,Integer user_phone)
    {
        JSONObject jsonObject = new JSONObject();
        jyUserMapper.changeuserphone(_id,user_phone);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }
    @RequestMapping(value = "/informationhobby",method = RequestMethod.POST)
    public JSONObject informationhobby(String _id,String user_hobby)
    {
        JSONObject jsonObject = new JSONObject();
        jyUserMapper.changeuserhobby(_id,user_hobby);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }
    @RequestMapping(value = "/informationautograph",method = RequestMethod.POST)
    public JSONObject informationautograph(String _id,String user_autograph)
    {
        JSONObject jsonObject = new JSONObject();
        jyUserMapper.changeuserautograph(_id,user_autograph);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }
    @RequestMapping(value = "/informationaddress",method = RequestMethod.POST)
    public JSONObject informationaddress(String _id,String user_address)
    {
        JSONObject jsonObject = new JSONObject();
        jyUserMapper.changeuseraddress  (_id,user_address);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }
    @RequestMapping(value = "/informationbirth",method = RequestMethod.POST)
    public JSONObject informationbirth(String _id,Date user_birth)
    {
        JSONObject jsonObject = new JSONObject();
        jyUserMapper.changeuserbirth(_id,user_birth);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }



//    //实现删除
//    @RequestMapping(value = "/delete",method = RequestMethod.POST)
//    @ResponseBody
//    public JSONObject delete(Integer id)throws Exception{
//        JSONObject jsonObject=new JSONObject();
//        int result=jyUserService.delete(id);
//        if (result==0){
//            jsonObject.put("code",0);
//            jsonObject.put("data",null);
//            jsonObject.put("message","删除失败");
//            return jsonObject;
//        }
//        jsonObject.put("code",1);
//        jsonObject.put("data",result);
//        jsonObject.put("message","删除成功");
//        return jsonObject;
//    }

        /**头像上传*/
    @RequestMapping(value = "/personal/account",method = RequestMethod.POST)
    public JSONObject imgupload(@RequestParam("avatar") MultipartFile file, String _id)
    {
        JSONObject  jsonObject = new JSONObject();
        jyUserService.imgupload(file,_id);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","上传成功");
        return jsonObject;
    }


/**修改邮箱*/
    @RequestMapping(value = "/informationemail",method = RequestMethod.GET)
    public JSONObject emailchange(  String email, String _id)
    {
        JSONObject  jsonObject = new JSONObject();
        jyUserMapper.changeuseremail(_id,email);
        jsonObject.put("code",1);
        jsonObject.put("data",null);
        jsonObject.put("message","修改成功");
        return jsonObject;
    }
}