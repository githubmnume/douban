package us.codecraft.jobhunter;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.model.DoubanFilm;
import us.codecraft.jobhunter.model.FilmDTO;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:19
 */
@Component
public class DoubanFilmCrawler {

    @Qualifier("DoubanFilmInfoDaoPipeline")
    @Autowired
    private PageModelPipeline<DoubanFilm> filmInfoDaoPipeline;

    public void crawl() {
//        HttpHost httpProxy= new HttpHost("www-proxy.ericsson.se", 8080);
		OOSpider.create(Site.me().setSleepTime(3000).setRetryTimes(3).setDomain("movie.douban.com")//.setHttpProxy(httpProxy)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36"),
                filmInfoDaoPipeline, DoubanFilm.class)
                .addUrl("https://movie.douban.com/top250")//entry url
                .thread(1)
                .run();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final DoubanFilmCrawler doubanFilmCrawler = applicationContext.getBean(DoubanFilmCrawler.class);
        doubanFilmCrawler.crawl();
    }
}
