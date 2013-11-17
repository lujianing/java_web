var BASE = '/smart_html';

$(function() {
    // 全局 AJAX 设置
    $.ajaxSetup({
        error: function() {
            location.href = BASE + '/';
        }
    });
});