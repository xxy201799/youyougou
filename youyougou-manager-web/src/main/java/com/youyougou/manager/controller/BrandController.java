package com.youyougou.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.youyougou.entity.PageResult;
import com.youyougou.entity.Result;
import com.youyougou.pojo.TbBrand;
import com.youyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理业务逻辑层
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌数据
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    /**
     * 分页查询所有品牌数据
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page,int size){
        System.out.println("hello");
        return brandService.findPage(page,size);
    }

    /**
     * 添加品牌
     * @param tbBrand
     * @return 返回添加结果
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"增加成功");

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"增加失败");
        }
    }

    /**
     * 更新数据
     * @param brand
     * @return 返回更新结果
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try{
            brandService.update(brand);
            return new Result(true,"修改成功");

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    /**
     * 查找实体对象
     * @param id
     * @return 返回单个品牌
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        if(ids==null||ids.length==0){
            return new Result(false,"请选择你要删除的记录");
        }
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    /**
     * 查询加分页
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,int page,int size){
       return   brandService.findPage(brand,page,size);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }


}
