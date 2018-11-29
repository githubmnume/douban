package us.codecraft.jobhunter.dao;

import org.apache.ibatis.annotations.Insert;

import us.codecraft.jobhunter.model.DoubanFilm;

/**
 * 
 * @author zhouufen
 *
 */
public interface DoubanFilmInfoDAO {

    @Insert("insert into film(`name`,`url`,`img`,`info`,`year`) values (#{name},#{url},#{img},#{info},#{year})")
    public int add(DoubanFilm doubanFilm);
}
