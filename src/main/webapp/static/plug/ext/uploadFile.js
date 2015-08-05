/**
 * 文件上传uploadify 封装
 * @author  chrimer(林江毅)
 * @time    2015-03-03 14:07:07
 */


/**
 * $uploadify
 * 
 * @使用方法1:
 *      @参数：id,text,success,error
 *      @参数说明：id    ->  按钮id
 *           text   ->  显示按钮名称
 *           success->  上传成功时的处理函数
 *           error  ->  上传失败时的处理函数
 *      @示例：
               $uploadify("#id","example",function(){},function(){})
 *
 * @使用方法2：
 *      @参数：Object类型 -> 键：id,text,success,error
 *      @参数中的键说明：如使用方法1的参数
 *      @示例：
        $uploadify({
                    id:"#id",
                    text:'example',
                    success:function(){},
                    error:function(){}
             });
 */
function $uploadify(id, text, success, error) {
    if (id.constructor == Object) {
        object = id;
        id = object.id;
        text = object.text;
        success = object.success;
        error = object.error;
    }

    setTimeout(function() {
        $(id).uploadify({
            'height': 27,
            'width': 80,
            'buttonText': text,
            'swf': '/aishou/static/plug/uploadify/uploadify.swf',
            'uploader': '/aishou/upload/picture.jhtml',
            'auto': true,
            'onUploadSuccess': function(file, data, response) {
                var data = jQuery.parseJSON(data); //字符串转JSON
                if (data.error == false) {
                    if (success) {
                        success(data.data);
                    }
                } else if (error) {
                    error(data.data);
                }
            },
            'onUploadError': function(file, errorCode, errorMsg, errorString, swfuploadifyQueue) {
                error(errorMsg);
            }
        });

    }, 10);
}

/**
 * 文件上传ajaxfileupload 封装
 * @author  bob(傅文城)
 * @time    2015-03-03 14:07:07
 */


/**
 * ajaxFileUpload(id,img_id)
 * 
 * @使用方法1:
 *      @参数：id,img_id
 *      @参数说明：id    ->  在两个位置中使用:选择上传文件input的id ;  form表单里面input的name
 *           img_id ->  要预览的img的id
 *           success->  上传成功时的处理函数
 *           error  ->  上传失败时的处理函数
 *      @示例：
              function ajaxFileUpload(id,img_id)
 */
function ajaxFileUpload(id, img_id) {
    $.ajaxFileUpload({
        url: 'upload/picture.jhtml',
        secureuri: false,
        fileElementId: id,
        dataType: 'json',
        success: function(data, status) {
            if (typeof(data.error) != 'undefined') {
                if (data.error != '') {
                    alert(data.error);
                } else {
                    layer.msg("上传成功！", 2, 1, function() {
                        $('#' + img_id + '').attr("src", data.data);
                        $("#form").find("[name=" + id + "]").val(data.data);
                    });
                }
            }
        },
        error: function(data, status, e) {
            layer.msg("上传失败", 2, 0);
        }
    })
    return false;
}


/**
 * ajaxExcelUpload(id)
 * 
 * @使用方法1:
 *      @参数：id
 *      @参数说明：id    ->  在两个位置中使用:选择上传文件input的id ;  form表单里面input的name
 *           success->  上传成功时的处理函数
 *           error  ->  上传失败时的处理函数
 *      @示例：
              function ajaxExcelUpload(id)
 */
function ajaxExcelUpload(id) {
    $.ajaxFileUpload({
        url: 'upload/excel.jhtml',
        secureuri: false,
        fileElementId: id,
        dataType: 'json',
        success: function(data, status) {
            if (typeof(data.error) != 'undefined') {
                if (data.error != '') {
                    layer.msg(data.data, 2, 0);
                } else {
                    if (data.data && data.data.insuredUserList && data.data.insuredUserList.length > 0) {
                        var source = $("#insured-user-list-template").html();
                        var template = Handlebars.compile(source);
                        var insuredUserListHtml = template(data.data);
                        $("#insured-user-list").children().remove();
                        $("#insured-user-list").append(insuredUserListHtml);                    
                    }
                    layer.msg("上传成功！", 2, 1, function() {
                        $("input[name='excelAddr']").val(data.data.url);
                    });
                }
            }
        },
        error: function(data, status, e) {
            layer.msg("上传失败", 2, 0);
        }
    })
    return false;
}

/**
 * ajaxPdfUpload(id)
 * 
 * @使用方法1:
 *      @参数：id
 *      @参数说明：id    ->  在两个位置中使用:选择上传文件input的id ;  form表单里面input的name
 *           success->  上传成功时的处理函数
 *           error  ->  上传失败时的处理函数
 *      @示例：
              function ajaxPdfUpload(id)
 */
function ajaxPdfUpload(id) {
    $.ajaxFileUpload({
        url: 'upload/pdf.jhtml',
        secureuri: false,
        fileElementId: id,
        dataType: 'json',
        success: function(data, status) {
            if (typeof(data.error) != 'undefined') {
                if (data.error != '') {
                    layer.msg(data.data, 2, 0);
                } else {
                    layer.msg("上传成功！", 2, 1, function() {
                        $("input[name='pdfAddr']").val(data.data.url);
                    });
                }
            }
        },
        error: function(data, status, e) {
            layer.msg("上传失败", 2, 0);
        }
    })
    return false;
}

/**
 * ajaxDocUpload(id)
 * 
 * @使用方法1:
 *      @参数：id
 *      @参数说明：id    ->  在两个位置中使用:选择上传文件input的id ;  form表单里面input的name
 *           success->  上传成功时的处理函数
 *           error  ->  上传失败时的处理函数
 *      @示例：
              function ajaxDocUpload(id)
 */
function ajaxDocUpload(id) {
    $.ajaxFileUpload({
        url: 'upload/doc.jhtml',
        secureuri: false,
        fileElementId: id,
        dataType: 'json',
        success: function(data, status) {
            if (typeof(data.error) != 'undefined') {
                if (data.error != '') {
                    layer.msg(data.data, 2, 0);
                } else {
                    layer.msg("上传成功！", 2, 1, function() {
                        $("input[name='tkAddr']").val(data.data.url);
                    });
                }
            }
        },
        error: function(data, status, e) {
            layer.msg("上传失败", 2, 0);
        }
    })
    return false;
}