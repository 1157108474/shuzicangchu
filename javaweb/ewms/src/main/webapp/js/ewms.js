//�ӿڴ�����ʽ
function triggerName(code) {
    var msg ='';
    switch(code)
    {
        case 1:
            msg = 'ʵʱ';
            break;
        case 2:
            msg = '�ֶ�';
            break;
        case 3:
            msg = '��ʱ';
            break;
        case 4:
            msg = '����';
            break;
    }
    return msg;
}
//�ӿ�����
function kindName(code) {
    var msg ='';
    switch(code)
    {
        case 1:
            msg = '�ṩ��';
            break;
        case 2:
            msg = '���÷�';
            break;
    }
    return msg;
}

//ͬ����ʽ
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

//״̬����
function statusDesc(code) {
    var msg ='';
    switch(code)
    {
        case 0:
            msg = '����';
            break;
        case 1:
            msg = '����';
            break;
    }
    return msg;
}

function yesOrNo(code) {
    var msg ='';
    switch(code)
    {
        case 0:
            msg = '��';
            break;
        case 1:
            msg = '��';
            break;
    }
    return msg;
}



function propertyName(code) {
    var msg ='';
    switch(code)
    {
        case 1:
            msg = '�ⷿ';
            break;
        case 2:
            msg = '����';
            break;
        case 3:
            msg = '����';
            break;
        case 4:
            msg = '��λ';
            break;
    }
    return msg;
}