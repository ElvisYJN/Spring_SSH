package com.xinhuanet.app.tool;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.springframework.util.StringUtils;

public class Lang {
	public static Type[] getTypeParams(Class<?> klass) {
		if (klass != null && !"java.lang.Object".equals(klass.getName())) {
			Type superclass = klass.getGenericSuperclass();
			if (superclass instanceof ParameterizedType) {
				return ((ParameterizedType) superclass)
						.getActualTypeArguments();
			} else {
				Type[] interfaces = klass.getGenericInterfaces();
				Type[] arg2 = interfaces;
				int arg3 = interfaces.length;

				for (int arg4 = 0; arg4 < arg3; ++arg4) {
					Type inf = arg2[arg4];
					if (inf instanceof ParameterizedType) {
						return ((ParameterizedType) inf)
								.getActualTypeArguments();
					}
				}

				return getTypeParams(klass.getSuperclass());
			}
		} else {
			return null;
		}
	}

	public static <T> Class<T> getTypeParam(Class<?> klass, int index)
			throws Exception {
		Type[] types = getTypeParams(klass);
		if (index >= 0 && index < types.length) {
			Type t = types[index];
			Class clazz = getTypeClass(t);
			if (clazz == null) {
				throw new Exception("Type \'%s\' is not a Class" + t.toString());
			} else {
				return clazz;
			}
		} else {
			throw new Exception("Class type param out of range %d/%d" + index
					+ types.length);
		}
	}

	public static Class<?> getTypeClass(Type type) {
		Class clazz = null;
		if (type instanceof Class) {
			clazz = (Class) type;
		} else if (type instanceof ParameterizedType) {
			ParameterizedType wt = (ParameterizedType) type;
			clazz = (Class) wt.getRawType();
		} else {
			if (type instanceof GenericArrayType) {
				GenericArrayType wt3 = (GenericArrayType) type;
				Class tLow1 = getTypeClass(wt3.getGenericComponentType());
				return Array.newInstance(tLow1, 0).getClass();
			}

			Type[] tLow;
			if (type instanceof TypeVariable) {
				TypeVariable wt1 = (TypeVariable) type;
				tLow = wt1.getBounds();
				if (tLow != null && tLow.length > 0) {
					return getTypeClass(tLow[0]);
				}
			} else if (type instanceof WildcardType) {
				WildcardType wt2 = (WildcardType) type;
				tLow = wt2.getLowerBounds();
				if (tLow.length > 0) {
					return getTypeClass(tLow[0]);
				}

				Type[] tUp = wt2.getUpperBounds();
				return getTypeClass(tUp[0]);
			}
		}

		return clazz;
	}

	public static String filterString(String str) throws PatternSyntaxException {
		if (StringUtils.isEmpty(str)) {
			return "";
		} else {
			String regEx = "[`~!@#$%^&*()+=|{}\':;\',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"《》]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			return m.replaceAll("@").trim();
		}
	}

	public static int getAge(Date birthday) {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			return 0;
		} else {
			int yearNow = cal.get(1);
			int monthNow = cal.get(2);
			int dayOfMonthNow = cal.get(5);
			cal.setTime(birthday);
			int yearBirth = cal.get(1);
			int monthBirth = cal.get(2);
			int dayOfMonthBirth = cal.get(5);
			int age = yearNow - yearBirth;
			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth) {
						--age;
					}
				} else {
					--age;
				}
			}

			return age;
		}
	}

	public static boolean isEmpty(Object obj) {
		return obj == null ? true : (obj instanceof CharSequence
				&& "".equals(obj) ? true
				: (obj.getClass().isArray() ? Array.getLength(obj) == 0
						: (obj instanceof Collection ? ((Collection) obj)
								.isEmpty() : (obj instanceof Map ? ((Map) obj)
								.isEmpty() : false))));
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static <T> List<T> list(T... eles) {
		ArrayList list = new ArrayList(eles.length);
		Object[] arg1 = eles;
		int arg2 = eles.length;

		for (int arg3 = 0; arg3 < arg2; ++arg3) {
			Object ele = arg1[arg3];
			list.add(ele);
		}

		return list;
	}
}
