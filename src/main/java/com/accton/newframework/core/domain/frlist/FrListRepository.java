package com.accton.newframework.core.domain.frlist;

import com.accton.newframework.core.domain.IRepository;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import com.accton.newframework.core.domain.frlist.model.FrListModel;

import java.util.List;

public interface FrListRepository extends IRepository<FrListModel,Long> {

    List<FrListModel> getByFilter(FrListGet filter);
    FrListModel add(FrListAdd add);
}
