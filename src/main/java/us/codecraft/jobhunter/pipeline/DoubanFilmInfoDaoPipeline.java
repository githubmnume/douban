package us.codecraft.jobhunter.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.dao.DoubanFilmInfoDAO;
import us.codecraft.jobhunter.model.DoubanFilm;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("DoubanFilmInfoDaoPipeline")
public class DoubanFilmInfoDaoPipeline implements PageModelPipeline<DoubanFilm> {

    @Resource
    private DoubanFilmInfoDAO doubanFilmInfoDAO;


	@Override
	public void process(DoubanFilm doubanFilm, Task task) {
		doubanFilmInfoDAO.add(doubanFilm)		;
	}
    
    
}
