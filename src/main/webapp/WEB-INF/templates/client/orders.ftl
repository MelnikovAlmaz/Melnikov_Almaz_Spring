<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
<#include "../cabinet/links.ftl">
    <title>Заказы - ${user.name}</title>
</head>
<body>

<#include "../cabinet/nav_bar.ftl">

<div class="container-fluid main-container">
    <div id="system-message"></div>
    <div class="row-fluid">
        <div class="span3">
            <div class="sidebar-nav">
                <ul id="yw10" class="nav nav-pills nav-stacked">
                    <li><a href="/client/cabinet">Новый заказ</a></li>
                    <li class="active"><a href="/client/cabinet/orders"> Заказы</a></li>
                    <li><a href="/client/cabinet/templates"> Шаблоны</a></li>
                    <li><a href="/client/cabinet/profile"> Профиль</a></li>
                    <li><a href="/client/cabinet/feedback"> Обратная связь</a></li>
                </ul>
            </div>
        </div>

        <div class="span9">
            <div class="row-fluid">
                <div class="span12">
                    <div id="yw1"></div>
                    <div id="content">
                        <h2>Заказы</h2>
                        <div id="currentDataset" class="grid-view"></div>
                        <div class="row-fluid">
                            <div class="span9">
                                <span class="label" style="margin-bottom: 10px">История отображается за последние 3 месяца</span>
                                <table class="table table-striped">
                                    <tr>
                                        <th>Название</th>
                                        <th>Город</th>
                                        <th>Откуда</th>
                                        <th>Куда</th>
                                        <th>Стоимость</th>
                                    </tr>
                                <#list books as book>
                                    <tr>
                                        <td>${book.name}</td>
                                        <td>${book.city.name}</td>
                                        <td>${book.fromstreet} ${book.fromhouse}</td>
                                        <td>${book.tostreet} ${book.tohouse}</td>
                                        <td>${book.cost}</td>
                                    </tr>
                                </#list>
                                </table>
                            </div>
                            <div class="span3 text-right">
                                <a class="btn btn-success" href="/client/cabinet/export">Выгрузить реестр поездок</a>
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
</body>
</html>
