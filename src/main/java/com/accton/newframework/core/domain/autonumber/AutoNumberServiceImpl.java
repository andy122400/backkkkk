package com.accton.newframework.core.domain.autonumber;

import com.accton.newframework.core.application.dto.response.AutoNumberResponse;
import com.accton.newframework.core.domain.autonumber.event.AutoNumberAdd;
import com.accton.newframework.core.domain.autonumber.model.AutoNumberModel;
import com.accton.newframework.core.domain.mapper.AutoNumberDomainMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoNumberServiceImpl implements AutoNumberService {

    private final AutoNumberRepository autoNumberRepository;

    public AutoNumberServiceImpl(AutoNumberRepository autoNumberRepository) {
        this.autoNumberRepository = autoNumberRepository;
    }

    @Override
    public List<AutoNumberResponse> list() {
        List<AutoNumberModel> res = autoNumberRepository.list();
        if (res != null) {
            return res.stream().map(AutoNumberDomainMapper::toDto).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public AutoNumberResponse save(AutoNumberAdd event) {
        return AutoNumberDomainMapper.toDto(autoNumberRepository.save(event));
    }

    @Override
    public void deleteAutoNumber(Long id) {
        autoNumberRepository.deleteAutoNumber(id);
    }
}
