package hzj.base;


import hzj.utils.Md5Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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



    }

    protected abstract void setToken();
    protected abstract void setUserId();


    public String packJsonReqParams(){
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
        /*jsonBody.put("password", Md5Utils.md5(Md5Utils.md5("a111111") + clientUuid));
        jsonBody.put("uuid",clientUuid.toString());
        jsonBody.put("versionId",versionId);
        jsonBody.put("versionType",versionType);*/
        jsonMap.put("head",jsonHead);
        jsonMap.put("body",jsonBody);


        return null;
    }


    public static String getTimestamp() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }


}
