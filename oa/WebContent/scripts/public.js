window.onload = function(){

//完成删除机构
//1:获取所有删除元素a
var alis = document.querySelectorAll("a.delOrg");
var deleteDialog = document.getElementById("deleteDialog");
var delName = document.getElementById("delName");
//2:为每一个a元素添加点击事件
for(var i=0;i<alis.length;i++){
  alis[i].onclick = function(){
	  //4:获取删除机构名称
	  var tr = this.parentNode.parentNode;
	  var tds = tr.getElementsByTagName("td");
	  var orgName = tds[1].innerText;
	  //5:获取删除机构id
	  var orgId = tds[0].innerText;
	  //6:将机构名称赋值div
	  delName.innerHTML = orgName;
	  //7:显示div
	  deleteDialog.style.display = "block";
	  //8:保存机构id
	  deleteDialog.className = orgId;
  }	
}

var delOK = document.getElementById("delOK");
var delNo = document.getElementById("delNo");
//点击确定按钮
delOK.onclick = function(){
	var div = this.parentNode;
	var id = div.className;
	location.href = "org?command=del&id="+id;
}
//点击取消按钮
delNo.onclick = function(){
	deleteDialog.style.display = "none";
}



//添加机构
var addOrg = document.getElementById("addOrg");
addOrg.onclick = function(){
	//1:获取第一个删除按钮
	//var delOrg = document.querySelector("a.delOrg");
	//var tr = delOrg.parentNode.parentNode;
	//var tds = tr.getElementsByTagName("td")[3];
	//2:父机构名称
	//var pname = tds.innerText;  //9:50--9:55
	//3:获取表单 pname
	//var pnameid = document.getElementById("pname");
	//4:将机构名称赋值 表单pname
	//pnameid.value = pname;
	
	var addOrgDialog = document.getElementById("addOrgDialog");
	addOrgDialog.style.display = "block";
	return false;
};

    //机构名称 机构描述
    var orgname = document.getElementById("orgname");
    var orgdescr = document.getElementById("orgdescr");
    var addOrgMsg = document.getElementById("addOrgMsg")
    var addOrgForm = document.getElementById("addOrgForm");
    //正则表达式
    var orgnamereg = /^[a-z0-9_\u4e00-\u9fa5]{3,25}$/i;
    function testOrgName(){
    	if(!orgnamereg.test(orgname.value)){
    	   addOrgMsg.innerHTML = "机构名称格式不正确";
    	   orgname.style.borderColor = "red";
    	   return false;	
    	}else{
     	   addOrgMsg.innerHTML = "机构名称正确";
    	   orgname.style.borderColor = "#abc";
    	   return true;	    		
    	} 
    }
    //调用函数
    orgname.onblur = testOrgName;
    function testOrgDescr(){
    	if(!orgnamereg.test(orgdescr.value)){
     	   addOrgMsg.innerHTML = "机构描述格式不正确";
     	  orgdescr.style.borderColor = "red";
     	   return false;	
     	}else{
      	   addOrgMsg.innerHTML = "机构名称正确";
      	 orgdescr.style.borderColor = "#abc";
     	   return true;	    		
     	}     	
    }
    orgdescr.onblur = testOrgName;
    addOrgForm.onsubmit = function(){
    	return testOrgName()&&testOrgName();
    }

}//onload





