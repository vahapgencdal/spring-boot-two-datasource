package com.vahap.twodatasource.model.service.servicelog;

import com.vahap.twodatasource.model.entity.radar.ServiceLog;
import com.vahap.twodatasource.model.service.BaseService;

import java.util.List;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 29.09.2018
 * @description: Authentication Service for user login, register
 */
public interface ServiceLogService extends BaseService<ServiceLog> {
    List<ServiceLog> getAll();

}
