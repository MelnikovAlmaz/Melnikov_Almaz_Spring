<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Такси Алмаз</title>
    <link href="/css/main.css" rel="stylesheet">
    <link href="/css/second.css" rel="stylesheet">
</head>
<body class="b-index-page fixed-buttons lang-ru">
<div class="b-container">
<#include "sidebar.ftl">

    <div class="b-main">
    <#include "city_choose.ftl">

        <iframe id="frame-order" class="b-order-block"
                src="http://taxsee.ru/orders3/frame.jsp?org=1&base=19&lang=ru&metrika=31467963&style=taximaxim&host=http://taximaxim.ru"
                frameborder="0"
                style="min-height: 650px;display: block;">
        </iframe>
    </div>
    <div class="b-modal" data-id="feedback-form">
        <div class="m-overlay js-modal-close"></div>
        <div class="m-body">
            <button type="button" class="m-close js-modal-close"><i class="fa fa-times"></i></button>
            <div class="m-body-wrapper">
                <iframe id="frame-feedback" class="b-feedback-block"
                        src="http://taxsee.ru/orders3/framefeedback.jsp?base=19&org=1&lang=ru&taximaximStyle=1&host=http://taximaxim.ru"
                        frameborder="0"
                        style="min-height: 660px;width: 100%;display: block;"></iframe>
            </div>
        </div>
    </div>
</div>
<script src="/js/main.js"></script>
</body>
</html>
