package com.jianyi.service;

import com.jianyi.model.JyGoods;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JyGoodsService {
    /**文件上传
   */ public  int upload(MultipartFile file,Integer user_id,String goods_name,String goods_type);
    /**验证good_name,user_id,file是否为空*/
    public  int isempty(MultipartFile file, Integer user_id, String goods_name, String goods_type);
    /**图片的上传
  */  public  int add(JyGoods goods);
    /**物品的搜索
  */  public  List<JyGoods> serach(String keyword2);
    /**根据id返回物品*/
    public  List<JyGoods> getid(Integer goods_id);
    /**修改物品信息*/   public  int informationchange(Integer goods_id,String goods_name,String goods_information,String goods_type,Integer goods_now);
}
