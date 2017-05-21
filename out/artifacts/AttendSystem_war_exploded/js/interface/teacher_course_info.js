$(document).ready(function(){
	$("#course_list_li").on("click",function(){
		showTeacherCourse();
	});
	$("#stu_info_li").on("click",function(){
		showStuInfoTables();
	});
	$(".pagination").css("display","none");
	
});

/******点击删除刷新还没有写，分页还没有写********************/


/************************得到分页**************/
function getPage(data_info){
	$.ajax({
		type:"post",
		url:"/tGetStudentPages",
		async:true,
		data:data_info,
		success:function(data){
			handlerData(data);
		}
	});
	function  handlerData(data) {
		console.log(data);
		$(".pagination").css("display","block");
		$(".pagination ul").empty();
		var sContent = " <li ><a class='last_page' href='#'>&#8249;</a></li>";
		for(var i = 1;i<=data;i++){
			if(i ==1){
				sContent +='<li data-page= "'+i+'" class="page_num"><a class="active" href="#">'+i+'</a></li>';
			}else{
				sContent +='<li data-page="'+i+'" class="page_num"><a  href="#">'+i+'</a></li>';
			}
		}
		sContent+=' <li class="next_page"><a href="#">&#8250;</a></li>';

		$(".pagination ul").append(sContent);
		$(".page_num").on("click",function () {
			$(".pagination ul a").removeClass("active");
			$(this).children("a").addClass("active");
			var page = parseInt($(this).attr("data-page"));
			var mydata_info = {"course_id":data_info.course_id,"page_id":page};
			console.log(mydata_info);
			showStuTables(mydata_info);
		});
	}
}


/********************查看我的课程**************
 * ) 得到教课信息
  url :/tGetMyTeach   
请求方式：Get
返回参数
   Couse_id   string  课程号 
   Course_name  String 课程名称
   Course_credit   int    课程学分
*************/
function showTeacherCourse(){
	$(".t_info_wrap").css("display","none");
	$("#show_course_list").css("display","block");
	$.ajax({
		type:"get",
		url:"/tGetMyTeach",
		async:true,
		success:function(data){
				handlerData(data);
		}
	});
	function handlerData(data){
		
		data = JSON.parse(data);
		$(".table tbody").empty();
		$("#course_list tbody").empty();
		for(var i = 0;i<data.length;i++){
			showTable(data[i].course_id,data[i].course_name,data[i].course_credit);
		}
		$(".show_course_detail_btn").on("click",function(){
			var cid = $(this).siblings(".cid").text();
			var data_info = {"course_id":cid};
			showCourseDetail(data_info);
		});
		
	}
}


function showCourseDetail(data_info){
	$("#show_course_list").css("display","none");
	$("#show_course_detail").css("display","block");
	$.ajax({
		type:"post",
		url:"tGetMyTeachDetail",
		async:true,
		data:data_info,
		success:function(data){
			handlerData(data);
		}
	});
	function handlerData(data){
		data = JSON.parse(data);
		console.log(data);
		$(".detail_cid").text(data[0].course.course_id);
		$(".detail_cname").text(data[0].course.course_name);
		$(".detail_credit").text(data[0].course.course_credit);
		$(".detail_teacherid").text(data[0].teacher.teacher_id);
		$(".detail_teachername").text(data[0].teacher.teacher_name);
		$(".detail_teachersex").text(data[0].teacher.teacher_sex);
		$(".detail_deptname").text(data[0].teacher.dept_name);
		$(".detail_classroom").text(data[0].classRoom.building+data[0].classRoom.roomnum);
		$(".detail_acayear").text(data[0].academicYear.ecadyear);
		$(".detail_term").text(data[0].academicYear.term);
		$(".detail_week").text(data[0].courseTime.week);
		$(".detail_order").text(data[0].courseTime.order);
		$(".detail_time").text(data[0].realtime);
		
		$("#to_signin_btn").on("click",function(){
			var cid = data[0].course.course_id;
			var mydata = {"course_id":cid};
			console.log(mydata);
			$.ajax({
				type:"post",
				url:"/tGetSignQR",
				async:true,
				data:mydata,
				success:function(data){
					console.log(data);
					init2dimensional(data,mydata,"签到二维码");
				},
				
			});
		});
		$("#to_select_btn").on("click",function(){
			var cid =  data[0].course.course_id;
			var myselectdata = {"course_id":cid};
			$.ajax({
				type:"post",
				url:"/tGetChooseCourseQR",
				async:true,
				data:myselectdata,
				success:function(data){

					init2dimensional(data,myselectdata,"选课二维码");
				},
				
			});
		});
		$(".close-icon").on("click",function () {

			$("#show_course_detail").css("display","none");
			$("#show_course_list").css("display","block");
		})
	}
	
}




