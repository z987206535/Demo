 window.onload = function(){
	 //表格隔行变色
	 var trs = document.querySelectorAll("#tab tr");   
	 //循环，判断如果奇数行添加背景颜色
	 for(var i=0;i<trs.length;i++){
		 if(i%2==1){
			 trs[i].style.backgroundColor="#fff";
		 }else{
			 trs[i].style.backgroundColor="#ccc";
		 }
	 }
	 
	//1:为每一个删除按钮添加点击事件
	var dels = document.querySelectorAll("a.delOrg");
	for(var i=0;i<dels.length;i++){
	  dels[i].onclick = function(){	
	   //2:显示删除确认框
	   var delName = document.getElementById("delName");
	   //查找父元素
	   var tr = this.parentNode.parentNode;
	   var td = tr.getElementsByTagName("td")[1];
	   //获取删除人员名称
	   delName.innerHTML = td.innerText;
	   document.getElementById("deleteDialog").style.display="block";
	   //3:删除人员名称显示确认确
	   var id = tr.getElementsByTagName("td")[0].innerText;
	   document.getElementById("delOK").className=id;
	  }
	}
	//4:为确定按钮添加点击事件
	var delOK = document.getElementById("delOK");
	delOK.onclick = function(){
	  //1:获取人员id
	  var id = this.className;
	  //2:重定向
	  location.href = "person?command=del&id="+id;
	};
	//5:为取消按钮添加点击事件
	var delNo = document.getElementById("delNo");
	delNo.onclick = function(){
	document.getElementById("deleteDialog").style.display="none";
		
	};
	
	//14:08---14:13
	var addPersonDialog = document.getElementById("addPersonDialog");
	//6:添加人员
	var addPerson = document.getElementById("addPerson");
	addPerson.onclick = function(){
		addPersonDialog.style.display = "block";	
	};
	
	//7:选择机构
	var orgList = document.getElementById("orgList");
	var selOrg = document.getElementById("selOrg");
	selOrg.onclick = function(){
		orgList.style.display="block";
	};
	
	//8:为每一个inpu绑定一个点击事件
	var orgList = document.getElementById("orgList");
	orgList.onclick = function(){
	  //a:获取事件对象
	  var e = window.event || arguments[0];
	  //b:获取目标对象
	  var target = e.srcElement || e.target;
	  //c:判断是不是input
	  if(target.nodeName == "INPUT"){
	  //d:获取className值 30_四川分公司
	  var id_name = target.className;
	  //e:拆分  id  name
	  var id = id_name.split("_")[0];
	  var name = id_name.split("_")[1];
	  //f:将机构名称保存 id=orgname
	  document.getElementById("orgname").value=name;
	  //g:id  保存          addPersonDialog 添加隐藏域
	  document.getElementById("oid").value=id;
	  //i:自己隐藏
	  this.style.display = "none";
	  }	  
	};
	
	
	
	
	//#------------------------------
	//表单验证
	//1:获取表单
	var addPersonForm = document.getElementById("addPersonForm");
	//2:创建对应的元素正则表达式
	//所属机构: orgname
	var orgnameReg = /^[\u4e00-\u9fa5a-z0-9]{2,25}$/i;
	//人员姓名: name
	var nameReg = /^[\u4e00-\u9fa5a-z0-9]{2,10}$/i;
	//出生日期: age  2000-10-10
	var ageReg = /^\d{4}-\d{2}-\d{2}$/;
	//电话tel
	var telReg = /^1[345789]\d{9}$/;
	//地址addr
	var addrReg = /^[\u4e00-\u9fa5a-z0-9]{1,10}$/;
	//描述descr
	var descrReg = /^[\u4e00-\u9fa5a-z0-9]{5,50}$/;
	//出错信息保存span  addOrgMsg
	var addOrgMsg = document.getElementById("addOrgMsg");
	
	//a:使用onblur验证用户名
	var name = document.getElementById("name");
	name.onblur = testName;
	function testName(){
		var name = document.getElementById("name");
		if(!nameReg.test(name.value)){
			addOrgMsg.innerHTML = "用户名格式不正确";
			name.style.borderColor="red";
			return false;
		}else{
			addOrgMsg.innerHTML = "";
			name.style.borderColor="#abc";
			return true;
		}
	}
	//b:验证电话
	var tel = document.getElementById("tel");
	tel.onblur = testTel;
	function testTel(){
		var tel = document.getElementById("tel");
		if(!telReg.test(tel.value)){
			addOrgMsg.innerHTML = "电话格式不正确";
			tel.style.borderColor = "red";
			return false;
		}else{
			addOrgMsg.innerHTML = "";
			tel.style.borderColor = "#abc";
			return true;
		}		
	}
	
	//地址
	var addr = document.getElementById("addr");
	addr.onblur = testaddr;
	function testaddr(){
		var addr = document.getElementById("addr");
		if(!addrReg.test(addr.value)){
			addOrgMsg.innerHTML = "地址格式不正确";
			addr.style.borderColor = "red";
			return false;
		}else{
			addOrgMsg.innerHTML = "";
			addr.style.borderColor = "#abc";
			return true;
		}		
	}	
	//描述
	var descr = document.getElementById("descr");
	descr.onblur = testdescr;
	function testdescr(){
		var descr = document.getElementById("descr");
		if(!descrReg.test(descr.value)){
			addOrgMsg.innerHTML = "描述格式不正确";
			descr.style.borderColor = "red";
			return false;
		}else{
			addOrgMsg.innerHTML = "";
			descr.style.borderColor = "#abc";
			return true;
		}		
	}	
	
	//3:为表单添加 onsubmit事件
	addPersonForm.onsubmit = function(){
	//4:验证 出错 返回false
	var orgname = document.getElementById("orgname");
	if(orgname.value == ""){
		addOrgMsg.innerHTML = "必须选项所属机构";
		return false;
	}
	var age = document.getElementById("age");
	if(age.value == ""){
		addOrgMsg.innerHTML = "必须选项年龄";
		return false;
	}	
	//  将出错信息保存在<span></span>
	//5:全正确返回true
		return testName()&&testTel()&&testaddr()&&testdescr();
	};	
	
	
   var age = document.getElementById("age");
   age.onclick = function(){
	   WdatePicker();
   }
	
 };//unload 
 
 
 
 
 