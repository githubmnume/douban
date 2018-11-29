package us.codecraft.jobhunter.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://zmz\\d+.com/s/\\w+")
@HelpUrl(value="http://www.zimuzu.tv/subtitle/\\w+"/*,sourceRegion="//div[@class='clearfix search-item']/div[@class='fl-info']/a"*/)//怎么只取第一个呢？
public class ZMZQuote implements AfterExtractor {

	@ExtractBy("/html/body/div[2]/div/div/div/div/a/@href")
	private String url;
	
	@ExtractBy("/html/body/div[2]/div/div/div/div/a/text()")
	private String fileName;
	
	@ExtractBy("/html/body/header/div/p/span[2]/text()")
	private String filmName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	@Override
	public void afterProcess(Page page) {
		
	}
}