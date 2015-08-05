<@layout.extends name="layout/simple_layout.ftl">
	<@layout.put block="title" type="replace">太平洋保险管理系统</@layout.put>
	<@layout.put block="contents">
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs', 'fixed')}catch(e){}
				</script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="javascript:void(0);">太平洋财产保险</a>
					</li>
					<li>
						<a href="javascript:void(0);">用户管理</a>
					</li>
					<li class="active">
						<#if RequestParameters.type=="2">
							普通用户
						<#elseif RequestParameters.type=="3">
							保险公司
						</#if>
					</li>
				</ul>
			</div>

			<div class="page-content">
	        	<@action uri="pUserWeb!pageByTypeSearchParam" nickname='users'/>
				<!-- <@toJson data=users encode='false'/> --> 
				<div class="row">
					<div class="col-xs-12">
						<div class="row">
						    <div class="col-xs-6">
								<div class="btn-group group-xs">
									<button type="button" class="btn btn-default" id="add-btn">新增用户</button>
									<button type="button" class="btn btn-default" onclick="start_or_end(1)">启用用户</button>
									<button type="button" class="btn btn-default" onclick="start_or_end(0)">禁用用户</button>
	                            </div>
						    </div>
						    
							<div class="col-xs-4 col-xs-offset-8">
								<div class="nav-search" id="nav-search">
									<form class="form-search" method="post" action="common/user.jhtml">
									<input type="hidden" id="user-type" name="type" <#if RequestParameters.type?exists> value="${RequestParameters.type}" </#if> />
										<span class="input-icon">
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
	                                        <td class="active">用户名  </td>
	                                        <td class="active">是否有用</td>  
	                                        <td class="active">管理个人信息</td>                                         
	                                        <td class="active">关联产品</td>                                         
											<td class="active">操作</td>
	                                    </tr>
		                                <#list users.data.content as user >
			                                <tr>
												<td>
													<input type="checkBox" name="check" class="checkOne" value="${user.id}" onclick="clickOne()" />
												</td>
				                                <td>${user.username}</td>
												<td> ${user.useful?string("是","否")}</td>
												<td> 
													<#if user.type==2>
														<a onclick="manageUserInfo('${user.id}')" href="javascript:void(0);">管理个人信息</a>
													</#if>
												</td>
												<td> 
													<a onclick="manageUserProduct('${user.id}')" href="javascript:void(0);">关联产品</a>
												</td>
												<td>
													<button style="background-color:white" onclick="update('${user.id}','${user.username}','${user.password}')">修改</button>
												</td>
			                                </tr>
		                                </#list> 
	                               	</table>
						            <form id="listForm" action="common/user.jhtml" method="get">
							            <#if RequestParameters.type?exists>
			                            	<input type="hidden" value="${RequestParameters.type}" name="type" />
	                                	</#if>
										<#assign page = users.data>
								 		<@pagination pageNumber = page.pageNumber totalPages = page.totalPages>
											<#include "../../common/pagination.ftl">
										</@pagination>
									</form>     
		                    	</div>
		                	</div>
		            	</div>	            	
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			function update(id, username, password) {
			    $summerLayer({
			        title: "修改用户",
			        url: 'common/user.jhtml?p=user_iframe&opt=' + opt_update + '&id=' + id + "&username=" + username + "&password=" + password,
			        area: ['400px', '210px']
			    });
			}

			function manageUserInfo(userId) {
			    $summerLayer({
			        title: "管理用户个人信息",
			        url: 'common/user.jhtml?p=user_info&userId=' + userId,
			        area: ['450px', '420px']
			    });
			}

			function manageUserProduct(userId) {
			    $summerLayer({
			        title: "用户关联产品",
			        url: 'common/user.jhtml?p=user_product&userId=' + userId,
			        area: ['650px', '400px']
			    });
			}

			function start_or_end(useful) {
			    var chk_value = getCheck();
			    if (chk_value.length == 0) {
			        layer.msg("请选择操作", 1);
			    } else {

			        $post({
			            url: 'user/updateUseful.jhtml?useful=' + useful,
			            data: {
			                ids: chk_value
			            },
			            success: function(data) {
			                layer.msg("您成功修改了" + chk_value.length + "条数据", 1, 1, function() {
			                    location.reload();
			                });
			            },
			            error: function(data) {
			                layer.msg(json.data, 3, 0);
			            },
			            dataType: "json"
			        });

			    }
			}
			
			$().ready(function() {
			    var type = $("[name='type']").val();
			    $("#search-btn").click(function() {
			        $(".form-search").submit();
			    });
			    $('#add-btn').on('click', function() {
			        $summerLayer({
			            title: "添加用户",
			            url: 'common/user.jhtml?p=user_iframe&opt=' + opt_add + "&type=" + type,
			            area: ['400px', '210px']
			        });
			    });
			});
		</script>
	</@layout.put>
</@layout.extends>