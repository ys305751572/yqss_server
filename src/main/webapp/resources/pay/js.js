$(function(){
    $('#test_div').bind('click',function(event){
        alert("123");
  });
});
//window.android = {
//    showToast: function (message) {
//        alert("message:" + message);
//        try {
//            window.APP_NATIVE.showToast(message);
//        } catch (e) {
//            alert("222");
//            return;
//        }
//    }
//};