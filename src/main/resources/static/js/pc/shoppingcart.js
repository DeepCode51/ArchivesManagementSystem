
//初始化每一个商品的   选中 和非选中 的点击事件。
function init_click_select(){
	var itemList = $(".item");
	console.log(itemList.length);
	for(var i=0;i<itemList.length;i++){
		var img = $(itemList[i]).find(".img");
		
		$(img).unbind("click");//这里需要 解除 绑定，如果不解除，会有错误 。 点击没有反应。
		$(img).click(function(){
			//console.log(this);
			var item = $(this).parent();
			var select = $(item).attr("select");
			console.log(select);
			if(select==0){
				$(this).find("img").attr("src","/image/pc/shopping/selected.png");
				$(item).attr("select",1);
			}else{
				$(this).find("img").attr("src","/image/pc/shopping/select.png");
				$(item).attr("select",0);
			}
			
			//判断  全选的状态是否正确。  以及检测数量和合计
			check_heji_select();
			
		});
	}
}

//初始化商品的添加  减少按钮操作
function init_item_sub_add(){
	var itemList = $(".item");
	for(var i=0;i<itemList.length;i++){
		var btn_sub = $(itemList[i]).find("#btn_sub");
		var btn_add = $(itemList[i]).find("#btn_add");
		
		$(btn_sub).unbind("click");//这里需要 解除 绑定 
		$(btn_sub).click(function(){
			var input = $(this).siblings("input");
			var curr_num = input.val();
			curr_num = parseInt(curr_num)-1;
			if(curr_num<1){
				curr_num=1;
			}
			input.val(curr_num);
			var item = $(this).parents(".item");
			//console.log(item);
			$(item).attr("num",curr_num);
			//判断  全选的状态是否正确。  以及检测数量和合计
			check_heji_select();
		});
		
		$(btn_add).unbind("click");//这里需要 解除 绑定 
		$(btn_add).click(function(){
			var input = $(this).siblings("input");
			var curr_num = input.val();
			curr_num = parseInt(curr_num)+1;
			input.val(curr_num)
			var item = $(this).parents(".item");
			//console.log(item);
			$(item).attr("num",curr_num);
			
			//判断  全选的状态是否正确。  以及检测数量和合计
			check_heji_select();
		});
	}
}



//全选的点击事件
function click_select_all(){
	//判断 有没有全选    true全选    false没有全选
	if(check_all_select()){
		//取消全选
		cancel_select_all();
	}else{
		//执行全选
		act_select_all();
	}
	
	//判断  全选的状态是否正确。  以及检测数量和合计
	check_heji_select();
}

//取消全选
function cancel_select_all(){
	var itemList = $(".item");
	//console.log(itemList.length);
	for(var i=0;i<itemList.length;i++){
		var img = $(itemList[i]).find(".img");
		$(img).find("img").attr("src","/image/pc/shopping/select.png");
		$(itemList[i]).attr("select",0);
	}
}

//执行全选
function act_select_all(){
	var itemList = $(".item");
	//console.log(itemList.length);
	for(var i=0;i<itemList.length;i++){
		var img = $(itemList[i]).find(".img");
		$(img).find("img").attr("src","/image/pc/shopping/selected.png");
		$(itemList[i]).attr("select",1);
	}
}

//判断 有没有全选    true全选    false没有全选
function check_all_select(){
	var itemList = $(".item");
	for(var i=0;i<itemList.length;i++){
		var select = $(itemList[i]).attr("select");
		if(select==0){
			return false;
		}
	}
	return true;
}

//判断  全选的状态是否正确。  以及检测数量和合计
function check_heji_select(){
	var jiesuan_img = $(".jiesuan_img").find("img");
	if(check_all_select()){
		//是全选
		$(jiesuan_img).attr("src","/image/pc/shopping/selected.png");
	}else{
		//不是全选
		$(jiesuan_img).attr("src","/image/pc/shopping/select.png");
	}
	//检测合计和数量
	
	var itemList = $(".item");
	var total_num = 0;
	var total_heji = 0 ;
	for(var i=0;i<itemList.length;i++){
		var select = $(itemList[i]).attr("select");
		if(select==1){
			var num = $(itemList[i]).attr("num");
			var price = $(itemList[i]).attr("price");
			var heji = (parseFloat(num) * parseFloat(price)).toFixed(2);
			total_heji = (parseFloat(total_heji) + parseFloat(heji)).toFixed(2);
			total_num = parseInt(total_num)+ parseInt(num);
		}
	}
	console.log("total_heji:"+total_heji)
	console.log("total_num:"+total_num)
	$("#total_heji").html("¥"+total_heji);
	$("#total_num").html(total_num);
}


//提交结算
function jiesuan(){
	// 遍历商品和数量 
	var itemList = $(".item");
	//console.log(itemList.length);
	var data = "";
	for(var i=0;i<itemList.length;i++){
		var select = $(itemList[i]).attr("select");
		if(select==1){
			var goodsId = $(itemList[i]).attr("id");
			var num = $(itemList[i]).attr("num");
			data = data + goodsId+"_"+num+","
		}
	}
	data = data.substring(0,data.length-1);
	// see?data=11_1   商品id_数量
	if(data.length>1){
		location.replace("/order/see?data="+data);
	}else{
		layer.msg('请选择商品');
	}
	
}

var page=1;
var limit = 2;
// 加载更多数据
function load_more_data(){
	$("#loadData").show();
	$.ajax({
        type:"POST", 
        url:"/shopping/cart/list?page="+page+"&limit="+limit, 
        async:false,
        dataType:"json", 
        success:function(result){
     	    layer.closeAll();
   			if(result.data.length>0){
   				page =page+1;
   				loadData(result);
   			}else{
   				 //提示没有更多数据
   				  layer.open({
   				    content: '没有更多数据'
   				    ,skin: 'msg'
   				    ,time: 2 //2秒后自动关闭
   				  });
   			}
	   			
       }
	 });
	$("#loadData").hide();
	
	//重新初始化一边
	init_item_sub_add();
	init_click_select();
}


function loadData(result){
	for(var i=0;i<result.data.length;i++){
		$(".list").append(
				'<div class="item" id="'+result.data[i].goods.id+'" select="0" num="1" price="'+result.data[i].goods.currPrice+'">'+
				'<div class="img" >'+
					'<img src="/image/pc/shopping/select.png">'+
				'</div>'+
				'<img class="logo" src="'+result.data[i].goods.imageUrl+'" />'+
				'<div class="info">'+
					'<div class="title">'+result.data[i].goods.title+'</div>'+
					'<div class="price">'+
						'<span style="font-size: 13px;">¥</span>'+
						'<span>'+result.data[i].goods.currPrice+'</span>'+
						'<span style="font-size: 13px; margin-left: 8px; text-decoration: line-through; color: #8c8c8c;" >¥'+result.data[i].goods.price+'</span>'+
						'<div class="num">'+
							'<span id="btn_sub" class="btn">-</span>'+
							'<input  type="text" value="1" />'+
							'<span id="btn_add" class="btn">+</span>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>');
	}
}
