package cn.y3h2.blog.web.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@UtilityClass
public class StreamUtil {

    public static <T, R> List<R> list(List<T> sourceList, Function<T, R> mapperFunction) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        }
        if (mapperFunction == null) {
            throw new RuntimeException("stream to list error, mapperFunction is null");
        }
        return sourceList.stream().map(mapperFunction).filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSet(List<T> sourceList, Function<T, R> mapperFunction) {
        return Sets.newHashSet(list(sourceList, mapperFunction));
    }

    public static <T, R> Set<R> toSet(List<T> sourceList, Function<T, R> mapperFunction, Predicate<T> filter) {
        return Sets.newHashSet(list(sourceList, mapperFunction, filter));
    }

    public static <T, R> Set<R> set(List<T> sourceList, Function<T, R> mapperFunction) {
        return new HashSet<>(list(sourceList, mapperFunction));
    }

    /**
     * 获取对象列表的某个字段list
     *
     * @param sourceList     源数据
     * @param mapperFunction 映射方法
     * @param filter         过滤器
     * @param <T>            源数据类型
     * @param <R>            结果数据类型
     * @return 转化后的列表数据
     */
    public static <T, R> List<R> list(List<T> sourceList, Function<T, R> mapperFunction, Predicate<? super T> filter) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        }
        if (mapperFunction == null) {
            throw new RuntimeException("stream to list error, mapperFunction is null");
        }
        if (filter == null) {
            throw new RuntimeException("stream to list error, mapperFunction is null");
        }
        return sourceList.stream().filter(filter).map(mapperFunction).distinct().collect(Collectors.toList());
    }

    /**
     * list -> map
     *
     * @param sourceList  源数据
     * @param keyMapper   key映射
     * @param valueMapper value映射
     * @param <T>         对象集合类型
     * @param <K>         map键类型
     * @param <R>         map值类型
     * @return 由list转化而的map
     */
    public static <T, K, R> Map<K, R> toMap(List<T> sourceList, Function<T, K> keyMapper, Function<T, R> valueMapper,
                                            BinaryOperator<R> mergeFunction) {
        if (CollectionUtils.isEmpty(sourceList) || keyMapper == null || valueMapper == null) {
            return Maps.newHashMap();
        }
        return sourceList.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    public static <T, K, R> Map<K, R> toMap(List<T> sourceList, Function<T, K> keyMapper, Function<T, R> valueMapper) {
        if (CollectionUtils.isEmpty(sourceList) || keyMapper == null || valueMapper == null) {
            return Maps.newHashMap();
        }
        return sourceList.stream().collect(Collectors.toMap(keyMapper, valueMapper, (v1, v2) -> v1));
    }

    public static <T, K, R> Map<K, R> toMap(List<T> sourceList, Function<T, K> keyMapper, Function<T, R> valueMapper,
                                            Predicate<? super T> filter) {
        if (CollectionUtils.isEmpty(sourceList) || keyMapper == null || valueMapper == null) {
            return Maps.newHashMap();
        }
        return sourceList.stream().filter(filter).collect(Collectors.toMap(keyMapper, valueMapper, (v1, v2) -> v1));
    }

    public static <T, K, R> Map<K, R> toNonNullMap(List<T> sourceList, Function<T, K> keyMapper,
                                                   Function<T, R> valueMapper) {
        if (CollectionUtils.isEmpty(sourceList) || keyMapper == null || valueMapper == null) {
            return Maps.newHashMap();
        }
        sourceList.removeIf(Objects::isNull);
        return sourceList.stream().collect(Collectors.toMap(keyMapper, valueMapper, (v1, v2) -> v1));
    }

    /**
     * list -> map (map值类型同list元素类型)
     *
     * @param sourceList 源数据
     * @param keyMapper  key映射
     * @param <T>        对象集合类型
     * @param <K>        map键类型
     * @return 由list转化而来的map
     */
    public static <T, K> Map<K, T> toMap(List<T> sourceList, Function<T, K> keyMapper) {
        return toMap(sourceList, keyMapper, Function.identity(), (v1, v2) -> v1);
    }

    public static <T, K> Map<K, List<T>> groupingBy(List<T> sourceList, Function<T, K> keyMapper) {
        if (CollectionUtils.isEmpty(sourceList) || keyMapper == null) {
            return Maps.newHashMap();
        }
        return sourceList.stream().collect(Collectors.groupingBy(keyMapper));
    }

    public static <T> List<T> sorted(List<T> sourceList, Comparator<T> comparator) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        }
        if (comparator == null) {
            throw new RuntimeException("stream sorted error, comparator is null");
        }
        return sourceList.stream().sorted(comparator).collect(Collectors.toList());
    }

    public static <T> List<T> sorted(List<T> sourceList, Predicate<T> filter, Comparator<T> comparator) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        }
        if (comparator == null) {
            throw new RuntimeException("stream sorted error, comparator is null");
        }
        return sourceList.stream().filter(filter).sorted(comparator).collect(Collectors.toList());
    }

    public static <T> T first(List<T> sourceList, Comparator<T> comparator) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        return sorted(sourceList, comparator).get(0);
    }

    public static <T> T first(List<T> sourceList, Predicate<T> filter, Comparator<T> comparator) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        return sorted(sourceList, filter, comparator).get(0);
    }


    public static void main(String[] args) {
        Map<Double, Integer> res = toNonNullMap(Lists.newArrayList(1, 3, null), Integer::doubleValue,
                Function.identity());
        System.out.println(res);
    }

}
