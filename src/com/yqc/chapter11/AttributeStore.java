package com.yqc.chapter11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
/**
 * 该锁被持有过长时间
 *
 * @author yangqc
 * 2016年8月16日
 */
public class AttributeStore {
	private final Map<String, String> attributes = new HashMap<String, String>();

	public synchronized boolean userLocationMatches(String name, String regexp) {
		String key = "users." + name + ".location";
		String location = attributes.get(key);
		if (location == null) {
			return false;
		} else {
			return Pattern.matches(regexp, location);
		}
	}
}
