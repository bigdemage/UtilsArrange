package com.lyn.codeLearing.lambda;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StearmTest {
    public static void main(String[] args) throws NoSuchMethodException {

        List<String> list = Arrays.asList(new String[]{"www","baidu","com","www","google","com"});

        //有多少个单词长度大于3   parallelStream并行执行
        long count =list.parallelStream().filter(str->str.length()>3).count();

        System.out.println(count);


        Stream<BigInteger> bigIntegerStream=Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));

        List<String> list2= list.stream().filter(str->str.length()>3).collect(Collectors.toList());

        System.out.println(list2.toString());


        List<String> list3 =list.stream().map(str->str.toUpperCase()).distinct().collect(Collectors.toList());

        System.out.println(list3);



        List<String> list4 =list.stream().distinct().collect(Collectors.toList());

        System.out.println(list4);


        Optional<String> optional =list.stream().filter(str->str.startsWith("o")).findFirst();

//        System.out.println(optional.orElseThrow(NoSuchMethodException::new));

        List<Integer> intList =Arrays.asList(new Integer[]{1,2,3,4,5,6});


        //!!一个 Stream 只可以使用一次!!!!!!

        Stream<Integer> intStream =intList.stream();

        Optional<Integer> intOption =intStream.reduce(Integer::sum);

        Stream<Integer> intStream2=intList.stream();


        Integer ii =intStream2.reduce(100,(x,y)->x+y);

        System.out.println(intOption.get());

        System.out.println(ii);

    }
}
