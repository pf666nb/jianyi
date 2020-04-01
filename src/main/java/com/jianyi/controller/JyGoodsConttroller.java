package com.jianyi.controller;
import com.alibaba.fastjson.JSONObject;
import com.jianyi.mapper.JyGoodsMapper;
import com.jianyi.model.JyGoods;
import com.jianyi.service.JyGoodsService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
@RestController
/**自动配置。相当于spring配置文件*/
@EnableAutoConfiguration
@RequestMapping(value = "goods",method = RequestMethod.POST)

public class JyGoodsConttroller {


    JSONObject jsonObject = new JSONObject();
    @Resource
    private JyGoodsService jyGoodsService;
    @Resource
    private JyGoodsMapper jyGoodsMapper;

    @PostMapping(value = "upload")
    /**图片上传功能的实现*/
    public JSONObject upload(@RequestParam("file") MultipartFile file, Integer user_id, String goods_name, String goods_type) {
        int number = jyGoodsService.isempty(file, user_id, goods_name, goods_type);
        int code1 = 1;
        int code2 = 2;
        int code3 = 3;
        int code4 = 4;
        if (number == code1) {
            jsonObject.put("code", 0);
            jsonObject.put("data", null);
            jsonObject.put("message", "图片为空上传失败");
            return jsonObject;
        } else if (number == code2) {
            jsonObject.put("code", 0);
            jsonObject.put("data", null);
            jsonObject.put("message", "用户账号为空上传失败");
            return jsonObject;
        } else if (number == code3) {
            jsonObject.put("code", 0);
            jsonObject.put("data", null);
            jsonObject.put("message", "物品名为空上传失败");
            return jsonObject;
        } else if (number == code4){
            jsonObject.put("code", 0);
            jsonObject.put("data", null);
            jsonObject.put("message", "物品类型为空上传失败");
            return jsonObject;
        }
        int number2 = jyGoodsService.upload(file, user_id, goods_name, goods_type);
        if (number2 == 0) {
            jsonObject.put("code", 0);
            jsonObject.put("data", null);
            jsonObject.put("message", "上传失败");
            return jsonObject;
        } else if (number2 == 1) {
            jsonObject.put("code", 1);
            jsonObject.put("data", null);
            jsonObject.put("message", "上传成功");
            return jsonObject;
        }
        jsonObject.put("code", 0);
        jsonObject.put("data", null);
        jsonObject.put("message", "上传失败");
        return jsonObject;
    }

    /**收藏夹功能的实现*/
@PostMapping(value = "collection")
public JSONObject Collection(HttpSession session, Integer goods_id)
{
    session.setAttribute("goods_id",goods_id);
    List<JyGoods> goods = jyGoodsService.getid(goods_id);
    jsonObject.put("code", 1);
    jsonObject.put("data", goods);
    jsonObject.put("message", "物品详情");
    return jsonObject;
}




    @PostMapping(value = "serach")
    /**搜索功能的实现*/
    public JSONObject serach(String keyword){
        String keyword2  = keyword.replace(" ", "");
        jyGoodsService.serach(keyword2);
        List<JyGoods> goodsList=jyGoodsService.serach(keyword2);
        jsonObject.put("code", 1);
        jsonObject.put("data", goodsList);
        jsonObject.put("message", "null");
        return jsonObject;
    }

    @PostMapping(value = "informationchange")
    /**物品信息修改*/
    public JSONObject informationchange(Integer goods_id,String goods_name,String goods_information,String goods_type,Integer goods_now)
    {
        jyGoodsService.informationchange(goods_id,goods_name,goods_information,goods_type,goods_now);
        jsonObject.put("code", 1);
        jsonObject.put("data", null);
        jsonObject.put("message", "修改成功");
        return jsonObject;
    }
    @PostMapping(value = "inforchangegoodsname")
    /**修改goodsname*/
    public JSONObject informationchangegoodsname(Integer goods_id,String goods_name)
    {
        jyGoodsMapper.informationgoodsname(goods_id,goods_name);
        jsonObject.put("code", 1);
        jsonObject.put("data", null);
        jsonObject.put("message", "修改成功");
        return jsonObject;
    }
    @PostMapping(value = "inforchangegoodstype")
    /**修改goodstype*/
    public JSONObject informationchangegoodstype(Integer goods_id,String goods_type)
    {
        jyGoodsMapper.informationgoodstype( goods_id,goods_type);
        jsonObject.put("code", 1);
        jsonObject.put("data", null);
        jsonObject.put("message", "修改成功");
        return jsonObject;

    }

    @PostMapping(value = "inforchangegoodsinformation")
    /**修改goodstype*/
    public JSONObject informationchangegoodsinformation(Integer goods_id,String goods_information)
    {
        jyGoodsMapper.informationgoodsinformation( goods_id,goods_information);
        jsonObject.put("code", 1);
        jsonObject.put("data", null);
        jsonObject.put("message", "修改成功");
        return jsonObject;

    }
    @PostMapping(value = "inforchangegoodsnow")
    /**修改goodstype*/
    public JSONObject informationchangegoodsnow(Integer goods_id,Integer goods_now)
    {
        jyGoodsMapper.informationgoodsnow( goods_id,goods_now);
        jsonObject.put("code", 1);
        jsonObject.put("data", null);
        jsonObject.put("message", "修改成功");
        return jsonObject;

    }


    @PostMapping(value = "findgoods")
    /**根据物品ID查询物品*/
    public JSONObject findgoods(Integer goods_id)
    {
        List<JyGoods> goodsList =jyGoodsMapper.getid(goods_id);
        if (goodsList == null)
        {
            jsonObject.put("code", 0);
            jsonObject.put("data", null);
            jsonObject.put("message", "查找失败");
            return jsonObject;
        }
        jsonObject.put("code", 1);
        jsonObject.put("data", goodsList);
        jsonObject.put("message", "查找成功");
        return jsonObject;
    }



}