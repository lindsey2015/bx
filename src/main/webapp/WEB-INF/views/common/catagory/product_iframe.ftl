<@layout.extends name="layout/frame_layout.ftl">
    <@layout.put block="contents">
        <div class="" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">                   
            <form id="form">
                <#if RequestParameters.catagoryId?exists>
                    <input type="hidden" value="${RequestParameters.catagoryId}" name="catagoryId" />
                </#if>
                <input type="hidden" name="id" <#if RequestParameters.id?exists> value="${RequestParameters.id}" </#if> />                              
                <div class="modal-body">
                    <table>
                        <tr>
                            <td><span>名称：</span></td>
                            <td><input type="text" name="name"  size="25" /></td>
                        </tr>
                        <tr>
                            <td><span>投保范围：</span></td>
                            <td>
                                <textarea id="tbArea" name="tbArea" style="width: 700px; height: 300px; visibility: hidden;"></textarea>
                            </td>
                        </tr>
                        <tr> 
                            <td><span>条款文件上传(DOC格式)：</span></td>
                            <td>
                               <input type="file" name="file" id="docAddr" onchange="ajaxDocUpload('docAddr');" />
                            </td>
                        </tr>
                        <input type="hidden" name="tkAddr">         
                    </table> 
                </div>
                <div class="modal-footer">
                    <button type="button" id="add" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
        
        <script charset="utf-8" src="static/plug/kindeditor/kindeditor-min.js"></script>
        <script>
            var opt = ${RequestParameters.opt}; 
            
            KindEditor.ready(function(K) {
                var editor =  K.create("#tbArea", 
                  {
                    uploadJson: 'static/plug/kindeditor/jsp/upload_json.jsp',
                    resizeType: 2, 
                    allowFileManager: false,
                    themeType: "default",
                  });
           
                if (opt==opt_update) { 
                    var id = $("input[name='id']").val();
                    $post({
                        url:'product/updateUI.jhtml?id='+id,
                        data:{},
                        success:function(data) {
                            $("#form").find("[name='name']").val(data.name);
                            editor.html(data.tbArea);
                        },
                        error:function(data) {
                            layer.msg(data,3,0);
                        },
                        dataType:"json"
                    });  
                    
                    $("#add").click(function() {
                        editor.sync();
                        var name = $("#form").find("[name='name']").val();
                        var tkAddr = $("#form").find("[name='tkAddr']").val();
                        var tbArea = editor.html();
                        if (name=="") {
                            layer.msg("请输入产品名称",3,0);
                            return;
                        }
                        if (tbArea=="") {
                            layer.msg("请输入投保范围",3,0);
                            return;
                        }

                        $post({
                            url:'product/update.jhtml',
                            data:{
                                name:name,
                                tbArea:encodeURIComponent(tbArea),
                                tkAddr:tkAddr,
                                id:id
                            },
                            success:function(data) {
                                parent.location.reload();
                            },
                            error:function(data) {
                                layer.msg(data,3,0);
                            },
                            dataType:"json"
                       });  
                    }); 
                } else {            
                    $("#add").click(function(){
                        var catagoryId = $("input[name='catagoryId']").val();
                        editor.sync();
                        var name = $("#form").find("[name='name']").val();
                        var tkAddr = $("#form").find("[name='tkAddr']").val();
                        var tbArea = editor.html();
                        if (name=="") {
                            layer.msg("请输入产品名称",3,0);
                            return;
                        }
                        if (tkAddr=="") {
                            layer.msg("请上传条款",3,0);
                            return;
                        }
                        if (tbArea=="") {
                            layer.msg("请输入投保范围",3,0);
                            return;
                        }
                        $post({
                            url:'product/add.jhtml',
                            data:{
                                name:name,
                                tbArea:encodeURIComponent(tbArea),
                                tkAddr:tkAddr,
                                catagoryId:catagoryId
                            },
                            success:function(data){
                                parent.location.reload();
                            },
                            error:function(data){
                                layer.msg(data,3,0);
                            },
                            dataType:"json"
                        }); 
                    }); 
                }
            });
        </script>
    </@layout.put>
</@layout.extends>