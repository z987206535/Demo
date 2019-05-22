package cn.tedu.oa.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author axman
 *
 */
public class Upload {
	private String saveDir = "."; // 要保存文件的路径
	private String charset = "UTF-8"; // 字符集

	private HashMap<String, ArrayList> parameter = new HashMap<String, ArrayList>(); // 存放参数名和值的数据结构
	private HttpServletRequest request; // 用于传入请求对象的实例
	private String boundary = ""; // 内存数据的分隔符
	private int len = 0; // 每次从内在中实际读到的字节长度
	private String queryString;
	private int count; // 上载的文件总数
	private String[] fileName; // 上载的文件名数组
	private long maxFileSize = 1024 * 1024 * 10; // 最大文件上载字节;
	private String tagFileName = "";

	public final void init(HttpServletRequest request) throws ServletException {
		this.request = request;
		Matcher m = Pattern.compile("boundary=([\\s\\S]+)").matcher(
				request.getContentType());
		if (m.find())
			this.boundary = m.group(1);
		this.queryString = request.getQueryString();
	}

	/***
	 * 用于得到指定字段的参数值,重写request.getParameter(String s)
	 */
	public String getParameter(String key) {
		if (parameter.isEmpty()) {
			return null;
		}
		return (String) ((ArrayList) parameter.get(key)).get(0);
	}

	/***
	 * 用于得到指定同名字段的参数数组,重写request.getParameterValues(String s)
	 */
	public String[] getParameterValues(String key) {
		if (parameter.isEmpty())
			return null;
		ArrayList al = parameter.get(key);
		return (String[]) al.toArray(new String[al.size()]);
	}

	public String getQueryString() {
		return queryString;
	}

	public int getCount() {
		return count;
	}

	public String[] getFileName() {
		return fileName;
	}

	public void setMaxFileSize(long size) {
		maxFileSize = size;
	}

	public void setTagFileName(String filename) {
		tagFileName = filename;
	}

