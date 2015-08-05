<@layout.extends name="layout/frame_layout.ftl">
    <@layout.put block="contents">
		<div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  			      
	        <form id="form">
    	        <#if RequestParameters.ageGroupId?exists>
                    <input type="hidden" value="${RequestParameters.ageGroupId}" name="ageGroupId" />
                </#if>
	            <input type="hidden" name="id" <#if RequestParameters.id?exists> value="${RequestParameters.id}" </#if> />					      		
			    <div class="modal-body">
					<dd><span>最小天数：</span></dd>
					<dd><input type="text" name="minDay"  size="25" <#if RequestParameters.minDay?exists> value="${RequestParameters.minDay}" </#if>/></dd>
					<dd><span>最大天数：</span></dd>
					<dd><input type="text" name="maxDay"  size="25" <#if RequestParameters.maxDay?exists> value="${RequestParameters.maxDay}" </#if>/></dd>
					<dd><span>费率：</span></dd>
					<dd><input type="text" name="value"  size="25" <#if RequestParameters.value?exists> value="${RequestParameters.value}" </#if>/></dd>
			    </div>
			    <div class="modal-footer">
			        <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			</form>
    	</div>
	<script>
	   var opt = ${RequestParameters.opt};	
	
    	$().ready(function() {  
        	if (opt == opt_update) { 
    	    	$formAjax({
        	    	id:"#form",
        	    	url:'bzdate/update.jhtml',
        	    	rules:{
        	    		minDay:["#","请输入最小天数","#maxLength",20],
        	    		maxDay:["#","请输入最大天数","#maxLength",20],
        	    		value:["#","请输入费率","#maxLength",20]
    	    		},
        	    	success:function(data){
    					parent.location.reload();
    	    	    },
    	    	    error:function(data){
    			    	layer.msg(data,3,0);
    	    	    }	    	    	   
    	    	});	  	
        	} else {    		
        	    var ageGroupId = $("input[name='ageGroupId']").val();
        	    $formAjax({
        	    	id: "#form",
        	    	url:'bzdate/add.jhtml',
        	    	rules:{
        	    		minDay: ["#","请输入最小天数","#maxLength",20],
        	    		maxDay: ["#","请输入最大天数","#maxLength",20],
        	    		value: ["#","请输入费率","#maxLength",20]
    	    		},
        	    	success: function(data) {
        	    	    updateParentCount('bz_date-count',ageGroupId,1);
    					parent.location.reload();
    	    	    },
    	    	    error: function(data) {
			    	    layer.msg(data,3,0);
	    	    	}
        	    });
        	}
        });		 	
	</script>
	</@layout.put>
</@layout.extends>