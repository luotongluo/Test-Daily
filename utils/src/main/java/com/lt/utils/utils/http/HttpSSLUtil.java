package com.lt.utils.utils.http;

import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;

public class HttpSSLUtil {
    protected static final int SOCKET_TIMEOUT = 10000;
    protected static final int READ_TIMEOUT = 20000;
    protected static final String GET = "GET";
    protected static final String CHARSET = "utf-8";
    private static TrustManager myX509TrustManager = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    };

    public HttpSSLUtil() {
    }

    public static String get(String host, Map<String, String> params) {
        return get(host, params, "utf-8");
    }

    public static String get(String host, Map<String, String> params, String charSet) {
        return get(host, params, charSet, 10000, 20000);
    }

    public static String get(String host, Map<String, String> params, String charSet, int connectTimeOut, int readTimeOut) {
        try {
            if (StringUtils.isEmpty(charSet)) {
                charSet = "utf-8";
            }

            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init((KeyManager[]) null, new TrustManager[]{myX509TrustManager}, (SecureRandom) null);
            String sendUrl = getUrlWithQueryString(host, params);
            URL uri = new URL(sendUrl);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
            }

            conn.setConnectTimeout(connectTimeOut);
            conn.setReadTimeout(readTimeOut);
            conn.setRequestMethod("GET");
            int statusCode = conn.getResponseCode();
            if (statusCode != 200) {
                System.out.println("Http错误码：" + statusCode);
            }

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, charSet));
            StringBuilder builder = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

            String text = builder.toString();
            close(br);
            close(is);
            conn.disconnect();
            return text;
        } catch (MalformedURLException var15) {
            var15.printStackTrace();
        } catch (IOException var16) {
            var16.printStackTrace();
        } catch (KeyManagementException var17) {
            var17.printStackTrace();
        } catch (NoSuchAlgorithmException var18) {
            var18.printStackTrace();
        }

        return null;
    }

    public static String getUrlWithQueryString(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        } else {
            StringBuilder builder = new StringBuilder(url);
            if (url.contains("?")) {
                builder.append("&");
            } else {
                builder.append("?");
            }

            int i = 0;
            Iterator var4 = params.keySet().iterator();

            while (var4.hasNext()) {
                String key = (String) var4.next();
                String value = (String) params.get(key);
                if (value != null) {
                    if (i != 0) {
                        builder.append('&');
                    }

                    builder.append(key);
                    builder.append('=');
                    builder.append(encode(value));
                    ++i;
                }
            }

            return builder.toString();
        }
    }

    protected static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    public static String encode(String input) {
        if (input == null) {
            return "";
        } else {
            try {
                return URLEncoder.encode(input, "utf-8");
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
                return input;
            }
        }
    }
}
