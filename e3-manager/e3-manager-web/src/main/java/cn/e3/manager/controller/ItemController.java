package cn.e3.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3.manager.service.ItemService;
import cn.e3.pojo.TbItem;

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
}
