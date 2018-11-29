package us.codecraft.jobhunter.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

//@TargetUrl("https://*.assrt.net/download/\\d+/\\w+.zip")
//http://file0.assrt.net/download/622964/Ant-Man.and.the.Wasp.2018.1080p.BluRay.x264.DTS-HD.MA.7.1-FGT.rar?_=1540262226&-=4062992bff917143bcc62e670f08492c
@TargetUrl("https://assrt.net/xml/sub/612/612458.xml")
//@TargetUrl("https://assrt.net/xml/sub/\\d+/\\d+.xml")
@HelpUrl("https://assrt.net/sub/?searchword=\\w+")
//@HelpUrl("https://assrt.net/xml/sub/\\\\d+/\\\\d+.xml")
public class QuoteDTO implements AfterExtractor {

	@ExtractBy("//div[@class='download']/a[@class='waves-effect waves-button waves-input-wrapper']/@href")
//	@ExtractBy("a[@id='btn_download']/@href")
	private String fileName;

	public String getAfile() {
		return fileName;
	}

	public void setAfile(String afile) {
		this.fileName = afile;
	}

	@Override
	public void afterProcess(Page page) {
		
//		page.addTargetRequest(new Request("https://assrt.net"+this.getAfile()));
//		ResultItems resultItems = page.getResultItems();
		
//		this.setAfile("https://assrt.net"+this.getAfile());
		
		 List<String> all = page.getHtml().xpath("//*[@id=\"detail-tbl-main\"]/div/div[8]").links().all();
		// TODO Auto-generated method stub
		page.addTargetRequests(all);
		/*if (page.getRequest().getUrl().contains("file0")) {
			System.out.println(page.getRequest().getUrl());
			Map<String, Object> extras = page.getRequest().getExtras();
		}*/
		/*if (page.getStatusCode()==HttpURLConnection.HTTP_MOVED_TEMP) {
			page.getUrl().links().all();
		}*/
		/*String url2 = page.getRequest().getUrl();
		System.out.println(url2);
		Selectable url = page.getUrl();
		System.out.println(url);*/
		/*String[] strings = this.getAfile().split("/");
		String filename=strings[strings.length-1];
		String link="https://assrt.net"+this.getAfile();
		System.out.println(link);*/
	/*	try {
//			if (filename.endsWith("rar")) {
//				 link="http://tu.jstucdn.com/ftp/2018/1023/0c8f955b4559cdaf776f5428740b62ef.zip";
//				DownLoadUtils.download(link, "0c8f955b4559cdaf776f5428740b62ef.zip", "c://zhoufeng//");
				//DownLoadUtils.download("http://file0.assrt.net/download/265763/The.Apprentice.Season12.EP03.zip?_=1540281217&-=d0d70339c783646b0a7bb664238b93b5", "The.Apprentice.Season12.EP03.zip", "c://zhoufeng//");
//				DownLoadUtils.download("http://file0.assrt.net/onthefly/265763/-/1/The.Apprentice.Season12.EP03.srt?_=1540281065&-=7584c19a2a16da566ba0723d600e6d26", "The.Apprentice.Season12.EP03.srt", "c://zhoufeng//");
//				DownLoadUtils.download("http://dl1.subhd.com/sub/2016/12/148078190212385.zip", "148078190212385.zip", "c://zhoufeng//");
//				DownLoadUtils.download("https://assrt.net/download/314036/Still_Walking.2008.720p.BluRay.x264.DTS-HDChina.st.srt", "Still_Walking.2008.720p.BluRay.x264.DTS-HDChina.st.srt", "c://zhoufeng//");
//			}
//			DownLoadUtils.download("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1910813120.jpg", "p1910813120.jpg", "c://zhoufeng//");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	/*	try {
			download(link, this.getAfile().split("/")[3], "c://zhoufeng//");
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
	}
	
	 public  void download(String urlStr,String filename,String savePath) throws IOException {
		 
	        URL url = new URL(urlStr);
	        //打开url连接
	        URLConnection connection = url.openConnection();
	        //请求超时时间
	        connection.setConnectTimeout(5000);
	        //输入流
	        InputStream in = connection.getInputStream();
	        //缓冲数据
	        byte [] bytes = new byte[1024];
	        //数据长度
	        int len;
	        //文件
	        File file = new File(savePath);
	        if(!file.exists())
	            file.mkdirs();
	 
	        OutputStream out  = new FileOutputStream(file.getPath()+"\\"+filename);
	        //先读到bytes中
	        while ((len=in.read(bytes))!=-1){
	            //再从bytes中写入文件
	            out.write(bytes,0,len);
	        }
	        //关闭IO
	        out.close();
	        in.close();
	 
	    }
}
