package cn.edu.xmut.utils;

/**
 * @author yangzj
 * @version 2014-8-18 下午3:58:39
 * 常量定义
 */
public class Constants {
	//报价的status
	/**
	 * 可用报价 1
	 */
	public static final int QUOTE_VALIDATE=1;
	/**
	 * 预报价 0
	 */
	public static final int QUOTE_UNSAVE=0;
	/**
	 * 不可用报价 2
	 */
	public static final int QUOTE_UNVALIDATE=2;
	
	//角色名称
	public static final String FRONT="前台用户";
	public static final String PARTNER="合作商";
	public static final String JOINER="加盟商";

	public static final String MOBILE_TYPE = "1";
	public static final String PAD_TYPE = "2";
	//估值状态
	/**
	 * 以旧换新，购物车
	 */
	public static final int EVALUATE_YJHX = -6;
	/**
	 * 投废
	 */
	public static final int EVALUATE_TOUFEI = -5;
	/**
	 * 退单
	 */
	public static final int EVALUATE_TUIDAN = -4;
	/**
	 * 估值与实际不符
	 */
	public static final int EVALUATE_DIFFERENT = -3;
	/**
	 * 估值后的订单--游客
	 */
	public static final int EVALUATE_YOUKEGUZHI = -2;
	/**
	 * 进入环保回收箱
	 */
	public static final int EVALUATE_HUISHOUXIANG = -1;
	/**
	 * 正常订单
	 */
	public static final int EVALUATE_NORMAL = 0;
	/**
	 * 检测完毕
	 */
	public static final int EVALUATE_CHECK = 1;
	/**
	 * 选择合作商
	 */
	public static final int EVALUATE_CHOOSEPARTNER = 2;
	/**
	 * 客户确认评价
	 */
	public static final int EVALUATE_CONFIRM = 3;
	
	//订单状态
	/**
	 * 退单
	 */
	public static final int DD_TUIDAN = -2;
	/**
	 * 估值与实际不符
	 */
	public static final int DD_DIFFERENT = -1;	
	/**
	 * 用户下订单
	 */
	public static final int DD_YONGHUXIADAN = 0;
	/**
	 * 检测完毕
	 */
	public static final int DD_CHECK = 1;
	/**
	 * 选择中标合作商
	 */
	public static final int DD_CHOOSEPARTNER = 2;
	/**
	 * 客户确认评价
	 */
	public static final int DD_CONFIRM = 3;
	// 实体验证是否成功
	public static final String VALID_SUCCESS = "valid_success";
	public static final String VALID_FALIURE = "valid_failure";
	
	
	// 性别
	public static final int SEX_MALE = 0; 
	public static final int SEX_FEMALE = 1; 
	
	//品牌添加参数
	public static final int BRAND_BEG_MODELCOUNT = 0;
	public static final int BRAND_BEG_FDCOUNT = 0;
	
	//品牌useful字段
	public static final boolean BRAND_USEFUL = true;
	public static final boolean BRAND_NO_USEFUL = false;
	
	//型号useful字段
	public static final boolean MODEL_USEFUL = true;
	public static final boolean MODEL_NO_USEFUL = false;
	
	//二级参数useful字段
		public static final boolean FD_USEFUL = true;
		public static final boolean FDL_NO_USEFUL = false;
		
	//类别fdcount初始化
	public static final int CATAGORY_BEG_FDCOUNT = 0;
	
	//类别type
	public static final int CATAGORY_TYPE_MOBILE = 1;
	public static final int CATAGORY_TYPE_PAD = 2;
	
	//类别useful字段
	public static final boolean CATAGORY_USEFUL = true;
	public static final boolean CATAGORY_NO_USEFUL = false;
	
	//一级参数useful字段
	public static final boolean FACTOR_USEFUL = true;
	public static final boolean FACTOR_NO_USEFUL = false;
	
	//二级参数useful字段
	public static final boolean FACTORDETAIL_USEFUL = true;
	public static final boolean FACTORDETAIL_NO_USEFUL = false;
	

	
	//回收单创建状态
	public static final int EVALUATE_STATUS_BEGIN = -1; 
	
	//订单状态
	public static final int DINGDAN_STATUS_ORDER = 0; 
	
	//brand 排序一
	public static final int BRAND_ORDER_FIRST = 1; 
	
	//类别type
	public static final int YJHXDD_TYPE_MOBILE = 1;
	public static final int YJHXDD_TYPE_PAD = 2;
}
