<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">
    <div class="layui-colla-content layui-show">
        <form class="layui-form " action="">
            <table>
                <tr>
                    <td>
                        <div class="layui-form-item">
                            <label class="layui-form-label">输入盘点数量：</label>
                            <div class="layui-input-block">
                                <input type="hidden" id="id" value="${id}">
                                <input type="hidden" id="sysCount" value="${sysCount}">
                                <textarea   rows="3" cols="20" type="text" class="layui-textarea" name="detailCount" id="detailCount" ></textarea>
                            </div>
                        </div>
                    </td>
                </tr>

            </table>
            <div style=" margin: auto;width: 100%">
                <button style="float: right;margin-right: 25%;margin-top: 30px;height: 30px;line-height: 30px;width: 50px" class="layui-btn bill-td-button " type="reset" id="reset">取消</button>
                <button style="float: right;margin-right: 25%;margin-top: 30px;height: 30px;line-height: 30px;width: 50px" class="layui-btn bill-td-button" id="saveDetail">保存</button>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'table', 'layer'], function () {
        var table = layui.table;
        var $ = layui.$;
        var form = layui.form;
        var layer = layui.layer;

    $("#saveDetail").on("click", function (e) {
        var val = $("#detailCount").val();
        var regu = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
        if (regu.test(val)) {
            $.post("editDetail.json", {
                    'id': $("#id").val(),
                    'detailCount': $("#detailCount").val(),
                    'sysCount': $("#sysCount").val()
                },
                function (ret) {
                    layer.msg(ret.message, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                    /*if(ret.status == '1'){
                        layer.msg( ret.message, function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }else{
                        layer.alert(ret.message);
                    }*/
                }, "JSON");
        } else {
            layer.alert('盘点数量只能输入正数！');
        }
        return false;
    })
    $("#reset").on("click", function (e) {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    })
</script>
</html>

