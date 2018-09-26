package com.xinhuanet.app.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterAttachmentTools {

	public static List<String> findContentPic(String urlStr) {

		List<String> picList = new ArrayList<String>();

		String urlRegex = "http://[^\":<>]*\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png)";

		Pattern pattern2 = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);// 匹配表达式，忽略大小写
		Matcher matcher2 = pattern2.matcher(urlStr);

		// 对匹配的集合进行循环处理，将文件保存到本地
		while (matcher2.find()) {
			System.out.println(matcher2.group(0));
			picList.add(matcher2.group(0));
		}
		return picList;

		// List<String> picList = new ArrayList<String>();
		//
		// Pattern p = Pattern
		// .compile("(i?)<img.*? src=\"?(.*?\\.(jpg|gif|bmp|bnp|png))\".*? />");
		// Matcher m = p.matcher(str);
		// while (m.find()) {
		// int srcIndex = m.group(0).toString().indexOf("src=\"");
		// String strSrc = m.group(0).toString().substring(srcIndex);
		// int firstIndex = strSrc.indexOf("\"");
		// int lastIndex = strSrc.indexOf("\"", firstIndex + 1);
		// String src = strSrc.substring(firstIndex + 1, lastIndex);
		// picList.add(src);
		// }
		//
		// return picList;
	}

}
