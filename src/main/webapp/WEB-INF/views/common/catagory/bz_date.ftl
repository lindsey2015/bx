<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
		<div class="page-content">
			<#if RequestParameters.ageGroupId?exists>
				<input type="hidden" value="${RequestParameters.ageGroupId}" name="ageGroupId" /> 
			</#if>
	        <@action uri="pBzDateWeb!listByAgeGroupId" nickname='bzdates'/>
		    <!-- <@toJson data=bzdates encode='false'/>  -->
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
					    <div class="col-xs-7">
							<div class="btn-group group-xs">
								<button type="button" class="btn btn-default" id="add-btn">新增保障日期</button>
								<button type="button" class="btn btn-default" id="del-btn">删除保障日期</button>
                            </div>
					    </div>
					</div>
					
				    <div class="row">
                    	<div class="col-xs-12 text-center" style="margin-top:10px;width:100%" >
                        	<div class="table-responsive">
                            	<table class="table table-hover table-bordered">
                                	<tr>
		      							<td class="active"><input type="checkBox" class="checkAll" onclick="clickAll()"/></td>
                                        <td class="active">最小天数</td>                                       
										<td class="active">最大天数</td>
										<td class="active">费率</td>
										<td class="active">操作</td>
                                    </tr>
	                                <#list bzdates.data as bzdate>
		                                <tr>
											<td><input type="checkBox" name="check" class="checkOne" value="${bzdate.id}" onclick="clickOne()" /></td>
			                                <td>${bzdate.minDay}</td>
			                                <td>${bzdate.maxDay}</td>
			                                <td>${bzdate.value}</td>
											<td>
												<button style="background-color:white" onclick="update('${bzdate.id}','${bzdate.minDay}','${bzdate.maxDay}','${bzdate.value}')">修改</button>
											</td>
		                                </tr>
	                                </#list> 
                               	</table>
	                      	</div>
	                	</div>
	            	</div>
				</div>
			</div>
		</div>
		<script>
			function update(id,minDay,maxDay,value){
			    $summerLayer({
	    	    	title:"修改保障日期",
	    	    	url:'common/catagory.jhtml?p=bz_date_iframe&opt='+opt_update+'&id='+id+"&minDay="+minDay+"&maxDay="+maxDay+"&value="+value,
	    	    	area: ['400px' , '300px']
	 	    	});			
			}
			
			$().ready(function() {
				var ageGroupId = $("[name='ageGroupId']").val();
			    	
		    	$('#add-btn').on('click', function() {	
				    $summerLayer({
	 	    	    	title:"添加年龄段",
	 	    	    	url:'common/catagory.jhtml?p=bz_date_iframe&opt='+opt_add+'&ageGroupId='+ageGroupId,
	 	    	    	area: ['400px' , '300px']
		 	    	});				
				});	
				$('#del-btn').on('click',function(){
					var chk_value = getCheck();
				    if (chk_value.length == 0) {
						layer.msg("请选择操作", 1);
				    } else {
						$post({
		 	    	    	url:'bzdate/del.jhtml',
			    	    	data:{ids:chk_value},
		 	    	    	success:function(data){
			 	    	    	layer.msg("您成功删除了" + chk_value.length + "条数据", 1, 1, function() {
							   	    location.reload();
							   	    updateCount('bz_date-count',ageGroupId,-chk_value.length);
								});
		 	    	    	},
		 	    	    	error:function(data){
		 	    	    		layer.msg(json.data,3,0);
		 	    	    	},
		 					dataType:"json"
			 	    	});
				    }
				});
			});
		</script>
	</@layout.put>
</@layout.extends>