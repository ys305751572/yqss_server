<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width,initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/web/css/common.css">
    <script src="${contextPath}/resources/js/jquery-1.7.2.min.js"></script>
    <title>看看一个大四女生，如何开上玛莎拉蒂</title>
    <script type="text/javascript">
        var countdown = 60;
        function settime(btn) {
            if (countdown == 0) {
                btn.removeAttribute("disabled");

                btn.value = "点击发送验证码";
                btn.value = btn.innerHTML;
                countdown = 60;
                btn.style.background = "#2ec3ab";
            } else {
                countdown--;
                btn.setAttribute("disabled", true);
                btn.value = countdown + "秒后重新发送";
                btn.style.background = "#aeaeae";
                setTimeout(function () {
                    settime(btn)
                }, 1000)
            }
        }
    </script>
</head>
<body>
<header class="header">
    <p>看看一个大四女生，如何开上玛莎拉蒂</p>
</header>
<section class="read">
    <span>导读</span>
    <p>
        &nbsp;&nbsp;&nbsp;半年，6个月，你能赚多少？每天朝九晚五的上班，算你月薪两万，6个月也顶多挣12万，这还是税前，什么？6个月买玛莎拉蒂？不可能！赞个10年8年再说吧！
    </p>
    <p>&nbsp;&nbsp;&nbsp;然后这件对于你们来说，算是“异想天开”的梦想，我却成功了。2015年大四毕业炒起原油，历时半年，赚到了我人生第一桶金、全款买了我人生中、第一台玛莎拉蒂。我可以说，有些人辛苦10年，也不一定抵得上我一天炒油赚的！</p>
</section>
<div class="arrow-down">
</div><!-- 向下三角形 -->
<section class="read_img">
    <img src="${contextPath}/resources/web/images/1.png">
    <p>
        &nbsp;&nbsp;我是丹丹，一名金融系大四毕业生。自从上次自拍晒了新买的玛莎拉蒂、整个朋友圈都爆了！媒体都有好多人，问我是不是真的？求问赚钱秘诀！毕业半年，六个多月，一个一穷二白的应届毕业生能赚上百万，说起来你可能不信，但我确确实实通过炒油成功了！
    </p>
</section>
<div class="arrow-down">
</div><!-- 向下三角形 -->
<section class="read_img">
    <img src="${contextPath}/resources/web/images/2.png">
    <p>
        为什么炒油能赚这么多？听我从投资的角度给你们分析分析：
    </p>
    <p>
        1.石油作为一种不可再生的资源，未来只会越来越少，成为稀缺性石油产品，所以石油未来价格趋势指挥越来越高。
    </p>
    <p>
        2.生活中我们离不开石油产品，开车要加油，汽油、柴油、没有、燃料油等，修路用的沥青、润滑油都跟石油资源分不开。
    </p>
    <p>
        3.当前原油价格收行业恶性竞争、全球经济不景气的影响，价格基本居于近几年最低水位线，目前正蓄势待发！
    </p>
    <p>
        抓住现在这个机会入市炒油，可以说是前景大好.
        再加上，从14年开始，国家与俄、没、加四国签订一系列能源合作协议，旨在推动能源价格平稳增长。此外，G20峰会也出台一系列战略措施，来保障中国原油投资市场数据透明、投资空间自由开放、投资环境安全有序。
        未来几年，中国原油市场发展趋势，只会越来越好。所以，大学毕业，就把握好这一投资趋势的我，能月入百万。不光我自己赚，跟我同期炒油的几个朋友都在赚！
    </p>
</section>
<div class="arrow-down">
</div><!-- 向下三角形 -->
<section class="read_img">
    <img src="${contextPath}/resources/web/images/3.png">
    <p>
        你可能要问了，炒油难不难？真的这么容易赚钱吗？我没有经验，也不太懂得如何去操作，更不晓得行情，也能炒油赚钱吗？会不会亏啊？
    </p>
    <p>
        我的回答是：把握趋势，一定会成功！因为，就原油本身的投资属性来说，他的投资价值空间是不可限量的！我总结了以下4条原因，供投资原油的朋友参考。
    </p>
    <p>
        1.保证金交易：原油采取保证金交易，将投资成本大大降低，用3%的资金可以做100%的交易，用小成本就能轻松博取大利润。
    </p>
    <p>
        2.T+0交易：当天买入当天就可以卖出，每天可以交易无数次，增加哦获利机会，减少投资风险。
    </p>
    <p>
        3.双向交易：原油投资主要通过买卖差价来盈利，这和我们熟悉的股票类似，但它更具有优势的是：既可以先低买、再高卖，也可以先高卖、再低买，无论牛市熊市，炒油都能赚钱。
    </p>
    <p>
        4.资金银行第三方存管：客户资金完全实行第三方存管模式，保证了客户资金的安全性。
    </p>
</section>
<div class="arrow-down">
</div><!-- 向下三角形 -->
<section class="form_box">
    <img src="${contextPath}/resources/web/images/33.png">
    <form action="" method="post">
        <fieldset class="fieldset">
            <div class="inputbox">
                <span class="img_box"> <img src="${contextPath}/resources/web/images/4.png"></span>
                <input type="text" id="mobile" name="mobile" placeholder="请输入手机号码" />
            </div>
            <div class="password">
                <input type="text" id="code" name="code" placeholder="请输入您的验证码" class="inp" />
                <input type="button" id="codeBtn" value="点击输入验证码" class="btn"></button>
            </div>
            <div style="text-align: center;color: #ef6a1e;margin-top: 3rem;font-size: 1.6rem;font-weight: 700;position: relative;top: 1rem;">
                <span>点击立即加入送千元油卡 </span>
            </div>
            <input type="button" id="doSubmit" value="立即加入" class="submit"/>
        </fieldset>
    </form>
</section>
<script>
    mobile.setCustomValidity("手机号码格式错误");

    $(function () {
        $("#codeBtn").click(function () {
            $.ajax({
                url : "${contextPath}/moneymag/sendCode",
                type : "POST",
                data : {
                    "mobile" : $("#mobile").val()
                }
            });
            settime(this);
        });

        $("#doSubmit").click(function () {
            var mobile = $("#mobile").val();
            if(mobile == '' || mobile == null) {
                alert("手机号不能为空");
                return;
            }
            var p = /^1[345678][0-9]{9}$/;
            if(!p.test(mobile)) {
                alert("手机号格式错误");
                return;
            }

            var code = $("#code").val();
            if(code == '' || code == null) {
                alert("验证码不能为空");
                return;
            }
            $.ajax({
                url: "${contextPath}/moneymag/act/insert",
                type: "POST",
                data: {
                    "mobile": $("#mobile").val(),
                    "code": $("#code").val()
                },
                success: function (data) {
                    if (data.status == 0) {
                        alert("加入成功");
                        window.location.reload();
                    }
                    else {
                        alert(data.msg);
                    }
                },
                error : function(data) {
                    alert("error:" + data);
                }
            });
        });
    })
</script>
</body>
</html>