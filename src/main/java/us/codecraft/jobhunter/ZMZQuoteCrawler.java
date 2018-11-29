package us.codecraft.jobhunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.model.QuoteDTO;
import us.codecraft.jobhunter.model.ZMZQuote;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author code4crafer@gmail.com Date: 13-6-23 Time: 下午4:19
 */
@Component
public class ZMZQuoteCrawler {

	@Qualifier("ZMZQuoteInfoDaoPipeline")
	@Autowired
	private PageModelPipeline<ZMZQuote> zMZQuoteInfoDaoPipeline;

	public void crawl() {
		OOSpider.create(Site.me().setUserAgent(
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36"),
				zMZQuoteInfoDaoPipeline, ZMZQuote.class)
//			 	.addUrl("http://www.zimuzu.tv/search?keyword=%E8%82%96%E7%94%B3%E5%85%8B%E7%9A%84%E6%95%91%E8%B5%8E&type=subtitle")// Quote name
			 	.addUrl("http://www.zimuzu.tv/search?keyword=%E7%9B%97%E6%A2%A6%E7%A9%BA%E9%97%B4&type=subtitle")// Quote name
		
///			 	.addUrl("http://www.zimuzu.tv/search?keyword=%E7%A2%9F%E4%B8%AD%E8%B0%8D5%EF%BC%9A%E6%B3%95%E5%A4%96%E5%9B%BD%E5%BA%A6&type=subtitle")// Quote name
//			 	.addUrl("http://www.zimuzu.tv/search?keyword=%E7%A2%9F%E4%B8%AD%E8%B0%8D&type=subtitle")// Quote name
				.thread(1).run();
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext*.xml");
		final ZMZQuoteCrawler zmzQuoteCrawlerquoteCrawler = applicationContext.getBean(ZMZQuoteCrawler.class);
		zmzQuoteCrawlerquoteCrawler.crawl();
	}
}
