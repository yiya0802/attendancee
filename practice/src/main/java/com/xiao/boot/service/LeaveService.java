package com.xiao.boot.service;

import com.xiao.boot.bean.dto.Checktable;
import com.xiao.boot.bean.dto.Circuit;
import com.xiao.boot.bean.dto.Leavetable;

public interface LeaveService {
    Leavetable getMyLeaveService(String name);

    int addLeaveProcess(Circuit c);

    Integer checkProcess(Checktable check);

    int addResignProcess(Circuit c);

    int addReimburseProcess(Circuit c);
}
