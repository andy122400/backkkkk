package com.accton.newframework.core.domain.frlist;

import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;

import java.util.List;

public interface FrListService {
    List<FrListResponse> getByFilter(FrListGet filter);
    FrListResponse add(FrListAdd add);
}
