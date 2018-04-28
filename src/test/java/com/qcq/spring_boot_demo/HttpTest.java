package com.qcq.spring_boot_demo;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpTest {
	@Test
	public void testPost() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String url = "http://39.108.162.211:8060/seekerSetIsForbidden";
		HttpPost post = new HttpPost(url);

		post.addHeader("charset", "UTF-8");


		List<NameValuePair> values = new ArrayList<>();
		values.add(new BasicNameValuePair("id", "8733613"));
		values.add(new BasicNameValuePair("isForbidden", "0"));

		try {
			HttpEntity httpEntity = new UrlEncodedFormEntity(values);
			post.setEntity(httpEntity);
			CloseableHttpResponse response = httpClient.execute(post);
			//获取返回的所有headers
			Header[] headers = response.getAllHeaders();
			System.out.println("headers:");
			for (Header header : headers) {
				System.out.println(header);
			}
			//获取返回的状态
			int status = response.getStatusLine().getStatusCode();
			System.out.println("status: " + status);
			//获取HttpEntity消息载体对象
			HttpEntity entity = response.getEntity();
			// EntityUtils中的toString()方法转换服务器的响应数据
			String str = EntityUtils.toString(entity, "utf-8");
			System.out.println("服务器的响应是:" + str);
			//5.关闭连接
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGet() {
		String url = "http://39.108.162.211:8060/getSelectedId";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				Header[] headers = response.getAllHeaders();
				System.out.println("header length: " + headers.length);
				HttpEntity entity = response.getEntity();
				System.out.println("entity: " + EntityUtils.toString(entity));
				System.out.println("status line: " + response.getStatusLine());
				System.out.println("headers:");
				for (int i = 0; i < headers.length; ++i) {
					System.out.println(headers[i].toString());
				}
			}
			((CloseableHttpClient) client).close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//这个请求可以实现老师给的api调用
	@Test
	public void testPost3() {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String url = "http://221.4.217.254:13100/recognize?communityid=97&buildingid=643&width=500&height=500&format=1";
//		String url = "http://221.4.217.254:13100/register?communityid=97&buildingid=643&width=500&height=665&cardid=8733613&regnew=1&format=1";

		HttpPost post = new HttpPost(url);

		post.addHeader("Content-Type", "application/json");
		post.addHeader("charset", "UTF-8");


		try {
			File file = new File("C:\\a.jpg");
			HttpEntity httpEntity = new FileEntity(file);
			post.setEntity(httpEntity);
			System.out.println(EntityUtils.toString(httpEntity));
			CloseableHttpResponse response = httpClient.execute(post);
			System.out.println("\n");
			//获取返回的所有headers
			Header[] headers = response.getAllHeaders();
			System.out.println("headers:");
			for (Header header : headers) {
				System.out.println(header);
			}
			//获取返回的状态
			int status = response.getStatusLine().getStatusCode();
			System.out.println("status: " + status);
			//获取HttpEntity消息载体对象
			HttpEntity entity = response.getEntity();
			// EntityUtils中的toString()方法转换服务器的响应数据
			String str = EntityUtils.toString(entity, "utf-8");
			System.out.println("服务器的响应是:" + str);
			//5.关闭连接
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	//这个请求无法实现老师给的api调用
//	@Test
//	public void testPost2() {
//
//		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//		String url = "http://221.4.217.254:13100/recognize";
//		HttpPost post = new HttpPost(url);
//
//		post.addHeader("Content-Type","application/json");
////		post.addHeader("charset", "UTF-8");
//
//
//		List<NameValuePair> values = new ArrayList<>();
//		values.add(new BasicNameValuePair("communityid", "1"));
//		values.add(new BasicNameValuePair("buildingid", "1"));
//		values.add(new BasicNameValuePair("width", "1280"));
//		values.add(new BasicNameValuePair("height", "720"));
//		values.add(new BasicNameValuePair("format", "1"));
//
//
//		try {
//			HttpEntity httpEntity = new UrlEncodedFormEntity(values);
//			post.setEntity(httpEntity);
//			System.out.println(EntityUtils.toString(httpEntity));
//			CloseableHttpResponse response = httpClient.execute(post);
//			System.out.println("\n");
//			//获取返回的所有headers
//			Header[] headers = response.getAllHeaders();
//			System.out.println("headers:");
//			for (Header header : headers) {
//				System.out.println(header);
//			}
//			//获取返回的状态
//			int status = response.getStatusLine().getStatusCode();
//			System.out.println("status: "+status);
//			//获取HttpEntity消息载体对象
//			HttpEntity entity = response.getEntity();
//			// EntityUtils中的toString()方法转换服务器的响应数据
//			String str = EntityUtils.toString(entity, "utf-8");
//			System.out.println("服务器的响应是:" + str);
//			//5.关闭连接
//			httpClient.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
