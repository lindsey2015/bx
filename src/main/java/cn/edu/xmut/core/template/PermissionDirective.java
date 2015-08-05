package cn.edu.xmut.core.template;
import java.io.IOException;
import java.util.Map;

import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.utils.UtilCtrl;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class PermissionDirective implements TemplateDirectiveModel {
	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String value = params.get("value").toString();
		if(value==null){
			throw new RuntimeException("@permission指令需要value属性");
		}	
		int iType = Integer.parseInt(value);
		boolean blFlag = false;
		User user = UtilCtrl.sessionGet("user");
		if(user==null ){
			return;
		}
		
		if(iType==user.getType()){
			blFlag = true;			
		}
	
		if(blFlag){
			body.render(env.getOut());
		}
		
	}
}