<#if RequestParameters.p?exists>
	<input type="hidden" value="${RequestParameters.p}" name="p" />
</#if>
<#if RequestParameters.roleId?exists>
	<input type="hidden" value="${RequestParameters.roleId}" name="roleId" />
</#if>
<#if RequestParameters.dingDanType?exists>
	<input type="hidden" value="${RequestParameters.dingDanType}" name="dingDanType" />
</#if>
<!-- <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" /> -->

<div class="clear"></div>
<div class="page pagination">
	<div></div>
	每页显示条数：
	<select name="pageSize" onchange="changeSubmit(this.value);">
		<#if page.pageSize =10 >
			<option value="10" selected="selected">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
		<#elseif page.pageSize =20>
			<option value="10">10</option>
			<option value="20"  selected="selected">20</option>
			<option value="30">30</option>
		<#elseif page.pageSize =30>
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30" selected="selected">30</option>
		<#else>
			<option value="${page.pageSize}" selected="selected">${page.pageSize}</option>
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
		</#if>	
	</select>
	<#if (totalPages > 1)>
		<#if isFirst>
			<a href="javascript:;" class="firstPage">首页</a>
		<#else>
			<a class="firstPage" href="javascript: $.pageSkip(${firstPageNumber});">首页</a>
		</#if>
		<#if hasPrevious>
			<a href="javascript: $.pageSkip(${previousPageNumber});"><</a>
		<#else>
			<a href="javascript:;"><</a>
		</#if>
	
		<#list segment as segmentPageNumber>
			<#if (segmentPageNumber_index == 0 && segmentPageNumber > firstPageNumber + 1)>
				<div class="_more">...</div>
			</#if>
			<#if (segmentPageNumber != pageNumber)>
				<a href="javascript: $.pageSkip(${segmentPageNumber});">${segmentPageNumber}</a>
			<#else>
				<a href="javascript:;" class="_crr">${segmentPageNumber}</a>
			</#if>
			<#if (!segmentPageNumber_has_next && segmentPageNumber < lastPageNumber - 1)>
				<div class="_more">...</div>
			</#if>
		</#list>

		<#if hasNext>
			<a href="javascript: $.pageSkip(${nextPageNumber});">></a>
		<#else>
			<a href="javascript:;">></a>
		</#if>
		<#if isLast>
			<#--<a href="javascript:;" class="lastPage">末页</a>-->
		<#else>
			<#--<a href="javascript: $.pageSkip(${lastPageNumber});" class="lastPage">末页</a>-->
		</#if>
	</#if>	
	<span class="pageSkip">		
		共${page.total}条记录，${page.totalPages}页 
		<input type="hidden" id="totalPageVal" value="${page.totalPages}"/>
		<input type="text" id="pageNumber" name="pageNumber" value="${page.pageNumber}" maxlength="9" onpaste="return false;" />
		<input type="button" id="skip" value="跳转" />
	</span>
	<div></div>
</div>

<div class="clear"></div>
<style>
	.page{width: 990px; height: 50px; font-size: 14px; font-family:"微软雅黑"; line-height: 50px; margin: 10px auto; text-align: center;}
	.page>a{ background: #fff;border:1px solid #ccc; width: 30px; height: 30px; display: inline-block; line-height: 30px;}
	.page>._more{ width: 30px; height: 30px;display: inline-block; line-height: 30px;background:none;}
	.page>a.firstPage, .enterprise-find-more>a.lastPage{width:50px;}
	.page>a._crr{background-color: #f40; color: #fff; font-weight: 700;}
	/*.enterprise-find-more>div{height: 1px; width: 100%; background: url("static/images/main/seeker-find-list-dot.jpg") repeat-x;}*/
	.pageSkip{margin-left:15px;font-size:12px;}
	.pageSkip>input[type='text'] {width:30px;height:25px;text-align:center;border:1px solid #f40;margin-left:10px;}
	.pageSkip>input[type='button'] {width:50px;height:50px;border:1px solid #f40;background:#f40;color:#fff;content:"跳转"; cursor:pointer;}
</style>
<!-- <script type="text/javascript" src="static/js/pagination.js"></script> -->
<script>
    function changeSubmit(value) {
		var totalPage = $("#totalPageVal").val();
		var pageNumber = $("#pageNumber").val();
		if (pageNumber > totalPage) {
			layer.msg("页码不能超过总页数", 1, 5);
		} else {
			$("#listForm").submit();
		}
    }
	$(document).ready(function() {
	    var $listForm = $("#listForm");
	    var $pageNumber = $("#pageNumber");
	    
		$("#skip").click(function() {
			var totalPage = $("#totalPageVal").val();
			var pageNumber = $("#pageNumber").val();
			if (pageNumber > totalPage) {
				layer.msg("页码不能超过总页数", 1, 5);
			} else {
				$("#listForm").submit();
			}
		});
		
		// 表单提交
		$listForm.submit(function() {
			if (!/^\d*[1-9]\d*$/.test($pageNumber.val())) {
				$pageNumber.val("1");
			}			
		});
		
		// 页码跳转
		$.pageSkip = function(pageNumber) {
			$pageNumber.val(pageNumber);
			$listForm.submit();
			return false;
		}
	});
</script>