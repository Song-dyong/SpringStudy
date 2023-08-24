<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
	margin: 0px auto;
	width: 800px;
}
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
  google.charts.load("current", {packages:["corechart"]});
  google.charts.setOnLoadCallback(drawChart);
  function drawChart() {
    var data = google.visualization.arrayToDataTable([
      ['단어', '횟수'],
      <c:forEach var="wvo" items="${wList}">
      	['<c:out value="${wvo.word}"/>', <c:out value="${wvo.count}"/>],
      </c:forEach>
    ]);

    var options = {
      title: 'Words',
      is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
  }
</script>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">Detail</h2>
			<div class="row">
				<table class="table">
					<tr>
						<th width="20%" class="text-center">No</th>
						<td width="30%" class="text-center">{{board_detail.no}}</td>
						<th width="20%" class="text-center">Date</th>
						<td width="30%" class="text-center">{{board_detail.dbday}}</td>
					</tr>
					<tr>
						<th width="20%" class="text-center">Name</th>
						<td width="30%" class="text-center">{{board_detail.name}}</td>
						<th width="20%" class="text-center">Hit</th>
						<td width="30%" class="text-center">{{board_detail.hit}}</td>
					</tr>
					<tr>
						<th width="20%" class="text-center">Subject</th>
						<td colspan="3">{{board_detail.subject}}</td>
					</tr>
					<tr v-if="filecount!=0">
						<th width="20%" class="text-center">File</th>
						<td colspan="3">
							<ul>
								<li v-for="fn,index in filenames"><a :href="'../databoard/download.do?fn='+fn">{{fn}}</a>&nbsp;{{filesizes[index]}}BYTES</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="text-left" valign="top" height="200">
							<pre style="white-space: pre-wrap;background-color: white; border: none;">{{board_detail.content}}</pre>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="text-right">
							<a :href="'../databoard/update.do?no='+no" class="btn btn-sm btn-primary">수정</a>
							<a :href="'../databoard/delete.do?no='+no" class="btn btn-sm btn-primary">삭제</a>
							<a href="../databoard/list.do" class="btn btn-sm btn-primary">목록</a>
						</td>
					</tr>
				</table>
			</div>
		</main>
	</div>
	<div class="row">
		<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
	</div>
	<script>
		new Vue({
			el:'.container',
			data:{
				no:${no},
				board_detail:{},
				filecount:0,
				filenames:[],
				filesizes:[]
			},
			mounted:function(){
				axios.get('../databoard/detail_vue.do',{
					params:{
						no:this.no
					}
				}).then(res=>{
					console.log(res.data)
					this.board_detail=res.data
					this.filecount=this.board_detail.filecount
					this.filenames=this.board_detail.filename.split(",")
					this.filesizes=this.board_detail.filesize.split(",")
				}).catch(error=>{
					console.log(error.response)
				})
			}
		})
	</script>
</body>
</html>