	/***
	 * 设置上载文件要保存的路径
	 */
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
		File testdir = new File(saveDir); // 为了保证目录存在,如果没有则新建该目录
		if (!testdir.exists())
			testdir.mkdirs();
	}

	/***
	 * 设置字符集
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/***
	 * 用户调用的上载方法
	 */
	public boolean uploadFile() throws ServletException, IOException {
		setCharset(request.getCharacterEncoding());
		return uploadFile(request.getInputStream());
	}

	/***
	 * 清理参数占用的数据结构资源.
	 */
	public void clear() {
		Iterator i = this.parameter.values().iterator();
		while (i.hasNext()) {
			ArrayList al = (ArrayList) i.next();
			al.clear();
		}
		parameter.clear();
	}

	/***
	 * 取得InputStream中数据的主方法
	 */
	private boolean uploadFile(ServletInputStream in) throws ServletException,
			IOException {
		String line = null;
		byte[] buffer = new byte[256];
		byte[] lastbuffer = null;
		int lastlen = 0;
		ArrayList<String> tmpFileName = new ArrayList();
		while ((line = readLine(buffer, in, charset)) != null) {
			if (line.toLowerCase()
					.startsWith("content-disposition: form-data;")) {

				if (line.indexOf("filename=") != -1) { //如果一段分界符内的描述中有filename=,
														// 说明是文件的编码内容
					String fName = getFileName(line);
					if (fName.trim().length() == 0) {
						continue;
					}
					if (count == 0 && tagFileName.length() != 0) {
						// 对第一个文件扩展名进行特殊处理.
						String ext = fName
								.substring((fName.lastIndexOf(".") + 1));
						fName = tagFileName + "." + ext;
						//if(Constants.DEBUG){
						///	System.out.println(fName);
						//}
					}
					tmpFileName.add(fName);

					count++;
					while ((line = readLine(buffer, in, charset)) != null) {
						if (line.length() <= 2) {
							break;
						}
					}
					// System.out.println(fName);
					File f = new File(saveDir, fName);
					FileOutputStream out = new FileOutputStream(f);
					long size = 0l;
					lastbuffer = new byte[0];
					lastlen = 0;
					size += len;
					if (size >= maxFileSize) {
						throw new IOException("文件已超过最大值:" + this.maxFileSize
								+ "字节");
					}
					while ((line = readLine(buffer, in, null)) != null) {
						if (line.indexOf(boundary) != -1) {
							out.write(lastbuffer, 0, lastlen - 2);
							break;
						}
						out.write(lastbuffer, 0, lastlen);
						lastbuffer = arraycopy(buffer, 0, len);
						lastlen = len;
					}
					out.close();
				} else { // 否则是字段编码的内容
					String key = getKey(line);
					StringBuilder value = new StringBuilder();
					while ((line = readLine(buffer, in, charset)) != null) {
						if (line.length() <= 2) {
							break;
						}
					}
					while ((line = readLine(buffer, in, charset)) != null) {
						if (line.indexOf(boundary) != -1) {
							break;
						}
						value.append(line);
					}
					put(key.trim(), value.toString().trim(), parameter);
				}
			}
		}//while

		if (queryString != null) {
			String[] each = split(queryString, "&");
			for (int k = 0; k < each.length; k++) {
				String[] nv = split(each[k], "=");
				if (nv.length == 2) {
					put(nv[0].trim(), nv[1].trim(), parameter);
				}
			}
		}
		fileName = tmpFileName.toArray(new String[tmpFileName.size()]);
		tmpFileName.clear();
		if (fileName.length == 0) {
			return false; // 如果fileName数据为空说明没有上载任何文件
		}
		return true;
	}

	/***
	 * 可以将多个value压入一上同名KEY中.
	 */
	private void put(String key, String value, HashMap<String, ArrayList> hm) {
		ArrayList al = (hm.containsKey(key)) ? hm.get(key) : new ArrayList();
		al.add(value);
		hm.put(key, al);
	}

	/***
	 * 从输入流中读取一行数据到byte[]中同时返回该数据的String表示以例分析
	 */
	private String readLine(byte[] lineBytes, ServletInputStream in,
			String charset) {
		try {
			len = in.readLine(lineBytes, 0, lineBytes.length);
			if (len == -1)
				return null;
			if (charset == null)
				return new String(lineBytes, 0, len);
			return new String(lineBytes, 0, len, charset);
		} catch (Exception ex) {
			return null;
		}
	}

	/***
	 * 从描述字符串中分离出文件名
	 */
	private String getFileName(String line) {
		Matcher m = Pattern.compile("filename=\"([^\"]*)\"").matcher(line);
		if (m.find()) {
			String name = m.group(1);
			int offset = name.lastIndexOf("\\");
			name = name.substring(++offset);
			offset = name.lastIndexOf("/");
			name = name.substring(++offset);
			return name;
		}
		return "";
	}

	/***
	 * 从描述字符串中分离出字段名
	 */
	private String getKey(String line) {
		Matcher m = Pattern.compile("name\\=\"([^\"]*)\"").matcher(line);
		if (m.find())
			return m.group(1);
		return "";
	}

	/***
	 * 不用正则表达式分割字符串
	 */
	private static String[] split(String line, String mark) {
		if (line == null)
			return null;
		StringTokenizer st = new StringTokenizer(line, mark);
		ArrayList<String> tmp = new ArrayList();
		while (st.hasMoreTokens())
			tmp.add(st.nextToken());
		String[] strArr = tmp.toArray(new String[tmp.size()]);
		tmp.clear();
		return strArr;
	}

	private static byte[] arraycopy(byte[] source, int offset, int len) {
		byte[] target = new byte[source.length];
		for (int i = offset; i < offset + len; i++) {
			target[i] = source[i];
		}
		return target;
	}
}
