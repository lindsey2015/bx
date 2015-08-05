/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.edu.xmut.core.web;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;

import com.ckfinder.connector.ServletContextFactory;
import com.ckfinder.connector.configuration.Configuration;
import com.ckfinder.connector.data.AccessControlLevel;
import com.ckfinder.connector.utils.AccessControlUtil;

/**
 * CKFinder配置
 * @author lilin
 * @version 2013-01-15
 */
public class CKFinderConfig extends Configuration {

	public CKFinderConfig(ServletConfig servletConfig) {
		super(servletConfig);
	}

	@Override
	protected Configuration createConfigurationInstance() {
		boolean isView = SecurityUtils.getSubject().isPermitted("cms:ckfinder:view");
		boolean isUpload = SecurityUtils.getSubject().isPermitted("cms:ckfinder:upload");
		boolean isEdit = SecurityUtils.getSubject().isPermitted("cms:ckfinder:edit");
		AccessControlLevel alc = this.getAccessConrolLevels().get(0);
		alc.setFolderView(isView);
		alc.setFolderCreate(isEdit);
		alc.setFolderRename(isEdit);
		alc.setFolderDelete(isEdit);
		alc.setFileView(isView);
		alc.setFileUpload(isUpload);
		alc.setFileRename(isEdit);
		alc.setFileDelete(isEdit);
		//		for (AccessControlLevel a : this.getAccessConrolLevels()){
		//			System.out.println(a.getRole()+", "+a.getResourceType()+", "+a.getFolder()
		//					+", "+a.isFolderView()+", "+a.isFolderCreate()+", "+a.isFolderRename()+", "+a.isFolderDelete()
		//					+", "+a.isFileView()+", "+a.isFileUpload()+", "+a.isFileRename()+", "+a.isFileDelete());
		//		}
		AccessControlUtil.getInstance(this).loadACLConfig();
		try {
			Object o = SecurityUtils.getSubject().getPrincipal();
			Long id = Long.parseLong(BeanUtils.getProperty(o, "id"));
			//			if (o instanceof Principal) {
			//			Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
			this.baseURL = ServletContextFactory.getServletContext().getContextPath() + "/userfiles/"
					+ (o != null ? id : 0) + "/";
			//			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new CKFinderConfig(this.servletConf);
	}

	@Override
	public boolean checkAuthentication(final HttpServletRequest request) {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

}
