<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
		<div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  			      
	        <form id="form">	      					
			    <div class="modal-body">		            
					<span>请输入新密码：</span>
					<input type="text" name="password" size="25"/>					
			    </div>
			    <div class="modal-footer">
			        <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			</form>
		</div>
		
		<script type="text/javascript">
			$formAjax({
			    id: "#form",
			    url: 'user/changePassword.jhtml',
			    rules: {
			        password: ["#", "请输入新密码", "#maxLength", 25]
			    },
			    success: function(data) {
			        parent.location.href = "index.jhtml";
			    },
			    error: function(data) {
			        layer.msg(data, 3, 0);
			    }
			});		 	 	
		</script>	
	</@layout.put>
</@layout.extends>