<@layout.extends name="layout/simple_layout.ftl">
	<@layout.put block="title" type="replace">太平洋保险</@layout.put>
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
						<a href="javascript:void(0);">产品管理</a>
					</li>
					<li class="active">类别设置</li>
				</ul><!-- .breadcrumb -->
			</div>
			<div class="page-content">
         		<@action uri="pCatagoryWeb!pageBySearchParam" nickname='catagorys'/>
				<!-- <@toJson data=catagorys encode='false'/> -->
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
							      	<form class="form-search" method="post" action="common/catagory.jhtml">
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
		                    <div class="col-lg-12 text-center" style="margin-top:10px;width:100%;">
		                        <div class="table-responsive">
		                            <table class="table table-hover table-bordered">
		                                <tr>
										    <td class="active"><input type="checkBox" class="checkAll" onclick="clickAll()"/></td>
		                                    <td class="active">类别名称</td>
		                                    <td class="active">险种码</td>
		                                    <td class="active">是否有用</td>  
		                                    <td class="active">管理产品</td>         
											<td class="active">操作</td>
		                                </tr>
		                                <#list catagorys.data.content as catagory>
		                                    <tr>
											    <td><input type="checkBox" name="check" class="checkOne" value="${catagory.id}" onclick="clickOne()" /></td>
		                                        <td>${catagory.name}</td>
		                                        <td>${catagory.nameE}</td>
												<td> ${catagory.useful?string("是","否")}</td>
												<td> 
													<span>${catagory.etc.count}</span>&nbsp;<a onclick="manageProduct('${catagory.id}')" href="javascript:void(0);">管理产品</a>
												</td>
												<td>
													<button style="background-color:white" onclick="update('${catagory.id}','${catagory.name}','${catagory.nameE}')">修改</button>
		                                        </td>
		                                    </tr>
		                                </#list> 
		                            </table>
		                            
								    <form id="listForm" action="common/catagory.jhtml" method="get">
										<#assign page = catagorys.data>
								 		<@pagination pageNumber = page.pageNumber totalPages = page.totalPages>
											<#include "../../common/pagination.ftl">
										</@pagination>
									</form>     
		                        </div>
		                    </div>
		                </div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->	
				
		<script type="text/javascript">
			function manageProduct(catagoryId){
			    $summerLayer({
			    	title: "管理产品",
			    	url: 'common/catagory.jhtml?p=product&catagoryId='+catagoryId,
			    	area: ['1200px' , '700px'],
		    	    close: function() {
			    	    location.reload();
			        }
		    	});		   
			} 

			function update(id,name,nameE) {
			    $summerLayer({
			    	title:"修改类别名称",
			    	url:'common/catagory.jhtml?p=catagory_iframe&opt='+opt_update+'&id='+id+"&name="+name+"&nameE="+nameE,
			    	area:['400px' , '250px']
			 	});			
			}

			$().ready(function(){ 
			    $("#search-btn").click(function() {
					$(".form-search").submit();
				});
				$('#add-btn').on('click', function() {		
				    $summerLayer({
				    	title:"添加类别",
				    	url:'common/catagory.jhtml?p=catagory_iframe&opt='+opt_add,
				    	area:['400px' , '250px']
				 	});				
				});
			});
		 	

		    function start_or_end(useful) {
			    var chk_value = getCheck();
			    if (chk_value.length == 0) {
					layer.msg("请选择操作", 1);
			    } else {
					$post({
		    	    	url:'catagory/updateUseful.jhtml?useful='+useful,
	    	    		data:{ids:chk_value},
		    	    	success:function(data) {
	 	    	    	layer.msg("您成功修改了" + chk_value.length + "条数据", 1, 1, function() {
					   	    location.reload();
						});
		    	    	},
		    	    	error:function(data) {
		    	    		layer.msg(json.data,3,0);
		    	    	},
						dataType:"json"
		 	    	});
			    }
			}
		</script>
	</@layout.put>
</@layout.extends>