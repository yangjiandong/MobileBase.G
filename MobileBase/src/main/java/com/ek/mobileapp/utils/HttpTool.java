package com.ek.mobileapp.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.ek.mobileapp.MainApplication;

public class HttpTool {
    final static String TAG = "HttpTool";
    final static String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss.SS";
    boolean isDebuggable = false;

    public static final String REQUEST_HTTP = "http://";
    public static final String REQUEST_HTTPS = "https://";

    protected static DefaultHttpClient httpclient;
    private static String cookie = "";
    private static HttpTool tool = null;

    private HttpTool() {
        httpclient = getHttpClient();
    }

    public static HttpTool getTool() {
        if (tool == null)
            tool = new HttpTool();
        return tool;
    }

    public JSONObject login(Context mContext, String url) {
        HttpGet get = null;
        if (url == null)
            return null;
        try {
            httpclient = getHttpClient();
            get = new HttpGet();
            get.setHeader("Accept-Encoding", "gzip, deflate");
            get.setHeader("User-Agent", GlobalCache.getCache().getUserAgent());
            get.setURI(new URI(url));
            HttpResponse response = httpclient.execute(get);
            int result = response.getStatusLine().getStatusCode();
            if (result == 200) {

                String strResult = "";
                Header contentEncoding = response.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    strResult = getGzipData(response);
                } else {
                    strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                }

                JSONObject json = new JSONObject(strResult);
                Header[] headers = response.getHeaders("Set-Cookie");
                if (headers.length != 0) {
                    String tmpcookie = headers[0].toString();
                    //Set-Cookie: JSESSIONID=09C3EDE5202D506F326F6EA5460A8BA4; Path=/sshapp
                    String[] splitCookie = tmpcookie.split(";");
                    String[] splitSessionId = splitCookie[0].split("=");
                    cookie = splitSessionId[1];
                    MainApplication.get().saveSessionCookie(cookie);
                }
                get.abort();
                return json;
            } else {
                get.abort();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            get.abort();
        }
        return null;
    }

    public void logout(Context mContext, String url) {
        if (url == null)
            return;
        try {
            HttpGet get = new HttpGet();
            get.setHeader("Accept-Encoding", "gzip, deflate");
            get.setHeader("User-Agent", GlobalCache.getCache().getUserAgent());
            get.setURI(new URI(url));
            httpclient.execute(get);

            cookie = "";
            MainApplication.get().saveSessionCookie("");
            //((MainApplication) mContext.getApplicationContext()).setProperty(AppConfig.CONF_COOKIE, "");
            get.abort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getGzipData(HttpResponse response) throws IOException, UnsupportedEncodingException {
        String strResult;
        InputStream ois = response.getEntity().getContent();
        InputStream is = new GZIPInputStream(ois);
        Reader reader = null;
        StringWriter writer = null;
        try {
            reader = new InputStreamReader(is, "UTF-8");
            writer = new StringWriter();
            char[] buffer = new char[10240];
            for (int length = 0; (length = reader.read(buffer)) > 0;) {
                writer.write(buffer, 0, length);
            }
        } finally {
            writer.close();
            reader.close();
        }
        strResult = writer.toString();
        return strResult;
    }

    public JSONObject post(String url, List<NameValuePair> parameters) {
        HttpPost post = null;
        if (url == null)
            return null;

        try {
            post = new HttpPost(url);
            post.setHeader("Accept-Encoding", "gzip, deflate");
            post.setHeader("Cookie", cookie);
            post.setHeader("User-Agent", GlobalCache.getCache().getUserAgent());
            post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));

            //
            if (isDebuggable)
                Log.d(TAG, "httpclient.execute," + url + "," + TimeTool.nowDateString(TIMEFORMAT));
            HttpResponse response = httpclient.execute(post);
            int result = response.getStatusLine().getStatusCode();
            if (isDebuggable)
                Log.d(TAG, "finish httpclient.execute," + url + "," + TimeTool.nowDateString(TIMEFORMAT));

            if (result == 200) {
                String strResult = "";
                Header contentEncoding = response.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    strResult = getGzipData(response);
                } else {
                    strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                }

                //String strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                JSONObject json = new JSONObject(strResult);
                return json;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("http post", e.getMessage());
            e.printStackTrace();
        } finally {
            post.abort();
        }
        return null;
    }

