$(document).ready(function(){
    		// var ltdate = ["第一周","第二周","第三周","第四周","第五周","第六周","第七周"];
    		// var dtdata = [98,97,95,100,78,79,80];
    		// InitWgraph(ltdate,dtdata);
	$("#refreshAttend").on("click",function () {
		var course_name =$("#course").val();
		console.log(course_name);
		var course_id =  $("#course option[value="+course_name+"]").attr('data-id');
		console.log($("#classid").val());
		var grade_id = $("#classid").val().substring(5,6);
		var class_id = $("#classid").val().substring(0,4);
		var mydata ={"course_id":course_id,"class_id":class_id,"grade_id":grade_id};
		console.log(mydata);
		RequestAttendTable(mydata);
	});
	$("#classid").empty();
	RequestCourse();

});
/*****************初始化图表************************/
function InitWgraph(ldate,ddata){
	var areaChartData = {
      labels: ldate,
      datasets: [
        {
          label: "出勤率",
          fillColor: "rgba(210, 214, 222, 1)",
          strokeColor: "rgba(210, 214, 222, 1)",
          pointColor: "rgba(210, 214, 222, 1)",
          pointStrokeColor: "#c1c7d1",
          pointHighlightFill: "#fff",
          pointHighlightStroke: "rgba(220,220,220,1)",
          data: ddata
        }
       
      ]
    };
		var areaChartOptions = {
      //Boolean - If we should show the scale at all
      showScale: true,
      //Boolean - Whether grid lines are shown across the chart
      scaleShowGridLines: true,
      //String - Colour of the grid lines
      scaleGridLineColor: "rgba(0,0,0,.05)",
      //Number - Width of the grid lines
      scaleGridLineWidth: 1,
      //Boolean - Whether to show horizontal lines (except X axis)
      scaleShowHorizontalLines: true,
      //Boolean - Whether to show vertical lines (except Y axis)
      scaleShowVerticalLines: true,
      //Boolean - Whether the line is curved between points
      bezierCurve: true,
      //Number - Tension of the bezier curve between points
      bezierCurveTension: 0.3,
      //Boolean - Whether to show a dot for each point
      pointDot: true,
      //Number - Radius of each point dot in pixels
      pointDotRadius: 4,
      //Number - Pixel width of point dot stroke
      pointDotStrokeWidth: 1,
      //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
      pointHitDetectionRadius: 20,
      //Boolean - Whether to show a stroke for datasets
      datasetStroke: true,
      //Number - Pixel width of dataset stroke
      datasetStrokeWidth: 2,
      //Boolean - Whether to fill the dataset with a color
      datasetFill: true,
      //String - A legend template
      legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
      //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
      maintainAspectRatio: true,
      //Boolean - whether to make the chart responsive to window resizing
      responsive: true
    };
   
     var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
    var lineChart = new Chart(lineChartCanvas);
    var lineChartOptions = areaChartOptions;
    lineChartOptions.datasetFill = false;
    lineChart.Line(areaChartData, lineChartOptions);

}


/************获得课程普通教师******************/
	function RequestCourse(){
	$.ajax({
		type:"get",
		url:"/tGetMyTeach",
		async:true,
		success:function(data){
			data = JSON.parse(data);
			console.log(data);
			var pnode = $("#course");
			$("#course").empty();
			for(var i = 0;i<data.length;i++){
				InitOption(pnode,data[0].course_id,data[0].course_name);
			}
			$("#course").on("click",function () {
				var course_name = $(this).val();
				console.log(course_name);
				var course_id =  $("#course option[value="+course_name+"]").attr('data-id');
				var data_info = {"course_id":course_id};
				console.log(data_info);
				Requestclass(data_info);
			})
		}
	});
}

