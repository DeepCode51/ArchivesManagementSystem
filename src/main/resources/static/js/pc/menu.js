jQuery(document).ready(function($) {
    var timer = null, timer2 = null, currentEvent = null;
	if ($('html').width() > 640) {
		currentEvent = 'mousemove';
	} else {
		currentEvent = 'click';
	}
    $('header').unbind('click', 'mousemove').bind(currentEvent, function(e) {
    	e.stopPropagation();
    	if ($(e.target).is('a') && $(e.target).next().hasClass('subnav')) {
	    	clearTimeout(timer);
	    	timer = null;
	    	clearTimeout(timer2);
	    	timer2 = null;
    		$('.subnavPanel').removeClass().addClass('subnavPanel').hide();
    		//$('.subnav').hide();
    		//if ($(e.target).next().attr('data-class')) {
    		//	$('.subnavPanel').addClass($(e.target).next().attr('data-class'));
    		//}
    		$('.subnavPanel').show();
    		$('.subnav').hide();
    		$(e.target).next().show();
    	} else if ($(e.target).is('a') && !$(e.target).parents('.subnav').length) {
	    		$('.subnavPanel').removeClass().addClass('subnavPanel').hide();
	    		$('.subnav').hide();
    	} else if ($(e.target).is('a') && $(e.target).parents('.subnav').length || $(e.target).is('.subnavPanel')) {
	    	clearTimeout(timer);
	    	timer = null;
	    	clearTimeout(timer2);
	    	timer2 = null;
    	}
		if (currentEvent == 'mousemove' && !$(e.target).is('a')) {
    		if (timer == null) {
    			timer = setTimeout(function() {
		    		$('.subnavPanel').removeClass().addClass('subnavPanel').hide();
		    		$('.subnav').hide();
    			}, 1000);
    		}
    	}
    });
	if (currentEvent == 'mousemove') {
		$('.subnav, .subnavPanel').unbind('mouseout').bind('mouseout', function(e) {
			e.stopPropagation();
			if (timer2 == null) {
				timer2 = setTimeout(function() {
					$('.subnavPanel').removeClass().addClass('subnavPanel').hide();
					$('.subnav').hide();
				}, 500);
			}
		});
	}

	function setNav() {
	    if ($('html').width() > 640){
    		$('nav ul').eq(0).css('height', 'auto');
    		$('.nav_icon').unbind('click');
			$('.topmenu').not('.displayPc, .displayMobile').each(function() {
				$(this).attr('href', $(this).attr('data-href'));
			});
    	} else {
		    var h = $(window).height();
		    var isSlide = false;
		    $('nav ul').eq(0).css('overflow', 'auto');
		    $('.nav_icon').click(function() {
		    	//$('html').toggleClass('slideLeft');
		    	if (!isSlide) {
			    	$('body, header').animate({'left': '-79.5%'}, 500);
			    	$('header nav ul').animate({'left': '20%'}, 500);
			    	isSlide = true;
		    	} else {
			    	$('body, header').animate({'left': '0'}, 500);
			    	$('header nav ul').animate({'left': '100%'}, 500);
			    	isSlide = false;
		    	}
		    });
			$('.topmenu').not('.displayPc, .displayMobile').each(function() {
				$(this).attr('href', 'javascript:;');
			});
    	}
	}
	setNav();

    $(window).resize(function() {
    	setNav();
    });

	$('#gototop').click(function() {
		$("body,html").animate({scrollTop: 0}, 500);
	});

	$('.wechat_weibo').hover(function(){
		$('.wechat_weibo p').show();
	}, function(){
		$('.wechat_weibo p').hide();
	});

    $('.friends').hover(function(e) {
        $('#friends_pop').show();
    }, function(e) {
        $('#friends_pop').hide();
    });

	// DeviceOrientation ///////////
	var supportOrientation=(typeof window.orientation == "number" && typeof window.onorientationchange == "object");
	
	var updateOrientation=function(){
		if(supportOrientation){
			updateOrientation=function(){
				var orientation=window.orientation;
				switch(orientation){
					case 90:
					case -90:
						$('html').addClass('viewport');
						orientation="block";  //landscape
						break;
					default:
						$('html').removeClass('viewport');
						orientation="none";  //portrait
				}
				document.getElementById("orientLayer").style.display = orientation;
			};
		}else{
			// updateOrientation=function(){
			// 	var orientation=(window.innerWidth > window.innerHeight)? "block":"none";
			// 	orientation == 'block' ? $('html').addClass('viewport') : $('html').removeClass('viewport');
			// 	document.getElementById("orientLayer").style.display = orientation;
			// };
		}
	};

	updateOrientation();
	if(supportOrientation){
		window.addEventListener("orientationchange",updateOrientation,false);
	}else{
		//window.setInterval(updateOrientation,3000);
	}
});
