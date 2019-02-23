//返回年-月-日型的日期
function dateformat(longdate) {
	if(null==longdate||''==longdate){
		return '';
	}else {
	  var date = new Date(longdate);
	  var year = date.getFullYear();
	  var month = date.getMonth() + 1;
	  var day = date.getDate();
	  return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
    }
}
//返回年-月-日 时：分：秒型的日期
function datetimeformat(longdate) {
    if(null==longdate||''==longdate){
        return '';
    }else {
	  var date = new Date(longdate);
	  var year = date.getFullYear();
	  var month = date.getMonth() + 1;
	  var day = date.getDate();
	  var hour = date.getHours();
	  var minute = date.getMinutes();
	  var seconds = date.getSeconds();
	  return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) +" "+(hour < 10 ? "0" + hour : hour)+":"+(minute < 10 ? "0" + minute : minute)+":"+(seconds < 10 ? "0" + seconds : seconds);
    }
}