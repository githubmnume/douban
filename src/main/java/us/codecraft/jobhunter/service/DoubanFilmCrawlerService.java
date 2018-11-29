package us.codecraft.jobhunter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.model.DoubanFilm;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:19
 */
@Component
public class DoubanFilmCrawlerService {

    @Qualifier("DoubanFilmInfoDaoPipeline")
    @Autowired
    private PageModelPipeline<DoubanFilm> filmInfoDaoPipeline;

    public void crawl(String domain,String url) {
        OOSpider.create(Site.me().setSleepTime(300).setRetryTimes(3).setDomain(domain)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36"),
                filmInfoDaoPipeline, DoubanFilm.class)
                .addUrl(url)//entry url
                .thread(1)
                .run();
    }

   /* public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final DoubanFilmCrawler doubanFilmCrawler = applicationContext.getBean(DoubanFilmCrawler.class);
        doubanFilmCrawler.crawl();
    }*/
}
