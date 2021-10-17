package com.guidelk.tourism.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "role", schema = "tourism")
public class Role extends SharedModel {
    private Integer roleId;
    private String roleName;
    private String roleDescription;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_G1")
    @SequenceGenerator(name = "ROLE_G1", sequenceName = "role_id", schema = "tourism", allocationSize = 1)
    @Column(name = "role_id", nullable = false, precision = 0, unique = true)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name",nullable = false)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_description")
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

   }
