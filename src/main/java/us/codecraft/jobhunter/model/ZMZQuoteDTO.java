package us.codecraft.jobhunter.model;

import java.io.Serializable;

public class ZMZQuoteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String startTimestamp;
	private String endTimestamp;
	private String content;
	private String filmName;
	public String getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public String getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	@Override
	public String toString() {
		return String.format("ZMZQuoteDTO [filmName=%s, startTimestamp=%s, endTimestamp=%s, content=%s]", filmName,
				startTimestamp, endTimestamp, content);
	}

}
