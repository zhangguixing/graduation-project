package com.sams.service;

import com.sams.dao.SystemInfoDao;
import com.sams.entity.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SysteminfoService {

    @Autowired
    private SystemInfoDao systemInfoDao;

    public SystemInfo getSystemInfo() {
        List<SystemInfo> systemInfos = systemInfoDao.selectAll();
        if(!CollectionUtils.isEmpty(systemInfos)){
            return systemInfos.get(0);
        }
        return null;
    }

    public SystemInfo updateSystemInfo(String name, String value) {
        systemInfoDao.updateSystemInfo(name,value);
        return getSystemInfo();
    }
}
