package com.vahap.twodatasource.model.service.servicelog;

import com.vahap.twodatasource.model.entity.radar.ServiceLog;
import com.vahap.twodatasource.model.repository.ServiceLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 29.09.2018
 */
@Service
@AllArgsConstructor
public class ServiceLogServiceImpl implements ServiceLogService {

    private final ServiceLogRepository serviceLogRepository;

    @Override
    public void save(ServiceLog t, Long userId) {
        serviceLogRepository.save(t, userId);
    }

    @Override
    public void update(ServiceLog t, Long userId) {
        serviceLogRepository.update(t, userId);
    }

    @Override
    public void deleteSoft(Long id, Long userId) {
        serviceLogRepository.deleteSoft(id, userId);
    }

    @Override
    public void deleteHard(Long id) {
        serviceLogRepository.deleteHard(id);
    }

    @Override
    public ServiceLog getById(Long id) {
        return serviceLogRepository.findById(id);
    }

    @Override
    public List<ServiceLog> getAll() {
        return serviceLogRepository.list();
    }
}
