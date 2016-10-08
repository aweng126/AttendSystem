$(document).ready(function(){
	SubmitInfo();
});

function SubmitInfo(){
	var stuid = $("#stuid").val();
	var data_info = {"stuid":stuid,"account":account};
	$.ajax({
		type:"post",
		url:"",
		async:true,
		data:data_info,
		success:function(data){
			handlerData
		}
	});
	function handlerData(data){
		data = JSON.parse(data);
		switch(data.statue){
			case 1:
			alert("注册成功");
			break;
			
		}
	}
}
