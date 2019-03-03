package com.vahap.twodatasource.model.service;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 02.10.2018
 */
public interface BaseService<T> {
    void save(T t, Long userId);

    void update(T t, Long userId);

    void deleteSoft(Long id, Long userId);

    void deleteHard(Long id);

    T getById(Long id);
}
