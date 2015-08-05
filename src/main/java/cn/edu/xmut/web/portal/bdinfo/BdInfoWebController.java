package cn.edu.xmut.web.portal.bdinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.UtilCtrl;
import cn.edu.xmut.web.bdinfo.BdInfoController;
/**
 * 
 * @描述 保单WebController
 * @作者 bob(傅文城)
 * @创建时间 2015年4月22日 上午10:30:45
 */
@Controller("pBdInfopWeb")
@RequestMapping(value = "pBdInfopWeb")
public class BdInfoWebController {
	
         @Resource(name = "bdInfoController")
         public BdInfoController bdInfoController;
         
         
         public ModelMap pageByStatusSearchParam() {
     		Pageable pageable = PageUtils.getPageable();
     		int status = UtilCtrl.getRequestIntValueDefaultZero("status");
     		String searchParam = UtilCtrl.getRequestValueNotNull("searchParam");
     		String startTime = UtilCtrl.getRequestValueNotNull("startTime");
     		String endTime = UtilCtrl.getRequestValueNotNull("endTime");
     		ModelMap model = new ModelMap();		
     		JSONObject result = bdInfoController.pageByStatusSearchParam(pageable,status,searchParam,startTime,endTime);
     		model.put("data", result);
     		return model;
     	}
}
