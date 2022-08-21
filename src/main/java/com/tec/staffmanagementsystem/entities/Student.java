package com.tec.staffmanagementsystem.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Student")
@Getter
@Setter
@RequiredArgsConstructor

/**
 *
 * @author tec
 */
/**
 * The class Student extends base entity
 */
public class Student extends BaseEntity {
    private static final long serialVersionUID = -1312649373937694507L;

    private long EngineerId;
    private String University;
}
