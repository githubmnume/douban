package us.codecraft.jobhunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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
public class JobCrawler {

    @Qualifier("FilmInfoDaoPipeline")
    @Autowired
    private PageModelPipeline<FilmDTO> filmInfoDaoPipeline;

    public void crawl() {
        OOSpider.create(Site.me()
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),filmInfoDaoPipeline, FilmDTO.class)
                .addUrl("https://movie.douban.com/top250")
                .thread(1)
                .run();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final JobCrawler jobCrawler = applicationContext.getBean(JobCrawler.class);
        jobCrawler.crawl();
    }
}
