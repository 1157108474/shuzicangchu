layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element','vip_table'], function () {
	var vipTable = layui.vip_table;
//	window.location.href='applyExcel.json';
	var url = "/system/print/sheet/apply";
    vipTable.openPage("打印接收单", url, '1200px', '500px');
});