    //上传文件
    public JSONObject postFile(String fileName, String url) throws ClientProtocolException, IOException, JSONException {
        HttpPost post = null;
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        try {

            post = new HttpPost(url);
            post.setHeader("Accept-Encoding", "gzip, deflate");
            post.setHeader("Cookie", cookie);
            post.setHeader("User-Agent", GlobalCache.getCache().getUserAgent());
            File file = new File(fileName);
            MultipartEntity mpEntity = new MultipartEntity(); //文件传输
            ContentBody cbFile = new FileBody(file);
            mpEntity.addPart("userfile", cbFile);
            post.setEntity(mpEntity);
            HttpResponse response = httpclient.execute(post);
            int result = response.getStatusLine().getStatusCode();
            if (result == 200) {
                String strResult = "";
                Header contentEncoding = response.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    strResult = getGzipData(response);
                } else {
                    strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                }

                //String strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                JSONObject json = new JSONObject(strResult);
                return json;
            } else {
                return null;
            }
        } catch (Exception e) {

        } finally {
            //post.abort();//这里不能关闭连接,上传文件会失败
        }
        return null;
    }

    public JSONObject postFile(File fileName, String url) throws ClientProtocolException, IOException, JSONException {
        HttpPost post = null;
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        try {

            post = new HttpPost(url);
            post.setHeader("Accept-Encoding", "gzip, deflate");
            post.setHeader("Cookie", cookie);
            post.setHeader("User-Agent", GlobalCache.getCache().getUserAgent());
            MultipartEntity mpEntity = new MultipartEntity(); //文件传输
            ContentBody cbFile = new FileBody(fileName);
            mpEntity.addPart("userfile", cbFile);
            post.setEntity(mpEntity);
            HttpResponse response = httpclient.execute(post);
            int result = response.getStatusLine().getStatusCode();
            if (result == 200) {
                String strResult = "";
                Header contentEncoding = response.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    strResult = getGzipData(response);
                } else {
                    strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                }

                //String strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                JSONObject json = new JSONObject(strResult);
                return json;
            } else {
                return null;
            }
        } catch (Exception e) {

        } finally {
            //post.abort();//这里不能关闭连接,上传文件会失败
        }
        return null;
    }

    //下载文件
    public InputStream getFile(String filePath, String url) {

        File file = new File(filePath);
        if (file.exists())
            file.delete();

        return getFile(url);
    }

    //下载文件
    public InputStream getFile(String url) {
        HttpGet httpGet = null;
        if (url == null || UtilString.isBlank(url))
            return null;

        InputStream is = null;

        try {
            httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            httpGet.setHeader("Cookie", cookie);
            httpGet.setHeader("User-Agent", GlobalCache.getCache().getUserAgent());
            HttpResponse httpResponse = httpclient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                is = httpResponse.getEntity().getContent();
            }
        } catch (Exception e) {
        } finally {
            // httpGet.abort(); //这里不能关闭连接,下载文件会失败
        }
        return is;
    }

    public JSONObject get(String url) {
        HttpGet get = null;
        if (url == null)
            return null;
        try {
            get = new HttpGet();
            get.setURI(new URI(url));
            get.setHeader("Cookie", cookie);
            get.setHeader("User-Agent", GlobalCache.getCache().getUserAgent());
            HttpResponse response = httpclient.execute(get);
            int result = response.getStatusLine().getStatusCode();
            if (result == 200) {
                String strResult = "";
                Header contentEncoding = response.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    strResult = getGzipData(response);
                } else {
                    strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                }
                //String strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                //Log.e("web", strResult);
                JSONObject json = new JSONObject(strResult);
                return json;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            get.abort();
        }
        return null;
    }

    //TODO
    //有可能不要登录就能访问url
    public static String getCookie(MainApplication appContext) {
        if (cookie == null || cookie == "") {
            //cookie = appContext.getProperty(AppConfig.CONF_COOKIE);
        }
        return cookie;
    }

    public static void cleanCookie() {
        cookie = "";
    }

    private static DefaultHttpClient getHttpClient() {
        BasicHttpParams params = new BasicHttpParams();
        //        /* 从连接池中取连接的超时时间,这定义了从ConnectionManager管理的连接池中取出连接的超时时间，此处设置为1秒 */
        //        ConnManagerParams.setTimeout(params, 1000);
        //        ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(20));
        //        ConnManagerParams.setMaxTotalConnections(params, 30);
        //
        //        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        //        //HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        //        /* 请求超时,这定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间，此处设置为4秒。*/
        //        HttpConnectionParams.setSoTimeout(params, 4000);
        //        /* 连接超时,这定义了通过网络与服务器建立连接的超时时间。Httpclient包中通过一个异步线程去创建与服务器的socket连接，这就是该socket连接的超时时间，此处设置为2秒。*/
        //        HttpConnectionParams.setConnectionTimeout(params, 2000);
        //        HttpProtocolParams.setUseExpectContinue(params, true);
        //        HttpConnectionParams.setStaleCheckingEnabled(params, false);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));

        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
        DefaultHttpClient httpclient = new DefaultHttpClient(cm, params);

        return httpclient;
    }
}
