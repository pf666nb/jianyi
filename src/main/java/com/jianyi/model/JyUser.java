package com.jianyi.model;

import java.util.Date;

public class JyUser {
    private String user_id ;
    private String user_name ;
    private String user_password ;
    private String user_oldpassword ;
    private String user_img ;
    private Integer user_sex ;
    private Integer user_phone ;
    private String user_email ;
    private String user_address ;
    private Date user_birth ;
    private Integer user_status ;
    private Date user_updated ;
    private String user_hobby;
    private String user_autograph;
    public String getUser_varcode() {
        return user_varcode;
    }
    public void setUser_varcode(String user_varcode) {
        this.user_varcode = user_varcode;
    }
    private Date user_created ;
    private String user_varcode;


    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) {this.user_id = user_id; }
    public void setUser_name(String user_name)
    {
        this.user_name=user_name;
    }
    public String getUser_name()
    {
        return this.user_name;
    }
    public void setUser_password(String user_password)
    {
        this.user_password=user_password;
    }
    public String getUser_password()
    {
        return this.user_password;
    }
    public void setUser_oldpassword(String user_oldpassword)
    {
        this.user_oldpassword=user_oldpassword;
    }
    public String getUser_oldpassword()
    {
        return this.user_oldpassword;
    }
    public void setUser_img(String user_img)
    {
        this.user_img=user_img;
    }
    public String getUser_img()
    {
        return this.user_img;
    }
    public void setUser_sex(Integer user_sex)
    {
        this.user_sex=user_sex;
    }
    public Integer getUser_sex()
    {
        return this.user_sex;
    }
    public void setUser_phone(Integer user_phone)
    {
        this.user_phone=user_phone;
    }
    public Integer getUser_phone()
    {
        return this.user_phone;
    }
    public void setUser_email(String user_email)
    {
        this.user_email=user_email;
    }
    public String getUser_email()
    {
        return this.user_email;
    }
    public void setUser_address(String user_address)
    {
        this.user_address=user_address;
    }
    public String getUser_address()
    {
        return this.user_address;
    }
    public void setUser_birth(Date user_birth)
    {
        this.user_birth=user_birth;
    }
    public Date getUser_birth()
    {
        return this.user_birth;
    }
    public void setUser_status(Integer user_status)
    {
        this.user_status=user_status;
    }
    public Integer getUser_status()
    {
        return this.user_status;
    }
    public void setUser_updated(Date user_updated)
    {
        this.user_updated=user_updated;
    }
    public Date getUser_updated()
    {
        return this.user_updated;
    }
    public void setUser_created(Date user_created)
    {
        this.user_created=user_created;
    }
    public Date getUser_created()
    {
        return this.user_created;
    }

    public String getUser_hobby() {
        return user_hobby;
    }

    public void setUser_hobby(String user_hobby) {
        this.user_hobby = user_hobby;
    }

    public String getUser_autograph() {
        return user_autograph;
    }

    public void setUser_autograph(String user_autograph) {
        this.user_autograph = user_autograph;
    }

    @Override
    public String toString() {
        return "JyUser{" +
                "_id=" + user_id +
                ", username='" + user_name + '\'' +
                ", password='" + user_password + '\'' +
                ", user_oldpassword='" + user_oldpassword + '\'' +
                ", user_img='" + user_img + '\'' +
                ", user_sex=" + user_sex +
                ", user_phone=" + user_phone +
                ", user_email='" + user_email + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_birth=" + user_birth +
                ", user_status=" + user_status +
                ", user_updated=" + user_updated +
                ", user_created=" + user_created +
                '}';
    }
}
