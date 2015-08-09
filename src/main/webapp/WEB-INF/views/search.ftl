<!DOCTYPE html>
<html>
    <head>
        <base href="${base}" />
        <meta charset="utf-8" />
        <title>公共查询页面</title>
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />        
        <link rel="stylesheet" href="static/plug/ace/assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="static/plug/ace/assets/css/font-awesome.min.css" />
        <style>
            #search-div, #bd-info {
                margin: 10px;
            }
        </style>
    </head>
    <body>
        <div id="search-div">
            <form id="search-form" action="do-search.jhtml" method="POST">
                <input id="bbxr-name" maxlength=20 name="insuredUserName" placeholder="请输入被保险人姓名" /> 
                <input id="bd-no" maxlength=40 name="bdNo" placeholder="请输入保单号" /> 
                <button id="search-btn" type="button" class="btn btn-default btn-xs">查询</button>
                <a href="login.jhtml">返回首页</a>
            </form>
        </div>

        <div id="bd-info">
        </div>

        <div style="position:fixed;bottom:50px;top:auto;left:47%;">
            <a href="http://www.miitbeian.gov.cn/">闽ICP备15008657号</a>
        </div>
        
        <!-- basic scripts -->
        <script type="text/javascript" src="static/plug/ace/assets/js/jquery-1.10.2.min.js"></script>         
        <script type="text/javascript" src="static/plug/jquery-summer/core.js"></script>
        <script type="text/javascript" src="static/plug/jquery-summer/ajax.js"></script>
        <script type="text/javascript" src="static/plug/jquery-summer/form.js"></script>
        <script type="text/javascript" src="static/plug/layer/layer.min.js"></script>
        <script src="static/plug/handlebars/handlebars-v3.0.3.js"></script>
        <script src="static/plug/handlebars/handlebars-helper.js"></script>

        <script id="searched-bdinfo-template" type="text/x-handlebars-template">
            <p>您输入的是有效保单：</p>
            <ul>
                <li>产品名称: {{product.name}}</li>
                <li>保险期限: {{days}}天</li>
                <li>保额: {{total}}元</li>                
            </ul>
        </script>

        <script type="text/javascript">
            $("#search-btn").click(function() {
                $.post(
                    "do-search.jhtml", 
                    $("#search-form").serialize(),
                    function(json) {    
                        if (!json.error) {
                            var source = $("#searched-bdinfo-template").html();
                            var template = Handlebars.compile(source);
                            var bdInfoHtml = template(json.bdInfo);
                            $("#bd-info").html(bdInfoHtml);
                        } else {
                            layer.msg(json.data,3,0);
                        }
                    },
                    "json"
                );
            });           
        </script>
    </body>
</html>