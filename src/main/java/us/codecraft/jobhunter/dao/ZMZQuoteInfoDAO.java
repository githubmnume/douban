package us.codecraft.jobhunter.dao;

import org.apache.ibatis.annotations.Insert;

import us.codecraft.jobhunter.model.QuoteDTO;
import us.codecraft.jobhunter.model.ZMZQuoteDTO;

/**
 * 
 * @author zhouufen
 *
 */
public interface ZMZQuoteInfoDAO {

    @Insert("insert into quote(`quotestart`,`quoteend`,`filmname`,`quote`) values (#{startTimestamp},#{endTimestamp},#{filmName},#{content})")
    public int add(ZMZQuoteDTO quoteDTO);
}
