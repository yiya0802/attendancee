package com.hodo.practice.service;


import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.TLeave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {
    Integer getIdByName(String name);

    R<TLeave> process(TLeave leave);

     List<TLeave> getMyService(String name);
}
