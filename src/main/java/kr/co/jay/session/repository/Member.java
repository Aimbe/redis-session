package kr.co.jay.session.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Member {

    @Id
    private Integer id;

    private String name;

}
