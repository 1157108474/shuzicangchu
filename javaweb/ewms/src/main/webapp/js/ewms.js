//接口触发方式
function triggerName(code) {
    var msg ='';
    switch(code)
    {
        case 1:
            msg = '实时';
            break;
        case 2:
            msg = '手动';
            break;
        case 3:
            msg = '定时';
            break;
        case 4:
            msg = '其他';
            break;
    }
    return msg;
}
//接口种类
function kindName(code) {
    var msg ='';
    switch(code)
    {
        case 1:
            msg = '提供方';
            break;
        case 2:
            msg = '调用方';
            break;
    }
    return msg;
}

//同步方式
function syncName(code) {
    var msg ='';
    switch(code)
    {
        case 1:
            msg = 'WebService';
            break;
        case 2:
            msg = 'WebApi';
            break;
        case 3:
            msg = 'FileExport';
            break;
        case 4:
            msg = 'dbLink';
            break;
        case 5:
            msg = 'other';
            break;
    }
    return msg;
}

//状态描述
function statusDesc(code) {
    var msg ='';
    switch(code)
    {
        case 0:
            msg = '禁用';
            break;
        case 1:
            msg = '启用';
            break;
    }
    return msg;
}

function yesOrNo(code) {
    var msg ='';
    switch(code)
    {
        case 0:
            msg = '否';
            break;
        case 1:
            msg = '是';
            break;
    }
    return msg;
}



function propertyName(code) {
    var msg ='';
    switch(code)
    {
        case 1:
            msg = '库房';
            break;
        case 2:
            msg = '库区';
            break;
        case 3:
            msg = '货架';
            break;
        case 4:
            msg = '货位';
            break;
    }
    return msg;
}