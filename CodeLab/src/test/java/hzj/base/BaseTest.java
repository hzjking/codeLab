package hzj.base;



import com.alibaba.fastjson.JSONObject;
import hzj.utils.HttpUtil;
import hzj.utils.MD5Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by MichaelHe on 2017/7/28.
 */
public abstract class BaseTest {

    protected static final String HUSERID = "huserid";
    protected static final String HTOKEN = "htoken";
    protected static final String HUUID = "huuid";


    protected int userId = 0;
    protected String token = "";
    protected String uuid = "";

    public void settingToken(){

        String result  = packJsonReqParams("http://localhost:7001/");

    }

    protected abstract void setToken();
    protected abstract void setUserId();


    public String packJsonReqParams(String url){
        UUID clientUUID = UUID.randomUUID();
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> jsonHead = new HashMap<String, Object>();
        jsonHead.put("timestamp",getTimestamp());
        jsonHead.put("version","1.0");
        headers.put(HUSERID,Integer.toString(userId));
        headers.put(HTOKEN,token);
        headers.put(HUUID,uuid);
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("username","13901620812");
        jsonBody.put("password", MD5Util.sign(MD5Util.sign("a111111") + clientUUID));
        jsonBody.put("uuid",clientUUID.toString());
        jsonBody.put("versionId","1.0");
        jsonBody.put("versionType","android");
        jsonMap.put("head",jsonHead);
        jsonMap.put("body",jsonBody);

        String resp = HttpUtil.httpMethodPost(url,JSONObject.toJSONString(jsonMap),headers,"UTF-8");


        return null;
    }


    public static String getTimestamp() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }


}
