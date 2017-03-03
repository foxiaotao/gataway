jQuery(document).ready(function () {
	$("#success").click(function(){
		showSuccessDialog("成功的消息提示");
	});
	$("#error").click(function(){
		showErrorDialog("错误的消息提示");
	$("#warn").click(function(){
		showWarningDialog("警告消息提示");
	});

    });
});

//showSuccessDialog("请阅读《软件使用和用户注册协议》");
//showWarningDialog("请阅读《软件使用和用户注册协议》");
//showErrorDialog("请阅读《软件使用和用户注册协议》");

