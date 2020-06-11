package com.blog.newsangblog2.web.manager.menu.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ManagerMenuDtoTest {

    @Test
    public void test() {
        List<TestData> tests = new ArrayList<>();
        tests.add(new TestData("이름1", 2));
        tests.add(new TestData("이름2", 5));
        tests.add(new TestData("이름3", 4));
        tests.add(new TestData("이름4", 1));
        tests.add(new TestData("이름5", 3));

        tests = tests.stream()
                .sorted(
                        (o1, o2) -> o1.getOrdering().compareTo(o2.getOrdering())
                )
                .collect(Collectors.toList());


        /*tests.stream()
                .sorted((o1, o2) -> {
                });*/

        for (TestData test : tests) {
            System.out.println("test: " + test.getOrdering());
        }
    }

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor
    class TestData {
        private String name;
        private Integer ordering;
    }

}