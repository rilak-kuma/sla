function ajaxSubmit(url, data, success_callback, ar_callbackCode) {
	var queCode = pageLoadQue();

	if (!ar_callbackCode) {
		ar_callbackCode = ['0000'];
	}

	if (!success_callback) {
		success_callback = function() {};
	}

	var opt = {
		url: url,
		dataType: 'json',
		method: 'POST',
		data: data,
		success: function(data, textStatus, jqXHR) {
			var resultCode = data.resultCode;

			if ($.inArray(data.resultCode, ar_callbackCode) === -1) {
				var msg = data.msg;
				if (msg) {
					alert(msg);
				} else if (messageSource['result.code.' + resultCode]) {
					alert(messageSource['result.code.' + resultCode]);
				}
			} else if (data.resultCode == '9999') {
				alert(messageSource['result.code.' + resultCode]);
			} else {
				success_callback(data);
			}
		},
		statusCode: {
			401: function(){
				alert('권한이 없습니다.');
				location.href = '/';
			},
			403: function(){
				alert('권한이 없습니다.');
				location.href = '/';
			}
		},
		error: function(xhr, status, err) {
			alert('error');
			console.log(xhr);
		},
		complete: function(){
			pageLoadQue(queCode);
		}
	};

	if(jQuery.type(data) === "string"){
		opt.contentType = "application/json";
	}

	$.ajax(opt);

}

function pageLoadQue(queCode) {
	var ques = $(document).data('ques');

	if (!ques) {
		$(document).data('ques', []);
		ques = $(document).data('ques');
	}

	if (queCode) {
		var queIndex = $.inArray(queCode, ques);
		if (queIndex !== -1) {
			ques.splice(queIndex, 1);
		}
	} else {
		var rnd = rand(100000, 999999);
		queCode = new Date().getTime() + '_' + rnd;

		ques.push(queCode);
	}

	if (ques.length) {
		pageLoad();
	} else {
		$.unblockUI();
	}

	return queCode;
}

function rand(min, max){
	var rand = Math.ceil((max - min + 1) * Math.random() + min);

	return rand;
}

function pageLoad() {
	if (!$('.loading').length) {
		$('body').append('<img src="/img/loading.png" class="loading"/>');
	}

	$.blockUI({
		message: $('.loading'),
		css: {
			backgroundColor: '',
			border: 0
		}
	});
}

