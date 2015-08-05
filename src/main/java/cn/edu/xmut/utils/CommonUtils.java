package cn.edu.xmut.utils;

public class CommonUtils {
	/**
	 * 
	 * @描述  通过加盟商类型编号，获取对应的具体类型
	 * @说明 1-校园达人 2-绿色便利店 3-公益团体 4-回收从业 5-战略合作商
	 * @请求参数
	 * int iType 类型编号
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年2月3日 下午2:23:33
	 */
	public static String getJoinerType(int iType){
		String result = "未知类型";
		switch(iType){
		case 1:
			result = "校园达人";
			break;
		case 2:
			result = "绿色便利店";
			break;
		case 3:
			result = "公益团体";
			break;
		case 4:
			result = "回收从业";
			break;
		case 5:
			result = "战略合作商";
			break;
		}
		
		return result;
	}

	/**
	 * 
	 * @描述  把字符串 phone 从第i个字符 到 第i+j个字符 更改为 字符 c
	 * @请求参数
	 * String phone 要处理的字符串
	 * char c  更换后的字符
	 * int i   开始位置
	 * int j   更换的长度
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年3月8日 下午3:32:18
	 */
	public static Object replace(String phone, char c, int i, int j) {
		String strResult="";
		for(int k=0;k<i;k++){
			strResult += phone.charAt(k);
		}
		for(int k=i;k<i+j;k++){
			strResult += c;
		}
		for(int k=i+j;k<phone.length();k++){
			strResult += phone.charAt(k);
		}
	
		return strResult;
	}
}	
