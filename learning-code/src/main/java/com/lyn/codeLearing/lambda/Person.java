package com.lyn.codeLearing.lambda;

public interface Person {
    Long getId();
    default String getName(){return "wudi";}
}
