<!DOCTYPE html>
<html>
    <head>
        <base href="${base}" />
        <meta charset="utf-8" />
        <title>登录页面</title>
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!--[if IE 7]>
            <link rel="stylesheet" href="static/plug/ace/assets/css/font-awesome-ie7.min.css" />
        <![endif]-->

        <!-- page specific plugin styles -->
        <!-- fonts -->

        <!--[if lte IE 8]>
            <link rel="stylesheet" href="static/plug/ace/assets/css/ace-ie.min.css" />
        <![endif]-->

        <!-- inline styles related to this page -->
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="static/plug/ace/assets/js/html5shiv.js"></script>
            <script src="static/plug/ace/assets/js/respond.min.js"></script>
        <![endif]-->
        <LINK href="static/images/login/User_Login.css" type=text/css rel=stylesheet>
    </head>
    <body id=userlogin_body>
        <form id="form" method="post">
            <input type="hidden" <#if RequestParameters.from?exists> value="${RequestParameters.from}" </#if> id="from"/>
            <div id=user_login>
                <dl>
                    <dd id=user_top>
                        <ul>
                            <li class=user_top_l></li>
                            <li class=user_top_c></li>
                            <li class=user_top_r>
                                <img width="142" height="39" src="${base}static/plug/ace/assets/avatars/logo.jpg" />
                            </li>
                        </ul>
                    </dd>
                    <dd id=user_main>
                        <ul>
                            <li class=user_main_l></li>
                            <li class=user_main_c>
                                <div class=user_main_box>
                                    <ul>
                                        <li class=user_main_text>用户名：</li>
                                        <li class=user_main_input>
                                            <input class=txtusernamecssclass id=txtusername maxlength=20 name="username" placeholder="請輸入用戶名"> 
                                        </li>
                                    </ul> 
                                    <ul> 
                                        <li class=user_main_text>密码： </li>
                                        <li class=user_main_input>
                                            <input type="password" class=txtpasswordcssclass id=txtusername maxlength=20 name="password" placeholder="請輸入密碼">
                                        </li>
                                    </ul>
                                    <ul>
                                      <li class=user_main_text></li>
                                      <li class=user_main_input></li>
                                    </ul>
                                </div>
                            </li>
                            <li class=user_main_r><input class=ibtnentercssclass id=ibtnenter style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px"type=image src="${base}static/images/login/user_botton.gif" name=ibtnenter></li>
                        </ul>
                    </dd>
                    <dd id=user_bottom>
                        <ul>
                            <li class=user_bottom_l></li>
                            <li class=user_bottom_c></li>
                            <li class=user_bottom_r></li>
                        </ul>
                    </dd>
                </dl>
            </div>
        </form>
        <div style="position:fixed;bottom:50px;top:auto;left:47%;">
            <a href="http://www.miitbeian.gov.cn/">闽ICP备15008657号</a>
        </div>
        
        <!-- basic scripts -->
        <script type="text/javascript" src="static/plug/ace/assets/js/jquery-1.10.2.min.js"></script> 
        <!-- <script type="text/javascript" src="static/plug/ace/assets/js/jquery-2.0.3.min.js"></script> -->
        <script type="text/javascript" src="static/plug/jquery-summer/core.js"></script>
        <script type="text/javascript" src="static/plug/jquery-summer/ajax.js"></script>
        <script type="text/javascript" src="static/plug/jquery-summer/form.js"></script>
        <script type="text/javascript" src="static/plug/layer/layer.min.js"></script>

        <script type="text/javascript">
            if ("ontouchend" in document) document.write("<script src='static/plug/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
            
            $().ready(function() {
                $("#ibtnenter").click(function() {
                    $("#form").submit(function() {
                    
                        $.post("userlogin/login.jhtml", 
                            $("#form").serialize(),
                            function(json) {    
                                if (!json.error) {
                                        var from = $("#from").val();
                                        if (from == "") {                                                 
                                            window.location.href = "index.jhtml";                           
                                        } else {
                                            window.location.href = from;    
                                        }
                                } else {
                                    layer.msg(json.data,3,0);
                                    //$.messager.show({title:'提示',msg:json.msg,timeout:2000,showType:'slide'});
                                }
                            },
                            "json"
                        );
                        return false;
                    });
                });
            })            
        </script>
    </body>
</html>