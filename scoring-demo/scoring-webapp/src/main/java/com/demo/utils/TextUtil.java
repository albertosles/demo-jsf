package com.demo.utils;

import java.util.Collection;
import java.util.UUID;

public class TextUtil {

	public static String toStringOrNull(Object obj) {
		return (null == obj) ? null : obj.toString();
	}

	public static String toStringOrEmpty(Object obj) {
		return (null == obj) ? "" : obj.toString();
	}

	public static StringBuilder addIfNotNull(StringBuilder sb, Object... objs) {
		for (Object obj : objs) {
			if (null != obj) {
				sb.append(obj.toString());
			} else {
				break;
			}
		}
		return sb;
	}


	public static StringBuilder addAllIfNotNull(StringBuilder sb,
	                                            Object... objs)
	{
		for (Object obj : objs) {
			if (null != obj) {
				sb.append(obj.toString());
			}
		}
		return sb;

	}

	public static boolean isNotEmpty(String s) {
		return (s != null && s.length() > 0);
	}

	public static boolean isNotEmpty(Collection<?> c) {
		return (c != null && c.size() > 0);
	}


	public static boolean isNotEmpty(Object obj) {
		return (obj instanceof String) ? (obj.toString().length() > 0)
		        : (obj != null);
	}


	public static boolean isEmpty(String s) {
		return (s == null || s.length() == 0);
	}

	public static boolean isEmpty(Collection<?> c) {
		return (c == null || c.isEmpty());
	}


	public static boolean isEmpty(Object obj) {
		return (obj instanceof String) ? (obj.toString().length() == 0)
		        : (obj == null);
	}


	public static boolean isNotWhitespaced(String s) {
		return (s != null && s.trim().length() > 0);
	}

	public static boolean isWhitespaced(String s) {
		return (s == null || s.trim().length() == 0);
	}

	public static boolean isInBound(Collection<?> c, int idx) {
		return (c != null && idx >= 0 && c.size() > idx);
	}


	public static boolean isInBound(Collection<?> c, Number idx) {
		return (idx != null && isInBound(c, idx.intValue()));
	}

	public static String generateUUID() {
		UUID value = UUID.randomUUID();
		return String.valueOf(value);
	}

}
