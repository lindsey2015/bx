<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
		<div class="page-content">
			<#if RequestParameters.catagoryId?exists><input type="hidden" value="${RequestParameters.catagoryId}" name="catagoryId" /> </#if>
	        <@action uri="pProductWeb!listByCatagoryIdSearchParam" nickname='products'/>
			<!-- <@toJson data=products encode='false'/> --> 
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
					    <div class="col-xs-6">
							<div class="btn-group group-xs">
								<button type="button" class="btn btn-default" id="add-btn">新增</button>
								<button type="button" class="btn btn-default" onclick="start_or_end(1)">启用</button>
								<button type="button" class="btn btn-default" onclick="start_or_end(0)">禁用</button>
                            </div>
					    </div>
					    
						<div class="col-xs-4 col-xs-offset-8">
							<div class="nav-search" id="nav-search">
								<form class="form-search" method="post" action="common/catagory.jhtml?p=product">
									<span class="input-icon">
										<input type="hidden" name="catagoryId" <#if RequestParameters.catagoryId?exists> value="${RequestParameters.catagoryId}" </#if> />
										<input type="text" placeholder="Search ..." class="nav-search-input" name="searchParam"  autocomplete="off" />
										<i class="icon-search nav-search-icon"></i>
									</span>
									<button id="search-btn" type="button" class="btn btn-default btn-xs">搜索</button>
							    </form>
					         </div>
						</div>
					</div>
					
					<div class="row">
                        <div class="col-xs-12 text-center" style="margin-top:10px;">
                            <div class="table-responsive">
                                <table class="table table-hover table-bordered">
                                    <tr>
									    <td class="active"><input type="checkBox" class="checkAll" onclick="clickAll()"/></td>
                                        <td class="active">产品名称</td>
                                        <td class="active">所属类别</td>
                                        <td class="active">投保范围</td>
                                        <td class="active">条款下载</td>
                                        <td class="active">是否有用</td>
                                        <td class="active">年龄段</td>
										<td class="active">操作</td>
                                    </tr>
                                    <#list products.data as product>
                                        <tr>
										    <td><input type="checkBox" name="check" class="checkOne" value="${product.id}" onclick="clickOne()" /></td>
	                                        <td>${product.name}</td>
	                                        <td>${product.etc.catagoryName}</td>
	                                        <td style="width:500px">${product.tbArea}</td>
	                                        <td> <a href="${product.tkAddr}">点击下载</a></td>
	                                        <td> ${product.useful?string("是","否")}</td>
											<td> 
										    	<span  age-group-count="${product.id}">${product.etc.count}</span>&nbsp;<a onclick="manageAgeGroup('${product.id}')" href="javascript:void(0);">管理年龄段</a>
											</td>
											<td>
												<i class="ace-icon fa fa-pencil bigger-120"></i>
												<button style="background-color:white" onclick="update('${product.id}')">修改</button>
											</td>
                                        </tr>
                                    </#list> 
                                </table>
                            </div>
                        </div>
                    </div>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
				
		<script>
			function update(id) {
		   		$summerLayer({
	    	    	title: "修改产品",
	    	    	url: 'common/catagory.jhtml?p=product_iframe&opt='+opt_update+'&id='+id,
	    	    	area: ['1000px' , '550px']
		    	});			
			}
			function manageAgeGroup(productId) {
		    	$summerLayer({
	    	    	title:"管理年龄段",
	    	    	url:'common/catagory.jhtml?p=age_group&productId='+productId,
	    	    	area:['1000px' , '600px']
		    	});		   
			} 
			function start_or_end(useful) {
			   	var chk_value = getCheck();
			   	if (chk_value.length == 0) {
					layer.msg("请选择操作", 1);
			   	} else {
					$post({
	 	    	    	url: 'product/updateUseful.jhtml?useful='+useful,
		    	    	data: {ids:chk_value},
	 	    	    	success: function(data) {
		 	    	    	layer.msg("您成功修改了" + chk_value.length + "条数据", 1, 1, function() {
						   	    location.reload();
							});
	 	    	    	},
	 	    	    	error: function(data) {
	 	    	    		layer.msg(json.data,3,0);
	 	    	    	},
	 					dataType:"json"
		 	    	});
		   		}
			}
			
			$().ready(function(){
			    $("#search-btn").click(function() {
			    	$(".form-search").submit();
			    });
			    var catagoryId = $("input[name='catagoryId']").val();	
			    $('#add-btn').on('click', function() {	
				    $summerLayer({
	 	    	    	title:"添加产品",
	 	    	    	url:'common/catagory.jhtml?p=product_iframe&opt='+opt_add+'&catagoryId='+catagoryId,
	 	    	    	area:['1000px' , '550px']
		 	    	});				
				});		 
			});
		</script>
	</@layout.put>
</@layout.extends>