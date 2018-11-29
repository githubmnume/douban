package us.codecraft.jobhunter.dao;

import org.apache.ibatis.annotations.Insert;

import us.codecraft.jobhunter.model.QuoteDTO;

/**
 * 
 * @author zhouufen
 *
 */
public interface QuoteInfoDAO {

    @Insert("insert into film(`name`,`url`,`img`,`info`,`year`) values (#{name},#{url},#{img},#{info},#{id})")
    public int add(QuoteDTO quoteDTO);
}
