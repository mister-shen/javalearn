package com.shenrs;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author shenrs
 * @Description HttpClient模拟请求
 * @create 2018-09-17 14:09
 **/
public class HttpClientDemo {

    public static void main(String[] args) {
        post();
    }

    /**
     * 模拟http get请求
     */
    public static void get() {
        try {
            // 创建一个默认的连接
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 创建一个get请求
            HttpGet httpGet = new HttpGet("https://www.baidu.com/");
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // 获取状态
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("http请求结果：" + statusCode);
            if (statusCode == 200) { //获取返回值
                System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
            // 关闭资源
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟http post请求
     */
    public static void post() {
        // 创建一个默认的连接
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个post请求
        HttpPost httpPost = new HttpPost("http://image.so.com/related");
        // 创建参数队列
        ArrayList<NameValuePair> formparms = new ArrayList<NameValuePair>();
        formparms.add(new BasicNameValuePair("q", "美女"));
        formparms.add(new BasicNameValuePair("start", "0"));
        formparms.add(new BasicNameValuePair("count", "30"));

        CloseableHttpResponse response = null;
        try {
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparms, "UTF-8");
            httpPost.setEntity(uefEntity);

            System.out.println("executing request：" + httpPost.getURI());
            response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("---------------------------------------");
                System.out.println("Response content：" + EntityUtils.toString(entity, "UTF-8"));
                System.out.println("---------------------------------------");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接，释放资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
