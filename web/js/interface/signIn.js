/*$(document).ready(function(){
	Login();

	console.log("login js");
	
});*/

$("#sign_up_btn").click(function(){
	Login();
});
function Login(){

	var teacher_id=$("#teacher_id").val();

	var teacher_pass=$("#teacher_password").val();
	var data_info={"teacher_id":teacher_id,"teacher_pass":teacher_pass};
	console.log(data_info);
	$.ajax({
		type:"post",
		url:"/tLogin",
		async:true,
		data:data_info,
		success:function(data){

			alert("sign in js success "+data)

			if(data == 1){
				window.location.href="teacherIndex.html";
			}else if(data ==2){
				window.location.href ="adminIndex.html";
			}else{
				alert("登陆失败");
			}
		}
	});
}
