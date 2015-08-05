/**
 * @author	bingyi
 * @time	2015-02-05 21:00
 */

/**
 * $formAjax
 * 
 * @使用方法1:
 * 		@参数：id,url[,rules,success,error]
 * 		@参数说明：id	 ->	form的id，（jQuery的选择器语法）
 * 			 url	 ->	form的提交链接
 * 			 rules	 ->	规则  （可选参数）
 * 			 success ->	成功操作的回调函数，回调必须含有参数  （可选参数）
 * 			 error	 ->	失败操作的回调函数，回调必须含有参数  （可选参数）
 * 			 first	 ->	提交前先执行函数，该函数必须有返回值，返回true执行提交，返回false不执行提交  （可选参数）
 * 		@示例：
  		$formAjax("#id","url",{name:["#","请输入用户名称","#maxLength",25]},function(data){},function(data){},function(){return true;})
 *
 * @使用方法2：建议使用这种方式
 * 		@参数：Object类型 -> 键：id,url[,rules,success,error]
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：
  		$formAjax({
 	    	    	id:"#formId",
 	    	    	url:'url',
 	    	    	rules:{},
 	    	    	success:function(data){},
 	    	    	error:function(data){},
 	    	    	first:function(){return true;};
 	    	 });
 */

function $formAjax(id,url,rules,success,error,first){
	if(id.constructor==Object){
		object=id;
		id=object.id;
		url=object.url;
		rules=object.rules;
		success=object.success;
		error=object.error;
		first=object.first;
	}
	$(id).form({
	    rules:rules,
	    todo:function(form,data){
	    	flag=true;
	    	if(first){
	    		flag=first();
	    	}
	    	if(flag){
				var load = layer.load(notice); 
				Summer.ajax(url,data,
					function(data){
						layer.msg(data,1,1,function(){
						    layer.close(load); 
						    if(success){
							success(data);
						    }
						});
				    	},
				    	function(data){
				    	    layer.close(load); 
				    	    if(error){
				    	    	error(data);
				    	    }
				    });
	    	}
	    	}
	});    	
}

/**
 * $summerLayer
 * 
 * @使用方法1:
 * 		@参数：title,url,area,close
 * 		@参数说明：title	->	标题
 * 			 url	->	iframe链接
 * 			 area	->	大小  比如：[ '1200px', '600px' ]
 *			 close  ->  关闭弹窗的回调函数
 * 		@示例：
               $summerLayer("title","url",[ '1200px', '600px' ],function(){})
 *
 * @使用方法2：
 * 		@参数：Object类型 -> 键：title,url,area,close
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：
  		$summerLayer({
 	    	    	title:"title"
 	    	    	url:'url',
 	    	    	area,['1200px','600px'],
 	    	    	close,function(){}
 	    	 });
 */
function $summerLayer(title,url,area,close){
	if(title.constructor==Object){
		object=title;
		title=object.title;
		url=object.url;
		area=object.area;
		close=object.close;
	}
	document.documentElement.style.overflow='hidden';
	$.layer({
		type : 2,
		shade : [ 0.5, '#000' ],
		fix : true,
		title : title,
		maxmin : false,
		iframe : {
			src : url
		},
		area : area,
		close : function(index){
			document.documentElement.style.overflow='scroll';
			if(close){
				close();
			}
		}
	});
}

/**
 * len方法
 * 
 * 描述：获取Object键的个数
 */
len=function(object){
	var size = 0, key;
	for (key in object) {
		if (object.hasOwnProperty(key)){ 
			size++;
		}
	}
	return size;
};


/**
 * $post
 * 
 * @使用方法1:
 * 		@参数：url,[data,success,error,dataType]
 * 		@参数说明：
 * 				url		->	提交链接
 * 				data	->	提交数据 Object格式   （可选参数）
 * 				success	->	成功操作的回调函数，回调必须含有参数  （可选参数）
 * 				error	->	失败操作的回调函数，回调必须含有参数  （可选参数）
 * 				dataType->	返回数据类型，默认json    （可选参数）
 * 		@示例：$post("url",{name:"name",psw:"psw"},function(data){},function(data){},"json");
 *
 * @使用方法2：建议使用这种方式
 * 		@参数：Object类型 -> 键：url,[data,success,error,dataType]
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：
  		$post({
 	    	    	url:'url',
	    	    	data:{name:"name",psw:"psw"},
 	    	    	success:function(data){},
 	    	    	error:function(data){},
 			dataType:"json"
 	    	 });
 */

function $post(url,data,success,error,dataType){
	if(url.constructor==Object){
		object=url;
		url=object.url;
		data=object.data;
		success=object.success;
		error=object.error;
		dataType=object.dataType;
	}
	var s=[];
	for ( var key in data){
		s.push(key+"="+data[key]);
	}
	data=encodeURI(s.join("&"));
	if(dataType==undefined){
		if(error&&error.constructor==String){
		    dataType=error;
		    error=undefined;
		}else{
		    dataType='json';
		}
	}
	var load = layer.load(notice); 
	$.ajax({
		url:url,
		type:'POST',
		data:data,
		dataType:dataType,
		success:function(data){
		    layer.close(load); 
		    if(dataType=='json'&&data.error==true){
				if(error){
				    error(data.data&&len(data)==2?data.data:data);
				}
		    }else{
				if(success){
				    success(data.data&&len(data)==2?data.data:data);
				}	
		    }
		},
		error:function (XMLHttpRequest, textStatus, errorThrown){
		    layer.close(load); 
		    if(error){
		    debugger
		    	error("请求失败！");
		    }
		}
	});
}


/**
 * $load
 * 描述：post方式加载数据，有显示"载入中"
 * 
 * @使用方法1:
 * 		@参数：url,[data,success,error,dataType]
 * 		@参数说明：
 * 				url		->	提交链接
 * 				data	->	提交数据 Object格式   （可选参数）
 * 				success	->	成功操作的回调函数，回调必须含有参数  （可选参数）
 * 				error	->	失败操作的回调函数，回调必须含有参数  （可选参数）
 * 				dataType->	返回数据类型，默认json    （可选参数）
 * 		@示例：$post("url",{name:"name",psw:"psw"},function(data){},function(data){},"json");
 *
 * @使用方法2：建议使用这种方式
 * 		@参数：Object类型 -> 键：url,[data,success,error,dataType]
 * 		@参数中的键说明：如使用方法1的参数
 * 		@示例：$post({
 *	    	    	url:'url',
 *	    	    	data:{name:"name",psw:"psw"},
 *	    	    	success:function(data){},
 *	    	    	error:function(data){},
 *					dataType:"json"
 *	    	 });
 */
function $load(url,data,success,error,dataType){
	if(url.constructor==Object){
		object=url;
		url=object.url;
		data=object.data;
		success=object.success;
		error=object.error;
		dataType=object.dataType;
	}
	if(error&&error.constructor==String){
		dataType=error;
		error=undefined;
	}
	var load=layer.load("加载中...");
	$post(url,data,function(data){
			layer.close(load);
			if(success)
				success(data);
		},function(data){
			layer.close(load);
			if(error)
				error(data);
		},dataType);
}