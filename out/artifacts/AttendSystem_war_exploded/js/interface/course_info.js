$(document).ready(function(){
	$("#allocation_course_li").on("click",function(){
		allocationCourse();
	});
	$("#add_course_li").on("click",function(){
		AddCourse();
	});
	$("#add_classroom_li").on("click",function(){
		AddClassRoom();
	});
	$("#add_term_li").on("click",function(){
		AddTerm();
	});
	$("#add_teacher_li").on("click",function(){
		AddTeach();
	});
	$("#check_allocation_li").on("click",function(){
		showAllocation();
	});
});

function showForm(cnode,sid){
	$(cnode).on("click",function(){
		$(".pop-dialog").css("display","none");
		$(sid).css("display","block");
	});
}





/*****************************显示分配课程的列表，以及提交信息***/
function allocationCourse(){
	$("#allocate_course_name").attr("readonly","false");
	$(".pop-dialog").css("display","none");
	$("#allocation_course").css("display","block");
	$("#allocate_classroom_id").empty();
	$("#allocate_teacher").empty();
	$("#allocate__course_term").empty();
	$("#allocate__course_id").empty();
	requestAllCourseId();
	requestClassroomId();
	requestAllteacherId();
	requestCourseTerm();


	$("#allocate__course_id").on("change",function(){
			var t = $(this).val();
			var name = $("#allocate__course_id option[value="+t+"]").attr('data-cname');
			console.log(name);
			$("#allocate_course_name").val(name);
			$("#allocate_course_name").attr("readonly","true");
		});

	/*** 分配课程adminDistributeCourse*/
	$("#allocation_course .submit_btn").on("click",function(){
		 var teacher_val = $("#allocate_teacher").val();
		 var teacher_id = $("#allocate_teacher option[value="+teacher_val+"]").attr('data-cname');
		 
		 
		 var course_id = $("#allocate__course_id").val();
		 // var course_id =$("#allocate__course_id option[value="+course_val+"]").attr('data-cname');
		 
		 
		 var room_val = $("#allocate_classroom_id").val();
		 var room_id =$("#allocate_classroom_id option[value="+room_val+"]").attr('data-cname');
		 
		 
		 var time_week =$("#allocate_class_weekday").val();
		 var acadyear_val = $("#allocate__course_term").val();
        var acadyear_id = $("#allocate__course_term option[value="+acadyear_val+"]").attr('data-cname');
		 var time_order = $("#allocate_class_time").val();
		 var data_info = {"teacher_id":teacher_id,"academic_id":acadyear_id,"course_id":course_id,"room_id":room_id,"time_week":time_week,"time_order":time_order};
		 console.log(data_info);
		 submitAllocateCourse(data_info);
	});
}

/*********提交分配信息*******/
function submitAllocateCourse(data_info){
	$.ajax({
		type:"post",
		url:"/adminDistributeCourse",
		data:data_info,
		async:true,
		success:function(data){
			switch(data){
				case "1":
					alert("分配成功");
					break;
				case "0":
					alert("分配失败");
					break;
				
			}
			
		}
	});
}

/******************得到全部的课程id******/
function requestAllCourseId(){
	$.ajax({
		type:"get",
		url:"/adminGetCourseList",
		async:true,
		success:function(data){
			/*Course_id 课程号
			Course_name 课程名称*/
			data = JSON.parse(data);
			console.log(data);
			for(var i =0;i<data.length;i++){
				showOption($("#allocate__course_id"),data[i].course_id,data[i].course_name);
			}
		}
	});
}

/********得到全部教师信息*********AdminGetTeacherList
Teacher_id     老师id
Teacher_name 老师姓名
*/
function requestAllteacherId(){
	$.ajax({
		type:"get",
		url:"/adminGetTeacherList",
		async:true,
		success:function(data){
			/*Course_id 课程号
			Course_name 课程名称*/
			data = JSON.parse(data);
			console.log(data);
			for(var i =0;i<data.length;i++){
				showOption($("#allocate_teacher"),data[i].teacher_name,data[i].teacher_id);
			}
		}
	});
}

/********教室信息adminGetClassRoomList
Get
返回参数
Room_id  教室
Building  建筑
Roomnum 房间号

*/
function requestClassroomId(){
	$.ajax({
		type:"get",
		url:"/adminGetClassRoomList",
		async:true,
		success:function(data){
			data = JSON.parse(data);
			console.log(data);
			var classroom;
			for(var i =0;i<data.length;i++){
				classroom = data[i].building+data[i].roomnum;
				showOption($("#allocate_classroom_id"),classroom,data[i].roomid);
			}
		}
	});
}



/********得到学年信息
/adminGetAcademicYearList
Get
返回参数
Academic_id 学年id
Acadyear     学年
Term         学期


*/
function requestCourseTerm(){
	$.ajax({
		type:"get",
		url:"/adminGetAcademicYearList",
		async:true,
		success:function(data){
			data = JSON.parse(data);
			console.log(data);
			var classroom;
			for(var i =0;i<data.length;i++){
				term = data[i].ecadyear+"第"+data[i].term+"学期";
				showOption($("#allocate__course_term"),term,data[i].yearid);
			}
		}
	});
}





