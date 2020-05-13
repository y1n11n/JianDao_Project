package com.example.seethewayproject.net.api;

public class URLConstants {

    public static String BASE_URL = "https://www.seetao.com/";


    //推荐列表
   public static String RECOMMEND_LIST= "app/v_1_3/article/recommendlist";

    //栏目列表
    public static String COLUM_LIST = "api/column/columnlist";

    //视频列表
    public static  String VEDIO_LIST = "app/v_1_3/article/videolist";

    //栏目管理列表
//    public static String COLUM_MANAGE_LIST = "/api/column/columnmanagelist";

    //专题列表
    public static String SPECIAL_LIST = "/app/v_1_3/article/speciallist";

//    评论接口

    //发送短信验证码
    public static String SEND_SMS = "/api/sms/sendsms";

    //短信验证码登录
    public static String SMS_LOGIN = "/api/user/smslogin";

    //检查短信验证码是否正确
    public static String CHECKSMSCODE = "/api/sms/checksmscode";

    //用户注册
    public static String USER_REGISTER = "/api/user/register";

    //手机号或邮箱 密码登录
    public static String USER_LOGIN = "/api/user/login";
    
    //忘记密码
    public static String FORGET_PSW = "/app/v_1_1/user/savepassword";


}
