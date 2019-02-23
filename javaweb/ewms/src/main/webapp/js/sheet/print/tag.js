layui.use(['laypage', 'layer'], function(){
    var laypage = layui.laypage
        ,layer = layui.layer;
    var $= layui.$;
    var data = parent.layui.table.checkStatus('querygridTable').data;
    var num = parseInt($("#num").val());
    var index;
 //   �Զ�����ҳ��βҳ����һҳ����һҳ�ı�
    laypage.render({
        elem: 'page'
        ,count: num
        ,limit:1
        ,first: '1'
        ,last: num
        ,prev: '<em>��</em>'
        ,next: '<em>��</em>'
        ,jump:function(obj, first){
            index = obj.curr-1;
            // $('img').attr("alt",data[index].code)
            $('img').attr("src","print2D/"+data[index].materialcode);
            $('#materialcode').text(data[index].materialcode);
            $('#description').text(data[index].description);
            $.ajax({
                type: "POST",
                url: "/sheet/query/getDetailunitname.htm",
                data: {"materialcode":data[index].materialcode,"storeid":data[index].storeid},
                success: function(data){
                    $('#detailunitname').text(data);
                }
            });
        }
    });

    $("#print").on("click",function () {

        if(getExplorer() == "IE"){
            pagesetup_null();
        }
        $(".noPrint").hide();
        window.print();
        $(".noPrint").show();

    })



    function pagesetup_null(){
        var hkey_root,hkey_path,hkey_key;
        hkey_root="HKEY_CURRENT_USER";
        hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
        try{
            var RegWsh = new ActiveXObject("WScript.Shell");
            hkey_key="header";
            RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
            hkey_key="footer";
            RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
        }catch(e){}
    }

    var getExplorer =function () {
        var explorer = window.navigator.userAgent ;
        //ie
        if (explorer.indexOf("MSIE") >= 0) {
            return "IE";
        }
        //firefox
        else if (explorer.indexOf("Firefox") >= 0) {
            return "Firefox";
        }
        //Chrome
        else if(explorer.indexOf("Chrome") >= 0){
            return "Chrome";
        }
        //Opera
        else if(explorer.indexOf("Opera") >= 0){
            return "Opera";
        }
        //Safari
        else if(explorer.indexOf("Safari") >= 0){
            return "Safari";
        }
    }

});
