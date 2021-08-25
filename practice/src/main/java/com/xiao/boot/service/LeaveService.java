package com.xiao.boot.service;

import com.xiao.boot.bean.dto.Checktable;
import com.xiao.boot.bean.dto.Circuit;
import com.xiao.boot.bean.dto.Leavetable;
import com.xiao.boot.bean.po.R;

import java.util.List;

public interface LeaveService {
    Leavetable getMyLeaveService(String name);

    int addLeaveProcess(Circuit c);

    Integer checkProcess(Checktable check);

    int addResignProcess(Circuit c);

    int addReimburseProcess(Circuit c);

    R findPageCircuit(Long current, Long size);

}
