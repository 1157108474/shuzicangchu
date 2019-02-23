<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�������Ͻ��յ�</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote" style="margin-left:10px;margin-top: 10px">
        <%--<a href="javascript:void(0);" class="layui-btn save"   id="save" >����</a>
        <a href="javascript:void(0);" class="layui-btn commit"   id="commit">�ύ</a>
        <a href="javascript:void(0);" class="layui-btn layui-btn-danger print"  id="print">��ǩ��ӡ</a>--%>
        <c:forEach items="${button}" var="button">
            <a href="javascript:void(0);" class="layui-btn" id="${button.code}">${button.name}</a>
        </c:forEach>
    </blockquote>
    <input id="taskId" value="${taskId}" type="hidden"><%--������������--%>
    <input id="menuCode" value="${menuCode}" type="hidden">
    <span style="font-weight: bold;margin-left: 10px;margin-top: 10px">���Ͻ��յ�</span>
    <hr/>
    <div style="width:33%;float: left">
        <div class="layui-form-item">
            <label class="layui-form-label">�ɹ��������</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="�ɹ��������" class="layui-input" type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">���ź�</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="���ź�" class="layui-input" type="text" required>
            </div>
        </div>
    </div>
    <div style="width: 33%;float: left">
        <div class="layui-form-item">
            <label class="layui-form-label">��Ӧ��</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="��Ӧ��" class="layui-input" type="text" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">��������</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="��������" class="layui-input" type="text" required>
            </div>
        </div>
    </div>
    <div style="width: 33%;float: left">
        <div class="layui-form-item">
            <label class="layui-form-label">ҵ��ʵ��</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="ҵ��ʵ��" class="layui-input" type="text" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">�����֯</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="�����֯" class="layui-input" type="text">
            </div>
        </div>
    </div>
    <div style="clear: both;"></div>

    <span style="font-weight: bold;margin-left: 10px">��ϸ�б�</span>
    <hr/>

    <div class="layui-tab" style="margin-left: 20px">
        <ul class="layui-tab-title">
            <li class="layui-this">��ϸ�б�</li>
            <li>���ݸ���</li>
            <li>��������</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <blockquote class="layui-elem-quote">
                    <a href="javascript:void(0);" class="layui-btn">�����ϸ</a>
                    <a href="javascript:void(0);" class="layui-btn">ɾ����ϸ</a>
                </blockquote>
                <table id="providerList" lay-filter="providerList"></table>
            </div>
            <div class="layui-tab-item">����2</div>
            <div class="layui-tab-item">����3</div>
        </div>
    </div>

</div>
</body>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/processDetail.js"></script>
<script>
    layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var $ = layui.$;
        var element = layui.element;
        form.on("submit(formSubmit)", function (data) {
            reload(data);
            return false;
        });
        //�����б�
        table.render({
            elem: '#providerList'  //��table id
            , url: 'listProvider.json'  //��������·��
            , cellMinWidth: 80
            , height: 'full-200'
            , method: 'post'
            , cols: [[
                {checkbox: true, fixed: 'left'}
                , {field: 'code', align: 'center', title: '����', width: 100, fixed: 'left'}
                , {field: 'name', align: 'center', title: '��Ӧ������', width: 160, fixed: 'left'}
                , {field: 'address', align: 'center', title: '��Ӧ�̵�ַ', width: 160}
                , {field: 'zipCode', align: 'center', title: '��������', width: 100}
                , {field: 'contactPerson', align: 'center', title: '��ϵ��', width: 80}
                , {field: 'contractPhone', align: 'center', title: '��ϵ�绰', width: 140}
                , {field: 'fax', align: 'center', title: '����', width: 100}
                , {field: 'email', align: 'center', title: '��������', width: 140}
                , {
                    field: 'status', align: 'center', title: '�Ƿ�����', minWidth: 100, templet: function (d) {
                        if (d.status == 1) {
                            return "����";
                        } else {
                            return "����";
                        }
                    }
                }
                , {field: 'memo', align: 'center', title: '��ע'}
            ]]
            , page: true   //������ҳ
            , limit: 10   //Ĭ��ʮ������һҳ
            , limits: [10, 20, 30, 50]  //���ݷ�ҳ��
            , id: 'providerListReload'
        });
        var reload = function (data) {

            table.reload('providerListReload', {
                page: {
                    curr: 1 //���´ӵ� 1 ҳ��ʼ
                },
                where: JSON.parse(JSON.stringify(data.field))

            })
        };

        //�༭
        $("#edit").on("click", function (e) {
            var checkStatus = table.checkStatus('providerListReload');
            if (checkStatus.data.length != 1) {
                layer.msg("��ѡ��һ����¼���б༭", {
                    offset: 't',
                    anim: 6
                })
            } else {
                var data = checkStatus.data;
                layer.open({
                    type: 2,
                    title: '�༭��Ӧ��',
                    fixed: false,
                    area: ['60%', '90%'],
                    content: "/system/provider/updateProvider.htm?id=" + data[0].id
                })
            }
        });

        //����
        $("#add").on("click", function (e) {
            layer.open({
                type: 2,
                title: '������Ӧ��',
                //shadeClose: true,
                //shade: 0.8,
                fixed: false,
                area: ['60%', '90%'],
                content: "/system/provider/updateProvider.htm"
            })
        });

        $("#delete").on("click", function (e) {
            var checkStatus = table.checkStatus('providerListReload');
            var datas = checkStatus.data;
            var arr = [];
            if (datas == "") {
                layer.msg("��ѡ��һ����¼����ɾ��", {
                    offset: 't',
                    anim: 6
                });
            } else {
                layer.confirm('��ȷ��Ҫɾ����', {
                    icon: 3, title: '��ʾ��Ϣ', offset: '35%',
                    btn: ['ȷ��', 'ȡ��'] //��ť
                }, function () {
                    for (var i = 0; i < datas.length; i++) {
                        arr.push(datas[i].id);
                    }
                    var url = "delProvider.json?ids=" + arr;
                    $.post(url, function (data) {
                        if (data.success == true) {
                            layer.msg('ɾ���ɹ�', {
                                time: 2000,
                                end: function () {
                                    location.href = 'manageProvider.htm'
                                }
                            });

                        } else {
                            layer.alert("ɾ��ʧ��!", {
                                offset: '35%'
                            });
                        }
                    })
                }, function () {
                    layer.closeAll();
                });
            }
        })
    })


</script>

</html>