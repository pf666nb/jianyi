package com.jianyi.mapper;

import com.jianyi.model.JyGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JyGoodsMapper {
    /**添加*/
    public int add(JyGoods goods);
    /**搜索功能*/
    public List<JyGoods> serach( String keyword);
    /**根据ID返回图片*/
    public List<JyGoods> getid(Integer goods_id);
    public  int informationchange(Integer goods_id,String goods_name,String goods_information,String goods_type,Integer goods_now);
    /**修改goodsname*/
    public int informationgoodsname(Integer goods_id , String goods_name);
    /**修改goodsinformation*/
    public int informationgoodsinformation(Integer goods_id, String goods_information);
    /**修改type*/
    public int informationgoodstype(Integer goods_id,String goods_type);
    /**修改goods_now*/
    public int informationgoodsnow(Integer goods_id,Integer goods_now);


}
