package com.tsaidenis.mainservice.mapper;

public interface Mapper <D,S>{
    D map(S source);
}