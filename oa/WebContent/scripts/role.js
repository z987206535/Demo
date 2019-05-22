window.onload = function(){

	var aclRoles = document.querySelectorAll(".aclRole");
    
	for(var i=0;i<aclRoles.length;i++){
        aclRoles[i].onclick = function(){
        	//1:显示div id="orgList"
        	var orgList = document.getElementById("orgList");
        	orgList.style.display="block";
        	//2:获取当前元素id  8——角色名称
        	var ids = this.id;
        	var id =  ids.split("_")[0];
        	var rname = ids.split("_")[1]; 
        	//3:拆分  16:01---16:15
        	//4:将角色名称赋值 id=roleName
        	document.getElementById("roleName").innerHTML = rname;;
        	
        }	
    }
	
	var closewin = document.getElementById("closewin");
	closewin.onclick = function(){
		var orgList = document.getElementById("orgList");
    	orgList.style.display="none";
	};
	
	
};