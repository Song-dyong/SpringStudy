<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <table class="table">
    	<tr>
    		<td width=30% class="text-center" rowspan="2">
    			<img :src="chef_info.poster" style="width: 110px; height: 110px" class="img-circle"> 
    		</td>
    		<td width=70% colspan="4"><h3 style="color: orange;">{{chef_info.chef}}</h3></td>
    	</tr>
    	<tr>
    		<td class="text-center">
    			<img src="../recipe/images/mem1.png">&nbsp;{{chef_info.mem_cont1}}
    		</td>
    		<td class="text-center">
    			<img src="../recipe/images/mem2.png">&nbsp;{{chef_info.mem_cont2}}
    		</td>
    		<td class="text-center">
    			<img src="../recipe/images/mem3.png">&nbsp;{{chef_info.mem_cont3}}
    		</td>
    		<td class="text-center">
    			<img src="../recipe/images/mem7.png">&nbsp;{{chef_info.mem_cont4}}
    		</td>
    	</tr>
    </table>
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading inline">
          	<input type="text" size=20 ref="fd" class="input-sm" v-model="fd">
          	<input type="button" value="search" class="btn btn-sm btn-danger" @click="find()"> 
          </header>
          <ul class="nospace clear" v-if="count==0">
          	<li>검색된 결과가 없습니다.</li>
          </ul>
          <ul class="nospace clear">
            <li v-for="vo,index in recipe_list" :class="index%4==0?'one_quarter first':'one_quarter'">
            	<a href="#">
            		<img :src="vo.poster" :title="vo.title+' ('+vo.chef+')'">
            	</a>
            </li>
          </ul>
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a href="#" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i==curpage?'current':''"><a href="#" @click="pageChange(i)">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a href="#" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<script>
	new Vue({
		el:'.container',
		data:{
			food_list:[],
			chef_info:{},
			page_info:{},
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			/* model.addAttribute("chef",chef)에 담겨서 넘어온 값을 저장 ${chef}
				EL, JSTL => JavaScript에서 사용 가능 but! list, vo는 받을 수 없으므로 JSON으로 변경 */
			chef:'${chef}',
			fd:'all',
			count:0,
			recipe_list:[]
		},
		mounted:function(){
			axios.get('http://localhost/web/recipe/chef_info_vue.do',{
				params:{
					chef:this.chef
				}
			}).then(res=>{
				this.chef_info=res.data
			})
			
			this.dataRecv();
		},
		methods:{
			// 멤버 메소드 => this 
			// this 사용 => data / methods  멤버 변수와 멤버 메소드.
			// 공통 메소드 : 스프링, 자바, Front => 반복코딩
			   // 공통 모듈 / 핵심 모듈
			   dataRecv:function(){
				   // 레시피 읽기
				   axios.post('http://localhost/web/recipe/chef_find_vue.do',null,{
					   params:{
						   page:this.curpage,
						   fd:this.fd,
						   chef:this.chef
					   }
				   }).then(res=>{
					   console.log(res.data)
					   this.recipe_list=res.data
				   }).catch(error=>{
					   // 에러
					   console.log(error.response)
				   })
				   
				   axios.get('http://localhost/web/recipe/page_info_vue.do',{
					   params:{
						   page:this.curpage,
						   fd:this.fd,
						   chef:this.chef
					   }
				   }).then(res=>{
					   console.log(res.data)
					   this.page_info=res.data
					   this.curpage=this.page_info.curpage;
					   this.totalpage=this.page_info.totalpage;
					   this.startPage=this.page_info.startPage;
					   this.endPage=this.page_info.endPage;
					   this.count=Number(this.page_info.count)
				   }).catch(error=>{
					   console.log(error.response)
				   })
			   },
			   range:function(start,end){
				   let arr=[];
				   let leng=end-start;
				   for(let i=0;i<=leng;i++){
					   arr[i]=start
					   start++;
				   }
				   return arr;
			   },
			   find:function(){
				   this.curpage=1;
				   this.dataRecv();
			   },
			   next:function(){
				   this.curpage=this.endPage+1;
				   this.dataRecv();
			   },
			   prev:function(){
				   this.curpage=this.startPage-1;
				   this.dataRecv();
			   },
			   pageChange:function(page){
				   this.curpage=page;
				   this.dataRecv();
			   }
		}
	})
</script>
</body>
</html>