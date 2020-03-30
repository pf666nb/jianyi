package com.jianyi.service;
import com.alibaba.fastjson.JSONObject;
import com.jianyi.model.JyUser;
import com.jianyi.model.JyVercode;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public interface JyUserService {
    /**个人信息的完善
     * @param userId 用户的账号
     * @param user_address 用户的地址
     * @param user_autograph 用户的个性签名
     * @param user_birth 用户的生日
     * @param user_hobby 用户的兴趣爱好
     * @param user_phone 用户的电话号码
     * @param user_sex 用户的性别
     * @exception
     * @return 无实际意义
     * */
    public int information(String userId, Integer user_sex, Integer user_phone, String user_address, Date user_birth,String user_hobby,String user_autograph) throws Exception;
    /**修改密码
     * @param userId 用户账号
     * @param userPassWord 用户密码
     * @return
     * */
    public int change(String userPassWord,String userId);
    /**验证密码账号是否为空并修改
     * @param userPassWord 用户密码
     * @param userId 用户账号
     * @param useroldPassWord 用户的旧密码
     * @return
     * */
    public int isture(String useroldPassWord,String userPassWord,String userId);
    /**验证用户ID和名字是否相同
     * @param userId
     * @param userPassWord
     * @param useremail
     * @param userName
     * */
    public int isequally(String userName, String userPassWord, String userId, String useremail);
    /**验证账号密码是否为空
     * */
    public int isempty(String userName, String userPassWord, String userId, String useremail);
    /**验证id或者password是否为空
     * @param userPassWord 用户密码
     * @param userId 用户的账号
     * @param session 会话信息
     * @return 验证后将id存入会话
     * */
    public JSONObject Verification(String userId, String userPassWord, HttpSession session);
    /**验证邮箱
     * @param vercode 验证码
     * @param v_id 验证码的唯一标识符
     * */
    public int emailvercode(String vercode,String v_id);
    /**登录
     * @param user_id 用户的账号
     * @param userPassWord 用户的密码
     * @return 返回与账号密码所匹配的jyuser类
     *
     * */
    public List<JyUser> login(String user_id,String userPassWord);
    /**注册
     * @param user 封装好的user类
     * @return 无实际意义
     * */
    public  int add(JyUser user);
    /**修改密码
     * @param user 封装好的user类
     * @return 无实际意义
     * */
    public int update(JyUser user);
    /**删除用户
     * @param id 用户的账号
     * @return 无实际意义
     * */
    public int delete(String id);
    /**根据name查询数据库
     * @param user_name 用户的昵称
     * @return 返回包含该昵称的账号
     * */
    public List<JyUser> getAllname(String user_name);
    /**根据id查询数据库
     * @param user_id 用户的账号
     * @return 返回包含这个id的账号
     * */
    public List<JyUser> getAllid(String user_id);
    /**向数据库中添加验证码
     * @param vercode 封装好的验证码内含验证码与唯一标识符
     * @return 无实际意义
     * */
    public int addvercode(JyVercode vercode);
    /**删除数据库中的验证码*/
    public void deletevercode();
    /**获取数据库中的验证码
     * @param v_id 用于区别验证码的唯一标识符
     * @return 通过唯一标识符来查找验证码
     * */
    public String getvercode(String v_id);
    /**更新个人信息
     * @param user 封装好的usr pojo类
     * @return 更新用户的个人信息
     * */
    public  int addinformation(JyUser user);
    /**上传用户头像
     * @param user_id 用户的账号
     * @param file 用户的头像
     * @return 将头像存入本地
     * */
    public int imgupload(MultipartFile file, String user_id);
    /**验证id和email
     * @param user_id 用户的账号
     * @param email 用户的邮箱
     * @return 如果存在返回1，不存在返回0
     * */
    public int idemail(String user_id,String email);


}
