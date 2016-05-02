<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <#include "../cabinet/links.ftl" >
    <title>Отзыв - ${user.name}</title>
</head>
<body>

<#include "../cabinet/nav_bar.ftl" >

<div class="container-fluid main-container">
    <div id="system-message"></div>
    <div class="row-fluid">
        <div class="span3">
            <div class="sidebar-nav">
                <ul id="yw10" class="nav nav-pills nav-stacked">
                    <li><a href="/client/cabinet">Новый заказ</a></li>
                    <li><a href="/client/cabinet/orders"> Заказы</a></li>
                    <li><a href="/client/cabinet/templates"> Шаблоны</a></li>
                    <li><a href="/client/cabinet/profile"> Профиль</a></li>
                    <li class="active"><a href="/client/cabinet/feedback"> Обратная связь</a></li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <div class="row-fluid">
                <div class="span12">
                    <div id="yw2"></div>
                    <div id="content">
                        <h2>Обратная связь</h2>
                        <form class="well form-horizontal" id="feedbackForm" action="/client/cabinet/feedback"
                              method="post">
                            <h4>Город</h4>
                            <div class="control-group ">
                                <div class="controls">
                                    <select size="1" name="city">
                                        <#list cities as city>
                                            <option
                                                    <#if user.city.id == city.id>selected</#if> value="${city.id}">${city.name}
                                            </option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <h4>Что случилось</h4>
                            <div class="control-group">
                                <div class="control-label">
                                    <label for="subject">Что, где</label></div>
                                <div class="controls">
                                    <textarea maxlength="500" name="subject" id="subject" style="width: 200px; height: 100px;"></textarea>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="control-label">
                                    <label for="date">Дата</label></div>
                                <div class="controls">
                                    <input name="date" id="date" type="date"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="control-label">
                                    <label for="orderPhone">Телефон</label>
                                </div>
                                <div class="controls">
                                    <input type="tel" value="${user.phone}" autocomplete="off" maxlength="auto"
                                           name="orderPhone" id="orderPhone"/>
                                </div>
                            </div>
                            <h4>Контактные данные</h4>
                            <div class='control-group'>
                                <div class='control-label'>
                                    <div class='control-label'>
                                        <label>Телефон</label>
                                    </div>
                                </div>
                                <div class='controls'>
                                    <div class='input-append btn-group'>
                                        <input style="width: 173px;" type="tel" value="${user.phone}" name="phone"
                                               id="phone"/>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="control-label">
                                    <label for="additional">Дополнительно</label>
                                </div>
                                <div class="controls">
                                    <textarea name="additional" id="additional" type="text" maxlength="500" style="width: 200px; height: 100px;"></textarea>
                                </div>
                            </div>
                            <div class="control-group">
                                <button class="btn btn-success" type="submit">Отправить</button>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p>&copy;2003 - 2016 Служба заказа такси «Алмаз»</p>
    </footer>
</div>
</body>
</html>

