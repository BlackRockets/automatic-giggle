$(function() {
				getSheng('shen'); 
				yibuchashi();
				changeShis()
			});

var allshen = "";
var allshi = "";
var allxian = "";

var num = "";
var num1 = "";

function getSheng(sheng) {
	$.ajax({
		url : "findAllShen",
		dataType : "JSON",
		async:false,
		success : function(lists) {
			var shengs = document.getElementById("shengs").value;
			var re = "";
			for ( var shen in lists) {
				if(lists[shen].name == shengs ){
					allshen += "<option class='shengde' selected='selected' value=" + lists[shen].code + ">"
					+ lists[shen].name + "</option>";
					 num =lists[shen].code; 
					 re =0 ;
				}else{
					allshen += "<option class='shengde' value=" + lists[shen].code + ">"
					+ lists[shen].name + "</option>";
					re = "";
				}
			
			
			}
			if(re == "0"){
				qingkong()
			}
			$("#" + sheng).append(allshen);
		}
	});
}

function qingkong(){
	document.getElementById("xiang").value = "";
	document.getElementById("cun").value = "";
	document.getElementById("hu").value = "";
}
function yibuchashi(){
	if(num != ""){
		$.ajax({
			url : "findShiByShen",
			data : "shenCode=" + num,
			dataType : "JSON",
			async:false,
			success : function(lists) {
				var shengs = document.getElementById("shisss").value;
				$(shi).empty(); // 清空市option
				$(xian).empty(); // 清空县option
				allshi = "<option value='0' class='shide' >请选择</option>"; // 清空市变量
				allxian = "<option value='0' class='xiande' >请选择</option>"; // 清空县变量
				for ( var i in lists) {
					if(shengs == lists[i].name){
						allshi += "<option class='shide' selected='selected' value=" + lists[i].code + ">" // 给市变量赋值
						+ lists[i].name + "</option>";	
						num1 = lists[i].code;
					}else{
						allshi += "<option  class='shide' value=" + lists[i].code + ">" // 给市变量赋值
						+ lists[i].name + "</option>";
					}
					
				}
				$(shi).append(allshi); // 添加市option
				$(xian).append(allxian); // 添加县option
			}
		});
	}

	
}

function changeShis() {
if(num1 != ""){
	$.ajax({
		url : "findXianByShi",
		data : "shiCode=" + num1,
		dataType : "JSON",
		success : function(lists) {
			var shengs = document.getElementById("xiansss").value;
			$(xian).empty();
			allxian = "<option value='0' class='xiande' >请选择</option>";
			for ( var i in lists) {
				if(shengs == lists[i].name){
					allxian += "<option class='xiande' selected='selected' value=" + lists[i].code + ">"
					+ lists[i].name + "</option>";
				}else{
					allxian += "<option class='xiande'  value=" + lists[i].code + ">"
					+ lists[i].name + "</option>";
				}
			
			}
			$(xian).append(allxian);
		}
	});
}

	
}
function changeShen(shenCode, sheng, shi, xian) {
	shi = '#' + shi;
	xian = '#' + xian;
	$.ajax({
		url : "findShiByShen",
		data : "shenCode=" + shenCode,
		dataType : "JSON",
		success : function(lists) {
			document.getElementById("xiang").value = "";
			document.getElementById("cun").value = "";
			document.getElementById("hu").value = "";
			$(shi).empty(); // 清空市option
			$(xian).empty(); // 清空县option
			allshi = "<option value='0' class='shide'>请选择</option>"; // 清空市变量
			allxian = "<option value='0' class='xiande'>请选择</option>"; // 清空县变量
			for ( var i in lists) {
				allshi += "<option class='shide' value=" + lists[i].code + ">" // 给市变量赋值
						+ lists[i].name + "</option>";
			}
			$(shi).append(allshi); // 添加市option
			$(xian).append(allxian); // 添加县option
		}
	});
}
function changeShi(shiCode, sheng, shi, xian) {
	xian = '#' + xian;
	$.ajax({
		url : "findXianByShi",
		data : "shiCode=" + shiCode,
		dataType : "JSON",
		success : function(lists) {
			$(xian).empty();
			document.getElementById("xiang").value = "";
			document.getElementById("cun").value = "";
			document.getElementById("hu").value = "";
			allxian = "<option value='0' class='xiande'>请选择</option>";
			for ( var i in lists) {
				allxian += "<option class='xiande' value=" + lists[i].code + ">"
						+ lists[i].name + "</option>";
			}
			$(xian).append(allxian);
		}
	});
}

function xianwsx(){
	document.getElementById("xiang").value = "";
	document.getElementById("cun").value = "";
	document.getElementById("hu").value = "";
}
//------------------------------------------
function getDate(){
	var year='';
	for(var i=2000;i<2051;i++){
		year +='<option value='+i+'>&nbsp;&nbsp;'+i+'</option>';
	}
	$("#year").append(year);
}
function changeYear(){
	if($("#year").val()!="请选择"){
		$("#month").empty();
		$("#day").empty();
		var month='<option selected value="请选择">请选择</option>';
		for(var i=1;i<13;i++){
			month +='<option value='+i+'>&nbsp;&nbsp;'+i+'</option>';
		}
		$("#month").append(month);
		$("#day").append('<option selected value="请选择">请选择</option>');
	}else{
		$("#month").empty();
		$("#day").empty();
		$("#month").append('<option selected value="请选择">请选择</option>');
		$("#day").append('<option selected value="请选择">请选择</option>');
	}
}
function changeMonth(){
	if($("#year").val()!="请选择"){
		var year=$("#year").val();
		var month=$("#month").val();
		var mydate=new Date(year,month,1);
		var count=new Date(mydate.getTime()-864e5).getDate();
		$("#day").empty();
		var day='<option selected value="请选择">请选择</option>';
		for(var i=1;i<count+1;i++){
			day +='<option value='+i+'>&nbsp;&nbsp;'+i+'</option>';
		}
		$("#day").append(day);
	}else{
		$("#day").empty();
		$("#day").append('<option selected value="请选择">请选择</option>');
	}
}
//------------------------------------------
function getKind(){
	$.ajax({
		url:"getKind",
		dataType:"JSON",
		success:function(obj){
			$("#nzw_spacies").empty();
			var spacies='<option selected value="请选择">请选择</option>';
			for(var i in obj){
				spacies+='<option value='+obj[0].id+'>&nbsp;&nbsp;'+obj[i].crop_species+'</option>';
			}
			$("#nzw_spacies").append(spacies);
		}
	});
}