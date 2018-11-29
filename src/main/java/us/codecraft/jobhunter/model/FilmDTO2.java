package us.codecraft.jobhunter.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("https://movie.douban.com/subject/\\w+")
@HelpUrl("https://movie\\.douban\\.com/top250?start=\\d+&filter=\\w+")
@ExtractBy("//div[@class='article']//ol[@class='grid_view']/li")
	public class FilmDTO2 implements AfterExtractor {
	private Integer id;
	
	@ExtractBy("//div[@class='item']//div[@class='info']//div[@class='hd']/a/span[1]/text()")
	private String name;
	
	@ExtractBy("//div[@class='item']//div[@class='info']//div[@class='hd']/a/@href")
	private String url;

	@ExtractBy("//div[@class='item']//div[@class='pic']/a/img/@src")
	private String img;

	@ExtractBy("//div[@class='item']//div[@class='info']//div[@class='bd']/p[1]/text()")
	private String info;
	
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
		this.year = "1970";
	}
	

	@Override
	public String toString() {
		return String.format("FilmDTO [name=%s, url=%s, img=%s, info=%s, year=%s]", name, url, img, info, year);
	}

	@Override
	public void afterProcess(Page arg0) {
		// TODO Auto-generated method stub

	}

}
