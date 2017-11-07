package com.dchz.newwork.util;

import android.content.Intent;
import android.net.Uri;

import com.dchz.newwork.common.Global;
import com.dchz.newwork.listener.IDownLoadFileListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FileUtil {
	/**
	 * 下载文件
	 * @param url
	 * @param filePath
	 * @param fileName
	 * @param mListener
	 */
	public static void savaFileFromURL(String url,String filePath,String fileName,IDownLoadFileListener mListener){
		File file=new File(filePath,fileName);
		try {
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
		} catch (Exception e){
			mListener.fail();
			e.printStackTrace();
		}
		saveFileFromURL(url,file,mListener);
	}

	/**
	 * 实现网络访问文件，将获取到的数据保存在指定目录中
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return byte[]
	 */
	public static void saveFileFromURL(final String url, final File destFile, final IDownLoadFileListener mListener) {
		Global.postDelay(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection httpConn = null;
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
					bos = new BufferedOutputStream(new FileOutputStream(destFile));
					URL urlObj = new URL(url);
					trustAllHosts();
					if (urlObj.getProtocol().toLowerCase().equals("https")) {
						HttpsURLConnection https = (HttpsURLConnection)urlObj.openConnection();
						https.setHostnameVerifier(DO_NOT_VERIFY);
						httpConn = https;
					} else {
						httpConn = (HttpURLConnection)urlObj.openConnection();
					}
					httpConn.setDoInput(true);
					httpConn.setRequestMethod("GET");
					httpConn.setConnectTimeout(5000);
					httpConn.connect();
					if (httpConn.getResponseCode() == 200) {
						bis = new BufferedInputStream(httpConn.getInputStream());
						int c = 0;
						byte[] buffer = new byte[8 * 1024];
						while ((c = bis.read(buffer)) != -1) {
							bos.write(buffer, 0, c);
							bos.flush();
						}
					}
					mListener.success();
				} catch (Exception e) {
					mListener.fail();
					e.printStackTrace();
				} finally {
					try {
						if (bis != null) {
							bis.close();
						}
						if (bos != null) {
							bos.close();
						}
						if(httpConn!=null)
							httpConn.disconnect();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		return file.delete();
	}
	
	//android获取一个用于打开PDF文件的intent

	  public static Intent getPdfFileIntent(String param )

	  {

	    Intent intent = new Intent("android.intent.action.VIEW");

	    intent.addCategory("android.intent.category.DEFAULT");

	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	    Uri uri = Uri.fromFile(new File(param ));

	    intent.setDataAndType(uri, "application/pdf");

	    return intent;

	  }
	  
	  //android获取一个用于打开Word文件的intent

	  public static Intent getWordFileIntent(String param )

	  {

	    Intent intent = new Intent("android.intent.action.VIEW");

	    intent.addCategory("android.intent.category.DEFAULT");

	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	    Uri uri = Uri.fromFile(new File(param ));

	    intent.setDataAndType(uri, "application/msword");

	    return intent;

	  }

	//android获取一个用于打开Excel文件的intent

	  public static Intent getExcelFileIntent(String param )

	  {

	    Intent intent = new Intent("android.intent.action.VIEW");

	    intent.addCategory("android.intent.category.DEFAULT");

	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	    Uri uri = Uri.fromFile(new File(param ));

	    intent.setDataAndType(uri, "application/vnd.ms-excel");

	    return intent;

	  }
	  
	  private static void trustAllHosts() {
	        final String TAG = "trustAllHosts";
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	     
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	     
	            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	            }
	     
	            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	            }
	        } };
	     
	        // Install the all-trusting trust manager
	        try {
	            SSLContext sc = SSLContext.getInstance("SSL");
	            sc.init(null, trustAllCerts, new java.security.SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
			 
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    };
}
