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
        elem: '#querygrid'
        , url: 'listQueryCKCX.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {title: '�鿴', align: "center",fixed: "left", toolbar: '#bar', width: 80}
                , {field: 'code', title: '���ݱ��',fixed: "left", align: "center", width: 140}
                , {field: 'materialcode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'materialname', title: '��������', align: "center", width: 100}
                , {field: 'materialspecification', title: '���Ϲ��', align: "center", width: 100}
                , {field: 'materialmodel', title: '�����ͺ�', align: "center", width: 140}
                , {field: 'usdedepname', title: '���쵥λ', align: "center", width: 200}
                , {field: 'detailcount', title: '����', align: "center", width: 100}
                , {field: 'kindName', title: '����', align: "center", width: 100}
                , {field: 'description', title: '����˵��', align: "center", width: 200}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ztid: $("#ztid").val(),
                code: $("#code").val(),
                storeid: $("#storeid").val(),
                useddepartid:$("#usedDepartId").val(),
                materialname:$("#materialname").val(),
                materialcode:$("#materialcode").val()
            }
        });
    };
    //����������
    table.on('tool(querygrid)', function (obj) {
        var data = obj.data;
        if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: "/sheet/ck/"+data.sheetid+"?oper=show", //��ַ
                title: "�鿴���ʳ�����ϸ"
            });
        }
    });

    $("#export").click(function () {
        window.location.href="/sheet/query/exportCKExcel.htm?code="+$("#code").val()+"&storeid="+$("#storeid").val()+
            "&materialcode="+$("#materialcode").val()+"&materialname="+$("#materialname").val()
    });

    //��֯���ŵ���
   /* $("#departBtn").on("click", function () {
        layui.layer.open({
            type: 2,
            title: '��֯����',
            fixed: false,
            area: ['38%', '75%'],
            content: "../../system/user/publicDepart.htm",
            // scrollbar:false
        });
        return false;
    });*/

    //����ʹ�õ�λҳ��
    $("#openUseDep").on("click", function () {
        if($("#ztid").val()==""){
            alert("����ѡ����!");
            return false;
        }
        layui.layer.open({
            type: 2,
            title: '���쵥λ',
            fixed: false,
            area: ['500px', '80%'],
            content: "../../system/useDep/generalUseDep.htm?ztId=" + $("#ztid").val(),
            // scrollbar:false
        });
        return false;
    });

    $("#reset").click(function () {
        $("#usedDepartId").val("");
    });
});
//��ҳ��
function openPage(tit, url, width, height) {
    layui.layer.open({
        title: tit
        , type: 2
        , fixed: false
        , area: [width, height]
        , content: url
    })
}
function obtainPart(id, name, ztId, code) {
    document.getElementById("departName").value = name;
    document.getElementById("departId").value = id;
}

//��ȡʹ�õ�λ����ֵ
function UseDepGeneral(data) {
    document.getElementById("useDepName").value = data[0].name;
    document.getElementById("usedDepartId").value = data[0].id;
}