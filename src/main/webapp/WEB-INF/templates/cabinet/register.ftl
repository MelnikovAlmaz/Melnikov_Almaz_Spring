<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <#include "links.ftl" >
    <title>Кабинет для клиентов - Регистрация</title>
</head>
<body>
<#include "nav_bar.ftl">
<div class="container-fluid main-container">
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">
                <div class="span12">
                    <div id="content">
                        <div class="getPass_message"></div>
                        <h4>Регистрация в личный кабинет для клиентов службы заказа такси «Алмаз»</h4>
                        <#if error??>
                            <div class="error">${error}</div>
                        </#if>
                        <form class="well newLogin form-vertical" id="inlineForm"
                              action="/client/register" method="post">
                            <div class='newLoginPhone'>
                                <label class="required">Логин <span class="required">*</span></label>
                                <div class="input-prepend">
                                    <input class="span3" style="width: 223px;" name="username" type="text"
                                    <#if username??> value="${username}"</#if>
                                    />
                                </div>
                            </div>
                            <div class='newLoginPhone'>
                                <label class="required">Номер телефона<span class="required">*</span></label>
                                <div class="input-prepend">
                                    <input class="span3" style="width: 223px;" name="phone" type="tel"
                                            <#if phone??>
                                                value="${phone}"
                                            </#if>
                                    />
                                </div>
                            </div>
                            <div class="clear"></div>
                            <div class="control-group ">
                                <label class="control-label">Город</label>
                                <div class="controls">
                                    <select size="1" name="city">
                                        <#list cities as city>
                                            <option
                                                    <#if cityError??>
                                                        <#if city.id == cityError>
                                                            selected
                                                        </#if>
                                                    </#if>
                                                    value="${city.id}">${city.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="password-fields">
                                <div class="newLoginPassword">
                                    <label class="required">Пароль
                                        <span class="required">*</span>
                                    </label>
                                    <input class="span3" style="margin:0 10px 0 0; width: 200px;" name="password" type="password"/>
                                </div>
                            </div>
                            <div class="password-fields">
                                <div class="newLoginPassword">
                                    <label class="required">Повтор пароля
                                        <span class="required">*</span>
                                    </label>
                                    <input class="span3" style="margin:0 10px 0 0; width: 200px;"
                                           name="confirmPassword"
                                           type="password"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn-main-style btn btn-primary" type="submit" name="yt0">
                                    Регистрация
                                </button>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
            </div>
        </div><!--/span-->
    </div><!--/row-->
    <hr>
    <footer>
        <p id="base-info"></p>
        <p>&copy;2003 - 2016 Служба заказа такси «Алмаз»</p>
    </footer>
</div>
</body>
</html>

