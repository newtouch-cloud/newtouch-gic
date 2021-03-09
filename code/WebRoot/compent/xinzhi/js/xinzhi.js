/*$(function(){
	tabControl();    //选项卡
	pageTab(); //页面选项卡
	lineSkip() //页面跳转
});*/

/* 选项卡 */
function tabControl(){
	$('.tab_title').click(function(){
		$(this).parent().find('.tab_title').removeClass('visited');
		$(this).addClass('visited');
		$(this).parents('.tab_control').find('.tab_content').hide();
		$(this).parents('.tab_control').find('.tab_content').eq($(this).index()).show();
		try{
			parent.dyniframesize();
		}catch(e){
			
		}
	});
}

function pageTab(){
	$('.page_tab_title').click(function(){
		$(this).parent().find('.page_tab_title').removeClass('visited');
		$(this).addClass('visited');
		$(this).parents('.page_tab').find('.page_tab_content').hide();
		$(this).parents('.page_tab').find('.page_tab_content').eq($(this).index()).show();
		try{
			dyniframesize();
		}catch(e){
			
		}
	});	
}


function lineSkip(){
	var aPageTabTitleLength = 0;
	
	$('#sidebar').find('.submenu a').click(function(){
		var oB = $(this).find('b').length;   //判断是否末级菜单
		var aPageTabTitleIdLength = $('.page_tab_titles').find('#t_' + $(this).attr('id')).length; 
		var link = $(this).attr('name');
		if(oB==0){
			if(aPageTabTitleIdLength==0){
				var titleArray = $(".page_tab_titles").find("li");
				var titleWidth = 0;
				for(var i=0;i < titleArray.length;i++){
					titleWidth += $(titleArray[i]).width();
				}
				
				$('.page_tab_title').removeClass('visited');
				$('.page_tab_content').hide();
				$('.page_tab_titles ul').append(
					'<li class="page_tab_title visited" id="t_'+ $(this).attr('id') +'">'
						+ '<span class="title">'
							+ $(this).text() 
						+'</span>'
						+ '<span class="close">&times;</span>'
					+ '</li>'
				);
				
				$('.page_tab').append(
					'<div class="page_tab_content">'
						+ '<iframe class="iframe" id="ffame" name="mainIFrame" width="100%" height="800" onload="dyniframesize()" frameborder="0" src="'
							+ link
						+'" allowtransparency="true"></iframe>'
					+ '</div>'
				);
				
				aPageTabTitleLength = $('.page_tab_title').length;
				pageTab();
				titleWidth += $('#t_' + $(this).attr('id')).width();
				try{
					dyniframesize();
				}catch(e){
					
				}
			}else{
				$('.page_tab_title').removeClass('visited');
				$('#t_' + $(this).attr('id')).addClass('visited');
				$('.page_tab_content').hide();
				$('.page_tab_content').eq($('#t_' + $(this).attr('id')).index()).show();
			}
			
		}
		
		$('.page_tab_title .close').click(function(event){
			event.stopPropagation(); // 阻止冒泡
			aPageTabTitleLength = $('.page_tab_title').length;
			var oIndex = $(this).parents('.page_tab_title').index();
			//首页tab禁止删除
			if(oIndex == 0){
				return;
			}
			if(aPageTabTitleLength > 1){
				$(this).parents('.page_tab').find('.page_tab_content').eq(oIndex).remove();
				$('.page_tab_content').eq(0).show();
				$(this).parents('.page_tab_title').remove();
				$('.page_tab_title').removeClass('visited');
				$('.page_tab_title').eq(0).addClass('visited');
			}
			try{
				dyniframesize();
			}catch(e){
				
			}
		});
		
	});
}





















