package com.accton.newframework.core.domain;


public interface IRepository <T,ID>{
    T getById(ID id);
}
