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
						<a href="javaScript:void(0);" onclick="return false;">太平洋财产保险</a>
					</li>
					<li class="active">欢迎界面</li>
				</ul><!-- .breadcrumb -->
			</div>

			<div class="page-content">
				<center>欢迎访问太平洋保险管理页面</center>	                                 
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->	
	</@layout.put>	
</@layout.extends>