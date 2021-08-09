package com.hodo.practice.service;


import com.hodo.practice.entity.R;
import com.hodo.practice.entity.dto.Circuit;
import com.hodo.practice.entity.dto.check;
import com.hodo.practice.entity.po.TLeave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {
    /**
     * @anthor :zyy
     * @description: 离职，请假说明表
     * @param: name
     * @return: integer
     */
    Integer getIdByName(String name);
    /**
     *
     * @description: 流程
     * @param: leave
     * @return: List
     * @date: 2021/8/9
     */
    List<String> process(TLeave leave);
    /**
     *
     * @description: 获得自己的流程信息
     * @param: name
     * @return: list
     * @date: 2021/8/9
     */
     List<TLeave> getMyService(String name);
    /**
     *
     * @description: 审核
     * @param: check
     * @return: list
     * @date: 2021/8/9
     */

    List<String> checkProcess(check check);
    /**
     *
     * @description: 添加离开事件
     * @param: c
     * @return: integer
     * @date: 2021/8/9
     */

    Integer addLeaveProcess(Circuit c);
}
