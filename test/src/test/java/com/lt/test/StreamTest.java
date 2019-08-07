//package com.lt.test;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Set;
//import java.util.function.Function;
//import java.util.function.IntConsumer;
//import java.util.stream.Collectors;
//
///**
// * @Author: LT
// * @Date: 2019/4/15 11:11
// * @Description:
// * @Version 1.0
// */
//public class StreamTest {
//    /**
//     * 主要接口
//     * 1,predicate
//     * 2,Unary/BinaryOperator:传入参数和返回值必然是同一种数据类型
//     * 3,Int/Double/LongFunction/BiFunction:函数接口并不要求传入参数和返回值之间的数据类型必须一样
//     * 4,Int/Long/DoubleConsumer/BiConsumer:消费数据
//     * 5,Int/Long/DoubleSupplier:生产数据
//     *
//     * 主要方法:
//     * 1,filter
//     * 2,map
//     * 3,reduce
//     * 4,collect
//     * 5,peek
//     * -Djdk.internal.lambda.dumpProxyClasses
//     * Created by codecraft on 2016-02-05.
//     */
//
//        int[] arr = {4,12,1,3,5,7,9};
//
//        @Test
//        public void filter(){
//            Arrays.stream(arr).filter((x) -> x%2 !=0).forEach(System.out::println);
//        }
//
//        @Test
//        public void map(){
//            Arrays.stream(arr).map((x) -> x * x).forEach(System.out::println);
//        }
//
//        @Test
//        public void reduce(){
//            Arrays.stream(arr).reduce((x,y) -> x+y).ifPresent(System.out::println);
//            System.out.println(Arrays.stream(arr).reduce(-10, (x, y) -> x + y));
//        }
//
//        @Test
//        public void collect(){
//            List<Integer> list = Arrays.stream(arr).collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
//            System.out.println(list);
//
//            Set<Integer> set = list.stream().collect(Collectors.toSet());
//            System.out.println(set);
//
//            Map<String,Artist> map = SampleData.getThreeArtists().stream()
//                    .collect(Collectors.toMap(a -> a.getName(),a -> a));
//            System.out.println(map);
//        }
//
//        @Test
//        public void peek(){
//            long count = Arrays.stream(arr).filter(x -> x > 2).peek(System.out::println).count();
//            System.out.println(count);
//        }
//
//        @Test
//        public void average(){
//            Arrays.stream(arr).average().ifPresent(System.out::println);
//        }
//
//        @Test
//        public void sum(){
//            System.out.println(Arrays.stream(arr).sum());
//        }
//
//        @Test
//        public void max(){
//            Arrays.stream(arr).max().ifPresent(System.out::println);
//        }
//
//        @Test
//        public void min(){
//            Arrays.stream(arr).min().ifPresent(System.out::println);
//        }
//
//        @Test
//        public void sorted(){
//            Comparator<Artist> asc = (x,y) -> x.getName().compareTo(y.getName());
//            SampleData.getThreeArtists().stream().sorted(asc).forEach(System.out::println);
//            SampleData.getThreeArtists().stream().sorted(asc.reversed()).forEach(System.out::println);
//            SampleData.getThreeArtists().stream().sorted(Comparator.comparing(Artist::getName)).forEach(System.out::println);
//            SampleData.getThreeArtists().stream().sorted(Comparator.comparing(Artist::getName).reversed()).forEach(System.out::println);
//
//            SampleData.getThreeArtists().stream().sorted(Comparator.comparing(Artist::getName).thenComparing(Artist::getNationality)).forEach(System.out::println);
//        }
//
//        @Test
//        public void groupBy(){
//            Map<String,List<Artist>> rs = SampleData.getThreeArtists().stream().collect(Collectors.groupingBy(Artist::getNationality));
//            System.out.println(rs);
//        }
//
//        @Test
//        public void join(){
//            String joinedNames = SampleData.getThreeArtists().stream().map(Artist::getName).collect(Collectors.joining(","));
//            System.out.println(joinedNames);
//            joinedNames.chars().mapToObj(c -> (char) Character.toUpperCase(c)).forEach(System.out::println);
//        }
//
//        @Test
//        public void flatMap(){
//            Set<Artist> rs = SampleData.getThreeArtists().stream().flatMap(a -> a.getMembers()).collect(Collectors.toSet());
//            rs.stream().forEach(System.out::println);
//        }
//
//        @Test
//        public void arrStream(){
//            Arrays.stream(arr).forEach(System.out::println);
//        }
//
//        @Test
//        public void then(){
////        IntConsumer out = System.out::println;
////        IntConsumer err = System.err::println;
//            IntConsumer out = (x) -> System.out.println("out consume:"+x);
//            IntConsumer err = (x) -> System.err.println("err consume:"+x);
////        Arrays.stream(arr).forEach(out.andThen(err));
//            Arrays.stream(arr).forEach(err.andThen(out));
//        }
//
//        @Test
//        public void foreach(){
//            List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
//            numbers.forEach(System.out::println);
//        }
//
//        @Test
//        public void visitOuterVar(){
//            final int num = 2;
//            Function<Integer,Integer> fun = (from) -> from * num;
//            System.out.println(fun.apply(3));
//        }
//}
