<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 100%;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3" v-for="vo in goods_list">
				<div class="thumbnail">
				<%-- :href => vue 내의 변수를 사용하기 위해 필요! --%>
					<a :href="'detail.do?no='+vo.no" target="_blank"> <img :src="vo.poster"
						alt="Lights" style="width: 100%" :title="vo.name">
						<div class="caption">
							<p style="font-size: 12px">{{vo.price}}&nbsp;</p>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a href="#" @click="prev()">&lt;</a></li>
					<%--
						v-for="i in 10" => endPage가 들어가면 1~30까지 계속 반복되야하므로, range함수 제작
						range => [1,2,3,4,5] / [6,7,8,9,10]
					 --%>
					<li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a href="#">{{i}}</a></li>
					<li v-if="endPage<totalpage"><a href="#" @click="next()">&gt;</a></li>
				</ul>
			</div>
		</div>
		<h3>최근 본 상품</h3>
		<hr>
		<div class="row">
			
		</div>
	</div>
	<script>
		new Vue({
			el:'.container-fluid',
			data:{	/* 명시적 초기화 */
				goods_list:[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0,
				goods_cookie:[],
			},	/* 생성자와 동일한 역할 => 자동 호출 */
			mounted:function(){
				this.send();
			},	/* 사용자 정의 함수 => 반드시 호출 필요(CallBack Method) */
			methods:{
				/* 데이터를 보내고, 값을 가져오는 함수 => 서버와 통신 (양방향) */
				send:function(){
					let _this=this;
					// axios.get() => @GetMapping / axios.post() => @PostMapping
					axios.get("http://localhost/web/goods/list_vue.do",{
						params:{
							// 앞의 key는 컨트롤러에서 부르는 매핑 메소드의 매개변수와 동일해야한다.
							page:this.curpage
						}
					}).then(function(response){
						console.log(response.data)
						_this.goods_list=response.data
						_this.curpage=response.data[0].curpage
						_this.totalpage=response.data[0].totalpage
						_this.startPage=response.data[0].startPage
						_this.endPage=response.data[0].endPage
					})
				},
				/* 배열을 리턴하는 함수를 만든 뒤, v-for="i in 배열" => 페이징 */
				range:function(start,end){
					let arr=[];
					let length=end-start
					for(let i=0;i<=length;i++){
						arr[i]=start;
						start++;
					}
					return arr;
				},
				prev:function(){
					this.curpage=this.startPage-1
					this.send()
				},
				next:function(){
					this.curpage=this.endPage+1
					this.send()
				}
			}
		})
	</script>
</body>
</html>