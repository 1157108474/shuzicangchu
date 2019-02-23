window.check = {

	units : ["吨","T","米","m","平方米","㎡","立方米","m³","升","L","千克","公斤","kg"],


    isAllowDecimal : function (pattern) {
		var units = this.units;
		var length = units.length;
      	units.map(function(val,index){
      		if(val==pattern){
      			return true;
			}
        })
		return false;
	},

    isDot: function (num) {
        var result = (num.toString()).indexOf(".");
        if(result != -1) {
            return true;
        } else {
            return false;
        }
    } ,
	
}