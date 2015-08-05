var wenwen = {
		window : function(tit,text) {
			var content = $('<div class="bootbox modal fade in" tabindex="-1" role="dialog" style="display: block;" aria-hidden="false">');
			
			var html = '<div class="modal-dialog">' +
							'<div class="modal-content">'+
								'<div class="modal-header">'+
									'<button type="button" class="bootbox-close-button close" onclick=wenwen.close()>×</button>'+
									'<h4 class="modal-title">'+tit+'</h4>'+
								'</div>'+
								text+ 
								/*'<div class="modal-body">'+
									'<div class="bootbox-body">'+
										title+
									'</div>'+
								'</div>'+
								'<div class="modal-footer">'+
									'<button data-bb-handler="cancel" type="button" class="btn btn-default" onclick=wenwen.close()>Cancel</button>'+
									'<button data-bb-handler="confirm" type="button" class="btn btn-primary">OK</button>'+
								'</div>'+*/
							'</div>'+
						'</div>';
			var backdrop = $('<div class="modal-backdrop fade in"></div>');
			content.append(html);
			$("html>body").append(content);
			$("html>body").append(backdrop);
		},
		
		showTip: function(tit,text,fun) {
			var content = $('<div class="bootbox modal fade in" tabindex="-1" role="dialog" style="display: block;" aria-hidden="false">');
			var html = '<div class="modal-dialog">' +
							'<div class="modal-content">'+
								'<div class="modal-header">'+
									'<button type="button" class="bootbox-close-button close" onclick=wenwen.close()>×</button>'+
									'<h4 class="modal-title">'+tit+'</h4>'+
								'</div>'+
								'<div class="modal-body">'+
									'<div class="bootbox-body">'+
									text+
									'</div>'+
								'</div>'+
								'<div class="modal-footer">'+
									'<button data-bb-handler="cancel" type="button" class="btn btn-default" onclick=wenwen.close()>Cancel</button>'+
									'<button data-bb-handler="confirm" type="button" class="btn btn-primary">OK</button>'+
								'</div>'+
							'</div>'+
						'</div>';
			var backdrop = $('<div class="modal-backdrop fade in"></div>');
			content.append(html);
			$("html>body").append(content);
			$("html>body").append(backdrop);
			if(fun) {
				fun();
			};
		},
		
		close : function() {
			$(".bootbox").remove();
			$(".modal-backdrop").remove();
		},
		
		loadPage : function(tit,url) {
			wenwen.loader("亲，正在加载...");
			$("<div></div>").load(url,function(data){
				wenwen.loader("close")
				wenwen.window(tit,data);
			});
		},
		
		loader: function(text) {
			if(text==null || text == "close" || text == "") {
				wenwen.close();
			} else {
				var content = $('<div class="bootbox modal fade in" tabindex="-1" role="dialog" style="display: block;margin-top:150px;" aria-hidden="false">');
				var html = '<div class="modal-dialog">' +
								'<div class="modal-content">'+
									'<div class="modal-header">'+
										//'<button type="button" class="bootbox-close-button close" onclick=wenwen.close()>×</button>'+
										'<h4 class="modal-title" align="center">'+text+'</h4>'+
									'</div>'+
									'<div class="modal-body">'+
										'<div class="bootbox-body" align="center">'+
											'<i class="icon-spinner icon-spin orange bigger-275" style="font-size:1000%;"></i>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>';
				var backdrop = $('<div class="modal-backdrop fade in"></div>');
				content.append(html);
				$("html>body").append(content);
				$("html>body").append(backdrop);
			}
		},
		
		ajax: function(u,d,s,f) {
			$.ajax({
				type:'get',
				data:d,
				url:u,
				dataType:'json',
				success:function(data){
					wenwen.loader("正在加载...");
					if(data){
						wenwen.loader("close");
						if(data.error){
							f(data.data);
						}else{
							s(data.data);
						}
					}
				},
				error:function(a,b,c){
					wenwen.loader("正在加载...");
					if(a.status==200){
						wenwen.loader("close");
						wenwen.loadPage("error",a.responseText);
					}
				}
			});
		}, 
		confirm: function(msg, url, fun) {
			bootbox.confirm(msg, function(result) {
				if(result) {
					var success = function(data) {
						wenwen.showTip("成功消息",data);
						if(fun){
							fun();
						};
					};
					var failure = function(data) {
						wenwen.showTip("失败消息",data);
					};
					wenwen.ajax(url,null,success,failure);
				}
			});
		}
};