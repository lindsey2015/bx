Summer.ajax=function(u,d,s,f){
		$.ajax({
			type:'get',
			data:d,
			url:u,
			dataType:'json',
			success:function(data){
				if(data){
					if(data.error){
						f(data.data);
					}else{
						s(data.data);
					}
				}
			},
			error:function(a,b,c){
				if(a.status==200){
					s(a.responseText,true);
				}
			}
		});
};
