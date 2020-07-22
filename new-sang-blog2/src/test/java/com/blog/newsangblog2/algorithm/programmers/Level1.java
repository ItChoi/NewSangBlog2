package com.blog.newsangblog2.algorithm.programmers;

import org.junit.jupiter.api.Test;

/**
 * 프로그래머스 알고리즘 문제 풀어보자.
 */
public class Level1 {

    @Test
    public void 체육복() {
        /**
         *
         * 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
         * 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
         * 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
         * 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
         * 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
         *
         * 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
         * 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
         * 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
         *
         * 제한사항
         * - 전체 학생의 수는 2명 이상 30명 이하입니다.
         * - 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
         * - 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
         * - 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
         * - 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
         */

//        return 5
//        int n = 5; // 학생 수
//        int[] lost = {2, 4}; // 잃어버린 학생 수
//        int[] reserve = {1, 3, 5}; // 여유 학생 수

//        return 4
//        int n = 5;
//        int[] lost = {2, 4};
//        int[] reserve = {3};

//        return 2
//        int n = 3;
//        int[] lost = {3};
//        int[] reserve = {1};

//        return 6
//        int n = 7;
//        int[] lost = {1, 4};
//        int[] reserve = {5};

//        return 9
//        int n = 10;
//        int[] lost = {3, 9, 10};
//        int[] reserve = {3, 8, 9};

//        return 3
        int n = 5;
        int[] lost = {1, 2, 3, 4};
        int[] reserve = {2, 4, 5};

        int result = 체육복_함수1(n, lost, reserve);
        System.out.println("result: " + result);
    }

    // 체육복 private 함수 start
    private int 체육복_함수1(int n, int[] lost, int[] reserve) {
        int result = 0;
        result = n - lost.length;

        for (int i = 0; i < lost.length; i++) {
            int index = lost[i];

            for (int j = 0; j < reserve.length; j++) {
                if (reserve[j] == -1) {
                    continue;
                }

                if (j != reserve.length - 1) {
                    if (index == reserve[j + 1]) {
                        reserve[j + 1] = -1;
                        result++;
                        break;
                    }
                }

                if (index - 1 == reserve[j] || index == reserve[j] || index + 1 == reserve[j]) {
                    reserve[j] = -1;
                    result++;
                    break;
                }

            }
        }

        return result;
    }

    // 체육복 private 함수 start



}
