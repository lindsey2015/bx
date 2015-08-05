package cn.edu.xmut.web.catagory;

import java.util.List;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.catagory.bean.Catagory;
import cn.edu.xmut.modules.catagory.service.CatagoryService;
import cn.edu.xmut.modules.product.bean.Product;
import cn.edu.xmut.modules.product.service.ProductService;
import cn.edu.xmut.utils.JsonTool;
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("catagoryController")
@RequestMapping("catagory")
/**
 * 
 * @描述 类别Controller
 * @作者 bob(傅文城)
 * @创建时间 2015年3月4日 下午2:11:25
 */
public class CatagoryController extends BaseController {
	
	@Resource(name = "catagoryServiceImpl")
	public CatagoryService catagoryService;
	@Resource(name = "productServiceImpl")
	public ProductService productService;
	/**
	 * 
	 * @描述 添加类别
	 * @url  catagory/add.jhtml
	 * @请求参数
	 * String name 类别名称
	 * String nameE 类别险种码
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月27日 下午2:11:53
	 */
	@RequestMapping("/add")
	public @ResponseBody JSONObject add(Catagory catagory) {
		if (!beanValidator(catagory)) {
			return JsonTool.genErrorMsg("验证失败");
		} else {
			Catagory isExist = catagoryService.getByOneField(Catagory.FieldOfCatagory.NAME.name(), catagory.getName());
			if (isExist != null) {
				return JsonTool.genErrorMsg("参数名已存在");
			} else {			
				//新增的类别标记为 可用
				catagory.setUseful(true);
			    catagoryService.save(catagory);					
				return JsonTool.genSuccessMsg("保存成功");
			}
		}
	}

	/**
	 * 
	 * @描述 添加类别
	 * @url  catagory/update.jhtml
	 * @请求参数
	 * String name 类别名称
	 * String nameE 类别险种码
	 * String id 类别ID
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月27日 下午2:11:53
	 */
	@RequestMapping("/update")
	public @ResponseBody JSONObject update(Catagory catagory) {
		if (!beanValidator(catagory)) {
			return JsonTool.genErrorMsg("验证失败");
		} else {
			Catagory isExist = catagoryService.findById(catagory.getId());
			if (isExist.getName().equals(catagory.getName())) {
				isExist.setNameE(catagory.getNameE());
				catagoryService.save(isExist);
				return JsonTool.genSuccessMsg("更新成功");
			} else {
				Catagory isExistOther = catagoryService.getByOneField(
						Catagory.FieldOfCatagory.NAME.name(), catagory.getName());
				if (isExistOther != null) {
					return JsonTool.genErrorMsg("参数名已存在");
				} else {
					isExist.setName(catagory.getName());
					isExist.setNameE(catagory.getNameE());
					catagoryService.save(isExist);
					return JsonTool.genSuccessMsg("更新成功");
				}
			}
		}
	}
	
	/**
	 * 
	 * @描述 批量启用弃用
	 * @url  catagory/updateUseful.jhtml
	 * @请求参数
	 * int useful 是否启用
	 * String[] ids 设置的ID数组
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月27日 下午2:14:12
	 */
	@RequestMapping("/updateUseful")
	public @ResponseBody JSONObject updateUseful(int useful, String[] ids) {		
		if(ids.length>0){
			for (String id : ids) {	 
				Catagory catagory = catagoryService.findById(id);
				
				if(catagory.isUseful()){
					if(1==useful){
						continue;
					}else{
						catagory.setUseful(false);
						catagoryService.save(catagory);
						List<Product> products = productService.findByOneFieldOrderBy(Product.FieldOfProduct.CATAGORY_ID.name(),catagory.getId()," rand()");
						for(Product product:products){
							if(product.isUseful()){
								product.setUseful(false);
								productService.save(product);
							}else{
								continue;
							}
						}
					}
				}else {
					if(0==useful){
						continue;
					}else{
						catagory.setUseful(true);
						catagoryService.save(catagory);
					}
				}			
			}
		}
		return JsonTool.genSuccessMsg("修改成功");
	}

	/**
	 * 
	 * @描述 通过关键字搜索分页查询
	 * @url  catagory/pageBySearchParam.jhtml
	 * @请求参数
	 * pageable 分页数据
	 * String searchParam 搜索关键词
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月27日 下午2:15:22
	 */
	@RequestMapping("/pageBySearchParam")
	public @ResponseBody JSONObject pageBySearchParam(Pageable pageable,String searchParam) {		
		Page<Catagory> list = null;
		if (searchParam != null && !"".equals(searchParam)) {		
			list = catagoryService.findPageByOneFieldLike(pageable, Catagory.FieldOfCatagory.NAME.name(), searchParam);
		} else {
			list = catagoryService.findPageOrderBy(pageable,Catagory.FieldOfCatagory.USEFUL.name()+" desc");
		}
		JSONObject json = new JSONObject();
		json.put("data", list.getContent());
		JSONArray array = json.getJSONArray("data");			
		
		List<Catagory> parse = JSON.parseArray(array.toJSONString(), Catagory.class);
		for(Catagory catagory:parse){
			int count = productService.countByOneField(Product.FieldOfProduct.CATAGORY_ID.name(),catagory.getId());
			catagory.put("count",count);
		}
		Page<Catagory> dics = new Page<Catagory>(parse, list.getTotal(), pageable);
		json.put("data", dics);		
		return json;	
	}

	

}
