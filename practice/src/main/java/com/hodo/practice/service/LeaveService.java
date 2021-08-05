package com.hodo.practice.service;

import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.TLeave;
import org.springframework.stereotype.Service;

@Service
public interface LeaveService {
    Integer getIdByName(String name);

    R<TLeave> process(TLeave leave);
}
