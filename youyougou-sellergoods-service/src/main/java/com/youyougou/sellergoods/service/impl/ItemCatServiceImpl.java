package com.youyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youyougou.mapper.TbItemCatMapper;
import com.youyougou.pojo.TbItemCat;
import com.youyougou.pojo.TbItemCatExample;
import com.youyougou.pojo.TbItemCatExample.Criteria;
import com.youyougou.sellergoods.service.ItemCatService;

import com.youyougou.entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbItemCat> findAll() {
		return itemCatMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbItemCat> page=   (Page<TbItemCat>) itemCatMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbItemCat itemCat) {
		itemCatMapper.insert(itemCat);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbItemCat itemCat){
		itemCatMapper.updateByPrimaryKey(itemCat);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbItemCat findOne(Long id){
		return itemCatMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		System.out.println("进入删除工作"+ids.length);
		for(Long id:ids){
			criteria.andParentIdEqualTo(id);
			List<TbItemCat> list=itemCatMapper.selectByExample(example);
			if(list==null||list.size()==0){
				//System.out.println("此处执行删除操作");
				itemCatMapper.deleteByPrimaryKey(id);
			}else {
				//System.out.println("失败");
				throw new RuntimeException("删除失败");
			}

		}		
	}
	
	
		@Override
	public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		
		if(itemCat!=null){			
						if(itemCat.getName()!=null && itemCat.getName().length()>0){
				criteria.andNameLike("%"+itemCat.getName()+"%");
			}
	
		}
		
		Page<TbItemCat> page= (Page<TbItemCat>)itemCatMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<TbItemCat> findByParentId(Long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		return itemCatMapper.selectByExample(example);
	}

}
