package com.guidelk.tourism.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "module", schema = "tourism")
public class Module extends SharedModel {
    private Integer moduleId;
    private String moduleName;
    private String description;
    private String moduleCode;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODULE_G1")
    @SequenceGenerator(name = "MODULE_G1", sequenceName = "module_id", schema = "tourism", allocationSize = 1)
    @Column(name = "module_id", nullable = false, precision = 0, unique = true)
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Basic
    @Column(name = "module_name", nullable = false)
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "module_code")
    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

   }
