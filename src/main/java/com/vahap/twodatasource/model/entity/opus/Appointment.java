package com.vahap.twodatasource.model.entity.opus;

import com.vahap.twodatasource.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 3.10.2018
 * @description This Entity wont use for login, added for only show example for entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APPOINTMENTS")
@EqualsAndHashCode(callSuper = true)
@GenericGenerator(name = "TABLE_SEQUENCE", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@org.hibernate.annotations.Parameter(name = "optimizer", value = "none"), @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"), @org.hibernate.annotations.Parameter(name = "increment_size", value = "50"), @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "SEQ_APPOINTMENTS")})
public class Appointment extends BaseEntity {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "BRANCH_ID")
    private String branchId;

    @Column(name = "OPERATOR_ID")
    private String operatorId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "URL")
    private String url;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "APP_DATE", nullable = false)
    private LocalDate appDate;

    @Column(name = "START_TIME", nullable = false)
    private LocalTime startTime;

    @Column(name = "END_TIME", nullable = false)
    private LocalTime endTime;

}