var w,h;

$(function(){
	initCutImg();
});

function initCutImg(){
	w = $("#w").val();
	h = $("#h").val();
	w = parseInt(w)+2;
	h = parseInt(h)+2;
	
	$(".thumbBox").css("width",w+"px");
	$(".thumbBox").css("margin-left",(0-w/2)+"px");
	$(".thumbBox").css("height",h+"px");
	$(".thumbBox").css("margin-top",(0-h/2)+"px");
	
	$(".imageBox").css("height",h+"px");
}


//点击图片  弹出lay的窗口
function openImg(data){
	var temp_w = parseInt(w)+50;
	var temp_h = parseInt(h)+50;
	
	layer.open({
		  type: 2,
		  title: '查看裁剪图片',
		  shadeClose: true,
		  shade: 0.8,
		  area: [temp_w+'px',temp_h+'px'],
		  content: data
	});
}

$(window).load(function () {
        var options = {
				    thumbBox: '.thumbBox',
				    spinner: '.spinner',
				    imgSrc: '/static/a1a2.png'
        }
        
            var cropper = $('.imageBox').cropbox(options);
            $('#upload-file').on('change', function () {
                var reader = new FileReader();
                reader.onload = function (e) {
                    options.imgSrc = e.target.result;
                    cropper = $('.imageBox').cropbox(options);
                }
                reader.readAsDataURL(this.files[0]);
                //this.files = []; 这一行会报错。暂时注释-ch
            });
            
            $('#btnCrop').on('click', function () {
            	//"data:image/png;base64,iVBORw0KGgoAAA后面非常多的字符，就是imgData
            	//可以在浏览器直接打开 观看
                var imgData = cropper.getDataURL();
            	app.imgData = imgData;
            	
               /*$('.cropped').html('');
                $('.cropped').append('<img onclick="openImg(\'' + imgData + '\')" src="' + imgData + '" align="absmiddle" style="margin-top:4px;box-shadow:0px 0px 12px #7E7E7E;" >');
                */
                //$('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
                //$('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
                $("#image_url_div").empty();
            	$("#image_url_div").append('<img style="width: 200px;"  src="'+imgData+'" />');
            	
            	//把imgData上传后台，存服务器。
            	upload_imgData();
            });
            
            $('#btnZoomIn').on('click', function () {
                cropper.zoomIn();
            })
            $('#btnZoomOut').on('click', function () {
                cropper.zoomOut();
            })
            $('#btnSubmit').on('click', function () {
                //$('#circularG').show();
                var img = cropper.getDataURL().replace('data:image/png;base64,', '');
                console.log(img);
                $.post(save_url, {base64 : img}, function(result) {
        			if (result.success) {
        				//调用 父窗口的  关闭所有窗口 并且刷新 页面
        				window.parent.closeDlg(result.msg);
        			}
        		},"json");
            })
        });



