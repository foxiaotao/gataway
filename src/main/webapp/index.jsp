<html>
<body>
<script>
	var arr = new Array();
	arr.push("a");
	arr.push("b");
	arr.push("city");
	arr.push("d");
	
	arr.unshift("1","2");
	if(arr.indexOf("city")>-1){
		//如果有city，则显示省份
		arr.splice(arr.indexOf("city"),0,"province");
	}
	alert(arr.join(","))
</script>
<h2>Hello World!</h2>

</body>
</html>
