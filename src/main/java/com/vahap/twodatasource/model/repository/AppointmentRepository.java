package com.vahap.twodatasource.model.repository;

import com.vahap.twodatasource.model.entity.opus.Appointment;
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
 * @description TODO: Class Description
 */
@Repository
public class AppointmentRepository {
    @Autowired
    @PersistenceContext(unitName = DatasourceConstants.JPA_UNIT_NAME_OPUS)
    private EntityManager entityManager;

    public List<Appointment> list() {
        String sql = "Select app from " + Appointment.class.getName() + " app ";
        Query query = entityManager.createQuery(sql, Appointment.class);
        return query.getResultList();
    }

    public Appointment findById(Long id) {
        return this.entityManager.find(Appointment.class, id);
    }

    public void save(Appointment appointment, Long userId) {
        appointment.setCUser(userId);
        entityManager.getTransaction().begin();
        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
    }

    public void update(Appointment appointment, Long userId) {
        appointment.setUUser(userId);
        entityManager.getTransaction().begin();
        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
    }

    public void deleteSoft(Long id, Long userId) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setUUser(userId);
        appointment.setIsActv(Boolean.FALSE);

        entityManager.getTransaction().begin();
        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
    }

    public void deleteHard(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(appointment);
        entityManager.getTransaction().commit();
    }

    public void saveAll(List<Appointment> appointments, Long userId) {
        entityManager.getTransaction().begin();
        for (int i = 1; i <= appointments.size(); i++) {
            appointments.get(i).setCUser(userId);
            entityManager.persist(appointments.get(i));
            if ((i % 10000) == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.getTransaction().commit();
    }

    public void updateAll(List<Appointment> appointments, Long userId) {
        entityManager.getTransaction().begin();
        for (int i = 1; i <= appointments.size(); i++) {
            appointments.get(i).setUUser(userId);
            entityManager.persist(appointments.get(i));
            if ((i % 10000) == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.getTransaction().commit();
    }

}
