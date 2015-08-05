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
						<a href="javascript:void(0);">保单管理</a>
					</li>
					<li class="active">
						<#if RequestParameters.status=="1">
							暂存保单
						<#elseif RequestParameters.status=="2">
			               	已提交保单
						<#elseif RequestParameters.status=="3">
						    已通过保单
						</#if>
					 </li>
				</ul><!-- .breadcrumb -->
			</div>

			<div class="page-content">
	         	<@action uri="pBdInfopWeb!pageByStatusSearchParam" nickname='bdinfos'/>
				<!-- <@toJson data=bdinfos encode='false'/> --> 

				<div class="row">
					<div class="col-xs-12">
						<div class="row"style="height:35px">								
							<div class="col-xs-3 ">
								<div class="nav-search" id="nav-search">
								    <form class="form-search-bdno" method="post" action="common/bdinfo.jhtml">
										<input type="hidden" name="status" <#if RequestParameters.status?exists> value="${RequestParameters.status}" </#if>>
										<span class="input-icon">
											<input type="text" placeholder="搜投保单号" class="nav-search-input" name="searchParam" autocomplete="off" />
											<i class="icon-search nav-search-icon"></i>
										</span>
										<button id="search-btn-bdno" type="button" class="btn btn-default btn-xs">搜索</button>
					     			</form>
				         		</div><!-- #nav-search -->
							</div>
							<div class="col-xs-8 col-xs-offset-4">
								<div class="nav-search" id="nav-search">
						    		<form class="form-search-day" method="post" action="common/bdinfo.jhtml">
										<input type="hidden" name="status" <#if RequestParameters.status?exists> value="${RequestParameters.status}" </#if>>
										<span class="input-icon">
											<input type="text" placeholder="最小投保日期" class="nav-search-input" id="startTime"  name="startTime"  autocomplete="off" />
											<img src="static/plug/My97DatePicker/skin/datePicker.gif" onClick="WdatePicker({skin:'whyGreen',el:'startTime'})">
										</span>
										<span class="input-icon">
											<input type="text" placeholder="最大投保日期" class="nav-search-input" id="endTime" name="endTime"  autocomplete="off" />
											<img src="static/plug/My97DatePicker/skin/datePicker.gif" onClick="WdatePicker({skin:'whyGreen',el:'endTime'})">
										</span>
										<button id="search-btn-day" type="button" class="btn btn-default btn-xs">搜索</button>
									    <#if RequestParameters.status=="2" || RequestParameters.status=="3">
									    	<button id="search-btn-export" type="button" class="btn btn-default btn-xs">导出</button>
									    </#if>
								    </form>
					         	</div><!-- #nav-search -->
							</div>
						</div>
					    <div class="row">
	                    	<div class="col-lg-12 text-center" style="margin-top:10px;width:100%">
	                        	<div class="table-responsive">
		                            <table class="table table-hover table-bordered">	                           
	                                    <tr>
											<td class="active"><input type="checkBox" class="checkAll" onclick="clickAll()"/></td>
											<td class="active">投保单号</td>
											<#if RequestParameters.status=='3'>
												<td class="active">保险单号</td>
											</#if>
											<td class="active">投保人姓名</td>
											<td class="active">产品名称</td>
											<td class="active">起保日期</td>  
											<td class="active">保险费金额</td>
											<td class="active">保障天数</td>
											<td class="active">保障人数</td>
											<td class="active">年龄段</td>
											<td class="active">操作</td>
	                                    </tr>
	                                    <#list bdinfos.data.data.content as bdinfo>
		                                    <tr>
												<td>
													<input type="checkBox" name="check" class="checkOne" value="${bdinfo.id}" onclick="clickOne()" />
												</td>
												<td>${bdinfo.tbNo}</td>
												<#if RequestParameters.status=='3'>
													<td>${bdinfo.bdNo}</td>
												</#if>
												<td>${(bdinfo.userInfo.name)!}</td>
												<td>${(bdinfo.product.name)!}</td>
												<td>${bdinfo.startDay}</td>
												<td>${bdinfo.total}</td>
												<td>${bdinfo.days}</td>
												<td>${bdinfo.nums}</td>
												<td>${bdinfo.ageGroup}</td>
												<td>
													<button style="background-color:white" onclick="show('${bdinfo.id}','${bdinfo.status}')">保单详情</button>
													<#if RequestParameters.status=='1'&&bdinfos.data.type==2>
														<button style="background-color:white" onclick="update_status_confirm('${bdinfo.id}','${bdinfo.startDay}')">保单提交</button>
														<button style="background-color:white" onclick="del_bdinfo('${bdinfo.id}')">保单删除</button>
													</#if>
													<#if RequestParameters.status=='2'&&bdinfos.data.type==3>
														<button style="background-color:white" onclick="check('${bdinfo.id}')">保单审批</button>
													</#if>
												</td>
	                                     </#list> 
		                            </table>	                                        
						            <form id="listForm" action="common/bdinfo.jhtml" method="get">
							           	<#if RequestParameters.status?exists>
							        		<input type="hidden" value="${RequestParameters.status}" name="status"/>
						    		    </#if>
										<#assign page = bdinfos.data.data>
										DEBUG: ${page.totalPages}
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
				
		<script>
			function show(id,status) {
				$summerLayer({
 	    	    	title: "保单详情",
 	    	    	url: 'common/bdinfo.jhtml?p=bd_info_iframe&id='+id+'&status='+status,
 	    	    	area: ['550px' , '500px']
		 	    });			
			}
			function update_status_confirm(id,startDay) {
				$summerLayer({
 	    	    	title:"确认操作",
 	    	    	url:'common/bdinfo.jhtml?p=confirm_iframe&id='+id+'&startDay='+startDay,
 	    	    	area: ['200px' , '130px']
	 	    	});
			}
			function update_status(id,startDay) {
				$post({
			    	url:'bdinfo/updateStatus.jhtml',
			    	data:{
				    	id:id,
				    	startDay:startDay,
			    	},
			    	success:function(data) {
			    		layer.msg(data,3,1);
			    		location.href="common/bdinfo.jhtml?status=3";
			    	},
			    	error:function(data) {
			    		layer.msg(data,3,0);
			    	},
					dataType:"json"
			 	});  
				  
			}
			function del_bdinfo(id) {
				$post({
			    	url:'bdinfo/delbdinfo.jhtml?id='+id,
			    	data:{},
			    	success:function(data) {
			    		layer.msg(data,3,1);
			    		location.reload();
			    	},
			    	error:function(data) {
			    		layer.msg(data,3,0);
			    	},
					dataType:"json"
				}); 
			}
			function check(id) {
				$summerLayer({
	    	    	title:"保单审批",
	    	    	url:'common/bdinfo.jhtml?p=bd_check_iframe&id='+id,
	    	    	area: ['400px' , '230px']
	 	    	 });
			}
			$().ready(function() {
				function checkTime() {				   
				    var startTime = $("input[name='startTime']").val();
					var endTime = $("input[name='endTime']").val();
					if (startTime=="") {
					   	layer.msg("请选择最小起保日期",3,0);
					   	return false;
					}
					if (endTime=="") {
					   	layer.msg("请选择最大起保日期",3,0);
					   	return false;
					}
					if (startTime>endTime) {
					  	layer.msg("最小日期不能比最大日期大",3,0);
					  	return false;
					}
					return true;
				}
			    $("#search-btn-bdno").click(function() {
			    	$(".form-search-bdno").submit();
			    });
			    $("#search-btn-day").click(function() {
				    if (checkTime()) {			       
				   		$(".form-search-day").submit();
				    }
			    });
			    $("#search-btn-export").click(function() {
				   if (checkTime()) {	
				        var startTime = $("input[name='startTime']").val();
						var endTime = $("input[name='endTime']").val();
						var status = $("input[name='status']").val();
					
				        $post({
					    	url: 'bdinfo/export.jhtml',
					    	data: {startTime:startTime,endTime:endTime,status:status},
					    	success: function(data) {
					    	    window.open('${base}'+data);
					    	    //alert(data);
					    		//window.location.href=${base}data;				    	
					    	},
					    	error: function(data) {
					    		layer.msg(data,3,0);
					    	},
							dataType:"json"
					 	});  
				    	// window.open("bdinfor/export.jhtml?&startTime="+$("input[name='startTime']").val()+"&endTime="+$("input[name='endTime']").val()+"&status="+$("input[name='status']").val());
				   }
			   });
			})
		</script>
	</@layout.put>
</@layout.extends>