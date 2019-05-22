	  //点击添加按钮触发的时间
	  function add(){
		document.getElementById("tj").style.display = "block";
	  }
	  
	  //关闭
	  function close1(){
		  document.getElementById("tj").style.display = "none";
      }
	  
	  //点击查询按钮触发的方法
	  function find(){
		  var types = document.getElementsByName("types")[0].value;
		  var startTime = document.getElementsByName("startTime")[0].value;
		  var endTime = document.getElementsByName("endTime")[0].value;
		 //console.log(types+startTime + endTime);
		 if(startTime.length == 0 && endTime.length == 0){
			 location.href = "list?types="+types+"&pageNo=1";
		 }else if(startTime.length != 0 &&endTime.length == 0){
			 location.href = "list?types="+types+"&startTime="+startTime;
		 }else{
			 location.href = "list?types="+types+"&startTime="+startTime+"&endTime="+endTime;
		 }
	  }
	  
	 
	  
	 
			
