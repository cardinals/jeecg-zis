package org.jeecgframework.web.system.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.jeecgframework.web.system.service.SystemService;
//import org.jeewx.api.core.common.MyX509TrustManager;

//import com.jeecg.qywx.api.base.JwAccessTokenAPI;
//import com.jeecg.qywx.api.core.common.AccessToken;


/**
 * 公众平台通用接口工具类
* 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //客服接口地址
    public static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    //
//    private static final ResourceBundle bundle  = ResourceBundle.getBundle("weixin");

    /**
     * 发起https请求并获取结果
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
                // 创建SSLContext对象，并使用我们指定的信任管理器初始化
                //TrustManager[] tm = { new MyX509TrustManager() };
        	    TrustManager[] tm = null;
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象
                SSLSocketFactory ssf = sslContext.getSocketFactory();

                URL url = new URL(requestUrl);
                HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
                httpUrlConn.setSSLSocketFactory(ssf);

                httpUrlConn.setDoOutput(true);
                httpUrlConn.setDoInput(true);
                httpUrlConn.setUseCaches(false);
                // 设置请求方式（GET/POST）
                httpUrlConn.setRequestMethod(requestMethod);

                if ("GET".equalsIgnoreCase(requestMethod))
                        httpUrlConn.connect();

                // 当有数据需要提交时
                if (null != outputStr) {
                        OutputStream outputStream = httpUrlConn.getOutputStream();
                        // 注意编码格式，防止中文乱码
                        outputStream.write(outputStr.getBytes("UTF-8"));
                        outputStream.close();
                }

                // 将返回的输入流转换成字符串
                InputStream inputStream = httpUrlConn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                        buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                inputStream = null;
                httpUrlConn.disconnect();
                jsonObject = JSONObject.fromObject(buffer.toString());
                //jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
        	org.jeecgframework.core.util.LogUtil.info("Weixin server connection timed out.");
        } catch (Exception e) {
        	org.jeecgframework.core.util.LogUtil.info("https request error:{}"+e.getMessage());
        }
        return jsonObject;
    }
    
    /** 
     * 根据code获取成员信息 
     * @param access_token 调用接口凭证 
     * @param code 通过员工授权获取到的code，每次员工授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期 
     * @param agentid 跳转链接时所在的企业应用ID 
     * 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同 
     * */  
    public static String GetUserID (String access_token,String code ,String agentid){  
        String UserId = "";  
        CODE_TO_USERINFO = CODE_TO_USERINFO.replace("ACCESS_TOKEN", access_token).replace("CODE", code).replace("AGENTID", agentid);  
        JSONObject jsonobject = WeixinUtil.httpRequest(CODE_TO_USERINFO, "GET", null); 
        if(null!=jsonobject){  
            
            if (jsonobject.containsKey("errcode") ){ //是否有错误  
                if (jsonobject.getString("errcode").equals("40029")){  //是否为code错误  
      
                 String newstr=   CODE_TO_USERINFO.substring(0, CODE_TO_USERINFO.indexOf("&code=")).concat("&code=").concat(code); //去除错误的code加入正确的  
                     
                    jsonobject = WeixinUtil.httpRequest(newstr, "GET", null);//重新发起请求     
                      
                    }  
                }  
            if (jsonobject.containsKey("UserId") ){
                UserId = jsonobject.getString("UserId"); //到这里应该能获得正确的用户信息了  
            }   
            }else{  
            System.out.println("获取授权失败了，●﹏●，自己找原因。。。");  
        }  
        return UserId;  
    }   
    public static String GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=a123#wechat_redirect";  
    /** 
     * 企业获取code地址处理 
     * @param appid 企业的CorpID 
     * @param redirect_uri 授权后重定向的回调链接地址，请使用urlencode对链接进行处理 
     * @param response_type 返回类型，此时固定为：code 
     * @param scope 应用授权作用域，此时固定为：snsapi_base 
     * @param state 重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值 
     * @param #wechat_redirect 微信终端使用此参数判断是否需要带上身份信息 
     * 员工点击后，页面将跳转至 redirect_uri/?code=CODE&state=STATE，企业可根据code参数获得员工的userid 
     * */  
    public static String GetCode(){  
        String get_code_url = "";  
//        get_code_url = GET_CODE.replace("CORPID", ParamesAPI.corpId).replace("REDIRECT_URI", WeixinUtil.URLEncoder(ParamesAPI.REDIRECT_URI));  
        return get_code_url;  
    }  
    public static String CODE_TO_USERINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID";  
//    public static String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3711b1d9aec370a1&redirect_uri=wxtest2.zrfunds.com.cn%2fjeecg%2floginController.do%3fwxlogin&response_type=code&scope=snsapi_base&connect_redirect=1#wechat_redirect";  

    /** 
     * 编码 
     * @param bstr 
     * @return String 
     */  
    public static String encode(byte[] bstr){  
    	return new sun.misc.BASE64Encoder().encode(bstr);  
    }  
  
    /** 
     * 解码 
     * @param str 
     * @return string 
     */  
    public static byte[] decode(String str){ 
    	
	    byte[] bt = null;  
	    try {  
	        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	        bt = decoder.decodeBuffer( str );  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
        return bt;  
        
    }  
    public static String getUserId(String code){
    	String corpID = "wx3711b1d9aec370a1";
		String secret = "mBmXgsn_hDPwyhyYEW01dUj4cAEGhi9ORTwn_5ae4Ow";
		String agentId="1000002";
		//String accessToken = JwAccessTokenAPI.getAccessToken(corpID, secret).getAccesstoken();
		String accessToken = null;
		String userId = "";
		try{
		 userId = WeixinUtil.GetUserID(accessToken, code, agentId);
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("userid->"+userId);
		return userId;
    }
}