package com.zzong.egovframework.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdTime;
    
    @CreatedBy
    @Column(updatable = false, name = "writer_ip_address")
    private String writerIP;
    
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedTime;
    
    @LastModifiedBy
    @Column(name = "modifier_ip_address")
    private String modifierIP;
}
