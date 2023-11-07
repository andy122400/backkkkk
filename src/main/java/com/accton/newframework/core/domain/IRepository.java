package com.accton.newframework.core.domain;

public interface IRepository <T,ID>{
    T add(T model);
    T getById(ID id);
}
