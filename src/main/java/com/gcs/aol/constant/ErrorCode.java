package com.gcs.aol.constant;

/**
 * Created by gaoll on 2015/7/23.
 */
public class ErrorCode {

    public final static String ERROR_01="error_01"; // 系统错误

    public final static String ERROR_02="error_02"; // 参数错误（主要是必填的参数没有传）

    public final static String ERROR_03="error_03"; // 用户名或密码错误

    public final static String ERROR_04="error_04"; // openId没有绑定用户

    public final static String ERROR_05="error_05"; // 手机号重复

    public final static String ERROR_06="error_06"; // 手机号不存在

    public final static String ERROR_07="error_07"; // 不能重复提交（只能提交一次的信息不能重复提交）

    public final static String ERROR_08="error_08"; // 当前用户已经绑定此第三方

    public final static String ERROR_09="error_09"; // 验证码发送失败

    public final static String ERROR_10="error_10"; // 验证码不正确

    public final static String ERROR_11="error_11"; // 验证码已过期，请重新发送验证码
    
    public final static String ERROR_12="error_12"; // 昵称重复
    
    public final static String ERROR_14="error_14"; // 用户已下线
    
    public final static String ERROR_15="error_15"; // 原密码错误
    
    public final static String ERROR_16="error_16"; // 已绑定该银行卡
    
    public final static String ERROR_17="error_17"; // 已收藏该商品
    
    public final static String ERROR_18="error_18"; // 已借过款，无法再此借款
    
    public final static String ERROR_19="error_19"; // 第三方未绑定手机
    
    public final static String ERROR_20="error_20"; // 本月已还款，无法再次还款
}
