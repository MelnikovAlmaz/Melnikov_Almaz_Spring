function renderYearChart() {
    $.ajax({
        url: '/driver/cabinet/orders/find',
        data: {"bookName": $("#bookName").val()},
        dataType: "json",
        success: function (data, textStatus) {
            var bookTable = $("#bookTable");
            var text = "";
            for(var book in data){
                text = text + "<tr><td>" + book.name +"</td><td>" + book.city.name+"</td><td>" + book.fromstreet +" " + book.fromhouse + "</td><td>" + book.tostreet + " " + book.tohouse + "</td><td>" + book.cost + '</td><td><a href="/client/cabinet/orders/totemplate/' + book.id +'><button class="btn btn-success"' + '>Создать шаблон</button></a></td></tr>';
            }
            bookTable.innerHTML = text;
        }
    });
}