var idReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
function add(status) {
    validateForm();
    if ($(".error").length > 0) {
        var top = $($(".error")[0]).offset().top;
        $(window).scrollTop(top);
        $(".error")[0].focus();
    } else {
        submitForm(status);
    }
}

function validateForm() {  
    startDateReg = /^20\d{2}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3([0|1])))$/;
    startDay = $("input[name='startDay']").val();
    var startDayInpt = $("input[name='startDay']");
    if (!startDateReg.test(startDay)) {
        addErrorMsg(startDayInpt, "请选择起保日期");           
    } else {
        removeErrorMsg(startDayInpt);
    }

    var acceptedChk = $("#accepted-chk");
    if (!acceptedChk.attr('checked')) {       
        addErrorMsg(acceptedChk.parent(), "请接受投保申明");
    } else {
        removeErrorMsg(acceptedChk.parent());
    }

    var insuredUserType = $("input[name='insuredUserType']:checked").val();
    if (insuredUserType === "user-input") {
        var bbxrInfoDivs = $("div[name='bbxr-info']");
        for (var i =  0; i < bbxrInfoDivs.length; i++) {
            var bbxrInfoDiv = $(bbxrInfoDivs[i]);                
            var insuredUser = {};
            var nameInput = bbxrInfoDiv.find("input[name='insuredUser.name']")
            insuredUser.name = nameInput.val();
            if (insuredUser.name == "") {
                addErrorMsg(nameInput, "请输入姓名");
            } else {
                removeErrorMsg(nameInput);
            }

            insuredUser.identityType = bbxrInfoDiv.find("select[name='insuredUser.identityType']").val();

            var identityInput = bbxrInfoDiv.find("input[name='insuredUser.identity']");
            insuredUser.identity = identityInput.val();
            if (insuredUser.identity == "") {
                addErrorMsg(identityInput, "请输入证件号码");
            } else if (insuredUser.identityType == 1 && !idReg.test(insuredUser.identity)) {
                addErrorMsg(identityInput, "请输入正确的身份证号码");
            } else {
                removeErrorMsg(identityInput);
            }

            insuredUser.occupationType = bbxrInfoDiv.find("select[name='insuredUser.occupationType']").val();
            var birthdayInput = bbxrInfoDiv.find("input[name='insuredUser.birthday']");
            insuredUser.birthday = birthdayInput.val();
            if (insuredUser.birthday == "" || !/\d{8}/.test(insuredUser.birthday)) {
                addErrorMsg(birthdayInput, "请输入生日，格式YYYYMMDD");
            } else {
                removeErrorMsg(birthdayInput);
            }
        }
    } else if (insuredUserType === "group-import") {
        var excelAddr = $("input[name='excelAddr']").val();
        var excelAddrInput = $("#excelAddr");
        if (excelAddr == "") {                
            addErrorMsg(excelAddrInput, "请导入团单");
        } else {
            var insuredUserArray = getInsuredUserArray(); 
            if (insuredUserArray.length == 0) {
                addErrorMsg(excelAddrInput, "请导入正确格式的团单");
            } else {
                removeErrorMsg(excelAddrInput);
            }
        }
    }
}

function addErrorMsg(field, errorMsg) {
    removeErrorMsg(field);
    field.addClass("error");
    field.attr("title", errorMsg);
}

function removeErrorMsg(field) {
    if (field.hasClass("error")) {
        field.removeClass("error");
        field.attr("title", "");
    }
}

