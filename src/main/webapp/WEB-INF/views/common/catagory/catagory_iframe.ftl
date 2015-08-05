<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
		<div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  			      
	        <form id="form">
		        <input type="hidden" name="id" <#if RequestParameters.id?exists> value="${RequestParameters.id}" </#if> />	 		      		
				<div class="modal-body">
				    <table>
						<tr>
							<td><span>名称：</span></td>
							<td>
								<input type="text" name="name" size="25" <#if RequestParameters.name?exists> value="${RequestParameters.name}" </#if> />
							</td>
						</tr>
						<tr>
							<td><span>险种码：</span></td>
							<td>
								<input type="text" name="nameE" size="25" <#if RequestParameters.nameE?exists> value="${RequestParameters.nameE}" </#if> />
							</td>
				    	</tr>
				    </table>
				</div>
			    <div class="modal-footer">
			        <button id="add" class="btn btn-primary">保存</button>
			    </div>
			</form>
    	</div>
	
		<script>
			var opt = ${RequestParameters.opt};
			$().ready(function() {  
				if (opt == opt_update) { 
			    	$formAjax({
		    	    	id: "#form",
		    	    	url: 'catagory/update.jhtml',
		    	    	rules: {
		    	    		name: ["#","请输入类别名称","#maxLength",25],
		    	    		nameE: ["#","请输入险种码","#maxLength",25]
			    		},
		    	    	success: function(data) {
							parent.location.reload();
			    	    },
			    	    error: function(data) {
					    	layer.msg(data,3,0);
			    	    }	    	    	   
			    	});	  	
				} else {    		
		    	    $formAjax({
		    	    	id: "#form",
		    	    	url: 'catagory/add.jhtml',
		    	    	rules: {
		    	    		name:["#","请输入类别名称","#maxLength",25],
		    	    		nameE:["#","请输入险种码","#maxLength",25]
			    		},
		    	    	success:function(data) {
							parent.location.reload();
			    	    },
			    	    error:function(data) {
				    	    layer.msg(data,3,0);
		    	    	}
		    	    });
			    } 
		    });		 		
		</script>
	</@layout.put>
</@layout.extends>