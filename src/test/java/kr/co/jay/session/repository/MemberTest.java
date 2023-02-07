package kr.co.jay.session.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {


    @Test
    public void getId(){
        final Member member = Member.builder()
                .id(123)
                .name("단위유닛")
                .build();
        final int id = member.getId();
        assertEquals("testt", id);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}