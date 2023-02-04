package kr.co.jay.session.repository;

import org.springframework.data.annotation.Id;

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
