package yanzhikai.okhttpdemo;

import java.util.HashMap;
import java.util.Map;

import static yanzhikai.okhttpdemo.YoudaoUtil.md5;

/**
 * Created by yany on 2017/11/3.
 */

public class YoudaoQuery {
    String appKey ="5fda8add7fda26cd";
    String query = "你好";
    String salt = String.valueOf(System.currentTimeMillis());
    String from = "auto";
    String to = "auto";
    String sign ;

    public YoudaoQuery(String query){
        this.query = query;
        sign = md5(appKey + query + salt+ "fGFSKWh05I7AJeuCfJIpXGz5nFzSSh5W");
    }

    private Map<String,String> toMap(){
        Map<String,String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appKey);
        return params;
    }

    public String toUrl(){
        return YoudaoUtil.getUrlWithQueryString("http://openapi.youdao.com/api",toMap());
    }
}
