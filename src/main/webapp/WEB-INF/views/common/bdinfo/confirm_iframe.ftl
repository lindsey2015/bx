<@layout.extends name="layout/frame_layout.ftl">
    <@layout.put block="contents">
        <#if RequestParameters.id?exists>
        	<input type="hidden" value="${RequestParameters.id}" id="id"/>
        </#if>
        <#if RequestParameters.startDay?exists>
        	<input type="hidden" value="${RequestParameters.startDay}" id="startDay"/>
        </#if>
        <div class="page-content">
        	<div class="row">
        		<div class="col-xs-12">
        		    <div class="row">
                        <div class="col-lg-12 text-center" style="margin-top:10px;width:100%;height:100%">
                            <div class="table-responsive">
                                <table class="table table-hover table-bordered">
                                    <tr>
                                        <td>
                                            <button class="confirm" style="background-color:white">确认</button>
                                        </td>
                                        <td>
                                            <button class="closed" style="background-color:white">取消</button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
        	</div><!-- /.row -->
        </div><!-- /.page-content -->
    					
    	<script type="text/javascript" >
        	$().ready(function() {
        		var id = $("#id").val();   
        		var startDay = $("#startDay").val();   
        		$(".confirm").click(function() {
            		var i = parent.layer.getFrameIndex(window.name);
                	parent.update_status(id,startDay); 		
                    parent.layer.close(i);
        		});
        		$(".closed").click(function() {
            		var i = parent.layer.getFrameIndex(window.name);	
                    parent.layer.close(i);
        		});		
        	})
    	</script>
	</@layout.put>
</@layout.extends>