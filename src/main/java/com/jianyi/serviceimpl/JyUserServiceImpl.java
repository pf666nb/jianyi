package com.jianyi.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.jianyi.mapper.JyUserMapper;
import com.jianyi.model.JyUser;
import com.jianyi.model.JyVercode;
import com.jianyi.service.JyUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service("jyUserService")
public class JyUserServiceImpl implements JyUserService {

    private JyUser jyUser;
    @Resource
    private JyUserService jyUserService;
    @Resource
    private JyUserMapper jyUserMapper;
    @Override
    public int information(String userId, Integer user_sex, Integer user_phone, String user_address, Date user_birth,String user_hobby,String user_autograph)
            throws Exception
    {
        List<JyUser> idlist = jyUserService.getAllid(userId);
        if (idlist.size() ==0){
            return 0;
        }
        JyUser jyUser = new JyUser();
        //设定更新日期
        //获得系统时间.
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        //将修改的数据存入数据库
        jyUser.setUser_id(userId);
        jyUser.setUser_sex(user_sex);
        jyUser.setUser_phone(user_phone);
        jyUser.setUser_address(user_address);
        jyUser.setUser_birth(user_birth);
        jyUser.setUser_updated(time);
        jyUser.setUser_hobby(user_hobby);
        jyUser.setUser_autograph(user_autograph);
        jyUserService.addinformation(jyUser);
        return 1;
    }

    @Override
    /**修改密码*/
    public int change(String userPassWord,String userId)
    {
        String password="";
        List<JyUser> passwordlist=jyUserService.getAllid(userId);
        for (int i = 0; i <passwordlist.size() ; i++) {
            password=passwordlist.get(i).getUser_password();
        }
        if (password.equals(userPassWord)){
            return 1;

        }
        JyUser user=new JyUser();
        user.setUser_password(userPassWord);
        user.setUser_id(userId);
        int result=jyUserService.update(user);
        if (result==0){
            return 2;
        }

        return 3;
    }

    @Override//验证账号和密码并修改
    public int isture(String useroldPassWord,String userPassWord,String userId)
    {
        if(userPassWord == null){

            return 1;
        }else if (userId == null){

            return 2;
        }else if (useroldPassWord == null){
            return 3;
        }
        String password="";
        List<JyUser> passwordlist=jyUserService.getAllid(userId);
        for (int i = 0; i <passwordlist.size() ; i++) {
            password=passwordlist.get(i).getUser_password();
        }
        if (password.equals(useroldPassWord)){
            JyUser user=new JyUser();
            user.setUser_password(userPassWord);
            user.setUser_id(userId);
            int result=jyUserService.update(user);
            if (result==0)
            {
                return 3;
            }
            return 4;
        }
        return 5;
    }


    @Override//验证用户id和名字是否相同
    public int isequally(String userName, String userPassWord, String userId, String useremail)
    {
        JyUser user=new JyUser();
        //设定注册时间
        Date date = new Date();
        long ts = date.getTime();
        Date date1 =new Date(ts);
        user.setUser_id(userId);
        user.setUser_email(useremail);
        user.setUser_name(userName);
        user.setUser_password(userPassWord);
        user.setUser_oldpassword(userPassWord);
        user.setUser_created(date1);
        //从数据库中查询
        List<JyUser> namelist=jyUserService.getAllname(userName);
        List<JyUser> idlist = jyUserService.getAllid(userId);
        //判断用户名和账号是否相同
        if (namelist.size()!=0){
            return 1;
        }
        if (idlist.size() !=0){
            return 2;
        }
        int result=jyUserService.add(user);
        if(result==0){
            return 3;
        }
        //注册成功后使邮箱验证码过期
//        jyUserMapper.deletevercode();
        return 3;
    }
    @Override//验证账号密码
    public JSONObject Verification(String userId, String userPassWord , HttpSession session)
    {
        JSONObject jsonObject=new JSONObject();
        if (userId==null || userPassWord==null){
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            jsonObject.put("msg","账号或者密码为空");
            return jsonObject;
        }

        List<JyUser> userList=jyUserService.login(userId,userPassWord);
        if (userList.size()==0){
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            jsonObject.put("msg","账号或者密码错误");
            return jsonObject;
        }

        jsonObject.put("status",1);
        jsonObject.put("data",userList);
//        jsonObject.put("data",userList);
        jsonObject.put("msg","登陆成功");
        session.setAttribute("user_id",userId);
        return jsonObject;
    }
    @Override//验证是否为空
    public int isempty(String userName, String userPassWord, String userId, String useremail)
    {

        if (userName == null)
        {
            return 1;
        }else if (userId == null)
        {
            return 2;
        }else if (useremail == null)
        {
            return 3;
        }
        return 0;
    }

    @Override//验证邮箱
    public int emailvercode(String vercode,String v_id){
        //验证邮箱
        try {
            String vercode1 = jyUserService.getvercode(v_id);
            if ( !vercode.equals(vercode1))
            {
                return 0;
            }
        } catch (Exception e)
            {
                return 0;
            }
                return 1;
            }
    @Override
    public List<JyUser> login(String userId, String userPassWord) {
        return jyUserMapper.login(userId,userPassWord);
    }

    @Override
    public int add(JyUser user) {
        return jyUserMapper.add(user);
    }

    @Override
    public int update(JyUser user) {
        return jyUserMapper.update(user);
    }

    @Override
    public int delete(String id) {
        return jyUserMapper.delete(id);
    }
    @Override
    public List<JyUser> getAllname(String user_name){
        return  jyUserMapper.getAllname(user_name);
    }


    @Override
    public List<JyUser> getAllid(String user_id) {
        return jyUserMapper.getAllid(user_id);
    }

    @Override
    public int addvercode(JyVercode vercode){ return jyUserMapper.addvercode(vercode); }
    @Override
    public String getvercode(String v_id){ return jyUserMapper.getvercode(v_id); }
    @Override
    public void deletevercode(){}
    @Override
    public int addinformation(JyUser user){return  jyUserMapper.addinformation(user);}
    @Override
    public int imgupload(MultipartFile file, String user_id) {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //设置文件储存路径
        String filePath = "D:\\简易\\测试\\fx-project\\public\\avatar\\";
        String path = filePath + fileName ;
        File dest = new File(path);
        //检查是否存在目录
        if (!dest.getParentFile().exists()) {
            //新建文件夹
            dest.getParentFile().mkdir();
        }
        try {
            //文件写入
            file.transferTo(dest);
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jyUserMapper.imgupload(path ,user_id);
    }
    @Override
    public int idemail(String user_id,String email){
        List s = jyUserMapper.idemail(user_id,email);
        if (s.size()==0){
            return 0;
        }
        return 1;
    }


}
