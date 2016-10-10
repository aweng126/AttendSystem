$(document).ready(function(){
	Login();
	
});
function Login(){
	var stu_id=$("#stu_id").val();
	var stu_name=$("#stu_name").val();
	var stu_pass = $("stu_psw").val;
	var stu_sex = $("stu_sex").val();
	var stu_class = $("stu_grade").val();
	var stu_grade = $("#stu_grade").val();
	var dept_name = $("#stu_deptname").val();
	var data_info={"stu_id":stu_id,"stu_name":stu_name,"stu_pass":stu_pass,"stu_sex":stu_sex,"stu_class":stu_class,"stu_grade":stu_grade,"dept_name":dept_name};
	$.ajax({
		type:"post",
		url:"/sturegister",
		async:true,
		data:data_info,
		success:function(data){
			if(data == 1){
				alert("注册成功");
			}else{
				alert("登陆失败");
			}
		}
	});
}