function submitForm(status) {
    var startDay = $("input[name='startDay']").val();
    var excelAddr = $("input[name='excelAddr']").val();
    var total = $("#total").text();
    var days = $("#bzDate").find("option:selected").text();
    var nums = $("input[name='nums']").val();
    var ageGroup = $("#ageGroup").find("option:selected").text();
    var insuredUserArray = getInsuredUserArray(); 
    var product = {};
    product.id = $("#product").find("option:selected").val();
    product.name = $("#product").find("option:selected").text();
    var userInfo = {};
    userInfo.id = $("#user-info-id").val();
    $.ajax({
        type: "POST",
        url: 'bdinfo/add.jhtml',
        dataType: "json", 
        contentType: "application/json;charset=utf-8",
        headers: { 
            'Accept': 'application/json;charset=utf-8',
            'Content-Type': 'application/json;charset=utf-8' 
        },       
        data: JSON.stringify({
            product: product,
            userInfo: userInfo,
            startDay: startDay,
            excelAddr: excelAddr,
            total: total,
            status: status,
            days: days,
            nums: nums,
            ageGroup: ageGroup,
            insuredUserList: insuredUserArray
        }),            
        success: function(data) {                
            layer.msg(data.data, 3, 1);
            if (status == 1) {
                location.href = basePath + "common/bdinfo.jhtml?status=1";
            } else {
                location.href = basePath + "common/bdinfo.jhtml?status=3";
            }
        },
        error: function(data) {
            layer.msg(data, 3, 0);
        }            
    });
}

function getInsuredUserArray() {
    var insuredUserArray = new Array();
    var insuredUserType = $("input[name='insuredUserType']:checked").val();
    if (insuredUserType === "user-input") {
        // user fill in the insured user info
        var bbxrInfoDivs = $("div[name='bbxr-info']");
        for (var i =  0; i < bbxrInfoDivs.length; i++) {
            var bbxrInfoDiv = $(bbxrInfoDivs[i]);                
            var insuredUser = {};
            insuredUser.name = bbxrInfoDiv.find("input[name='insuredUser.name']").val();
            insuredUser.identityType = bbxrInfoDiv.find("select[name='insuredUser.identityType']").val();
            insuredUser.identity = bbxrInfoDiv.find("input[name='insuredUser.identity']").val();
            insuredUser.occupationType = bbxrInfoDiv.find("select[name='insuredUser.occupationType']").val();
            insuredUser.birthday = bbxrInfoDiv.find("input[name='insuredUser.birthday']").val();

            insuredUserArray.push(insuredUser);
        }
    } else if (insuredUserType === "group-import") {
        // user import the insured user info by excel
        var bbxrInfoRows = $("#insured-user-list").find("tr");
        for (var i = 1; i < bbxrInfoRows.length; i++) {
            var bbxrInfoRow = $(bbxrInfoRows[i]);
            var insuredUser = {};
            insuredUser.name = $(bbxrInfoRow.children()[0]).text();
            insuredUser.identityType = $(bbxrInfoRow.children()[1]).attr("value");
            insuredUser.identity = $(bbxrInfoRow.children()[2]).text();
            insuredUser.occupationType = $(bbxrInfoRow.children()[3]).attr("value");
            insuredUser.birthday = $(bbxrInfoRow.children()[4]).text();

            insuredUserArray.push(insuredUser);
        }
    }
    return insuredUserArray;
}

