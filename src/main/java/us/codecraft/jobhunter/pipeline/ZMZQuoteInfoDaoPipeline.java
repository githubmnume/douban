package us.codecraft.jobhunter.pipeline;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.core.util.CharacterUtil;
import us.codecraft.jobhunter.core.util.FileUtils;
import us.codecraft.jobhunter.core.util.ZipUtils;
import us.codecraft.jobhunter.dao.ZMZQuoteInfoDAO;
import us.codecraft.jobhunter.model.ZMZQuote;
import us.codecraft.jobhunter.model.ZMZQuoteDTO;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/*
 * zhouf
 */
@Component("ZMZQuoteInfoDaoPipeline")
public class ZMZQuoteInfoDaoPipeline implements PageModelPipeline<ZMZQuote> {

	@Resource
	private ZMZQuoteInfoDAO QuoteInfoDAO;

	@Override
	public void process(ZMZQuote quote, Task task) {
		String url = quote.getUrl();
		String fileName = quote.getFileName()+".zip";
		System.out.println("URL :" + url);
		System.out.println("fileName :" + fileName);

		String filmName = quote.getFilmName();

		String savePath = "C:\\zhoufeng";
		try {
			// download
			DownLoadUtils.download(url, fileName, savePath);
			// unzip
			ZipUtils.unZip(new File(savePath + File.separatorChar + fileName), savePath+ File.separatorChar + filmName,"srt", "简体");
			// read to object
			List<File> listFilesInDir = FileUtils.listFilesInDirWithFilter(savePath + File.separatorChar + filmName,"srt");

			List<String> readFile2List = FileUtils.readFile2List(listFilesInDir.get(0),"GBK");

			ZMZQuoteDTO zmzQuoteDTO = new ZMZQuoteDTO();
			zmzQuoteDTO.setFilmName(filmName);
			for (String string : readFile2List) {
				string =CharacterUtil.toChinese(string);
				System.out.println(string);
				if (string.isEmpty()) {
					// save to database
//					QuoteInfoDAO.add(zmzQuoteDTO);
					zmzQuoteDTO = new ZMZQuoteDTO();
					zmzQuoteDTO.setFilmName(filmName);
				} else if (string.matches("\\d+")) {
					zmzQuoteDTO.setId(1);
				} else if (string.contains("-->")) {
					String[] split = string.split("-->");
					zmzQuoteDTO.setStartTimestamp(split[0]);
					zmzQuoteDTO.setEndTimestamp(split[1]);
				} else {
					zmzQuoteDTO.setContent(Optional.ofNullable(zmzQuoteDTO.getContent()).orElse("")+ string);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
