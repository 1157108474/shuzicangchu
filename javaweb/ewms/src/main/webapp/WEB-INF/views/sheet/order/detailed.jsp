<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>添加明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">
    <div class="layui-colla-content layui-show">
        <input type="hidden" id="ordernum" value="${orderNum}">
        <form class="layui-form " action="">
            <table>
                <tr>
                    <td>
                        <div class="layui-form-item">
                            <label class="layui-form-label">物料编码：</label>
                            <div class="layui-input-block">
                                <input type="text" name="materialCode" class="layui-input" style="margin-left: -10px">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item">
                            <label class="layui-form-label">物料描述：</label>
                            <div class="layui-input-block">
                                <input type="text" name="description" class="layui-input">
                            </div>
                        </div>
                    </td>
                    <td class="bill-td">
                        <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit">查询
                        </button>
                        <button type="reset" class="layui-btn bill-td-button">重置</button>
                        <button class="layui-btn bill-td-button" id="add">添加</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div>
        <table id="detailGrid" lay-filter="detailGrid"></table>
    </div>
</div>
<script type="text/html" id="orEquipment">

    {{# if(d.isEnableSN == 'true'){ }}
    {{#     if(d.enableSn == '1'){ }}
    <input type="checkbox" checked disabled="false" name="{{ d.id }}" id="isEquipment{{ d.id }}" lay-skin="primary"
           value="1" lay-filter="singleCheck">
    {{#           $("#enableSn"+d.id).attr("disabled",true); }}
    {{#     }else if(d.enableSn == '0'){  }}
    {{#         if(d.isEquipment == '1'){ }}
    <input type="checkbox" checked name="{{ d.id }}" id="isEquipment{{ d.id }}" lay-skin="primary" value="1"
           lay-filter="singleCheck">
    {{#         }else if(d.isEquipment == '0'){ }}
    <input type="checkbox" name="{{ d.id }}" id="isEquipment{{ d.id }}" lay-skin="primary" value="0"
           lay-filter="singleCheck">
    {{#         } }}
    {{#     } }}
    {{# }else{  }}
    {{#     if(d.enableSn == '1'){ }}
    <input type="checkbox" name="{{ d.id }}" checked id="isEquipment{{ d.id }}" lay-skin="primary"
           value="1" lay-filter="singleCheck">
    {{#     }else if(d.enableSn == '0'){ }}
    {{#         if(d.isEquipment == '1'){ }}
    <input type="checkbox" name="{{ d.id }}" checked id="isEquipment{{ d.id }}" lay-skin="primary"
           value="1" lay-filter="singleCheck">
    {{#         }else if(d.isEquipment == '0'){ }}
    <input type="checkbox" name="{{ d.id }}" id="isEquipment{{ d.id }}" lay-skin="primary" value="0"
           lay-filter="singleCheck">
    {{#         } }}
    {{#     } }}
    {{# } }}
</script>
<script type="text/html" id="orEnableSn">
    {{# if(d.enableSn == '1'){ }}
    {{#     if(d.isEquipment == '1'){ }}
    <input type="checkbox" name="enableSn{{ d.id }}" checked id="enableSn{{ d.id }}" value="1" lay-skin="primary"
           lay-filter="singCheck">
    {{#     }else if(d.isEquipment == '0'){ }}
    <input type="checkbox" name="enableSn{{ d.id }}" checked id="enableSn{{ d.id }}" disabled="false" value="1"
           lay-filter="singCheck" lay-skin="primary">
    {{#     } }}
    {{# }else if(d.enableSn == '0'){ }}
    {{#     if(d.isEquipment == '1'){ }}
    <input type="checkbox" name="enableSn{{ d.id }}" id="enableSn{{ d.id }}" value="0" lay-skin="primary"
           lay-filter="singCheck">
    {{#     }else if(d.isEquipment == '0'){ }}
    <input type="checkbox" name="enableSn{{ d.id }}" id="enableSn{{ d.id }}" value="0" disabled="false"
           lay-filter="singCheck"
           lay-skin="primary">
    {{#     } }}
    {{# } }}
</script>
</body>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/order/addDetails.js"></script>
</html>

