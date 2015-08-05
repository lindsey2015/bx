<@layout.extends name="layout/frame_layout.ftl">
	<@layout.put block="contents">
	    <#if RequestParameters.userId?exists>
	    	<input type="hidden" value="${RequestParameters.userId}" id="userId"/>
	    </#if>
		<@action uri="pProductWeb!listByUserId" nickname='products'/>
		<!-- <@toJson data=products encode='false'/> -->
		<div class="row">
			<div class="col-xs-12">	
				<div class="row">
				    <div class="col-xs-6">
						<div class="btn-group group-xs">
						   <button type="button" class="btn btn-default" onclick="add()">添加</button>
						   <button type="button" class="btn btn-default" onclick="del()">移除</button>
		                </div>
				    </div>								
				</div>					
				<div class="row">
					<div class="col-lg-12 text-center" style="margin-top: 10px;width:100%;">
						<div class="table-responsive">
							<table class="table table-hover table-bordered">
								<tr>
								    <td class="active">
								    	<input type="checkBox" class="checkAll" onclick="clickAll();"/>
								    </td>
								    <td class="active">产品名称</td>
									<td class="active">操作</td>
								</tr>
								<#list products.data as product>
									<#if product.etc.include==true>
										<tr style="background-color: #abbac3">
									<#else>
										<tr>
									</#if>
									    <td>
									    	<input type="checkBox" name="check" class="checkOne" value="${product.id}" onclick="clickOne();" />
									    </td>
										<td>${product.name}</td>
										<td>
										    <#if product.etc.include==true>
												<a onclick="delProduct('${product.id}');" href="javascript:void(0);" >移除</a>	
											<#else>
												<a onclick="addProduct('${product.id}');" href="javascript:void(0);" >添加</a>	
											</#if>											
										</td>
									</tr>
								</#list>
							</table>									
						</div>
					</div> <!-- column in -->
				</div> <!-- row in -->
			</div> <!-- column out -->
		</div> <!-- row out -->				
		<script type="text/javascript">
			function del() {
			    var userId = $("#userId").val();
			    var chk_value = getCheck();

			    if (chk_value.length == 0) {
			        layer.msg("请先选择记录", 1);
			    } else {
			        $post({
			            url: 'product/deluserproduct.jhtml',
			            data: {
			                productIds: chk_value,
			                userId: userId
			            },
			            success: function(data) {
			                layer.msg("您成功移除了" + chk_value.length + "个关联", 1, 1, function() {
			                    location.reload();
			                    // updateCount('permission-count',roleId,-chk_value.length);
			                });
			            },
			            error: function(data) {
			                layer.msg(data, 3, 0);
			            },
			            dataType: "json"
			        });
			    }
			}

			function delProduct(productId) {
			    var userId = $("#userId").val();
			    $post({
			        url: 'product/deluserproduct.jhtml',
			        data: {
			            productIds: productId,
			            userId: userId
			        },
			        success: function(data) {
			            layer.msg("您成功移除了1个关联", 1, 1, function() {
			                location.reload();
			                // updateCount('permission-count',roleId,-1);
			            });
			        },
			        error: function(data) {
			            layer.msg(data, 3, 0);
			        },
			        dataType: "json"
			    });
			}

			function add() {
			    var userId = $("#userId").val();
			    var chk_value = getCheck();

			    if (chk_value.length == 0) {
			        layer.msg("请选择记录", 1);
			    } else {
			        $post({
			            url: 'product/adduserproduct.jhtml',
			            data: {
			                productIds: chk_value,
			                userId: userId
			            },
			            success: function(data) {
			                layer.msg("您成功添加了" + chk_value.length + "个关联", 1, 1, function() {
			                    location.reload();
			                    // updateCount('permission-count',roleId,chk_value.length);
			                });
			            },
			            error: function(data) {
			                layer.msg(data, 3, 0);
			            },
			            dataType: "json"
			        });
			    }
			}

			function addProduct(productId) {
			    var userId = $("#userId").val();
			    $post({
			        url: 'product/adduserproduct.jhtml',
			        data: {
			            productIds: productId,
			            userId: userId
			        },
			        success: function(data) {
			            layer.msg("您成功添加了1个关联", 1, 1, function() {
			                location.reload();
			                // updateCount('permission-count',roleId,1);
			            });
			        },
			        error: function(data) {
			            layer.msg(data, 3, 0);
			        },
			        dataType: "json"
			    });

			}
		</script>
	</@layout.put>
</@layout.extends>
