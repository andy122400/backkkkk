package com.accton.newframework.core.domain;

public interface IRepository <T,ID>{
    T save(T model);
    T getById(ID id);
}
