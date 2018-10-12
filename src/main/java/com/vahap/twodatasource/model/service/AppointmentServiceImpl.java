package com.vahap.twodatasource.model.service;

import com.vahap.twodatasource.model.entity.opus.Appointment;
import com.vahap.twodatasource.model.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 29.09.2018
 * @description TODO: Class Description
 */
@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public void save(Appointment t, Long userId) {
        appointmentRepository.save(t, userId);
    }

    @Override
    public void update(Appointment t, Long userId) {
        appointmentRepository.update(t, userId);
    }

    @Override
    public void deleteSoft(Long id, Long userId) {
        appointmentRepository.deleteSoft(id, userId);
    }

    @Override
    public void deleteHard(Long id) {
        appointmentRepository.deleteHard(id);
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id);
    }
}
