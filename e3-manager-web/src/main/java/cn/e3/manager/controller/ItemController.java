package cn.e3.manager.controller;

import cn.e3.pojo.TbItemDesc;
import cn.e3.utils.E3mallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3.manager.service.ItemService;
import cn.e3.pojo.TbItem;
import cn.e3.utils.DatagridPagebean;

@Controller//交给spring管理
public class ItemController {

	//注入service服务对象
	@Autowired
	private ItemService itemService;
	/**
	 * 需求:根据id查询商品数据
	 * 请求:item/list/{itemId}
	 */
	@RequestMapping("item/list/{itemId}")
	@ResponseBody//直接返回json数据
	/**
	 * 坏处是：返回之前，若前端编码格式不一致，很容易导致乱码
	 * 好处是：GET模式下，这里使用了@PathVariable绑定输入参数，非常适合Restful风格
	 * 因为隐藏了参数与路径的关系，可以提升网站的安全性，静态化页面，降低恶意攻击风险。
	 * POST模式下，使用@RequestBody绑定请求对象，Spring会帮你进行协议转换，将Json、Xml协议转换成你需要的对象。
	 * @PathVariable中的参数可以是任意的简单类型
	 * 支持使用正则表达式
	 * Spring会自动将其转换成合适的类型或者抛出 TypeMismatchException异常
	 */
	public TbItem findItemByID(@PathVariable Long itemId){//模板映射
		//调用service服务方法
		TbItem item = itemService.findItemByID(itemId);
		return item;
	}
	
	/**
	 * 需求:分页查询商品列表
	 * 请求:/item/list
	 * 参数:Integer page , Integer rows
	 * 返回值:DatagridPagebean
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public DatagridPagebean findItemListByPage(@RequestParam(defaultValue="1") Integer page ,
			@RequestParam(defaultValue="30") Integer rows){
		//远程调用service服务方法
		DatagridPagebean pagebean = itemService.findItemListByPage(page, rows);
		return pagebean;
	}

	/**
	 * 需求:保存商品对象
	 * 请求:/item/save
	 * 参数:TbItem item , TbItemDesc itemDesc , ItemParam parram
	 * 返回值:json格式E3mallResult
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public E3mallResult saveItem(TbItem item, TbItemDesc itemDesc , String itemParams){
		//调用远程service服务方法
		E3mallResult result = itemService.saveItem(item,itemDesc,itemParams);
		return result;
	}

}
