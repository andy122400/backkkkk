package com.accton.newframework.core.domain.frlist;


import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import com.accton.newframework.core.domain.frlist.model.FrListModel;
import com.accton.newframework.core.domain.mapper.FrListDomainMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrListServiceImpl implements FrListService {

    private final FrListRepository repository;

    public FrListServiceImpl(FrListRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FrListResponse> getByFilter(FrListGet filter) {
        List<FrListModel> list = repository.getByFilter(filter);
        if (list!=null){
            return list.stream().map(FrListDomainMapper::toDto).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public FrListResponse add(FrListAdd add) {
        return FrListDomainMapper.toDto(repository.add(add));
    }


}
