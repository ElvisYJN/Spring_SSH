package com.xinhuanet.app.tool;

public class ThreadLocalPageCode {

	private static final ThreadLocal<String> lasttag = new ThreadLocal<String>();

	public static void setLasttag(String tag) {
		lasttag.set(tag);
	}

	public static String getLasttag() {
		return lasttag.get();
	}

	private static final ThreadLocal<Integer> lasttotalcount = new ThreadLocal<Integer>();

	public static void setLasttotalcount(Integer totalcount) {
		lasttotalcount.set(totalcount);
	}

	public static Integer getLasttotalcount() {
		return lasttotalcount.get();
	}

}
