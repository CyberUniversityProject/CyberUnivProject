<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식단 api test중입니다</title>
<!-- jquery 로딩 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

	<script>	
	 $(document).ready(function(){
		 var url = 'https://open.neis.go.kr/hub/mealServiceDietInfo?'
				  +'ATPT_OFCDC_SC_CODE=T10&Type=json&pIndex=1&pSize=100';
		 var param = '';
		 ajaxCallApiTest(url, param, ApiCallBack);
	 })
	 
	 function ajaxCallApiTest(url, param, callback) {
		 
		 $.ajax({
			 url: url,
			 async: true,
			 type: "POST",
			 data: param,
			 dataType: 'json',
			 success: callback,
			 error:function(request,textStatus){
				 var format = new OpenLayers.Format.WFSDescribeFeatureType();
				 var doc = request.responseXML;
				 var describeFeatureType = format.read(doc);
				 alert(" describeFeatureType = "+ describeFeatureType + " testStatus = "+textStatus);
			 }
		 });
	 }
	
	 function ApiCallBack(json) {
		 
		 if(json.schoolInfo != null){
			 
			 document.write("<table style='border:1px solid #b5b5b5;paddingL1px;margin:1px;'>");
			 
			 document.wirte("<tr>");
			 
			 $.each(json.schoolInfo[1].row[0],function(key,state){
				 document.write("<td style='color:black;background-color:#CEFBC9'>");
				 document.write(key );
				 document.write("</td>");
			 });
			 
			 document.write("</tr>")
			 
			 for(var i=0;i<5;i++) {
				 document.write("<tr>");
				 $.each(json.schoolInfo[1].row[i],function(key,state){
					 document.write("<td style='color:black;background-color:#F0F8FF'>");
					 document.write(eval("json.schoolInfo[1].row["+i+"]."key) +" <br> ");
					 document.write("</td>");
				 });
				 document.write("</tr>");
			 }
			 
			 document.write("</table>");
		 }
	 }
	</script>
                                                                    
</body>
</html>