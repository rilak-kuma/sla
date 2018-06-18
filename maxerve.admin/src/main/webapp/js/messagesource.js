var messageSource = {};
var properties = {};
$.ajax({
	url: '/cmmn/messagesource.json',
	async: false,
	success: function (data) {
		messageSource = data.messageSource.value;
		properties = data.properties;
	}
});

function getMessage(key) {
	var msg = messageSource[key] ? messageSource[key]:'';

	if (arguments.length > 1) {
		for (var i = 1; i < arguments.length; i++) {
			msg = msg.replace('{' + (i-1) + '}', arguments[i]);
		}
	}

	return msg;
}