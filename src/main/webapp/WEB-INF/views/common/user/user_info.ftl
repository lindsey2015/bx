<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
    	<@action uri="pUserInfoWeb!getByUserId" nickname='userinfo'/>
		<!-- <@toJson data=userinfo encode='false'/> --> 
		<div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  			      
	        <form id="form">	      						
			    <div class="modal-body">	
				    <input type="hidden" name="userId"value="${userinfo.data.userId}"/>
				    <table>	
					    <tr>            
							<td><span>投保人姓名：</span></td>
							<td><input type="text" name="name" size="25" value="${userinfo.data.name}"/></td>
						</tr>	
						<tr>			
							<td><span>身份证：</span></td>
							<td><input type="text" name="identity" size="25" value="${userinfo.data.identity}"/></td>
						</tr>
						<tr>					
							<td><span>出生日期：</span></td>
							<td>
								<input id="birthday" size="16" type="text" name="birthday" value="${userinfo.data.birthday}"/>
								<img src="${base}static/plug/My97DatePicker/skin/datePicker.gif" onClick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d',el:'birthday'})">
							</td>	
						</tr>
						<tr>				
							<td><span>性别：</span></td>
							<td>
								<select name="sex">
									<option value="1" <#if userinfo.data.sex==true>selected="selected"</#if>>男</option>
									<option value="0" <#if userinfo.data.sex==false>selected="selected"</#if>>女</option>
								</select>
							</td>					
						</tr>
						<tr>
							<td><span>手机号码：</span></td>
							<td><input type="text" name="telephone" size="25" value="${userinfo.data.telephone}"/></td>					
						</tr>
						<tr>
							<td><span>电子邮箱：</span></td>
							<td><input type="text" name="email" size="25" value="${userinfo.data.email}"/></td>						
						</tr>
						 <tr>            
							<td><span>紧急联系人姓名：</span></td>
							<td><input type="text" name="eContact" size="25" value="${userinfo.data.eContact}"/></td>
						</tr>
						 <tr>            
							<td><span>紧急联系人电话：</span></td>
							<td><input type="text" name="eTelephone" size="25" value="${userinfo.data.eTelephone}"/></td>
						</tr>
					</table>			
			    </div>
			    <div class="modal-footer">
			        <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			</form>
    	</div>

		<script type="text/javascript">
		    $().ready(function() {
			    $formAjax({
			        id: "#form",
			        url: 'userinfo/save.jhtml',
			        rules: {
			            name: ["#", "请输入投保人姓名", "#maxLength", 25],
			            identity: ["#", "请输入身份证", "#maxLength", 25],
			            birthday: ["#", "请选择出生日期", "#maxLength", 25],
			            telephone: ["#", "请输入手机号", "#maxLength", 25],
			            email: ["#", "请输入电子邮箱", "#maxLength", 25],
			            eContact: ["#", "请输入紧急联系人姓名", "#maxLength", 25],
			            eTelephone: ["#", "请输入紧急联系人电话", "#maxLength", 25]
			        },
			        success: function(data) {
			            parent.location.reload();
			        },
			        error: function(data) {
			            layer.msg(data, 3, 0);
			        }
			    });
			});	 	
		</script>	
	</@layout.put>
</@layout.extends>