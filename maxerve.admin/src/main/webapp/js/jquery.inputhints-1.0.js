// jQuery Input Hints plugin
// Copyright (c) Rob Volk
// https://github.com/robvolk/jQuery.InputHints
// http://robvolk.com/jquery-form-input-hints-plugin

!function(t){t.fn.inputHints=function(){function n(n){""==jQuery(n).val()&&jQuery(n).val(t(n).attr("placeholder")).addClass("hint")}function i(n){t(n).val()==t(n).attr("placeholder")&&t(n).val("").removeClass("hint")}var u=t(this);return u.each(function(){n(this)}),u.closest("form").submit(function(){return u.each(function(){i(this)}),!0}),u.focus(function(){i(this)}).blur(function(){n(this)})}}(jQuery);
