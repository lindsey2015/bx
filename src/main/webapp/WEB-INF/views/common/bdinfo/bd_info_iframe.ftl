<@layout.extends name="layout/frame_layout.ftl">
<@layout.put block="contents">
	<div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			      
        <input type="hidden" id="id" <#if RequestParameters.id?exists> value="${RequestParameters.id}" </#if> />	    
		<div class="modal-body">	
			<table>	
			    <tr>            
					<td><span>投保人姓名：</span></td>
					<td><input type="text" name="name" disabled="disabled" size="25"/></td>
			    </tr> 
			    <tr> 
					<td><span>产品名称：</span></td>
					<td><input type="text" name="productName" disabled="disabled" size="25"/></td>	
			    </tr> 
			    <tr>	
					<td><span>起保日期：</span></td>
					<td><input type="text" name="startDay" disabled="disabled" size="25"/></td>
				</tr>
				<tr>
				    <td><span>保单总额：</span></td>
					<td><input type="text" name="total" disabled="disabled" size="25"/>	</td>	
				</tr>
				<tr>
					<td><span>保障天数：</span></td>
					<td><input type="text" name="days" disabled="disabled" size="25"/></td>
				</tr>
				<tr>
					<td><span>保障人数：</span></td>
					<td><input type="text" name="nums" disabled="disabled" size="25"/></td>
				</tr>
				<tr>
					<td><span>年龄段：</span></td>
					<td><input type="text" name="ageGroup" disabled="disabled" size="25"/></td>
				</tr>
				<tr>
					<td><span>投保单号：</span></td>
					<td><input type="text" name="tbNo" disabled="disabled" size="25"/></td>
				</tr>
				<tr>	
					<td><span>紧急联系人姓名：</span></td>
					<td><input type="text" name="eContact" disabled="disabled" size="25"/></td>
				</tr>
				<tr>
					<td><span>紧急联系人电话：</span></td>
					<td><input type="text" name="eTelephone" disabled="disabled" size="25"/></td>
				</tr>
				<tr>
					<td><span>excel团单下载：</span></td>
					<td><a id="excelDownload">点击下载</a></td>
				</tr>
				<tr>
					<td><span>被保险人：</span></span></td>
					<td><table class="table table-bordered"><tbody id="insured-user-list"></tbody></table></td>
				</tr>
				<!-- 
				<#if RequestParameters.status=="3">
					<tr>
						<td><span>保单下载：</span></td>
						<td><a id="pdfDownload">点击下载</a></td>
					</tr>
				</#if> 
				-->					
			</table>		
		</div>   
	</div>

	<script src="static/plug/handlebars/handlebars-v3.0.3.js"></script>
	<script src="static/plug/handlebars/handlebars-helper.js"></script>
	<#include "/tmpl/insured-user-list.html">
	
   	<script type="text/javascript" >
		$().ready(function(){    
    	  var id = $("#id").val();
    	  
		 	$post({
		    	url: 'bdinfo/getById.jhtml',
		    	data: {id: id},
		    	success: function(data) {
		    		$("input[name='name']").val(data.userInfo.name);
		    		$("input[name='productName']").val(data.product.name);
		    		$("input[name='startDay']").val(data.startDay);
		    		$("input[name='total']").val(data.total);
		    		$("input[name='days']").val(data.days);
		    		$("input[name='nums']").val(data.nums);
		    		$("input[name='ageGroup']").val(data.ageGroup);
		    		$("input[name='tbNo']").val(data.tbNo);
		    		$("input[name='eContact']").val(data.userInfo.eContact);
		    		$("input[name='eTelephone']").val(data.userInfo.eTelephone);
		    		if (data.excelAddr && data.excelAddr != "") {
		 	 			$("#excelDownload").attr('href',data.excelAddr);
		 	 		} else {
		 	 			$("#excelDownload").parent().remove();
		 	 		}
		 	 		$("#pdfDownload").attr('href',data.pdfAddr);
		 	 		if (data.insuredUserList && data.insuredUserList.length > 0) {
			 	 		var source = $("#insured-user-list-template").html();
	                    var template = Handlebars.compile(source);
	                    var insuredUserListHtml = template(data);
	                    $("#insured-user-list").append(insuredUserListHtml);
                    }
		    	},
		    	error:function(data) {
		    		layer.msg(data,3,0);
		    	},
				dataType:"json"
		 	});	
    	})		
    </script>
	</@layout.put>
</@layout.extends>