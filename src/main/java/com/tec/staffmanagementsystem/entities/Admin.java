package com.tec.staffmanagementsystem.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
@Getter
@Setter
@RequiredArgsConstructor
public class Admin extends BaseEntity {
    private static final long serialVersionUID = -1312649373937694507L;

    private long phoneNumber;

}
