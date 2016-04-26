$(function(){
    $('#test_div').bind('click',function(event){
        alert("123");
  });
});
window.Location = {
    alipay: function (message) {
        try {
            window.APP_NATIVE.alipay(message);
        } catch (e) {
            alert("222");
            return;
        }
    },
    weixin: function (message) {
        try {
            window.APP_NATIVE.weixin(message);
        } catch (e) {
            alert("222");
            return;
        }
    }
};


