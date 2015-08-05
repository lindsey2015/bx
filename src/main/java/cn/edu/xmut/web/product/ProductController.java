package cn.edu.xmut.web.product;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.core.utils.StringUtils;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.agegroup.bean.AgeGroup;
import cn.edu.xmut.modules.agegroup.service.AgeGroupService;
import cn.edu.xmut.modules.catagory.bean.Catagory;
import cn.edu.xmut.modules.catagory.service.CatagoryService;
import cn.edu.xmut.modules.product.bean.Product;
import cn.edu.xmut.modules.product.service.ProductService;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.userproduct.bean.UserProduct;
import cn.edu.xmut.modules.userproduct.service.UserProductService;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;
/**
 * 
 * @描述 产品Controller
 * @作者 bob(傅文城)
 * @创建时间 2015年4月27日 下午1:29:09
 */
@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("ProductController")
@RequestMapping("product")
public class ProductController extends BaseController{
	
	@Resource(name="productServiceImpl")
	public ProductService productService;
	
	@Resource(name="ageGroupServiceImpl")
	public AgeGroupService ageGroupService;
	@Resource(name="catagoryServiceImpl")
	public CatagoryService catagoryService;
	@Resource(name="userProductServiceImpl")
	public UserProductService userProductService;
	/**
	 * 
	 * @描述  通过搜索类别ID,关键字获取 参数列表
	 * @url  product/listByCatagoryIdSearchParam。jhtml
	 * @请求参数
	 * String searchParam 关键字
	 * String catagoryId 类别ID
	 * @作者  bob(傅文城)
	 * @创建时间 2015年4月27日 上午10:43:57
	 */
    @RequestMapping("/listByCatagoryIdSearchParam")
	public @ResponseBody JSONObject listByCatagoryIdSearchParam(String searchParam,String catagoryId) {		
		List<Product> list = null;
		if (searchParam != null && !"".equals(searchParam)) {	
			list = productService.findListByTwoFieldsLike(Product.FieldOfProduct.NAME.name(),searchParam,catagoryId);
		} else {
			list = productService.findByOneFieldOrderBy(Product.FieldOfProduct.CATAGORY_ID.name(),catagoryId,Product.FieldOfProduct.USEFUL.name()+" desc");
		}
		for(Product product:list){
		   	int count = ageGroupService.countByOneField(AgeGroup.FieldOfAgeGroup.PRODUCT_ID.name(),product.getId());
		   	product.put("count",count);
		   	Catagory catagory = catagoryService.getByOneField(Catagory.FieldOfCatagory.ID.name(),product.getCatagoryId());
		   	product.put("catagoryName",catagory.getName());
		}
		return JsonTool.genSuccessMsg(list);	
	}
    
    /**
     * 
     * @描述 通过用户关联产品遍历
     * @url  product/listByUserProduct.jhtml
     * @请求参数
     * @作者  bob(傅文城)
     * @创建时间 2015年4月27日 下午1:32:34
     */
    @RequestMapping("/listByUserProduct")
    public @ResponseBody JSONObject listByUserProduct() {		
    	 List<Product> products = productService.findByOneFieldOrderBy(Product.FieldOfProduct.USEFUL.name(),true,Product.FieldOfProduct.NAME.name());
  	   User user = (User) UtilCtrl.currentUser(User.class);
  	 List<Product> productResults = new ArrayList<Product>();
  	   for(Product product:products){
  		   UserProduct userProduct = userProductService.getByTwoFields(UserProduct.FieldOfUserProduct.PRODUCT_ID.name(),
  				   product.getId(),UserProduct.FieldOfUserProduct.USER_ID.name(),user.getId());
  		   if(userProduct!=null){
  			 productResults.add(product);
  		   }
  	   }
  	   return JsonTool.genSuccessMsg(productResults);
    }
    /**
     * 
     * @描述 通过用户ID关联出产品
     * @url  product/listByUserId.jhtml
     * @请求参数
     * String userId 用户ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月27日 下午1:32:34
     */
   @RequestMapping("/listByUserId")
   public @ResponseBody JSONObject listByUserId(String userId){
	   List<Product> products =null;
	   products = productService.findByOneFieldOrderBy(Product.FieldOfProduct.USEFUL.name(),true,Product.FieldOfProduct.NAME.name());
	   for(Product product:products){
		   UserProduct userProduct = userProductService.getByTwoFields(UserProduct.FieldOfUserProduct.PRODUCT_ID.name(),
				   product.getId(),UserProduct.FieldOfUserProduct.USER_ID.name(),userId);
		   if(userProduct!=null){
			   product.put("include",true);
		   }else{
			   product.put("include",false);
		   }
	   }
	   return JsonTool.genSuccessMsg(products);
	   
   }
   
   /**
    * 
    * @描述 通过用户ID关联出产品
    * @url  product/getByProductId.jhtml
    * @请求参数
    * String productId 产品ID
    * @作者  bob(傅文城)
    * @创建时间 2015年4月27日 下午1:32:34
    */
  @RequestMapping("/getByProductId")
  public @ResponseBody JSONObject getByProductId(String productId){
	   if(StringUtils.isEmpty(productId)){
		   return JsonTool.genErrorMsg("产品为空");
	   }else{
        Product product = productService.findById(productId);		   
		   return JsonTool.genSuccessMsg(product);
	   }
	   
  }
   
