package com.jianyi.serviceimpl;
import com.jianyi.mapper.JyGoodsMapper;
import com.jianyi.model.JyGoods;
import com.jianyi.service.JyGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("jyGoodsService")
public class JyGoodsServiceImpl implements JyGoodsService {
    @Resource
    private  JyGoodsService jyGoodsService;
    @Resource
    private JyGoodsMapper jyGoodsMapper;
    @Override
    public  int upload(MultipartFile file,Integer user_id,String goods_name,String goods_type)
    {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //设置文件储存路径
        String filePath = "E:\\工作\\test\\";
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
            JyGoods jyGoods = new JyGoods();
            jyGoods.setGoods_img(filePath);
            jyGoods.setGoods_name(fileName);
            jyGoods.setUser_id(user_id);
            jyGoods.setGoods_name(goods_name);
            jyGoods.setGoods_type(goods_type);
            jyGoods.setGoods_status(0);
            int result=jyGoodsService.add(jyGoods);
            if (result == 0){
                return 0;
            }
            return 1;
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public  int isempty(MultipartFile file, Integer user_id, String goods_name, String goods_type)
    {
        if (file.isEmpty())
        {
            return 1;
        }else if (user_id == null)
        {
            return 2;
        }else if (goods_name == null)
        {
            return 3;
        }else if (goods_type == null)
        {
            return 4;
        }
        return 5;
    }
    @Override
    public int add(JyGoods jyGoods) { return jyGoodsMapper.add(jyGoods);}
    @Override
    public List<JyGoods> serach(String keyword2){ return jyGoodsMapper.serach(keyword2);}
    @Override
    public  List<JyGoods> getid(Integer goods_id){return  jyGoodsMapper.getid(goods_id);}
    @Override
    public  int informationchange(Integer goods_id,String goods_name,String goods_information,String goods_type,Integer goods_now){return jyGoodsMapper.informationchange(goods_id,goods_name,goods_information,goods_type,goods_now);}
}
