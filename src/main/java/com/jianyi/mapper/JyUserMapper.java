package com.jianyi.mapper;
import com.jianyi.model.JyUser;
import com.jianyi.model.JyVercode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Mapper
public interface JyUserMapper  {
    /**根据id和密码查询数据库
     * @author wpf
     * @version 1.0
     * @param userId 用户的账号
     * @param userPassWord 用户的密码
     * @return 返回一个JyUser的链表
     * */
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    public List<JyUser> login(@Param("user_id") String userId, @Param("user_password") String userPassWord);
    /**添加
     * @param user 已经封装好的user类
     * @return
     * */
    public int add(JyUser user);
    /**修改*/
    public int update(JyUser user);
    /**删除*/
    public int delete(String id);
    /**根据id查询数据库*/
    public List<JyUser> getAllid(String user_id);
    /**根据name查询数据库*/
    public List<JyUser> getAllname(String user_name);
    /**向数据库中添加验证码*/
    public int addvercode(JyVercode vercode);
    /**获得数据库中的验证码*/
    public String getvercode(String v_id);
    /**删除数据库中的验证码*/
    public void deletevercode();
    /**更新个人信息*/
    public int addinformation(JyUser jyUser);
    /**更新name*/
    public int changeusername(String user_id,String user_name);
    /**更新sex*/
    public int changeusersex(String user_id,Integer user_sex);
    /**更新phone*/
    public int changeuserphone(String user_id,Integer user_phone);
    /**更新hobby*/
    public int changeuserhobby(String user_id,String user_hobby);
    /**更新autograph*/
    public int changeuserautograph(String user_id,String user_autograph);
    /**更新birth*/
    public int changeuserbirth(String user_id, Date user_birth);
    /**更新adress*/
    public int changeuseraddress(String user_id, String user_address);
    /**上传头像*/
    public int imgupload(String img, String user_id);
    /**根据ID查询邮箱*/
    public List idemail(String user_id,String email);
    /**修改邮箱*/
    public int changeuseremail(String user_id,String email);
}
