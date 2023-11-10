package com.accton.newframework.core.domain;

import java.util.List;

public interface IRepository <T,ID>{
    T save(T model);
    void saveAll(List<T> models);
    T getById(ID id);
}
