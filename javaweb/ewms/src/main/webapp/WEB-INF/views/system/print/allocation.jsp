<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>

</head>
<body>

<div class="admin-main" style="margin-left: 10px;width:80%;">
    <input id="num" value="${num}" type="hidden">
    <div>
    <div class="noPrint">
          <a href="javascript:;" class="layui-btn  layui-btn-small" id="print" style="height: 30px;line-height: 30px;margin-bottom: 1px">打印</a>&nbsp;<span id="page"></span>
    </div>
        <div >
              <table class="layui-table" style="height:100px;width:300px" >
                  <tr >
                      <td colspan="2" height="50px" align="center"><img style="max-width: 300px;height:50px"/></td>
                  </tr>
                  <tr>
                      <td  style="font-size:16px;font-weight: 400;font-style: normal;font-family: 宋体; width:70px;color:black">&nbsp;编码</td>
                      <td style="font-size:16px;font-weight: 400;font-style: normal;font-family: 宋体;color:black"><span id="code"></span></td>
                  </tr>
                  <tr style="height:50px;">
                      <td  style="font-size:16px;font-weight: 400;font-style: normal;font-family: 宋体; width:70px;color:black">&nbsp;库位</td>
                      <td style="font-size:16px;font-weight: 400;font-style: normal;font-family: 宋体;color:black"><span id="storageLocation"></span></td>
                  </tr>
                  <tr style="height:50px;">
                      <td  style="font-size:16px;font-weight: 400;font-style: normal;font-family: 宋体; width:70px;color:black">&nbsp;库房</td>
                      <td style="font-size:16px;font-weight: 400;font-style: normal;font-family: 宋体;color:black"><span id="ware"></span></td>
                  </tr>
              </table>

        </div>
         <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>

<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/print/allocation.js"></script>

</html>