$(function(){
    $('#test_div').bind('click',function(event){
        alert("123");
  });
});
window.Location = {
    alipay: function (message) {
        try {
            window.APP_NATIVE.appAlert(message);
        } catch (e) {
            alert("222");
            return;
        }
    },
    weixin: function () {
        try {
            window.APP_NATIVE.appPush();
        } catch (e) {
            alert("222");
            return;
        }
    }
};


