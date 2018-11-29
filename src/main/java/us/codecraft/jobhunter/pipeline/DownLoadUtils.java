package us.codecraft.jobhunter.pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

/**
 * 下载相关的工具类
 * 
 * @author xwer
 *
 */
public final class DownLoadUtils {

	/**
	 * 下载图片工具
	 * 
	 * @param urlString
	 *            图片链接地址
	 * @param filename
	 *            图片的文件名字
	 * @param savePath
	 *            图片保存的路径
	 * @throws Exception
	 */
	public static void download(String urlString, String filename, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		con.addRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
		con.setRequestProperty("dtoken", "22f53cb95186f1968171baf7afac6af869270671");
//		con.setRequestProperty("cookie", value);
		URL url2 = con.getURL();
//		con.get
//		con.connect();
		// 设置请求头
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
		Object content = con.getContent();
	}

	/**
	 * 截取真实文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String subFileName(String fileName) {
		// 查找最后一个 \出现位置
		int index = fileName.lastIndexOf("\\");
		if (index == -1) {
			return fileName;
		}
		return fileName.substring(index + 1);
	}
}
/*	*//**
		 * 获得随机UUID文件名
		 * 
		 * @param fileName
		 * @return
		 *//*
			 * public static String generateRandonFileName(String fileName) { // 获得扩展名
			 * String ext = fileName.substring(fileName.lastIndexOf(".")); return
			 * UUID.randomUUID().toString().replace("-", "") + ext;}
			 */
// }
