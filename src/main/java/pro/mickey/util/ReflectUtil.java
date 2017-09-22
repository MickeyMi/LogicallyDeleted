
package pro.mickey.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtil {
	/**
	 * 获取指定对象的指定属性
	 * 
	 * @param obj
	 *            指定对象
	 * @param fieldName
	 *            指定属性名称
	 * @return 指定属性
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		Object result = null;
		Field field = ReflectUtil.getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				result = field.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取指定对象里面的指定属性对象
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            指定属性名称
	 * @return 属性对象
	 */
	private static Field getField(Object obj, String fieldName) {
		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				// do nothing
			}
		}
		return field;
	}

	/**
	 * 设置指定对象的指定属性值
	 * 
	 * @param obj
	 *            指定对象
	 * @param fieldName
	 *            指定属性
	 * @param fieldValue
	 *            指定属性值
	 */
	public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
		Field field = ReflectUtil.getField(obj, fieldName);
		if (field != null) {
			try {
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 调用对象方法返回
	 * 
	 * @param obj
	 *            需要调用的对象
	 * @param methodNmae
	 *            方法名称
	 * @return 调用方法返回的值
	 */
	public static Object transferMethod(Object obj, String methodNmae) {
		try {
			Class<? extends Object> cls = obj.getClass();
			// 获得类的私有方法
			Method method = cls.getDeclaredMethod(methodNmae, null);
			method.setAccessible(true);
			// 调用该方法
			return method.invoke(obj, null);
		} catch (Exception e) {
			new RuntimeException(e);
			return null;
		}
	}
}