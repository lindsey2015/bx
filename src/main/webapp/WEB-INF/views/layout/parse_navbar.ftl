<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar', 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <!-- <i class="icon-leaf"></i> -->
                    <img width="142" height="39" src="${base}static/plug/ace/assets/avatars/logo.jpg">
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${base}static/plug/ace/assets/avatars/p.png" alt="Jason's Photo" />
                        <span class="user-info">
                            <small>欢迎,</small>
                            <#if user?exists>
                               ${user.username}
                            <#else>
                               您还未登录
                            </#if>
                        </span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="javascript:void(0);" onclick="changePassword();">
                                <i class="icon-cog"></i>
                                修改密码
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="javascript:void(0);" onclick="logout();">
                                <i class="icon-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>
<script type="text/javascript">
    function logout() {
        $.post("userlogin/logout.jhtml",
            {},
            function(json) {
                window.location.href = "${base}login.jhtml";
            },
            "json"
        );
    }

    function changePassword() {
        $summerLayer({
            title:"修改密码",
            url:'${base}common/user.jhtml?p=password_change',
            area:['350px', '200px']
        });
    }
</script>