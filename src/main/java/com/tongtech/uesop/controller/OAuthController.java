package com.tongtech.uesop.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongtech.uesop.mapper.UmMapper;
import com.tongtech.uesop.dto.UMStatus;
import com.tongtech.uesop.dto.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Value("${github.client-id}")
    private String githubClientId;

    @Value("${github.secret}")
    private String githubSecret;

    @Autowired
    private UmMapper umMapper;

    private Map<String, String> query2obj(HttpEntity httpEntity) throws IOException {
        List<NameValuePair> query = URLEncodedUtils.parse(EntityUtils.toString(httpEntity), Charset.forName("utf-8"));
        Iterator<NameValuePair> items = query.iterator();
        Map param = new HashMap<String, String>();
        while (items.hasNext()) {
            NameValuePair item = items.next();
            param.put(item.getName(), item.getValue());
        }
        return param;
    }

    @RequestMapping(path = "/github", method = RequestMethod.GET)
    public void redirect(@RequestParam String code, HttpServletResponse response) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token?" +
                "client_id=" + githubClientId + "&" +
                "client_secret=" + githubSecret + "&" +
                "code=" + code);
        HttpResponse httpresponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpresponse.getEntity();
        Map<String, String> params = query2obj(httpEntity);
        if (params.containsKey("access_token")) {
            HttpGet httpGet = new HttpGet("https://api.github.com/user?access_token=" + params.get("access_token"));
            HttpResponse personResponse = httpClient.execute(httpGet);
            HttpEntity personEntity = personResponse.getEntity();
            String personStr = EntityUtils.toString(personEntity);
            JSONObject object = JSONObject.parseObject(personStr);
            Integer user_id = (Integer) object.get("id");
            User user = umMapper.getUser(user_id.toString());
            if (user != null) {
                System.out.println(user);
            } else {
                String avatar2 = (String) object.get("avatar_url");
                String avatar0 = avatar2.replace("avatars2", "avatars0");
                user = new User();
                user.setUserName((String) object.get("login"));
                user.setStatus(UMStatus.VALID);
                user.setUserId(user_id.toString());
                user.setAvatarUrl(avatar0);
                user.setDepartment("github");
                umMapper.addUser(
                        user.getUserName(),
                        user.getDepartment(),
                        user.getAvatarUrl(),
                        user.getUserId(),
                        user.getStatus());
            }
            response.setHeader("token","ffffff");
            response.sendRedirect("http://127.0.0.1:8000/dashboard");
        } else {
            response.sendRedirect("http://127.0.0.1:8000/404");
        }
    }


}