    /**
     * 
     * @描述 新增产品
     * @url  product/add.jhtml
     * @请求参数
     * String name 产品名称
     * String catagoryId 类别ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月27日 下午1:32:34
     */
    @RequestMapping("/add")
    public @ResponseBody JSONObject add(Product product) throws UnsupportedEncodingException{
   	 if(!beanValidator(product)){
			return JsonTool.genErrorMsg("验证失败");
		}else{
			Product isExist = productService.getByTwoFields(Product.FieldOfProduct.CATAGORY_ID.name(),product.getCatagoryId(),Product.FieldOfProduct.NAME.name(),product.getName());
        	if(isExist!=null){
        		return JsonTool.genErrorMsg("该产品已存在");
        	}else{
        		product.setUseful(true);
        		product.setTbArea(java.net.URLDecoder.decode(product.getTbArea(),"UTF-8"));
        		productService.save(product);
        		return JsonTool.genSuccessMsg("产品添加成功");
        	}
		}
    }
    
    /**
     * 
     * @描述 新增用户产品关联
     * @url  product/adduserproduct.jhtml
     * @请求参数
     * String[] productIds 产品ID
     * String userId 用户ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月27日 下午1:32:34
     */
    @RequestMapping("/adduserproduct")
    public @ResponseBody JSONObject addUserProduct(String[] productIds,String userId){
    		for(String productId:productIds){
    			UserProduct userProduct = userProductService.getByTwoFields(UserProduct.FieldOfUserProduct.PRODUCT_ID.name(),
    					productId,UserProduct.FieldOfUserProduct.USER_ID.name(),userId);
    			if(userProduct!=null){
    				continue;
    			}else{
    				UserProduct userProduct1 = new UserProduct();
    				userProduct1.setProductId(productId);
    				userProduct1.setUserId(userId);
    				userProductService.save(userProduct1);
    				
    			}
    		}
        		return JsonTool.genSuccessMsg("关联添加成功");
    }
    /**
     * 
     * @描述 删除用户产品关联
     * @url  product/deluserproduct.jhtml
     * @请求参数
     * String[] productIds 产品ID
     * String userId 用户ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月27日 下午1:32:34
     */
    @RequestMapping("/deluserproduct")
    public @ResponseBody JSONObject delUserProduct(String[] productIds,String userId){
    		for(String productId:productIds){
    			UserProduct userProduct = userProductService.getByTwoFields(UserProduct.FieldOfUserProduct.PRODUCT_ID.name(),
    					productId,UserProduct.FieldOfUserProduct.USER_ID.name(),userId);
    			if(userProduct!=null){
    				userProductService.deleteById(userProduct.getId());
    			}
    		}
        		return JsonTool.genSuccessMsg("关联添加成功");
    }
    
    /**
     * 
     * @描述 修改产品名称
     * @url  product/update.jhtml
     * @请求参数
     * String name 产品名称
	 * String id 产品ID
     * @作者  bob(傅文城)
     * @创建时间 2015年4月27日 下午1:38:43
     */
    @RequestMapping("/update")
    public @ResponseBody JSONObject update(Product product) throws UnsupportedEncodingException{
    	if(!beanValidator(product)){
    		return JsonTool.genErrorMsg("验证失败");
 		}else{
 			Product isExist = productService.findById(product.getId());
         	if (isExist.getName().equals(product.getName())) {
         		isExist.setTbArea(java.net.URLDecoder.decode(product.getTbArea(),"UTF-8"));
         		isExist.setTkAddr(product.getTkAddr());
         		productService.save(isExist);
				return JsonTool.genSuccessMsg("更新成功");
			} else {
				Product isExistOther = productService.getByTwoFields(
						Product.FieldOfProduct.CATAGORY_ID.name(),product.getCatagoryId(),Product.FieldOfProduct.NAME.name(), product.getName());
				if (isExistOther != null) {
					return JsonTool.genErrorMsg("产品名已存在");
				} else {
					isExist.setTbArea(java.net.URLDecoder.decode(product.getTbArea(),"UTF-8"));
	         		isExist.setTkAddr(product.getTkAddr());
					isExist.setName(product.getName());
					productService.save(isExist);
					return JsonTool.genSuccessMsg("更新成功");
				}
			}
		}
	}
    /**
	 * 
	 * @描述 根据ID取一条产品信息
	 * @url  product/updateUI.jhtml
	 * @请求参数
	 * String id 产品信息ID
	 * @作者  bob(傅文城)
	 * @创建时间 2015年5月6日 下午11:23:04
	 */
	@RequestMapping("/updateUI")
	public @ResponseBody JSONObject updateUI() {
		//return JsonTool.genErrorMsg("id不存在");
		String noExit = "not exit";
		String id = UtilCtrl.getRequestValueWithDefault("id", noExit);
		if(noExit.equals(id)){
			return JsonTool.genErrorMsg("id不存在");
		}
		Product product = productService.findById(id);
		if (product == null) {
			return JsonTool.genErrorMsg("记录不存在");
		} else {			
			return JsonTool.genSuccessMsg(product);
		}
			
	}
    /**
     * 
     * @描述 产品启用与禁用
     * @url  product/updateUseful.jhtml
     * @请求参数
     * int useful 是否启用
     * String ids 修改的id数组
     * @作者  bob(傅文城)
     * @创建时间 2015年4月21日 下午1:37:39
     */
    @RequestMapping("/updateUseful")
	public @ResponseBody JSONObject updateUseful(int useful, String[] ids) {		
		if(ids.length>0){
			for (String id : ids) {	 
				Product product = productService.findById(id);
				if(product.isUseful()){
					if(1==useful){
						continue;
					}else{
						product.setUseful(false);
						productService.save(product);
					}
				}else {
					if(0==useful){
						continue;
					}else{
						product.setUseful(true);
						productService.save(product);
					}
				}			
			}
		}
		return JsonTool.genSuccessMsg("修改成功");
	}
    
}
