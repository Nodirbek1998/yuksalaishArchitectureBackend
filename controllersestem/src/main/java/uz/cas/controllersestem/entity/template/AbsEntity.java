package uz.cas.controllersestem.entity.template;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsEntity {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss",locale = "Asia/Tashkent")
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss",locale = "Asia/Tashkent")
    @UpdateTimestamp
    private Date updatedAt;

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @LastModifiedBy
    private UUID updatedBy;


}
