package com.tec.staffmanagementsystem.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Engineer")
@Getter
@Setter
@RequiredArgsConstructor
public class Engineer extends BaseEntity {

    public static final long serialVersionUID = -1312649373937694507L;

    private String email;
}