function ckConfig(opt) {
	var conf = {
		toolbar: [
			{ name: 'document', items: [ 'Source', '-', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
			{ name: 'clipboard', items : [ 'Paste','PasteText','-','Undo','Redo' ] },
			{ name: 'colors', items : [ 'TextColor','BGColor' ] },
			{ name: 'insert', items : [ 'Image', 'Table', 'HorizontalRule', 'SpecialChar' ] },
			{ name: 'tools', items: [ 'Maximize' ] },
			{ name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
			'/',
			{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
			{ name: 'links', items : [ 'Link','Unlink' ] },
			{ name: 'styles', items : [ 'Styles', 'lineheight', 'Format','Font','FontSize' ] }
		],
		//contentsCss: '/css/style.css',
		enterMode: CKEDITOR.ENTER_DIV,
		extraPlugins: 'uploadimage,image2,lineheight',
		filebrowserUploadUrl: '/file/editor_upload.do',
		'line_height': '50%;80%;100%;120%;140%;160%;180%;200%'
	};

	CKEDITOR.on( 'dialogDefinition', function( ev ) {
	      // Take the dialog name and its definition from the event data.
		var dialogName = ev.data.name;

		if(dialogName == 'image2'){//이미지 속성창
			var dialogDefinition = ev.data.definition;
			var dialog = ev.data.definition.dialog;

			dialog.on('show', function () {
				if(this["data-once"] != "Y"){
					this["data-once"] = "Y";
					$(".cke_dialog_body .cke_dialog_contents .cke_dialog_ui_vbox_child .cke_dialog_ui_labeled_label").last().text("서버로 전송 20MB");
				}
			});
		}
	});

	if (opt) {
		$.extend(conf, opt);
	}

	return conf;
}

//파일다운로드
function fileDownload(filePath) {
	if (!$('body').is(':has(iframe#downloadIframe)')) {
		$('body').append('<iframe id="downloadIframe" class="hidden"></iframe>');
	}

	$('iframe#downloadIframe').attr('src', '/cmmn/file_download.do?filePath=' + filePath);
}

//숫자
$.fn.onlyNumber = function (p) { // 숫자만, 1: 소숫점, 2: 마이너스, 3: 마이너스 소숫점
    $(this).each(function(i) {
    	$(this).off('keypress keyup');
    	
    	$(this).on('keypress keyup',function(e){
    		var val = $(this).val();
        	var regexp = /[^-\.0-9]/g;
        	var repexp = "";
        	var len = 3;
        	val = val.replace(regexp,repexp);
        	if(val.split(".").length > 1){
        		val = val.split(".")[0] +"."+ val.split(".")[1].substr(0,len);
    	    }
    		
        	var newval;
            switch (p) {
			case 1:
				regexp = /^(-?)([0-9]*)(\.?)([^0-9]*)([0-9]*)([^0-9]*)/;
				repexp = '$2$3$5';
				break;
			case 2:
				regexp = /^(-?)([0-9]*)([^0-9]*)([0-9]*)([^0-9]*)/;
				repexp = '$1$2$4';
				break;
			case 3:
				regexp = /^(-?)([0-9]*)(\.?)([^0-9]*)([0-9]*)([^0-9]*)/;
				repexp = '$1$2$3$5';
				break;

			default:
				regexp = /[^0-9]/g;
				repexp = '';
				break;
			}
            
            var newval = val.replace(regexp,repexp);
            
            if ($(this).val() != newval) {
            	$(this).val(newval);
			}
        });
    });
};

(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

jQuery(function($){
	$.unblockUI();

	// datepicker default
	$.datepicker.setDefaults({
		dateFormat: 'yy.mm.dd',
		numberOfMonths: 1,
		buttonImageOnly: true,
		autoSize: true, //오토리사이즈(body등 상위태그의 설정에 따른다)
		currentText: '오늘 날짜', // 오늘 날짜로 이동하는 버튼 패널
		closeText: '',  // 닫기 버튼 패널
		showMonthAfterYear: true, // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다.
		yearSuffix: '',
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], // 요일
        monthNames: ['.01', '.02', '.03', '.04', '.05', '.06', '.07', '.08', '.09', '.10', '.11', '.12'],
        monthNamesShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
	});

	$(window).on('beforeunload', function(){
		pageLoad();
	});

	$('button').on('click', function(){
		return false;
	});

	$.validator.setDefaults({
		onkeyup: false,
		onclick: false,
		onfocusout: false,
		showErrors: function(errorMap, errorList) {
			if (this.numberOfInvalids()) {
				alert(errorList[0].message);
				$(errorList[0].element).focus();
			}
		}
	});

	/****************	 jQuery validator addMethod start	****************/
	$.validator.addMethod("lettersonly", function(value, element){
		return this.optional(element) || /^[a-z]+$/i.test(value);
	}, "Letters only please");

	$.validator.addMethod("lettersSpaceonly", function(value, element){
		return this.optional(element) || /^[a-z," "]+$/i.test(value);// accept space
	}, "Letters and spaces only please");
	$.validator.addMethod("pwd", function(value, element){
		 //var chk1 = /^[a-zA-Z\d]{8,20}$/i;  //a-z와 0-9이외의 문자가 있는지 확인
		 var chk1 = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[\~,\!,\@,\#,\%,\$,\*,\(,\),\=,\+,\_,\.,\|,\[,\],\{,\},\<,\>,\`,\?,\/,\:,\;,\-,\&,\',\",\\]).*$/; //a-z와 0-9와 특수문자가 있는지 확인
		 //var chk2 = /[a-zA-Z]/i;  //적어도 한개의 a-z 확인
		 //var chk3 = /\d/;  //적어도 한개의 0-9 확인
		return this.optional(element) || (chk1.test(value));
	}, "password");
	$.validator.addMethod("regx", function(value, element, regexpr) {
	    return this.optional(element) || regexpr.test(value);
	}, "regx");

	$.validator.addMethod('equalOnly', function(value, element, $target){
		return this.optional(element) || $target.val() === value;
	}, 'equalOnly');

	$.validator.addMethod('preventEqual', function(value, element, $target){
		return this.optional(element) || $target.val() !== value;
	}, 'prevent equal');
	/****************	 jQuery validator addMethod end	****************/
});