package com.youyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youyougou.entity.PageResult;
import com.youyougou.mapper.TbBrandMapper;
import com.youyougou.pojo.TbBrand;
import com.youyougou.pojo.TbBrandExample;
import com.youyougou.pojo.TbBrandExample.Criteria;
import com.youyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理实现
 */

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper tbBrandMapper;

    /**
     * 查询所有品牌数据
     * @return
     */
    @Override
    public List<TbBrand> findAll() {

        return tbBrandMapper.selectByExample(null);
    }


    /**
     * 实现分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<TbBrand> page=(Page<TbBrand>)tbBrandMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 添加品牌实现
     * @param tbBrand
     */
    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public void update(TbBrand brand) {
        tbBrandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        for(int i=0;i<ids.length;i++){
            tbBrandMapper.deleteByPrimaryKey(ids[i]);
        }
    }

    /**
     * 条件查询：分页
     * @param brand
     * @param pageNum 当前页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        TbBrandExample example=new TbBrandExample();
        Criteria criteria=example.createCriteria();
        if(brand!=null){
            if(brand.getFirstChar()!=null&&brand.getFirstChar().length()>0){
                criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
            }
        }
        if(brand.getName()!=null&&brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
        }
        Page<TbBrand> page=null;
        try {
            page=(Page<TbBrand>)tbBrandMapper.selectByExample(example);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }


}
