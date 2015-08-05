<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="${base}" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title><@layout.block name="title">frame_layout</@layout.block> </title>
		<!--[if IE 7]>
			<link rel="stylesheet" href="static/plug/ace/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
		<!--[if lte IE 8]>
			<link rel="stylesheet" href="static/plug/ace/assets/css/ace-ie.min.css" />
		<![endif]-->
		<!--[if lt IE 9]>
			<script src="static/plug/ace/assets/js/html5shiv.js"></script>
			<script src="static/plug/ace/assets/js/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="static/plug/ace/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="static/plug/ace/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="static/plug/ace/assets/css/ace.min.css" />
		<link rel="stylesheet" href="static/plug/ace/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="static/plug/ace/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="static/plug/ace/assets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="static/plug/ace/assets/css/jquery.gritter.css" />
		<link rel="stylesheet" href="static/plug/ace/assets/css/select2.css" />

		<script type="text/javascript" src="static/plug/ace/assets/js/jquery-1.10.2.min.js"></script> 
		<script src="static/plug/ace/assets/js/ace-extra.min.js"></script>
		
		<script src="static/plug/ace/assets/js/bootstrap.min.js"></script>
		<script src="static/plug/ace/assets/js/typeahead-bs2.min.js"></script>
		
		<!-- page specific plugin scripts -->
		<script src="static/plug/ace/assets/js/jquery.dataTables.min.js"></script>
		<script src="static/plug/ace/assets/js/jquery.dataTables.bootstrap.js"></script>
		
		<script src="static/plug/ace/assets/js/wenwen.js"></script>
		
		<script type="text/javascript">
			if ("ontouchend" in document) document.write("<script src='static/plug/ace/assets/js/jquery.mobile.custom.min.js'>"+ "<"+"script>");
		</script>
		
		<script src="static/plug/ace/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="static/plug/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="static/plug/ace/assets/js/jquery.slimscroll.min.js"></script>
		<script src="static/plug/ace/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="static/plug/ace/assets/js/jquery.sparkline.min.js"></script>
		<script src="static/plug/ace/assets/js/flot/jquery.flot.min.js"></script>
		<script src="static/plug/ace/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="static/plug/ace/assets/js/flot/jquery.flot.resize.min.js"></script>
		
		<!-- ace scripts -->
		<script src="static/plug/ace/assets/js/ace-elements.min.js"></script>
		<script src="static/plug/ace/assets/js/ace.min.js"></script>
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/plug/jquery-summer/core.js"></script>
		<script type="text/javascript" src="static/plug/jquery-summer/ajax.js"></script>
		<script type="text/javascript" src="static/plug/jquery-summer/form.js"></script>
		<script type="text/javascript" src="static/plug/jquery-summer/encapsulate.js"></script>
		<script type="text/javascript" src="static/plug/layer/layer.min.js"></script>
		
		<script src="static/plug/ajaxfileupload/ajaxfileupload.js"></script>
		<script src="static/plug/ext/uploadFile.js"></script>
		<script type="text/javascript" src="static/js/common.js"></script>
		<script src="${base}static/plug/My97DatePicker/WdatePicker.js"></script>
		
		<style type="text/css">
			body{
				background:#fff;
			}
			<@layout.block name="style"></@layout.block>
		</style>
	</head>
	<body>
		<@layout.block name="contents"></@layout.block>
	</body>
	<script type="text/javascript">
		<@layout.block name="script"></@layout.block>
	</script>
</html>