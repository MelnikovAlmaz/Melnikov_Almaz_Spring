<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<div class="page-loading page-top navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <div class="nav-collapse" id="yii_bootstrap_collapse_0">
                <ul class="nav navbar-nav navbar-right">
                    <#if user??>
                        <li><a href="/logout">Выход (${user.name})</a></li>
                    <#else >
                        <li><a href="/login">Вход</a></li>
                        <li><a href="/register">Регистрация</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>