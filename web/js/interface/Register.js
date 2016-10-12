$(document).ready(function(){
	$("#signup").on("click",function () {
		Login();
	})
	
});
function Login(){
	var stu_id=$("#stuid").val();
	var stu_name=$("#stu_name").val();
	var stu_pass = $("#stu_psw").val();
	var stu_sex = $("#stu_sex").val();
	var stu_class = $("#stu_class").val();
	var stu_grade = $("#stu_grade").val();
	var dept_name = $("#stu_deptname").val();
	var data_info={"stu_id":stu_id,"stu_name":stu_name,"stu_pass":stu_pass,"stu_sex":stu_sex,"stu_class":stu_class,"stu_grade":stu_grade,"dept_name":dept_name};
	console.log(data_info);
	$.ajax({
		type:"post",
		url:"/sturegister",
		async:true,
		data:data_info,
		success:function(data){
			console.log(data);
			if(data == 0){
				alert("注册失败");
			}else {
				alert("注册成功，请重新扫描选课二维码");
				var url = "sChooseCourse?course_id=@&room_id=@&time_id=@&acadyear_id=@";
				url = url.replace("@",$.cookie('course_id')).replace("@",$.cookie('room_id')).replace("@",$.cookie('time_id')).replace("@",$.cookie('acadyear_id'));
				window.location.href=url;
			}
		}
	});

}