/*************************添加课程***************************************/
/**	添加课程
/adminAddCourse
请求方式 Post
请求参数： course_id  课程号
           Course_name  课程名
           Course_credit  int 学分 
返回参数
0	失败
1	成功
*/
function AddCourse(){
	$(".pop-dialog").css("display","none");
	$("#add_course").css("display","block");
	
	$("#add_course .submit_btn").on("click",function(){
		var course_id = $("#add_course_id").val();
		var course_name = $("#add_course_name").val();
		var course_credit= $("#add_course_credit").val();
		
		var data_info = {"course_id":course_id,"course_name":course_name,"course_credit":course_credit};
		console.log(data_info);
		PostCourseInfo(data_info);
	});
	
		
	function PostCourseInfo(data_info){
		$.ajax({
			type:"post",
			url:"/adminAddCourse",
			async:true,
			data:data_info,
			success:function(data){
				switch(data){
					case "1":
						alert("添加成功");
						break;
					case "0":
						alert("添加失败");
						break;
					
				}
			}
		});
	}
}



/******************************添加学年*****************************
 * 
 * url:   /adminAddAcademicYear
方式： Post
上传参数  Acadyear String 学年
Term    String 学年
返回参数 
0	格式错误
1	插入成功
***/
function AddTerm(){
	$(".pop-dialog").css("display","none");
	$("#add_term").css("display","block");
	
	$("#add_term .submit_btn").on("click",function(){
		var term = $("#add_term_str").val();
		var acadyear = $("#add_Acadyear").val();
		
		var data_info = {"term":term,"acadyear":acadyear};
		PostTermInfo(data_info);
	});
	
	function PostTermInfo(data_info){
		$.ajax({
			type:"post",
			url:"/adminAddAcademicYear",
			async:true,
			data:data_info,
			success:function(data){
				switch(data){
					case "1":
						alert("添加成功");
						break;
					case "0":
						alert("格式错误");
						break;
					
				}
			}
		});
	}

}


/********************添加教师*************************
 * 
 * /adminAddTeacher
Post
Teacher_id  教师号
Teacher_name 教师名
Dept_name院系
Isadmin    是否为管理员

返回参数  0 失败
2	成功
********/
function AddTeach(){
	$(".pop-dialog").css("display","none");
	$("#add_teacher").css("display","block");
	
	$("#add_teacher .submit_btn").on("click",function(){
		var teacher_id = $("#add_teacher_id").val();
		var teacher_name = $("#add_teacher_name").val();
		var dept_name = $("#add_teacher_dept").val();

		var text = $("#add_teacher_admin").get(0).checked;
		var isadmin ;
		if(text){
			isadmin = 1;
		}else{
			isadmin = 0;
		}
		
		var data_info = {"teacher_id":teacher_id,"teacher_name":teacher_name,"dept_name":dept_name,"isadmin":isadmin};
		console.log(data_info);
		PostTeachInfo(data_info);
	});
	
	function PostTeachInfo(data_info){
		$.ajax({
			type:"post",
			url:"/adminAddTeacher",
			data:data_info,
			async:true,
			success:function(data){


				console.log(data);
				switch(data){
					case "2":
						alert("添加成功");
						break;
					case "0":
						alert("格式错误");
						break;
					
				}
			}
		});
	}

}


/********************添加教室*************************
 * 
 *adminAddClassRoom
请求方式 Post
请求参数： building 建筑
           Roomnumber  房间号
返回参数
Status;0	失败
1	成功
********/
function AddClassRoom(){
	$(".pop-dialog").css("display","none");
	$("#add_classroom").css("display","block");
	
	$("#add_classroom .submit_btn").on("click",function(){
		var building = $("#add_classroom_building").val();
		var roomnumber = $("#add_classroom_roomnumber").val();
		
		
		var data_info = {"building":building,"roomnum":roomnumber};
		console.log(data_info);
		PostClassroomnfo(data_info);
	});
	
	function PostClassroomnfo(data_info){
		$.ajax({
			type:"post",
			url:"/adminAddClassRoom",
			data:data_info,
			async:true,
			success:function(data){

				console.log(data);
				switch(data){
					case "1":
						alert("添加成功");
						break;
					case "0":
						alert("格式错误");
						break;
					
				}
			}
		});
	}

}





/********************查看分配信息***************************/
function showAllocation(){
	$(".pop-dialog").css("display","none");
	$("#show_allocation").css("display","block");
	$.ajax({
		type:"get",
		url:"/adminGetTeachInfo",
		async:true,
		success:function(data){
				handlerData(data);
		}
	});
	function handlerData(data){
		data = JSON.parse(data);
		$(".table tbody").empty();
		console.log(data);
		for(var i = 0;i<data.length;i++){
			showTable(data[i].course_id,data[i].course_name,data[i].course_credit,data[i].teacher_id,data[i].teacher_name,data[i].dept_name);
		}
		$(".operate").on("click",function(){
			if(confirm("确定删除该课程")){
				var cid = $(this).siblings(".cid").text();
				var tid  = $(this).siblings(".tid").text();
				
				var data_info = {"course_id":cid,"teacher_id":tid};
				$.ajax({
					type:"post",
					url:"/adminDeleteTeaches",
					async:true,
					data:data_info,
					success:function(data){
						if(data = '1'){
							alert("删除成功");
						}else{
							alert("删除失败");
						}
					},
					error:function(){
						alert("删除失败errror");
					}
				});
				
			}
		});
		
	}
}



function showOption(pnode,cid,cname){
	var sContent ='<option value="'+cid+'" data-cname="'+cname+'">'+cid+'</option>';
	$(pnode).append(sContent);
}

function showTable(cid,cname,credit,tid,tname,dname){
	var sContent ='<tr class="first"><td class="cid">'+cid+'</td><td>'+cname+'</td><td>'+
	credit+'</td>'+'<td class="tid">'+
	tid+'</td><td>'+tname+'</td><td>'+dname+'</td><td class="operate">删除</td></tr>';
	$(".table tbody").append(sContent);
}