/************获得班级普通教师******************/
function Requestclass(data_info){
	$.ajax({
		type:"post",
		url:"/tGetMyGrades",
		async:true,
		data:data_info,
		success:function(data){
			$("#classid").empty();
			data = JSON.parse(data);
			console.log(data);
			var pnode = $("#classid");

			for(var i = 0;i<data.length;i++){
				InitOption(pnode,data[i].mclass,data[i].mclass+"级"+data[i].mgrade+"班");
			}
		}
	});
}
/************获得班级考勤情况******************/
function RequestAttendTable(data_info){

	$.ajax({
		type:"post",
		url:"/requestAttendByWeek",
		async:true,
		data:data_info,
		success:function(data){

			data = JSON.parse(data);
			console.log(data);
			//******************成功返回数据**************************
			var ldate = [],ddata = [];
			var first_week = data[0].week;

			for(var i  =0;i<data.length;i++){
				ldate.push(data[i].week);
				ddata.push(data[i].attendRate);
			}
			$("#lineChart").remove();
			$(".lineChartwrap").append("<canvas id ='lineChart'></canvas>");
			InitWgraph(ldate,ddata);
			$("#last").on("click",function () {
				var mydata = {"course_id":data_info.course_id,"class_id":data_info.class_id,"grade_id":data_info.grade_id,"display_firstweek":first_week};
				InitLastAttendTable(mydata);
			})
			$("#next").on("click",function () {
				var mydata = {"course_id":data_info.course_id,"class_id":data_info.class_id,"grade_id":data_info.grade_id,"display_firstweek":first_week};
				InitNextAttendTable(mydata);
			})

		}
	});
}
function InitLastAttendTable(data_info) {
	$.ajax({
		type:"post",
		url:"/requestAttendByWeekForward",
		async:true,
		data:data_info,
		success:function(data){
			data = JSON.parse(data);
			var ldate = [],ddata = [];
			var first_week = data[0].week;
			var last_week = data[data.length-1].week;
			for(var i  =0;i<data.length;i++){
				ldate.push(data[i].week);
				ddata.push(data[i].attendRate);
			}
			$("#lineChart").remove();
			$(".lineChartwrap").append("<canvas id ='lineChart'></canvas>");
			InitWgraph(ldate,ddata);
			$("#last").on("click",function () {
				var mydata = {"course_id":data_info.course_id,"class_id":data_info.class_id,"grade_id":data_info.grade_id,"display_firstweek":first_week};
				InitLastAttendTable( mydata );
			})
			$("#next").on("click",function () {
				var mydata = {"course_id":data_info.course_id,"class_id":data_info.class_id,"grade_id":data_info.grade_id,"display_firstweek":first_week};
				InitNextAttendTable( mydata );
			})

		}
	});
}

function InitNextAttendTable(data_info) {
	$.ajax({
		type:"post",
		url:"/requestAttendByWeekBackward1",
		async:true,
		data:data_info,
		success:function(data){
			data = JSON.parse(data);
			var ldate = [],ddata = [];
			var first_week = data[0].week;
			var last_week = data[data.length-1].week;
			for(var i  =0;i<data.length;i++){
				ldate.push(data[i].week);
				ddata.push(data[i].attendRate);
			}
			$("#lineChart").remove();
			$(".lineChartwrap").append("<canvas id ='lineChart'></canvas>");
			InitWgraph(ldate,ddata);
			$("#last").on("click",function () {
				var mydata = {"course_id":data_info.course_id,"class_id":data_info.class_id,"grade_id":data_info.grade_id,"display_firstweek":first_week};
				InitLastAttendTable(mydata );
			})
			$("#next").on("click",function () {
				var mydata = {"course_id":data_info.course_id,"class_id":data_info.class_id,"grade_id":data_info.grade_id,"display_firstweek":first_week};
				InitLastAttendTable(mydata );
			})

		}
	});
}

function InitOption(pnode,oid,ovalue){
	var sContent = '<option value="'+ovalue+'" data-id="'+oid+'">'+ovalue+'</option>';
	$(pnode).append(sContent);
}
