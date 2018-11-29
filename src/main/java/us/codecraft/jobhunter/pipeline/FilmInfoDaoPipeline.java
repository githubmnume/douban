package us.codecraft.jobhunter.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.dao.FilmInfoDAO;
import us.codecraft.jobhunter.model.FilmDTO;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("FilmInfoDaoPipeline")
public class FilmInfoDaoPipeline implements PageModelPipeline<FilmDTO> {

    @Resource
    private FilmInfoDAO filmInfoDAO;

    @Override
    public void process(FilmDTO filmDTO, Task task) {
        filmInfoDAO.add(filmDTO);
    }
    
    
}
