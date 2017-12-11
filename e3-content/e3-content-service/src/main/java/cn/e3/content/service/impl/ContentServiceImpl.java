package cn.e3.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.content.service.ContentService;
import cn.e3.mapper.TbContentMapper;
import cn.e3.pojo.TbContent;
import cn.e3.pojo.TbContentExample;
import cn.e3.pojo.TbContentExample.Criteria;
import cn.e3.utils.AdItem;
import cn.e3.utils.DatagridPagebean;
import cn.e3.utils.E3mallResult;
@Service
public class ContentServiceImpl implements ContentService {
	
	//注入内容mapper接口代理对象
	@Autowired
	private TbContentMapper contentMapper;
	
	//注入广告宽
	@Value("${WIDTH}")
	private Integer WIDTH;
	
	@Value("${WIDTHB}")
	private Integer WIDTHB;
	
	//高
	@Value("${HEIGHT}")
	private Integer HEIGHT;
	
	@Value("${HEIGHTB}")
	private Integer HEIGHTB;
	
	
	

	/**
	 * 需求:根据外键查询广告内容数据进行分页展示
	 * 参数:Long categoryId,Integer page,Integer rows
	 * 返回值:DatagridPagebean
	 */
	public DatagridPagebean findContentListByPage(Long categoryId,
			Integer page, Integer rows) {
		// 创建example对象
		TbContentExample example = new TbContentExample();
		//创建criteria对象
		Criteria createCriteria = example.createCriteria();
		//设置查询参数:根据外键查询广告内容数据
		createCriteria.andCategoryIdEqualTo(categoryId);
		
		//设置分页查询参数
		PageHelper.startPage(page, rows);
		
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//创建pageINfo分页工具类对象,从pageINfo获取分页数据
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		
		//创建分页包装类对象
		DatagridPagebean pagebean = new DatagridPagebean();
		//设置总记录数
		pagebean.setTotal(pageInfo.getTotal());
		//设置总记录
		pagebean.setRows(list);
		
		return pagebean;
	}

	/**
	 * 需求:保存广告内容数据
	 * 参数:TbContent content
	 * 返回值:E3mallResult
	 */
	public E3mallResult saveContent(TbContent content) {
		// 补全时间
		Date date = new Date();
		content.setUpdated(date);
		content.setCreated(date);
		//保存
		contentMapper.insertSelective(content);
		return E3mallResult.ok();
	}

	/**
	 * 需求:查询首页加载广告数据
	 * @param categoryId
	 * @return
	 */
	public List<AdItem> findContentAdList(Long categoryId) {
		//创建广告数据封装集合对象List<AdItem>
		List<AdItem> adList = new ArrayList<AdItem>();
		
		// 创建example对象
		TbContentExample example = new TbContentExample();
		//创建criteria对象
		Criteria createCriteria = example.createCriteria();
		//设置查询参数
		createCriteria.andCategoryIdEqualTo(categoryId);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		//循环广告内容数据,把广告内容封装广告集合对象
		for (TbContent tbContent : list) {
			//创建广告对象AdItem
			AdItem ad = new AdItem();
			//提示信息
			ad.setAlt(tbContent.getSubTitle());
			//售卖地址
			ad.setHref(tbContent.getUrl());
			//图片地址
			ad.setSrc(tbContent.getPic());
			//图片地址
			ad.setSrcB(tbContent.getPic2());
			
			//设置图片宽,高
			ad.setHeight(HEIGHT);
			ad.setHeightB(HEIGHTB);
			ad.setWidth(WIDTH);
			ad.setWidthB(WIDTHB);
			
			//把广告对象添加到广告集合
			adList.add(ad);
		}
		return adList;
	}

}
