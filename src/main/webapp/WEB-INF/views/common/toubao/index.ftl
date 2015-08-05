<@layout.extends name="layout/simple_layout.ftl">
	<@layout.put block="title" type="replace">太平洋保险管理系统</@layout.put>
	<@layout.put block="contents">
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="javascript:void(0);">太平洋财产保险</a>
					</li>
					<li>
						<a href="javascript:void(0);">投保管理</a>
					</li>
					<li class="active">
						欢迎投保
					</li>
				</ul>
			</div><!-- breadcrumbs -->

			<div class="page-content">
				<form id="toubao-form">
					<div class="row">
						<div class="col-xs-12">
							<div class="row" style="height:40px">
							    <div class="col-xs-6">
									<div class="btn-group group-xs">
			                        </div>
							    </div>
							    
								<div class="col-xs-4 col-xs-offset-8">
									<div>
										<span>选择产品:</span>
										<select id="product" class="tb_disabled">
										</select>
							         </div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">		
									<div class="middle">
										<div class="middle_content">
											<div class="measure">
												<div class="measure_left bfcs" style="display:none" >
													<p>保费测算</p>
													<span>请输入被保险人信息及相关保障要素进行保费测算</span>
													<ul>
														<li>承保年龄</li>
														<li>承保职业</li>
														<li>保障期限</li>
														<li>承保人数</li>
														<li><select id="ageGroup" class="tb_disabled"></select></li>
														<li>不限职业</li>
														<li> <select id="bzDate" class="tb_disabled"></select></li>
													    <li><input type="text" name="nums" class="tb_disabled"/></li>
													</ul>											 
												</div>
						
												<div class="measure_right bfcs" style="display:none" >
												    <h1>合计：￥<span id="total">0</span>元</h1>
													<div class="button">
														<a class="tb_hidden ljtb" title="立即投保" href="javascript:void(0)">立即投保</a>
													</div>
										   	    </div>										    
											</div>
					
											<div class="inf tbArea_tkDownload" style="display:none">
												<div class="inf_top">
													<p>投保范围和条款下载</p>
												</div>
												<div class="man">
													<p><span>*</span> 条款下载:</p>
													<ul>
														<li><a id="tk_addr">点击下载</a></li>
													</ul>
												</div>
												<h5 class="tbArea"></h5>
											</div>
					
											<div class="inf tb_info" style="display:none">
												<div class="inf_top">
													<p>投保人信息</p>
													<h1>(投保人必须年满18周岁)</h1>
												</div>
						
												<@action uri="pUserInfoWeb!get" nickname='userinfo'/>
												<!-- <@toJson data=userinfo encode='false'/> -->  	
					
										        <div class="detail">
													<div class="person">
														<p><span>* </span> 投保人姓名：</p>
														<input type="text"  disabled="disabled" name="name" value="${userinfo.data.name}" class="in_text" />
													</div>
													<div class="person">
														<p><span>* </span>身份证号码：</p>
														<input type="text"  disabled="disabled" name="identity" value="${userinfo.data.identity}" class="in_text" />
													</div>
													<div class="kind">
														<p><span>* </span>出生日期：</p>
														<input type="text"  disabled="disabled"  name="birthday" value="${userinfo.data.birthday}" class="in_text" />
													</div>
													<div class="sex">
														<p><span>* </span>性别：</p>
														<input type="radio"  disabled="disabled" name="sex" <#if userinfo.data.sex==true> checked="checked"</#if>/>
														<label for="1" class="male">男</label>
														<input type="radio"  disabled="disabled" name="sex" <#if userinfo.data.sex==false> checked="checked"</#if>/>
														<label for="1" class="male">女</label>
													</div>
													<div class="kind">
														<p><span>* </span>手机号码：</p>
														<input type="text"  disabled="disabled" name="telephone" value="${userinfo.data.telephone}" class="in_text" />
													</div>
													<div class="kind">
														<p><span>* </span>电子邮箱：</p>
														<input type="text"  disabled="disabled" name="email" value="${userinfo.data.email}" class="in_text" />
													</div>
												</div>
											</div>
					
											<div class="inf tb_info" style="display:none">
												<div class="inf_top">
													<p>被保险人信息</p>
												</div>

												<div class="detail">
							                        <div class="person">
								                        <input type="radio" name="insuredUserType" value="user-input" /> 多名被保险人
														<input type="radio" name="insuredUserType" value="group-import" checked="checked"/> 团单信息导入												                         
													</div>
							                    </div>
							                    
							                    <div id="user-input-div" style="display:none">					                    
													<div class="detail" name="bbxr-info" id="bbxr-info-0">
														<div class="person">
															<p><span>* </span>姓名：</p>
															<input type="text" name="insuredUser.name" class="required" />
														</div>
														<div class="person">
															<p><span>* </span>证件类型：</p>
															<select name="insuredUser.identityType">
																<option value="1">身份证</option>
																<option value="2">护照</option>
																<option value="3">港澳通行证</option>
																<option value="4">台湾通行证</option>
																<option value="5">其他</option>
															</select>
														</div>
														<div class="person">
															<p><span>* </span>证件号码：</p>
															<input type="text" name="insuredUser.identity" class="required" />
														</div>
														<div class="person">
															<p><span>* </span>职业类型：</p>
															<select name="insuredUser.occupationType">
																<option value="1">一类</option>
																<option value="2">二类</option>
																<option value="3">三类</option>
																<option value="4">四类</option>
																<option value="5">五类</option>
																<option value="6">六类</option>
																<option value="7">七类</option>
															</select>
														</div>
														<div class="kind">
															<p><span>* </span>出生日期：</p>
															<input type="text" name="insuredUser.birthday" class="required" />
														</div>	
													</div>
													<div class="tjbd-button" id="add-more-insured">
														<a id="add-more-insured-btn" href="javascript:void(0)">增加被保险人</a>
													</div>
												</div>
												
												<div id="group-import-div" class="detail">
													<div class="person">
														<p><span>* </span> 团单信息导入(excel格式)：</p>
											         	<input type="file" name="file" id="excelAddr" onchange="ajaxExcelUpload('excelAddr') "/>
													</div>
													<div class="person">
														<p><span>* </span> 模板下载(excel格式)：</p>
														<a href="${base}download/tdxx.xls">点击下载</a>
														<input type="hidden" name="excelAddr">
													</div>

													<div class="table-responsive">
														<table class="table table-hover table-bordered">
															<tbody id="insured-user-list">																															
															</tbody>													
														</table>
													</div>
												</div>
											</div>
					
											<div class="inf tb_info" style="display:none">
												<div class="inf_top">
													<p>受益人信息</p>
												</div>
												
												<div class="man">
													<p><span>*</span> 收益人:</p>
													<ul>
														<li>
															<input class="reg_check" disabled="disabled" checked="checked" type="radio"/>
															<label class="human">法定继承人</label>
														</li>
													</ul>
												</div>
												<h5>
													<strong>《继承法》规定的法定继承人范围是：</strong>
													第一继承人为配偶、子女、父母；第二继承人为兄弟姐妹、祖父母、外祖父母
												</h5>
											</div>
					
											<div class="inf tb_info" style="display:none">
												<div class="inf_top">
													<p>紧急联系人（非必填项）</p>
												</div>
												<div class="person">
													<p>联系人姓名：</p>
													<input type="text"  disabled="disabled" name="eContact" value="${userinfo.data.eContact}" class="in_text" />
												</div>
												<div class="kind">
													<p>手机号码：</p>
													<input type="text"  disabled="disabled" name="eTelephone" value="${userinfo.data.eTelephone}" class="in_text" />
												</div>
												<input type="hidden" id="user-info-id" name="userInfoId" value="${userinfo.data.id}" class="in_text" />
											</div>
					
											<div class="inf tb_info" style="display:none">
												<div class="inf_top">
													<p>选择起保日期</p>
													<span>起保日期以保险公司出具的保险单正本（或承保确认函）为准，起保日期将不早于您的缴费日期</span>
												</div>
												<div class="date">
													<p><span>*</span> 起保日期选择</p>
													<input id="startDay" class="span2" size="16" type="text" name="startDay"/>
													<img src="static/plug/My97DatePicker/skin/datePicker.gif" onClick="WdatePicker({skin:'whyGreen',el:'startDay',minDate:'%y-%M-{%d+1}'})">
												</div>
											</div>

											<#-- 投保声明确认-->
											<div class="passage tb_info" style="display:none">
												<h1><strong>投保申明确认</strong></h1>
												<ul>
													<li>1、投保时，本投保人已就该产品的保障内容以及保险金额向被保险人进行了明确说明，并征得其同意。</li>
													<li>2、本投保人兹声明上述各项内容填写属实，并知道如果投保信息不真实，保险公司将有权拒赔，一切后果由本人承担。</li>
													<li>3、本投保人已阅读该产品详细条款，了解并接受包括有关责任条款及免除责任条款、退保、投保人、被保险人义务的内容等重要事项。本投保人特此同意接受条款全部内容。</li>
													<li>4、本投保人已明确了解该保险合同的成立、生效是以保险公司出具保单为准。</li>
													<li>5、本投保人已明确并接受本计划的承保机构、承保、保全变更、退保和理赔办理方式及理赔金支付方式。</li>
													<li>6、根据《中华人民共和国合同法》第十一条规定，数据电文是合法的合同表现形式。本人接受保险公司提供的电子保单作为本投保书成立生效的合法有效凭证，具有完全证据效力。</li>
												</ul>

												<div class="on">
													<div class="check-wrap">
														<input id="accepted-chk" name="accepted" check="false" type="checkbox" />
														<p><span> *</span>本人接受以上投保申明</p>
													</div>
													<div class="tjbd-button">
														<a onclick="add(1)" href="javascript:void(0)">暂存订单</a>
													</div>
													<div class="tjbd-button">
														<a onclick="add(2)" href="javascript:void(0)">提交订单</a>
													</div>
												</div>
											</div>	
										</div><!-- middle content -->								
									</div><!-- middle -->
								</div><!-- column in-->
							</div><!-- row in -->	
						</div><!-- column out-->
					</div><!-- row out -->
				</form>
			</div><!-- page content -->
		</div><!-- main content -->

		<link rel="stylesheet" type="text/css" href="${base}static/css/common/content.css" />

		<script src="static/plug/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="static/js/CascadingSelect.js"></script>
		<script src="static/plug/ajaxfileupload/ajaxfileupload.js"></script>
		<script src="static/plug/ext/uploadFile.js"></script>
		<script src="static/plug/handlebars/handlebars-v3.0.3.js"></script>
		<script src="static/plug/handlebars/handlebars-helper.js"></script>
		<script src="static/js/toubao.js"></script>		

		<#include "/tmpl/insured-user-list.html" >
		<script>
			var basePath = "${base}";
		</script>

	</@layout.put>
</@layout.extends>