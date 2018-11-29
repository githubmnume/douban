package us.codecraft.jobhunter.dao;

import org.apache.ibatis.annotations.Insert;

import us.codecraft.jobhunter.model.FilmDTO;

/**
 * 
 * @author zhouufen
 *
 */
public interface FilmInfoDAO {

    @Insert("insert into film(`name`,`url`,`img`,`info`,`year`) values (#{name},#{url},#{img},#{info},#{id})")
    public int add(FilmDTO filmDTO);
}
