function connectWebViewJavascriptBridge(callback) {
	if (window.WebViewJavascriptBridge) {
		callback(WebViewJavascriptBridge)
	} else {
		document.addEventListener('WebViewJavascriptBridgeReady', function () {
			callback(WebViewJavascriptBridge)
		}, false);
	}
}

function sendPatientInfo(data) {
    var data = JSON.parse(data);
	$("#recordNo").text(data.recordNo);
	$("#name").text(data.name);
	$("#sex").text(data.sex);
	$("#age").text(data.age);
	$("#patientNo").text(data.patientNo);
	$("#bedNo").text(data.bedNo);
	$("#loc").text(data.loc);

}

function sendReport(data) {
    var data = JSON.parse(data);
	$("#observe").text(data.observe);
	$("#opinion").text(data.opinion);
	$("#method").text(data.method);
	$("#part").text(data.part);
}

connectWebViewJavascriptBridge(function (bridge) {
	bridge.init(function (message, responseCallback) {

	});

	bridge.registerHandler("sendPatientInfo", function (data, responseCallback) {
	    sendPatientInfo(data)
	});

	bridge.registerHandler("sendReport",function (data, responseCallback) {
        sendReport(data)
    });
});

