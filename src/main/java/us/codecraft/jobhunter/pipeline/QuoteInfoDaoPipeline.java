package us.codecraft.jobhunter.pipeline;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.dao.QuoteInfoDAO;
import us.codecraft.jobhunter.model.QuoteDTO;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.selector.Html;

/**
 * @author code4crafer@gmail.com Date: 13-6-23 Time: 下午8:56
 */
@Component("QuoteInfoDaoPipeline")
public class QuoteInfoDaoPipeline implements PageModelPipeline<QuoteDTO> {

	@Resource
	private QuoteInfoDAO QuoteInfoDAO;

	@Override
	public void process(QuoteDTO quoteDTO, Task task) {
		/*if (task.getUUID().) {
			
		}*/
		System.out.println(task.getUUID());
		Map<String, String> cookies = task.getSite().getCookies();
	String aString=	task.getSite().getHeaders().get("location");
	if (aString!=null && !aString.isEmpty()) {
		System.out.println(aString);
	}
		/*if (page.getRequest().getUrl().contains("file0")) {
			System.out.println(page.getRequest().getUrl());
			Map<String, Object> extras = page.getRequest().getExtras();
		}*/
		/*String link = quoteDTO.getAfile();
		System.out.println(link);

		Map<String, String> headers = task.getSite().getHeaders();
		if (headers!=null &&headers.get("status")!=null&&headers.get("status").equals(""+HttpURLConnection.HTTP_MOVED_TEMP)) {
			System.out.println("ssss");
		}
		try {
//			download(link, quoteDTO.getAfile().split("/")[3], "c://zhoufeng//");
			
//			DownLoadUtils.download(link, quoteDTO.getAfile().split("/")[3], "c://zhoufeng//");
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
	}

	public void download(String urlStr, String filename, String savePath) throws IOException {

		/*
		 * HttpClientDownloader httpClient= new HttpClientDownloader(); Html download =
		 * httpClient.download(urlStr); download.getText().toString();
		 */
		URL url = new URL(urlStr);
		// 打开url连接
		URLConnection connection = url.openConnection();
		// 请求超时时间
		connection.setConnectTimeout(5000);
		// 输入流
		InputStream in = connection.getInputStream();
		// 缓冲数据
		byte[] bytes = new byte[1024];
		// 数据长度
		int len;
		// 文件
		OutputStream out = new FileOutputStream(new File(savePath + "\\" + filename));
		// 先读到bytes中
		while ((len = in.read(bytes)) != -1) {
			// 再从bytes中写入文件
			out.write(bytes, 0, len);
		}
		// 关闭IO
		out.close();
		in.close();
//		connection.
	}

}
