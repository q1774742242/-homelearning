$(function(){
	 getcount();
});

function getcount(){
	$.ajax({
		url:"/cus.do?method=count",
		data:'',
		dataType: "json",
		type:'post',
		success: function(data){
			look(data, '<b style="font-size: 25px;">各区域客户数量比例图</b>');
		}
	});
	
	
	
	
}


function look(datas)
{
	var myChart = echarts.init(document.getElementById('main'));
	
	//展示图片
	option = null;

    function randomData(val) {
        now = new Date(+now + 60 * 1000);
        
        var valueName = now.getFullYear() + '/' + (now.getMonth() + 1) + '/' + now.getDate() + ' ' + (now.getHours() >= 10 ? now.getHours() : '0' + now.getHours()) + ':' + (now.getMinutes() >= 10 ? now.getMinutes() : '0' + now.getMinutes());
        //+ ':' + (now.getSeconds() >= 10 ? now.getSeconds() : '0' + now.getSeconds())
        return {
            name: now.toString(),
            value: [
                valueName,
                Math.round(datas)
            ]
        }
    }
    var data = [];

    // 给定某天的00：00
    var now = +new Date('2018-07-02 00:00:00');
    var n_now = new Date(now);
    var n_day = n_now.getDate();

    // 获取当前时间
    var c_now = new Date();
    var c_day = c_now.getDate();
    var c_month = c_now.getMonth() + 1;
    var c_year = c_now.getFullYear();

    var value = Math.random() * 1000;

    if (n_day !== c_day) { //给定日期与当天日期不符
        now = +new Date(c_year + '-' + c_month + '-' + c_day + ' ' + '00:00:00');
    }
    var num = Math.floor((+new Date() - now) / 60000) <= 1 ? 1 : Math.floor((+new Date() - now) / 60000);
    for (var i = 0; i < num; i++) {
        //初始化有多少个点
        data.push(randomData());
    }
    option = {
        tooltip: {
            trigger: 'axis',
            formatter: function(params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getHours() + ':' + date.getMinutes() + ' ' + date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '模拟数据',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: data
        }]
    };
    var timer = setInterval(function() {
        // data.shift();
        if ((new Date(now)).getDate() !== c_day) { //给定日期与当天日期不符,刷新页面从新的一天开始
            window.history.go(0)
        }

        data.push(randomData());

        myChart.setOption({
            series: [{
                data: data
            }]
        });
    }, 5000);
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }



}