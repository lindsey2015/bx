//package cn.edu.xmut.core.template;
//import java.io.IOException;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
//import javassist.ClassClassPath;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtMethod;
//import javassist.bytecode.CodeAttribute;
//import javassist.bytecode.LocalVariableAttribute;
//import javassist.bytecode.MethodInfo;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.support.RequestContextUtils;
//
//import freemarker.core.Environment;
//import freemarker.template.ObjectWrapper;
//import freemarker.template.TemplateDirectiveBody;
//import freemarker.template.TemplateDirectiveModel;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateModel;
//
///**
// * 
// * @鎻忚堪 TODO Freemarker @exec鏍囩
// * @鐢ㄦ硶 
// * 		鏍囩@exec涓湁涓や釜灞炴�锛歴rc鍜宯ame
// * 			src鐢眘pring妗嗘灦璁剧疆鐨凜ontroller鍚嶇О锛岃璋冪敤鐨勬柟娉曞悕锛岀被浼肩綉鍧�殑閿�瀵圭粍鎴� * 				Controller鍚嶇О鍜屾柟娉曞悕闂达紝浠�!"鍒嗗紑
// * 				鏂规硶鍚嶅拰閿�瀵归棿锛屼互"?"鍒嗗紑
// * 				閿�瀵归棿锛屼互"&"鍒嗗紑锛岃嫢閿�涓惈"&"锛岃浣跨敤"/&"鏇夸唬
// * 				閿拰鍊间互"="鍒嗗紑锛岃嫢閿�浣跨敤"="锛岃浣跨敤"/="鏇夸唬
// * 			name涓篺reeMarker椤甸潰鐨勫彉閲� * 		@绀轰緥锛�@exec src="controllerName!methodName?param=value&param2=value2" name='variable'/>
// * @鐗瑰埆璇存槑
// * 		閿�瀵癸紙鍙涓敭鍊煎锛夛細
// * 			閿細鏂规硶涓殑鍙橀噺鍚�-鍊硷細涓鸿鍙橀噺鍚嶈祴鍊� * 			濡傛灉鏄疄浣擄紝---閿細瀹炰綋涓殑鍙橀噺鍚�--鍊硷細涓鸿鍙橀噺鍚嶈祴鍊� * 			
// * @浣滆�	闄堣储
// * @鍒涘缓鏃堕棿 2015骞�鏈�2鏃�涓嬪崍11:05:48
// */
//public class ExecDirective implements TemplateDirectiveModel {
//	private String funcName,bean;
//	private Map<String,String> paramsMap;
//	private HttpServletRequest request;
//	
//	private static boolean isBaseType(String name){
//		String baseTypes[]={"int","boolean","float","double","java.lang.String","java.lang.Object"};
//        for (String type : baseTypes) {
//			if(type.equals(name)){
//				return true;
//			}
//		}
//        return false;
//	}
//	private static Object toParse(String name,String value){
//		String baseTypes[]={"int","boolean","float","double","java.lang.String","java.lang.Object"};
//		Object object = null;
//		if(name.equals(baseTypes[4])||name.equals(baseTypes[5])){
//			object=value;
//		}
//		else if(StringUtils.isBlank(value)){
//			value="0";
//		}
//		if(name.equals(baseTypes[0])){
//			object=Integer.parseInt(value);
//		}
//		else if(name.equals(baseTypes[1])){
//			object=Boolean.parseBoolean(value);
//		}
//		else if(name.equals(baseTypes[2])){
//			object=Float.parseFloat(value);
//		}
//		else if(name.equals(baseTypes[3])){
//			object=Double.parseDouble(value);
//		}
//		return object;
//			
//	}
//	
//	private void requestToMap(){
//		@SuppressWarnings("rawtypes")
//		Enumeration names = request.getParameterNames();
//		while(names.hasMoreElements()){
//			String key=names.nextElement().toString();
//			String value=request.getParameter(key);
//			paramsMap.put(key, value);
//		}
//	}
//	
//	private void analyze(String src){
//		String array[],arr[];
//
//		array=src.split("!",2);
//		if(array.length!=2){
//			throw new RuntimeException("@exec鏍囩涓璼rc灞炴�鏈夎锛屾槸鐢盋ontrollerName!MethodName缁勬垚");
//		}
//		bean=array[0];
//		array=array[1].split("\\?",2);
//		funcName=array[0];
//		if(array.length==2){
//			String prop = array[1];
//			if(prop.length()>0&&prop.charAt(0)=='&')
//				prop=prop.substring(1);
//			prop=prop.replaceAll("([^/])&", "$1\n").replaceAll("/&", "&");
//			prop=prop.replaceAll("([^/])=", "$1\b").replaceAll("/=", "=");
//			array=prop.split("\n");
//			for (String s : array) {
//				if(s.matches(".+\b.*")){
//					arr=s.split("\b");
//					if(!arr[0].equals("")){
//						paramsMap.put(arr[0], arr.length>1?arr[1]:"");
//					}
//				}
//			}
//		}
//	}
//	
//	private Object exec(){
//		WebApplicationContext applicationContext = RequestContextUtils.getWebApplicationContext(request);
//		Object action = applicationContext.getBean(bean);
//		if(action==null){
//			throw new RuntimeException("@exec鏍囩灞炴�src鏈夎锛屾湭鎵惧埌Controller锛�+bean);
//		}
//		Class<?> cls = action.getClass();
//		ClassPool pool=ClassPool.getDefault();
//		try {
//			//Constructor<?> constructor=cls.getDeclaredConstructor();
//			//Object exec = constructor.newInstance();
//			pool.insertClassPath(new ClassClassPath(cls));
//
//			/**
//			 *  If a program is running on a web application server such as JBoss and Tomcat, 
//			 *  the ClassPool object may not be able to find user classes 
//			 *  since such a web application server uses multiple class loaders 
//			 *  as well as the system class loader. 
//			 *  In that case, an additional class path must be registered to the ClassPool. 
//			 *	
//			 *	鎵�互鍔犲叆涓嬮潰杩欏彞锛岄伩鍏嶅湪閮ㄧ讲鍒皌omcat涓婂嚭鐜癹avassist.NotFoundException鐨勯敊璇�			*/
//			pool.insertClassPath(new ClassClassPath(cls));
//			
//			CtClass ctClass=pool.get(cls.getName());
//			CtMethod ctMethod=null,ctMethods[]=ctClass.getDeclaredMethods();
//			Method method = null,methods[]=cls.getDeclaredMethods();
//			int i=0;
//			for (CtMethod cm : ctMethods) {
//				if(cm.getName().equals(funcName)){
//					i++;
//					ctMethod=cm;
//				}
//			}
//			if(i!=1){
//				throw new RuntimeException("@exec鏍囩灞炴�src鏈夎锛岃绫诲唴鍙兘鏈変竴涓�+funcName+"鏂规硶");
//			}
//			for (Method m : methods) {
//				if(m.getName().equals(funcName))
//					method=m;;
//			}
//			MethodInfo methodInfo = ctMethod.getMethodInfo();
//			CodeAttribute codeAttribute= methodInfo.getCodeAttribute();
//			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
//	                .getAttribute(LocalVariableAttribute.tag);
//			Class<?>[] paramTypes = method.getParameterTypes();
//	        Object paramValues[] = new Object[paramTypes.length];
//	        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
//	        for (i = 0; i < paramValues.length; i++) {
//	        	if(isBaseType(paramTypes[i].getName())){
//		        	String key=attr.variableName(i+pos);
//		        	paramValues[i]=toParse(paramTypes[i].getName(),paramsMap.get(key));
//		        	//paramsMap.remove(key);
//	        	}
//	        	else{
//	        		Class<?> cl = paramTypes[i];
//	        		if(cl==HttpServletRequest.class){
//	        			paramValues[i]=request;
//	        		}
//	        		else{
//		        		Constructor<?> con=cl.getDeclaredConstructor();
//		        		Object ins = con.newInstance();
//		        		Method[] ms = cl.getDeclaredMethods();
//		        		for (Method m : ms) {
//		        			if(m.getParameterTypes().length==1&&isBaseType(m.getParameterTypes()[0].getName())&&m.getName().startsWith("set")){
//			        			String key=m.getName().substring(3);
//			        			key=key.substring(0,1).toLowerCase()+key.substring(1);
//								if(paramsMap.containsKey(key)){
//									String value=paramsMap.get(key);
//									Object o=toParse(m.getParameterTypes()[0].getName(), value);
//									m.invoke(ins, o);
//									//paramsMap.remove(key);
//								}
//		        			}
//						}
//		        		paramValues[i]=ins;
//	        		}
//	        	}
//	        }
//			return method.invoke(action,paramValues);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("@exec杩愯鍑洪敊");
//		}
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public void execute(Environment env, Map params, TemplateModel[] loopVars,
//			TemplateDirectiveBody body) throws TemplateException, IOException {
//		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		paramsMap=new HashMap<String,String>();
//		
//		Object srcObj = params.get("src");
//		String src=srcObj==null?"":srcObj.toString();
//		Object nameObj = params.get("name");
//		String name=nameObj==null?"":nameObj.toString();
//		if(StringUtils.isBlank(src)){
//			throw new RuntimeException("@exec鏍囩闇�src灞炴�");
//		}
//		if(StringUtils.isBlank(name)){
//			throw new RuntimeException("@exec鏍囩闇�name灞炴�");
//		}
//		requestToMap();
//		analyze(src);
//		Object object=exec();
//		env.setVariable(name, ObjectWrapper.DEFAULT_WRAPPER.wrap(object));
//		//env.setVariable("_"+name, ObjectWrapper.DEFAULT_WRAPPER.wrap(object));
//	}
//}