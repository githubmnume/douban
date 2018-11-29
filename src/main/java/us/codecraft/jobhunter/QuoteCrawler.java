package us.codecraft.jobhunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.model.QuoteDTO;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author code4crafer@gmail.com Date: 13-6-23 Time: 下午4:19
 */
@Component
public class QuoteCrawler {

	@Qualifier("QuoteInfoDaoPipeline")
	@Autowired
	private PageModelPipeline<QuoteDTO> QuoteInfoDaoPipeline;

	public void crawl() {
		OOSpider.create(Site.me().setUserAgent(
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36"),
				QuoteInfoDaoPipeline, QuoteDTO.class)
				.addUrl("https://assrt.net/sub/?searchword=%E8%99%8E%E5%8F%A3%E8%84%B1%E9%99%A9")// Quote name
				.thread(1).run();
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext*.xml");
		final QuoteCrawler quoteCrawler = applicationContext.getBean(QuoteCrawler.class);
		quoteCrawler.crawl();
	}
}
