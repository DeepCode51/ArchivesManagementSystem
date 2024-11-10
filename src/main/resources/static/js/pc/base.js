

/*首页计算 差多少天*/
function formartTime(lable){
	var now = new Date();
	var items = $("#"+lable+"").find(".fly-list-item");
	console.log(items);
	
	for(var i=0;i<items.size();i++){
		var time = $(items[i]).find(".fly-list-time").html();
		time = time.replace(/-/g, '/'); // "2010/08/01";
		//创建日期对象
		var blogDate = new Date(time);
		
		var date3=now.getTime()-blogDate.getTime()  //时间差的毫秒数
		console.log(date3);
		//计算出相差天数
		var days=Math.floor(date3/(24*3600*1000));
		//计算出小时数
		var leave1=date3%(24*3600*1000);
		//计算天数后剩余的毫秒数
		var hours=Math.floor(leave1/(3600*1000))
		//计算相差分钟数
		var leave2=leave1%(3600*1000)        
		//计算小时数后剩余的毫秒数
		var minutes=Math.floor(leave2/(60*1000))
		//计算相差秒数
		var leave3=leave2%(60*1000)      
		//计算分钟数后剩余的毫秒数
		var seconds=Math.round(leave3/1000)
		console.log(" 相差 "+days+"天 "+hours+"小时 "+minutes+" 分钟"+seconds+" 秒")
		
		if(days>0){
			if(days>20){
			}else{
				$(items[i]).find(".fly-list-time").html(days+"天前");
			}
		}else if(hours>0){
			$(items[i]).find(".fly-list-time").html(hours+"小时前");
		}else if(minutes>0){
			$(items[i]).find(".fly-list-time").html(minutes+"分钟前");
		}else{
			$(items[i]).find(".fly-list-time").html("刚刚");
		}
	}
}
/*首页计算 差多少天*/


