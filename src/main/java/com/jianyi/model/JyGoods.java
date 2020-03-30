package com.jianyi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.DynamicTemplates;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
@Document(indexName = "Jygoods",type = "JyGoods",shards = 1,replicas = 0)
public class JyGoods {

    @Id
    private Integer goods_id ;
    private Integer user_id ;
    private String goods_name ;
    private String goods_img ;
    private String goods_information ;
    private Integer goods_status ;
    private String goods_type ;

    public Integer getGoods_now() {
        return goods_now;
    }

    public void setGoods_now(Integer goods_now) {
        this.goods_now = goods_now;
    }

    private Date goods_updata ;
    private Date goods_create ;
    private Integer goods_now;


    public void setGoods_id(Integer goods_id)
    {
        this.goods_id=goods_id;
    }
    public Integer getGoods_id()
    {
        return this.goods_id;
    }
    public void setUser_id(Integer user_id)
    {
        this.user_id=user_id;
    }
    public Integer getUser_id()
    {
        return this.user_id;
    }
    public void setGoods_name(String goods_name)
    {
        this.goods_name=goods_name;
    }
    public String getGoods_name()
    {
        return this.goods_name;
    }
    public void setGoods_img(String goods_img)
    {
        this.goods_img=goods_img;
    }
    public String getGoods_img()
    {
        return this.goods_img;
    }
    public void setGoods_information(String goods_information)
    {
        this.goods_information=goods_information;
    }
    public String getGoods_information()
    {
        return this.goods_information;
    }
    public void setGoods_status(Integer goods_status)
    {
        this.goods_status=goods_status;
    }
    public Integer getGoods_status()
    {
        return this.goods_status;
    }
    public void setGoods_type(String goods_type)
    {
        this.goods_type=goods_type;
    }
    public String getGoods_type()
    {
        return this.goods_type;
    }
    public void setGoods_updata(Date goods_updata)
    {
        this.goods_updata=goods_updata;
    }
    public Date getGoods_updata()
    {
        return this.goods_updata;
    }
    public void setGoods_create(Date goods_create)
    {
        this.goods_create=goods_create;
    }
    public Date getGoods_create()
    {
        return this.goods_create;
    }

    @Override
    public String toString() {
        return "JyGoods{" +
                "goods_id=" + goods_id +
                ", user_id=" + user_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_img='" + goods_img + '\'' +
                ", goods_information='" + goods_information + '\'' +
                ", goods_status=" + goods_status +
                ", goods_type='" + goods_type + '\'' +
                ", goods_updata=" + goods_updata +
                ", goods_create=" + goods_create +
                '}';
    }
}


