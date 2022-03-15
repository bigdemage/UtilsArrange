package com.lyn.codeLearing.modelLearning.filterPattern;


import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤男性
 * java8的filter已经做了，没必要做这些
 */
public class ManFilterImpl implements FilterService{

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> manList=persons.stream().filter(person -> person.getSex()==1).collect(Collectors.toList());
        return manList;
    }
}
