layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element','vip_table'], function () {
	var vipTable = layui.vip_table;
//	window.location.href='applyExcel.json';
	var url = "/system/print/sheet/apply";
    vipTable.openPage("��ӡ���յ�", url, '1200px', '500px');
});