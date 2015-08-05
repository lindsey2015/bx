<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
        <form id="form">	      		
        	<#if RequestParameters.productId?exists>
        		<input type="hidden" value="${RequestParameters.productId}" name="productId" /> 
        	</#if>
			<input type="hidden" name="id" <#if RequestParameters.ageGroupId?exists> value="${RequestParameters.ageGroupId}" </#if> />					
		    <div class="modal-body">		            
				<span>年龄段：</span>
				<input type="text" name="ageGroup" size="25" <#if RequestParameters.ageGroup?exists> value="${RequestParameters.ageGroup}" </#if> />					
		    </div>
		    <div class="modal-footer">
		        <button type="submit" class="btn btn-primary">保存</button>
		    </div>
		</form>
	
		<script type="text/javascript">
		    var opt = ${RequestParameters.opt};	
		    $().ready(function() {    
			    if (opt == opt_update) {  
			    	$formAjax({
		    	    	id: "#form",
		    	    	url: 'agegroup/update.jhtml',
		    	    	rules: {
				    		ageGroup:["#","请输入年龄段","#maxLength",25]
			    		},
		    	    	success: function(data){
	    					parent.location.reload();
			    	    },
			    	    error: function(data){
					    	layer.msg(data,3,0);
			    	    }
	    	    	});	  	
			    } else {    		
		    	    $("#form").find("[name='ageGroup']").val("");
		    	   	var productId = $("input[name='productId']").val();
		    	    $formAjax({
		    	    	id: "#form",
		    	    	url: 'agegroup/add.jhtml',
		    	    	rules: {
				    		   	ageGroup:["#","请输入年龄段","#maxLength",25]
			    		},
		    	    	success: function(data) {
							updateParentCount('age-group-count',productId,1);
							parent.location.reload();
			    	    },
			    	    error: function(data) {
					    	layer.msg(data,3,0);
			    	    }
		    	    });
			    }
		    })		 	
		</script>	
	</@layout.put>
</@layout.extends>