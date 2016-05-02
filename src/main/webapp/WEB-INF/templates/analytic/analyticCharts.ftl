<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.0.1/Chart.min.js"></script>
<script type="text/javascript">
    var monthData = {
        labels: [<#list dataSet as data>"${data.date}", </#list>],
        datasets: [
            {
                label: "Количество заказов в день",
                fill: false,
                lineTension: 0.1,
                backgroundColor: "rgba(75,192,192,0.4)",
                borderColor: "rgba(75,192,192,1)",
                borderCapStyle: 'butt',
                borderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: 'miter',
                pointBorderColor: "rgba(75,192,192,1)",
                pointBackgroundColor: "#fff",
                pointBorderWidth: 1,
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(75,192,192,1)",
                pointHoverBorderColor: "rgba(220,220,220,1)",
                pointHoverBorderWidth: 2,
                pointRadius: 1,
                pointHitRadius: 10,
                data: [<#list dataSet as data>${data.count}, </#list>]
            }
        ]
    };
    var monthChartElem = document.getElementById("monthChart");
    var monthChart = new Chart(monthChartElem, {
        type: 'line',
        data: monthData
    });
    var yearData = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "Количество заказов в месяц",
                backgroundColor: "rgba(255,99,132,0.2)",
                borderColor: "rgba(255,99,132,1)",
                borderWidth: 1,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                data: [65, 59, 80, 81, 56, 55, 40],
            }
        ]
    };
    var yearChartElem = document.getElementById("yearChart");
    var yearChart = new Chart(yearChartElem, {
        type: 'bar',
        data: yearData
    });
</script>