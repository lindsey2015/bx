package cn.edu.xmut.web.portal.product;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.utils.UtilCtrl;
import cn.edu.xmut.web.product.ProductController;
/**
 * 
 * @描述 产品WebController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月27日 上午10:30:45
 */
@Controller("pProductWeb")
@RequestMapping(value = "pProductWeb")
public class ProductWebController {
	
         @Resource(name = "ProductController")
         public ProductController productController;
         
         
         public ModelMap listByCatagoryIdSearchParam() {
 			ModelMap model = new ModelMap();	
 			String searchParam = UtilCtrl.getRequestValueNotNull("searchParam");
 			String catagoryId = UtilCtrl.getRequestValueNotNull("catagoryId");
 			JSONObject result = productController.listByCatagoryIdSearchParam(searchParam,catagoryId);
 			model.put("data",result.get("data"));
 			return model;
 		}
         public ModelMap listByUserId() {
        	 ModelMap model = new ModelMap();	
        	 String userId = UtilCtrl.getRequestValueNotNull("userId");
        	 JSONObject result = productController.listByUserId(userId);
        	 model.put("data",result.get("data"));
        	 return model;
         }
}
