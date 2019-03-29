package com.suyang.mbg.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtils {
    /**
     * text是否存在于array集合中
     *
     * @param array           要检测的集合
     * @param text            要检测的文本
     * @param isCaseSensitive 是否大小写敏感
     * @return
     */
    public static boolean container(String[] array, String text, boolean isCaseSensitive) {
        boolean result = false;
        if (!StringUtils.isEmpty(text) && null != array && array.length > 0) {
            for (String s : array) {
                if (isCaseSensitive) {
                    if (s.equals(text)) {
                        result = true;
                        break;
                    }
                } else {
                    if (s.equalsIgnoreCase(text)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据某一个属性的值，在结合中查询
     *
     * @param list      要查询的集合
     * @param propName  属性名称
     * @param propValue 属性的值
     * @return 匹配的对象
     */
    public static <T> T findOne(List<T> list, String propName, Object propValue) {
        T result = null;
        if (list != null && list.size() > 0) {
            try {
                Field field = list.get(0).getClass().getDeclaredField(propName);
                field.setAccessible(true);
                for (T t : list) {
                    Object value = field.get(t);
                    if (value.equals(propValue)) {
                        result = t;
                        break;
                    }
                }
            } catch (NoSuchFieldException e) {
            } catch (SecurityException e) {
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            }
        }
        return result;
    }

    /**
     * 查找集合中存在的项（返回所有匹配的项）
     *
     * @param list       检测的集合
     * @param func       判断方法
     * @param isDistinct 是否去除重复
     * @return
     */
    public static <T> List<T> find(List<T> list, Predicate<T> func, boolean isDistinct) {
        List<T> result = new ArrayList<>();
        if (list == null || list.size() == 0 || func == null)
            return result;

        for (T t : list) {
            if (func.test(t)) {
                if (t == null)
                    continue;
                if (isDistinct && result.contains(t)) {
                    continue;
                }
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 查找集合中存在的一个项
     *
     * @param list 检测的集合
     * @param func 判断方法
     * @return
     */
    public static <T> T findOne(List<T> list, Predicate<T> func) {
        T result = null;
        if (list == null || list.size() == 0 || func == null)
            return result;

        for (T t : list) {
            if (func.test(t)) {
                result = t;
                break;
            }
        }
        return result;
    }

    /**
     * 数组转List
     *
     * @param array 数组
     * @return
     */
    public static <T> List<T> toList(T[] array) {
        List<T> result = new ArrayList<>();
        if (array != null && array.length > 0) {
            for (T t : array) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 集合转数组
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(List<T> list) {
        if (list == null)
            return null;
        T[] result = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 去除List中重复的对象
     *
     * @param list
     * @return
     */
    public static <T> List<T> distinct(List<T> list) {
        List<T> result = new ArrayList<T>();
        if (list != null && list.size() > 0) {
            for (T t : list) {
                if (!result.contains(t)) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    /**
     * 根据字符串、分隔符、返回字符串集合
     *
     * @param text 字符串
     * @param strSplit 分隔符
     * @param isDistinct 是否去掉重复
     * @return
     */
    public static List<String> split(String text, String strSplit, boolean isDistinct) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(strSplit))
            return result;

        String[] array = text.split(strSplit);
        if (array != null && array.length > 0) {
            for (String s : array) {
                s = s.trim();
                if (s == null)
                    continue;
                if (isDistinct && result.contains(s)) {
                    continue;
                }
                result.add(s);
            }
        }
        return result;
    }

    /**
     * 返回集合中，某个属性的集合
     *
     * @param list 检测的集合
     * @param propName 属性名称
     * @param isDistinct 是否去除重复
     * @return
     */
    public static <TProperty, TObject> List<TProperty> getPropertyList(List<TObject> list, String propName, boolean isDistinct) {
        List<TProperty> result = new ArrayList<TProperty>();
        if (list == null || list.size() == 0 || StringUtils.isEmpty(propName))
            return result;
        Field field = null;
        for (TObject t : list) {
            if (field == null) {
                try {
                    field = t.getClass().getDeclaredField(propName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
            field.setAccessible(true);
            try {
                @SuppressWarnings("unchecked")
                TProperty value = (TProperty) field.get(t);
                if (isDistinct && result.contains(value)) {
                    continue;
                }
                result.add(value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将指定的集合转换成另一个集合
     *
     * @param list 检测的集合
     * @param func 转换方法
     * @param isDistinct 是否去除重复
     * @return
     */
    public static <Tin, Tout> List<Tout> convertAll(List<Tin> list, Function<Tin, Tout> func, boolean isDistinct) {
        List<Tout> result = new ArrayList<>();
        if (list == null || list.size() == 0 || func == null)
            return result;

        for (Tin t : list) {
            Tout out = func.apply(t);
            if (isDistinct && result.contains(out))
                continue;
            result.add(out);
        }
        return result;
    }

    /**
     * 将集合转换成按指定字符串分割的字符串
     *
     * @param list 集合
     * @param strSplit 分隔符
     * @return
     */
    public static <T> String toString(List<T> list, String strSplit) {
        if (list == null || list.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i != list.size() - 1) {
                sb.append(strSplit);
            }
        }
        return sb.toString();
    }

    /**
     * 取出集合中的指定对象
     *
     * @param list 检测的集合
     * @param skip 跳过几个
     * @param take 取出几个
     * @return
     */
    public static <T> List<T> take(Collection<T> list, int skip, int take) {
        List<T> result = new ArrayList<T>();
        if (skip < list.size() && take > 0) {
            int index = 0;
            Iterator<T> itr = list.iterator();
            while (itr.hasNext()) {
                T t = itr.next();
                if (index >= skip && index < skip + take) {
                    result.add(t);
                }
                index++;
            }
        }
        return result;
    }

}
