<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>财蜜后台管理平台</title>
    <link href="${contextPath}/resources/aol/css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="">
    <!-- form验证 -->
    <link rel="stylesheet" href="${contextPath}/styles/formValidator.2.2.1/css/validationEngine.jquery.css"
          type="text/css"/>
    <script src="${contextPath}/styles/formValidator.2.2.1/js/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${contextPath}/styles/formValidator.2.2.1/js/languages/jquery.validationEngine-zh_CN.js"
            type="text/javascript" charset="utf-8"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"
            charset="utf-8"></script>


    <style type="text/css">
        .main-inner-left {
            float: left;
            width: 902px;
            height: 620px;
            overflow: visible;
            margin: 0px 0px;
            position: relative;
            clear: both;
            background-image: url(${contextPath}/resources/aol/images/login_image.png);
            background-position: 0% 0%;
            background-repeat: no-repeat no-repeat;
        }

        .main-inner-right {
            float: left;
            width: 396px;
            height: 620px;
            overflow: visible;
            margin: 0px 0px;
            position: relative;
        }

        .login {
            width: 338px;
            height: 328px;
            position: absolute;
            top: 76px;
            right: 60px;
            float: right;
            background: #fff;
            border: 1px solid #b7c2c9;
            _display: inline;
            text-align: left;
            position: relative;
            z-index: 2;
            border-radius: 2px;
        }

        .topTitle {
            float: right;
            position: relative;
            height: 18px;
            margin-top: 31px;
            margin-right: 31px;
            display: block;
            color: #666;
            font-size: 14px;
        }


    </style>
    <script>
        function readCookie(name) {
            var search;
            search = name + "=";
            offset = document.cookie.indexOf(search);
            if (offset != -1) {
                offset += search.length;
                end = document.cookie.indexOf(";", offset);
                if (end == -1) {
                    end = document.cookie.length;
                }
                return unescape(document.cookie.substring(offset, end));
            } else {
                return "";
            }
        }

        function writeCookie(name, value) {
            exp = new Date();
            exp.setTime(exp.getTime() + (86400 * 1000 * 30));
            document.cookie = name + "=" + escape(value) + "; expires=" + exp.toGMTString() + "; path=/";
        }




        jQuery(document).ready(function () {
            //绑定输入框事件
            //用户名
            if ($('#username').val() == '') {
                $('#username').next().show();
            } else {
                $('#username').next().hide();
            }
            $('#userNameInputLine').mouseover(function () {
                $(this).attr('class', function () {
                    return this.className + ' loginFormIpt-over'
                });
            });
            $('#userNameInputLine').mouseout(function () {
                $(this).attr('class', function () {
                    return this.className.replace(/\sloginFormIpt-over/g, '')
                });
            });
            $('#username').focus(function () {
                $(this).next().hide();
                $('#checkDiv').html("");
                $(this).attr('class', function () {
                    return this.className + ' loginFormTdIpt-focus'
                });
                $('#userNameInputLine').attr('class', function () {
                    return this.className + ' loginFormIpt-focus'
                });
            });
            $('#username').click(function () {
                $(this).next().hide();
            });
            $('#username').blur(function () {
                if ($(this).val() == '') {
                    $(this).next().show();
                }
                $('#userNameInputLine').attr('class', function () {
                    return this.className.replace(/\sloginFormIpt-focus/g, '')
                });
                if ($(this).val() == '') {
                    $('#userNameInputLine').attr('class', function () {
                        return this.className + ' showPlaceholder'
                    });
                    $(this).attr('class', function () {
                        return this.className.replace(/\sloginFormTdIpt-focus/g, '')
                    });
                }
            });

            //密码
            if ($('#password').val() == '') {
                $('#password').next().show();
            } else {
                $('#password').next().hide();
            }
            $('#pwdInputLine').mouseover(function () {
                $(this).attr('class', function () {
                    return this.className + ' loginFormIpt-over'
                });
            });
            $('#pwdInputLine').mouseout(function () {
                $(this).attr('class', function () {
                    return this.className.replace(/\sloginFormIpt-over/g, '')
                });
            });
            $('#password').focus(function () {
                $(this).next().hide();
                $('#passwordError').html("");
                $(this).attr('class', function () {
                    return this.className + ' loginFormTdIpt-focus'
                });
                $('#pwdInputLine').attr('class', function () {
                    return this.className + ' loginFormIpt-focus'
                });
            });
            $('#password').click(function () {
                $(this).next().hide();
            });
            $('#password').blur(function () {
                if ($(this).val() == '') {
                    $(this).next().show();
                }
                $('#pwdInputLine').attr('class', function () {
                    return this.className.replace(/\sloginFormIpt-focus/g, '')
                });
                if ($(this).val() == '') {
                    $('#pwdInputLine').attr('class', function () {
                        return this.className + ' showPlaceholder'
                    });
                    $(this).attr('class', function () {
                        return this.className.replace(/\sloginFormTdIpt-focus/g, '')
                    });
                }
            });

            //验证码
            if ($('#captcha_key').val() == '') {
                $('#captcha_key').next().show();
            } else {
                $('#captcha_key').next().hide();
            }
            $('#checkCodeInputLine').mouseover(function () {
                $(this).attr('class', function () {
                    return this.className + ' loginFormIpt-over'
                });
            });
            $('#checkCodeInputLine').mouseout(function () {
                $(this).attr('class', function () {
                    return this.className.replace(/\sloginFormIpt-over/g, '')
                });
            });
            $('#captcha_key').focus(function () {
                $(this).next().hide();
                $('#checkCodeError').html("");
                $(this).attr('class', function () {
                    return this.className + ' loginFormTdIpt-focus'
                });
                $('#checkCodeInputLine').attr('class', function () {
                    return this.className + ' loginFormIpt-focus'
                });
            });
            $('#captcha_key').click(function () {
                $(this).next().hide();
            });
            $('#captcha_key').blur(function () {
                if ($(this).val() == '') {
                    $(this).next().show();
                }
                $('#checkCodeInputLine').attr('class', function () {
                    return this.className.replace(/\sloginFormIpt-focus/g, '')
                });
                if ($(this).val() == '') {
                    $('#checkCodeInputLine').attr('class', function () {
                        return this.className + ' showPlaceholder'
                    });
                    $(this).attr('class', function () {
                        return this.className.replace(/\sloginFormTdIpt-focus/g, '')
                    });
                }
            });

            //登录按钮
            $('#loginBtn').mouseover(function () {
                $(this).attr('class', function () {
                    return this.className + ' btn-login-hover'
                });
            });
            $('#loginBtn').mouseout(function () {
                $(this).attr('class', 'btn btn-login');
            });
            $('#loginBtn').mousedown(function () {
                $(this).attr('class', function () {
                    return this.className + ' btn-login-active'
                });
            });
        });

    </script>
</head>

<body>
<div class="loginFormIpt loginFormIptWiotTh" style="text-align: center">
    <button id="loginBtn" class="btn btn-login" style="margin-left: 75px;" type="buttion"
            tabindex="7">登 录
    </button>
</div>

    <script type="application/javascript">
        $(function () {
            $("#loginBtn").click(function () {
                $.post("${contextPath}/weixin/toOauth", function (result) {
                    var obj = eval('(' + result + ')');
                    WeixinJSBridge.invoke( 'getBrandWCPayRequest', {
                            "appId": obj.appId,     //公众号名称，由商户传入
                            "timeStamp": obj.timeStamp,         //时间戳，自1970年以来的秒数
                            "nonceStr": obj.nonceStr, //随机串
                            "package": obj.package,
                            "signType": obj.signType,         //微信签名方式：
                            "paySign": obj.paySign //微信签名
                        }, function (res) {
                            if (res.err_msg == "get_brand_wcpay_request：ok") {
                            }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                        }
                    );
                });
            });
        });
    </script>
</body>
</html>