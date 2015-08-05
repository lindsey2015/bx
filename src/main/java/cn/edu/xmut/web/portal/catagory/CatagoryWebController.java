package cn.edu.xmut.web.portal.catagory;

import javax.annotation.Resource;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.UtilCtrl;
import cn.edu.xmut.web.catagory.CatagoryController;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @描述 类别WebController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月27日 下午2:16:36
 */
@Controller("pCatagoryWeb")
@RequestMapping(value = "pCatagoryWeb")
public class CatagoryWebController {

	@Resource(name = "catagoryController")
	private CatagoryController catagoryController;
	
	
	public ModelMap pageBySearchParam() {
		Pageable pageable = PageUtils.getPageable();
		String searchParam = UtilCtrl.getRequestValueNotNull("searchParam");
		ModelMap model = new ModelMap();		
		JSONObject result = catagoryController.pageBySearchParam(pageable,searchParam);
		model.put("data", result.getJSONObject("data"));
		return model;
	}
	
}
