package com.tec.staffmanagementsystem.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Data
@DynamicInsert
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
/**
 *
 * @author tec
 */

/**
 * The class Base entity implements serializable
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1312649373937694507L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private String firstName;

    private String lastName;

    private LocalDate employmentDate;

    private long salary;

    private String userName;

    private String password;



}
