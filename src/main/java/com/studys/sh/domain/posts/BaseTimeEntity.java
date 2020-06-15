package com.studys.sh.domain.posts;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass // JPA Entity 클래스들이 이 클래스를 상속할 경우 이 클래스의 필드들도 컬럼으로 인식하도록 만들어줌
@EntityListeners(AuditingEntityListener.class) //이 클래스에 Auditing 기능 (감사) 포함
public class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될 때 '시간'이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 '시간'이 자동 저장
    private LocalDateTime modifiedDate;

}
