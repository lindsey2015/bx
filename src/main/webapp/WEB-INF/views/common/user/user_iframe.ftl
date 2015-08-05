<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
		<div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  			      
	        <form id="form">	      		
	        	<#if RequestParameters.type?exists>
	        		<input type="hidden" value="${RequestParameters.type}" name="type" />
	        	</#if>
				<input type="hidden" name="id" <#if RequestParameters.id?exists> value="${RequestParameters.id}" </#if> />					
			    <div class="modal-body">		            
					<table>
						<tr>
							<td><span>用户名：</span></td>
							<td>
								<input type="text" name="username" size="25" <#if RequestParameters.username?exists> value="${RequestParameters.username}" </#if> />
							</td>				
						</tr>					
						<#if RequestParameters.opt=="2">	
							<tr>
								<td><span>密码：</span></td>
								<td>
									<input type="text" name="password" size="25" <#if RequestParameters.password?exists> value="${RequestParameters.password}"</#if> />
								</td>
							</tr>
						<#elseif RequestParameters.opt=="1">
							<tr>
								<input type="hidden" name="password" size="25" value="123456"/>	
								<td><span>初始密码：</span></td>
								<td><span>（123456）</span></td>
							</tr>
						</#if>
					</table>					
			    </div>
			    <div class="modal-footer">
			        <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			</form>
    	</div>

		<script type="text/javascript">
		    var opt = ${
			    RequestParameters.opt
			};
			$().ready(function() {
			    if (opt == opt_update) {
			        $formAjax({
			            id: "#form",
			            url: 'user/update.jhtml',
			            rules: {
			                username: ["#", "请输入用户名称", "#maxLength", 25],
			                password: ["#", "请输入密码", "#maxLength", 25]
			            },
			            success: function(data) {
			                parent.location.reload();
			            },
			            error: function(data) {
			                layer.msg(data, 3, 0);
			            }
			        });
			    } else {
			        $("#form").find("[name='username']").val("");

			        $formAjax({
			            id: "#form",
			            url: 'user/add.jhtml',
			            rules: {
			                username: ["#", "请输入用户名称", "#maxLength", 25]
			            },
			            success: function(data) {
			                parent.location.reload();
			            },
			            error: function(data) {
			                layer.msg(data, 3, 0);
			            }
			        });
			    }
			});		 	
		</script>	
	</@layout.put>
</@layout.extends>