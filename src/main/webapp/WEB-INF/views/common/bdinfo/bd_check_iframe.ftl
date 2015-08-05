<@layout.extends name="layout/frame_layout.ftl">
<@layout.put block="contents">      
	<div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  			      
        <form id="form">	      		
			<input type="hidden" name="id" <#if RequestParameters.id?exists> value="${RequestParameters.id}" </#if> />					
		    <div class="modal-body">		            
				<table>
					<tr>
						<td><span>保单号：</span></td>
						<td><input type="text" name="bdNo" size="25" /></td>
					</tr>
					<tr> 
						<td><span>文件上传(PDF、JPG)：</span></td>
						<td>
							<input type="file" name="file" id="pdfAddr" onchange="ajaxPdfUpload('pdfAddr') "/>
						</td>
			   		</tr>
					<input type="hidden" name="pdfAddr">			
		   		</table> 
		    </div>
		    <div class="modal-footer">
		        <button type="submit" class="btn btn-primary">提交</button>
		    </div>
		</form>
	</div>
	
	<script type="text/javascript">
	    $().ready(function(){  
		  $formAjax({
    	    	id: "#form",
    	    	url: 'bdinfo/check.jhtml',
    	    	rules: {
    	    	bdNo: ["#","请输保单号", "#maxLength",25]
	    		},
    	    	success:function(data){
    	    		layer.msg(data, 3, 0);
					parent.location.reload();
	    	    },
	    	    error:function(data){
			    	layer.msg(data, 3, 0);
	    	    }	    	    	   
	    	});	  	
	    })		 	
	</script>	
</@layout.put>
</@layout.extends>