/***********展示学生信息的整个内容*****/
function showStuInfoTables(){
	$(".t_info_wrap").css("display","none");
	$("#show_student_list").css("display","block");
	
	/*******得到该教师的课程******/
	$.ajax({
		type:"get",
		url:"/tGetMyTeach",
		async:true,
		success:function(data){
			data = JSON.parse(data);
			var sContent='';
			console.log(data);
			for(var i = 0;i<data.length;i++){
				 sContent +=' <option data-id="'+data[i].course_id+'"value="'+data[i].course_name+'">'+data[i].course_name+'</option>';
			}
			$("#teacher_course").empty();
			$("#teacher_course").append(sContent);
			
			$("#teacher_course").on("click",function(){
				var course_name = $(this).val();
				console.log(course_name);
				var course_id =  $("#teacher_course option[value='"+course_name+"']").attr('data-id');
				console.log(course_id);
				var data_info={"page_id":1,"course_id":course_id};
				console.log(data_info);
				$(".table tbody").empty();
				showStuTables(data_info);
				getPage({"course_id":course_id});
			});
		}
	});
}

//显示学生信息的分页表
function showStuTables(data_info){
	$("#show_student_list tbody").empty();
	$.ajax({
		type:"post",
		url:"/tCheckStudent",
		async:true,
		data:data_info,
		success:function(data){
			data =JSON.parse(data);
			console.log(data);

			for(var i = 0;i<data.length;i++){
				showStuTable(data_info.course_id,data[i].stuid,data[i].stuname,data[i].stugrade,data[i].stusex,data[i].stuclass,data[i].dept_name);
			}
			
			/*******点击名字得到签到信息*****/
			$(".stu_name").on("click",function(){
				$("#attend_wrap").remove();
				var stu_id = $(this).attr("data-stuid");
				var course_id = $(this).parent("tr").attr("data-cid");
				var data_info = {"stu_id":stu_id,"course_id":course_id};
				console.log(data_info);
				RequestStuAttend($(this).parent("tr"),data_info);
			});
			
			/**********删除该学生****/
			$(".del_stu").on("click",function(){
				if(confirm("确定删除该学生")){
					var stu_id =  $(this).siblings(".stu_name").attr("data-stuid");
					var mydata_info ={"stu_id":stu_id,"course_id":data_info.course_id};
					deleteStudent(mydata_info,data_info);

				}
			});
		}
	});
}

/*******查看某个学生的签到情况**********/
function RequestStuAttend(pnode,data_info){
	$.ajax({
		type:"post",
		url:"/tCheckStudentSign",
		data:data_info,
		async:true,
		success:function(data){
			handerData(data);
		}
	});
	
                                   
	function handerData(data){
		data = JSON.parse(data);
		console.log(data);

		var sContent = '<div id="attend_wrap" class="pop-dialog full"><div class="pointer"><div class="arrow"></div>'+
                        '<div class="arrow_border"></div></div><div class="body">'+
						'<a href="#" class="close-icon"><i class="icon-remove-sign"></i></a><h4 id="stu_rate_title" style="margin-left: 120px">签到率：'+
			             + data[0].attendRate+'%</h4><div class="settings">'+
                        '<div id="stu_attend_items" class="items">'+
                        '</div></div></div></div>';
        $(pnode).append(sContent);
        $(".close-icon").on("click",function(){
        	$("#attend_wrap").remove();
        });
        sContent = '';
		for(var i = 1;i<=data[0].current_week;i++){
			sContent +=' <div id="'+i+'" class="item"><i class="icon-reorder"></i>第'+i+
			'周<input type="checkbox" class="check"></div>';
		}
		$("#stu_attend_items").append(sContent);
			for(var i = 0;i<data.length;i++){
				if(data[i].sign_week ==0){
					$("#"+data[i].sign_week).children(".check").attr("checked", false);
				}
				$("#"+data[i].sign_week).children(".check").attr("checked", true);
			}
		
	}
}



/*************删除某个学生******************/

function deleteStudent(data_info,refresh_data){
	$.ajax({
		type:"post",
		url:"/tDeleteStudent",
		data:data_info,
		async:true,
		success:function(data){
			if(data =='1'){
				showStuTables(refresh_data);
				alert("删除成功");

			}else{
				alert("删除失败");
			}
		}
	});
}

function showStuTable(cid,sid,sname,sgrade,sex,stu_class,dname){
	var sContent = '<tr data-cid="'+cid+'" class="first"><td class="stu_name" data-stuid="'+sid+'">'+sname+'</td><td>'+sex+'</td><td>'+sid+' </td><td >'+sgrade+
		         '</td><td>'+stu_class+'</td><td>'+dname+'</td><td class="del_stu">删除</td></tr>';
	$("#show_student_list tbody").append(sContent);
}


function showOption(pnode,cid,cname){
	var sContent ='<option value="'+cid+'" data-cname="'+cname+'">'+cid+'</option>';
	$(pnode).append(sContent);
}

function showTable(cid,cname,credit,tid,tname,dname){
	var sContent ='<tr class="first"><td class="cid">'+cid+'</td><td>'+cname+'</td><td>'+
	credit+'</td>'+'<td class="show_course_detail_btn">查看详情</td></tr>';
	$(".table tbody").append(sContent);
}

function init2dimensional(data,mydata,title){
	$("#show_course_detail").css("display","none");
		$("#show_2dimensional_code").css("display","block");
		$("#dimensional_title").text(title);
		$("#two_dimensional_code_img").attr("src",data);
		$(".close-icon").on("click",function(){
			$("#show_2dimensional_code").css("display","none");
			showCourseDetail(mydata);
		});
}
