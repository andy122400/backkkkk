package com.accton.newframework.core.domain.frlist;

import com.accton.newframework.core.domain.IRepository;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListDetailAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import com.accton.newframework.core.domain.frlist.model.FrListDetailModel;
import com.accton.newframework.core.domain.frlist.model.FrListModel;

import java.util.List;

public interface FrListRepository extends IRepository<FrListModel,Long> {

    List<FrListModel> getByFilter(FrListGet filter);
    FrListModel save(FrListAdd add);
    List<FrListDetailModel> detail(Long id);
    void deleteFrList(Long id);
    void deleteFrListDetail(Long id);
    FrListDetailModel save(FrListDetailAdd add);
}
