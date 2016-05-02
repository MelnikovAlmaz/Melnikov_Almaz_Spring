<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <#include "../cabinet/links.ftl">
    <title>${user.name}</title>
</head>
<body>

<#include "../cabinet/nav_bar.ftl">

<div class="container-fluid main-container">
    <div id="system-message"></div>
    <div class="row-fluid">
        <div class="span3">
            <div class="sidebar-nav">
                <ul id="yw10" class="nav nav-pills nav-stacked">
                    <li class="active"><a href="/client/cabinet">Новый заказ</a></li>
                    <li><a href="/client/cabinet/orders"> Заказы</a></li>
                    <li><a href="/client/cabinet/templates"> Шаблоны</a></li>
                    <li><a href="/client/cabinet/profile"> Профиль</a></li>
                    <li><a href="/client/cabinet/feedback"> Обратная связь</a></li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <div class="row-fluid">
                <div class="span12">
                    <div id="content">
                        <h2 id="newOrderTitle">Новый заказ</h2>
                        <div id="neworder">
                            <div class="neworder-tab tabs-above" id="neworder-tab">
                                <ul id="yw9" class="nav nav-pills">
                                    <li class="active"><a data-toggle="tab" href="#neworder-tab_tab_1"><i
                                            class="icon-road"></i> По адресу</a></li>
                                    <li><a data-toggle="tab" href="#neworder-tab_tab_2"><i class="icon-tags"></i> По
                                        шаблону</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div id="neworder-tab_tab_1" class="tab-pane fade active in">
                                        <form class="well form-horizontal" id="horizontalForm" action="/client/cabinet"
                                              method="post">
                                            <h4>Основная информация</h4>
                                            <div id="templateName">
                                                <div class="control-group ">
                                                    <label class="control-label" for="name">Название</label>
                                                    <div class="controls">
                                                        <input name="name" id="name" type="text"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class='control-group' style='z-index:1000; position: relative;'>
                                                <label class="control-label" for="phone">Номер телефона</label>
                                                <div class='controls'>
                                                    <div class='input-append btn-group'>
                                                        <input data-provide="typeahead" style="width: 173px;"
                                                               type="tel" name="phone" id="phone" value="${user.phone}"/>
                                                    </div>
                                                    <br/>
                                                </div>
                                            </div>
                                            <div class="control-group ">
                                                <label class="control-label" for="">Город</label>
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
                                            <h4>Откуда</h4>
                                            <div class="control-group ">
                                                <label class="control-label">Объект/Улица</label>
                                                <div class="controls">
                                                    <input name="fromStreet" type="text" value="${user.street}"/>
                                                </div>
                                            </div>
                                            <div class="control-group ">
                                                <label class="control-label" for="">Дом</label>
                                                <div class="controls">
                                                    <input id="fromHouse" name="fromHouse" maxlength="15" type="text" value="${user.house}"/>
                                                </div>
                                            </div>
                                            <div class="control-group ">
                                                <label class="control-label" for="">Подъезд</label>
                                                <div class="controls">
                                                    <input id="fromBlock" name="fromBlock" type="text"/>
                                                </div>
                                            </div>
                                            <div class="control-group ">
                                                <label class="control-label" for="">Где встречать</label>
                                                <div class="controls">
                                                    <input id="fromPlace" name="fromPlace" type="text"/>
                                                </div>
                                            </div>
                                            <div id="mainPoint">
                                                <div class="pointNode">
                                                    <div class="control-group title">
                                                        <h4><span>Куда</span></h4>
                                                    </div>
                                                    <div class="control-group ">
                                                        <label class="control-label" for="">Объект/Улица</label>
                                                        <div class="controls">
                                                            <input name="toStreet" class="toStreet" type="text"/>
                                                        </div>
                                                    </div>
                                                    <div class="control-group ">
                                                        <label class="control-label" for="">Дом</label>
                                                        <div class="controls">
                                                            <input name="toHouse" class="toHouse" maxlength="15" id="" type="text"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="form-message"></div>
                                            <div class="form-actions">
                                                <input class="btn btn-success" type="submit" value="Отправить заказ">
                                            </div>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
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
</div>
</body>
</html>
