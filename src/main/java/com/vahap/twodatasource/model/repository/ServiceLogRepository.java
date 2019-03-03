package com.vahap.twodatasource.model.repository;

import com.vahap.twodatasource.model.entity.radar.ServiceLog;
import com.vahap.twodatasource.util.DatasourceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 29.09.2018
 */
@Repository
public class ServiceLogRepository {
    @Autowired
    @PersistenceContext(unitName = DatasourceConstants.JPA_UNIT_NAME_RADAR)
    private EntityManager entityManager;

    public List<ServiceLog> list() {
        String sql = "Select app from " + ServiceLog.class.getName() + " app ";
        Query query = entityManager.createQuery(sql, ServiceLog.class);
        return query.getResultList();
    }

    public ServiceLog findById(Long id) {
        return this.entityManager.find(ServiceLog.class, id);
    }

    public void save(ServiceLog serviceLog, Long userId) {
        serviceLog.setCUser(userId);
        entityManager.getTransaction().begin();
        entityManager.persist(serviceLog);
        entityManager.getTransaction().commit();
    }

    public void update(ServiceLog serviceLog, Long userId) {
        serviceLog.setUUser(userId);
        entityManager.getTransaction().begin();
        entityManager.persist(serviceLog);
        entityManager.getTransaction().commit();
    }

    public void deleteSoft(Long id, Long userId) {
        ServiceLog serviceLog = entityManager.find(ServiceLog.class, id);
        serviceLog.setUUser(userId);
        serviceLog.setIsActv(Boolean.FALSE);

        entityManager.getTransaction().begin();
        entityManager.persist(serviceLog);
        entityManager.getTransaction().commit();
    }

    public void deleteHard(Long id) {
        ServiceLog serviceLog = entityManager.find(ServiceLog.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(serviceLog);
        entityManager.getTransaction().commit();
    }

    public void saveAll(List<ServiceLog> serviceLogs, Long userId) {
        entityManager.getTransaction().begin();
        for (int i = 1; i <= serviceLogs.size(); i++) {
            serviceLogs.get(i).setCUser(userId);
            entityManager.persist(serviceLogs.get(i));
            if ((i % 10000) == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.getTransaction().commit();
    }

    public void updateAll(List<ServiceLog> serviceLogs, Long userId) {
        entityManager.getTransaction().begin();
        for (int i = 1; i <= serviceLogs.size(); i++) {
            serviceLogs.get(i).setUUser(userId);
            entityManager.persist(serviceLogs.get(i));
            if ((i % 10000) == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.getTransaction().commit();
    }
}
