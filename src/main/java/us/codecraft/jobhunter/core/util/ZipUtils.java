package us.codecraft.jobhunter.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class ZipUtils {

	/**
	 * 解压缩zipFile
	 * 
	 * @param zipFile
	 *            要解压的zip文件
	 * @param outputDir
	 *            要解压到某个指定的目录下
	 * @throws IOException
	 */
	public static void unZip(File zipFile, String outputDir, String fileType,String keyWords) throws IOException {
		ZipFile zip = null;
		Charset CP866 = Charset.forName("GBK");
		try {
			zip = new ZipFile(zipFile,CP866);
			createDirectory(outputDir, null);// 创建输出目录

			Enumeration<?> enums = zip.entries();
			while (enums.hasMoreElements()) {

				ZipEntry entry = (ZipEntry) enums.nextElement();
				if (entry.isDirectory()) {// 是目录
					createDirectory(outputDir, entry.getName());// 创建空目录
				} else {// 是文件
					if (entry.getName().endsWith(fileType)&&entry.getName().contains(keyWords)) {
						System.out.println("解压." + entry.getName());
						File tmpFile = new File(outputDir + "/" + entry.getName());
						createDirectory(tmpFile.getParent() + "/", null);// 创建输出目录
						InputStream in = null;
						OutputStream out = null;
						try {
							in = zip.getInputStream(entry);
							;
							out = new FileOutputStream(tmpFile);
							int length = 0;

							byte[] b = new byte[2048];
							while ((length = in.read(b)) != -1) {
								out.write(b, 0, length);
							}
						} catch (IOException ex) {
							throw ex;
						} finally {
							if (in != null)
								in.close();
							if (out != null)
								out.close();
						}
					} else {
						continue;
					}
				}
			}
		} catch (IOException e) {
			throw new IOException("解压缩文件出现异常", e);
		} finally {
			try {
				if (zip != null) {
					zip.close();
				}
			} catch (IOException ex) {
				throw new IOException("关闭zipFile出现异常", ex);
			}
		}
	}

	/**
	 * 构建目录
	 * 
	 * @param outputDir
	 * @param subDir
	 */
	public static void createDirectory(String outputDir, String subDir) {
		File file = new File(outputDir);
		if (!(subDir == null || subDir.trim().equals(""))) {// 子目录不为空
			file = new File(outputDir + "/" + subDir);
		}
		if (!file.exists()) {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			file.mkdirs();
		}
	}
}
