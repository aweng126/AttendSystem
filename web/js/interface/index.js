$(document).ready(function(){
    		var ltdate = ["第一周","第二周","第三周","第四周","第五周","第六周","第七周"];
    		var dtdata = [98,97,95,100,78,79,80];
    		InitWgraph(ltdate,dtdata);
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


/**********************判断权限*************/
function JudgePower(){
	$.ajax({
		type:"get",
		url:"judgePower",
		async:true,
		success:function(data){
			data = JSON.parse(data);
			if(data.power ==1){
				InitMDropMenu();
			}
			else{
				InitODropMenu();
			}
		}
	});
}
/******************管理者权限****/
function InitMDropMenu(){
	var sContent =' <div class="glow"><label>教师</label><select id="teacher"><option value="1" data-id="c1">'+
	'教师</option></select></div><div class="glow"><label>课程号</label><select id="course"><option value="1" data-id="c1">'+
	'课程</option></select></div><div class="glow"><label>班级</label><select id="classid"><option value="1" data-id="c1">'+
	'班级</option></select></div>';
	$(".pull-right").append(sContent);
}

/******************普通教师权限****/
function InitODropMenu(){
	var sContent ='<div class="glow"><label>课程号</label><select id="course"><option value="1" data-id="c1">'+
	'课程</option></select></div><div class="glow"><label>班级</label><select id="classid"><option value="1" data-id="c1">'+
	'班级</option></select></div>';
	$(".pull-right").append(sContent);
}


/************获得课程普通教师******************/
function RequestOCouse(){
	$.ajax({
		type:"get",
		url:"judgePower",
		async:true,
		success:function(data){
			data = JSON.parse(data);
			var pnode = $("#course");
			for(var i = 0;i<data.length;i++){
				InitOption(pnode,data.course_id,data.course_name);
			}
		}
	});
}

/************获得班级普通教师******************/
function RequestOCouse(){
	var courseid = $("#course").val();
	var data_info ={"course_id":courseid};
	$.ajax({
		type:"post",
		url:"judgePower",
		async:true,
		data:data_info,
		success:function(data){
			data = JSON.parse(data);
			var pnode = $("#classid");
			for(var i = 0;i<data.length;i++){
				InitOption(pnode,data.course_id,data.course_name);
			}
		}
	});
}

/************获得班级考勤情况******************/
function RequestOCouse(){
	var courseid = $("#course").val();
	var classid = $("#classid").val();
	var data_info ={"course_id":courseid,class_id:classid};
	$.ajax({
		type:"post",
		url:"judgePower",
		async:true,
		data:data_info,
		success:function(data){
			data = JSON.parse(data);
			var ldate = [],ddata = [];
			for(var i  =0;i<data.length;i++){
				ldate.push(data[i].week);
				ddata.push(data[i].attendRat);
			}
			InitWgraph(ltdate,dtdata);
		}
	});
}
function InitOption(pnode,oid,ovalue){
	var sContent = '<option value="'+ovalue+'" data-id="'+oid+'">'+ovalue+'</option>';
	$(pnode).append(sContent);
}
