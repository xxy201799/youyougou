package com.youyougou.sellergoods.service;

import com.youyougou.entity.PageResult;
import com.youyougou.pojo.TbBrand;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理接口
 */
public interface BrandService {
    public List<TbBrand> findAll();

    /**
     * 返回分页列表
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    /**
     * 添加品牌
     * @param tbBrand
     */
    public void add(TbBrand tbBrand);

    /**
     * 修改
     * @param brand
     */
    public void update(TbBrand brand);

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 批量删除
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 分页条件查询
     * @param brand
     * @param pageNum 当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TbBrand brand,int pageNum,int pageSize);

    /**
     * 品牌下拉框数据
     * @return
     */
    public List<Map> selectOptionList();

}
