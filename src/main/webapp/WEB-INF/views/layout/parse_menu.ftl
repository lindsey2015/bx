<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try{ace.settings.check('sidebar', 'fixed')}catch(e){}
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>
            <span class="btn btn-info"></span>
            <span class="btn btn-warning"></span>
            <span class="btn btn-danger"></span>
        </div>
    </div><!-- #sidebar-shortcuts -->

    <ul class="nav nav-list" >
        <@permission value="2" >
            <li>
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <span class="menu-text">用户信息</span>
                    <b class="arrow icon-angle-down"></b>
                </a>

                <ul class="submenu">
                    <li>
                        <a href="common/user.jhtml?p=person_info">
                            <i class="icon-double-angle-right"></i>
                            个人信息管理
                        </a>
                    </li>
                </ul>
            </li>
        </@permission>

        <li>
            <a href="javascript:void(0);" class="dropdown-toggle">
                <span class="menu-text">保单信息</span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <@permission value="2">
                    <li>
                        <a href="common/bdinfo.jhtml?status=1">
                            <i class="icon-double-angle-right"></i>
                            暂存保单信息
                        </a>
                    </li>
                </@permission>
                <li>
                    <a href="common/bdinfo.jhtml?status=2">
                        <i class="icon-double-angle-right"></i>
                        已提交保单信息
                    </a>
                </li>
                <li>
                    <a href="common/bdinfo.jhtml?status=3">
                        <i class="icon-double-angle-right"></i>
                        已通过保单信息
                    </a>
                </li>
            </ul>
        </li>
        <@permission value="1">
            <li>
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <span class="menu-text">用户管理</span>
                    <b class="arrow icon-angle-down"></b>
                </a>

                <ul class="submenu">
                    <li>
                        <a href="common/user.jhtml?type=2">
                            <i class="icon-double-angle-right"></i>
                            普通用户管理
                        </a>
                    </li>
                    <li>
                        <a href="common/user.jhtml?type=3">
                            <i class="icon-double-angle-right"></i>
                            保险公司管理
                        </a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <span class="menu-text">产品管理</span>
                    <b class="arrow icon-angle-down"></b>
                </a>

                <ul class="submenu">

                    <li>
                        <a href="common/catagory.jhtml">
                            <i class="icon-double-angle-right"></i>
                            产品类别管理
                        </a>
                    </li>

                </ul>
            </li>
        </@permission>
        <@permission value="2">
            <li>
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <span class="menu-text">投保管理</span>
                    <b class="arrow icon-angle-down"></b>
                </a>

                <ul class="submenu">
                    <li>
                        <a href="common/toubao.jhtml">
                            <i class="icon-double-angle-right"></i>
                            投保
                        </a>
                    </li>
                </ul>
            </li>
        </@permission>
    </ul>

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try{ace.settings.check('sidebar', 'collapsed')}catch(e){}

        //自动标志当前页面
        $(document).ready(function() {
            //http://localhost/aishou/admin/mobile.jhtml?p=ydcatagory&type=1
            url=window.location.href;
            //admin/mobile.jhtml?p=ydcatagory&type=1
            url=url.replace(url.split("/",4).join("/")+"/","");
            $(".submenu").each(function(i) {
                $(this).children("li").each(function(j) {
                    if ($(this).children("a").attr("href") == url) {
                        $(this).addClass("active");
                        $(this).parents("li").each(function(k) {
                            $(this).addClass("active");
                        });
                    }
                });
            });
        });
    </script>
</div>