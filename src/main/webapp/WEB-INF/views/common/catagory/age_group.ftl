<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
		<div class="page-content">
			<#if RequestParameters.productId?exists><input type="hidden" value="${RequestParameters.productId}" name="productId" /> </#if>
		    <@action uri="pAgeGroupWeb!listByProductId" nickname='agegroups'/>
			<!-- <@toJson data=agegroups encode='false'/>  -->
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
					    <div class="col-xs-7">
							<div class="btn-group group-xs">
								<button type="button" class="btn btn-default" id="add-btn">新增</button>
								<button type="button" class="btn btn-default" id="del-btn">批量删除</button>
	                        </div>
					    </div>
					</div>
					
				    <div class="row">
	                	<div class="col-xs-12 text-center" style="margin-top:10px;width:100%" >
	                    	<div class="table-responsive">
	                        	<table class="table table-hover table-bordered">
	                            	<tr>
		      							<td class="active"><input type="checkBox" class="checkAll" onclick="clickAll()"/></td>
	                                    <td class="active">年龄段</td>                                       
										<td class="active">管理保障天数</td>
										<td class="active">操作</td>
	                                </tr>
	                                <#list agegroups.data as agegroup>
		                                <tr>
											<td><input type="checkBox" name="check" class="checkOne" value="${agegroup.id}" onclick="clickOne()" /></td>
			                                <td>${agegroup.ageGroup}</td>
											<td>
												<span bz_date-count="${agegroup.id}">${agegroup.etc.count}</span>&nbsp;
												<a onclick="manageBzDate('${agegroup.id}')" href="javascript:void(0);">管理保障天数</a>
											</td>
											<td>
												<button style="background-color:white" onclick="update('${agegroup.id}','${agegroup.ageGroup}')">修改</button>
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
			function manageBzDate(ageGroupId) {
			    $summerLayer({
	    	    	title:"管理保障天数",
	    	    	url:'common/catagory.jhtml?p=bz_date&ageGroupId='+ageGroupId,
	    	    	area:['800px' , '500px']
	 	    	 });		   
			}
			
			function update(ageGroupId,ageGroup) {
			    $summerLayer({
	    	    	title:"修改年龄段",
	    	    	url:'common/catagory.jhtml?p=age_group_iframe&opt='+opt_update+'&ageGroupId='+ageGroupId+"&ageGroup="+ageGroup,
	    	    	area: ['500px' , '190px']
	 	    	});			
			}
			
			$().ready(function() {
				var productId = $("[name='productId']").val();
				    	
		    	$('#add-btn').on('click', function(){	
				    $summerLayer({
	 	    	    	title:"添加年龄段",
	 	    	    	url:'common/catagory.jhtml?p=age_group_iframe&opt='+opt_add+"&productId="+productId,
	 	    	    	area: ['500px' , '190px']
		 	    	});				
				});	

				$('#del-btn').on('click',function() {
					var chk_value = getCheck();
				    if (chk_value.length == 0) {
						layer.msg("请选择操作", 1);
				    } else {
						$post({
		 	    	    	url:'agegroup/del.jhtml',
			    	    	data:{ids:chk_value},
		 	    	    	success:function(data){
			 	    	    	layer.msg("您成功删除了" + chk_value.length + "条数据", 1, 1, function() {
							   	    location.reload();
							   	    updateCount('age-group-count',productId,-chk_value.length);
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