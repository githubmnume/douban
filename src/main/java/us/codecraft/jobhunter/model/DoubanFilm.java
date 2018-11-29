package us.codecraft.jobhunter.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("https://movie.douban.com/subject/\\d++/")
@HelpUrl("https://movie.douban.com/top250?start=\\d+&filter=") 
	public class DoubanFilm implements AfterExtractor {
	private Integer id;
	
	@ExtractBy("//*[@id=\"content\"]/h1/span[1]/text()")
	private String name;
	
	@ExtractBy("//div[@class='item']//div[@class='info']//div[@class='hd']/a/@href")
	private String url;

	@ExtractBy("//*[@id=\"mainpic\"]/a/img/@src")
	private String img;

	@ExtractBy("//*[@id=\"info\"]/span[1]/span[2]/a/text()")
	private String info;
	
	@ExtractBy("//*[@id='content']/h1/span[2]/text()")
	private String year;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	

	@Override
	public String toString() {
		return String.format("FilmDTO [name=%s, url=%s, img=%s, info=%s, year=%s]", name, url, img, info, year);
	}

	@Override
	public void afterProcess(Page page) {
		this.setUrl(page.getUrl().toString());

	}

}
