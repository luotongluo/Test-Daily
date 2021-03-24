package com.lt.activit.activiti.service;

import com.lt.activit.activiti.vo.ActiReqVo;

import java.util.Map;

/**
 * @author tong.luo
 * @description ActiDemoService
 * @date 2021/3/23 15:42
 */
public interface ActiDemoService {
    /**
     *
     * @param actiReqVo
     * @return
     */
    public Map createDemoActi(ActiReqVo actiReqVo);

    /**
     *
     * @param actiReqVo
     * @return
     */
    public Map startDemoActi(ActiReqVo actiReqVo);
}