$().ready(function() {
    $("#product").AddOption("请选择产品：", "-1", true, 0); //最上端插入一个文本为“请选择产品“，值为”-1“的列表项，并且是选中状态  
    $("#ageGroup").AddOption("请选择年龄段：", "-1", true, 0);
    $("#bzDate").AddOption("请选择保障日期：", "-1", true, 0);

    $("#product").FillOptions(
        "product/listByUserProduct.jhtml", {
            datatype: "json",
            textfield: "name",
            valuefiled: "id",
            selectedindex: 0, //填充并选中第1项  
            keepold: true //填充并且保留原有项  
        }
    );

    $("#product").CascadingSelect(
        $("#ageGroup"), //需要联动的下拉列表框，必须  
        "agegroup/listByProductId.jhtml", {
            datatype: "json",
            textfield: "ageGroup",
            valuefiled: "id",
            parameter: "productId"
        }, //通过设置parameter:”p”这个参数会生成一个"handler1.ashx?p=xxx”这样的地址来做ajax请求  
        function() { //完成联动后执行  

        }
    );
    $("#ageGroup").CascadingSelect(
        $("#bzDate"), //需要联动的下拉列表框，必须  
        "bzdate/listAllByAgeGroupId.jhtml", {
            datatype: "json",
            textfield: "etc.days",
            valuefiled: "id",
            parameter: "ageGroupId"
        }, //通过设置parameter:”p”这个参数会生成一个"handler1.ashx?p=xxx”这样的地址来做ajax请求  
        function() { //完成联动后执行  
            //log.info("测试");  
        }
    );

    $("#product").on('change', function() {
        var productId = $("#product").val();
        $post({
            url: 'product/getByProductId.jhtml',
            data: {
                productId: productId
            },
            success: function(data) {
                $(".tbArea").html("<strong>《投保范围》:</strong>" + data.tbArea);
                if (data.tkAddr == "") {
                    $("#tk_addr").attr('href', 'javascript:void(0)');
                } else {
                    $("#tk_addr").attr('href', data.tkAddr);
                }
            },
            error: function(data) {
                layer.msg(data, 3, 0);
            },
            dataType: "json"
        });
        $(".measure").css('border', '1px solid #d8d8d8');
        $(".tbArea_tkDownload").css('display', 'block');
        $(".bfcs").css('display', 'block');
    });

    $(".ljtb").on('click', function() {
        var bzDate = $("#bzDate").find("option:selected").text();
        var ageGroupId = $("#ageGroup").val();
        var nums = $("input[name='nums']").val();
        if ($("#ageGroup").val() == "-请选择-") {
            layer.msg("请选择年龄段");
            return;
        }
        if ($("#bzDate").val() == "-请选择-") {
            layer.msg("请选择保障天数");
            return;
        }
        reg = /^[0-9]*[1-9][0-9]*$/;
        if (!reg.test(nums)) {
            layer.msg("请输入正确的承保人数");
            return;
        }
        $post({
            url: 'bdinfo/checktotal.jhtml',
            data: {
                bzDate: bzDate,
                ageGroupId: ageGroupId,
                nums: nums
            },
            success: function(data) {
                layer.msg("计算成功", 1, 1);
                $("#total").html(data);
                $(".tb_disabled").attr('disabled', 'true');
                $(".tb_info").css('display', 'block');
                $(".button").css('display', 'none');
            },
            error: function(data) {
                layer.msg(data, 3, 0);
            },
            dataType: "json"
        });
    });

    $("#tk_addr").click(function() {
        if ($(this).attr('href') == "javascript:void(0)") {
            layer.msg("该产品没有条款", 2, 1);
        }
    });

    $("input[name='insuredUserType']").on("change", function() {
        if ($(this).val() === "user-input") {
            $("#group-import-div").hide();
            $("#group-import-div").find(".error").removeClass("error");
            $("#user-input-div").show();

        } else if ($(this).val() === "group-import") {
            $("#user-input-div").hide();
            $("#user-input-div").find(".error").removeClass("error");            
            $("#group-import-div").show();
        }
    });

    $("#add-more-insured-btn").click(function() {
        $that = $(this).parent();
        var preInsured = $that.prev();
        var id = preInsured.attr("id");
        var index = parseInt(id.split("-")[2]) + 1;
        $("<div class='tjbd-button' name='remove-insured-lnk'><a href='javascript:void(0)'>删除</a></div>").insertBefore($that);
        var cloneInsuredDiv = preInsured.clone();
        cloneInsuredDiv.attr("id", "bbx-info-" + index)
        cloneInsuredDiv.find("input").val("");
        cloneInsuredDiv.insertBefore($that);
    });

    $(document).on('blur', "input[name='insuredUser.identity']", function() {
        $this = $(this);
        var identityType = $this.parent().prev().find("select option:selected").val();  
        var id = $this.val();
        if (identityType === "1" && idReg.test(id)) {
            var birthday = "";
            if (id.length == 15) {
                birthday = "19" + id.substring(6, 12);
            } else if (id.length == 18) {
                birthday = id.substring(6, 14);
            }
            $this.parent().next().next().find("input").val(birthday);
        }
    });

    $(document).on('click', "div[name='remove-insured-lnk']", function() {        
        $(this).prev().remove();     
        $(this).remove();   
    });
}); 