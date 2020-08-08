package com.blog.newsangblog2.algorithm.programmers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    result++;
                    break;
                }
            }
        }

        for (int i = 0; i < lost.length; i++) {
            if (lost[i] == -1) {
                continue;
            }

            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] - 1 == reserve[j] || lost[i] == reserve[j] || lost[i] + 1 == reserve[j]) {
                    reserve[j] = -1;
                    result++;
                    break;
                }
            }
        }


        return result;
    }

    // 체육복 private 함수 start


    @Test
    public void 알고리즘_문제() {
        String q = "ab23c4d56e78f9g12h34i5j12k45l67n89m99o1k123p456q567r768s890t67u456v345w234x23y239z";
        String regEx = "\\d{1,}+";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(q);

        List<Integer> list = new ArrayList();

        while (matcher.find()) {
            list.add(Integer.valueOf(matcher.group()));
        }

        Integer[] numbers = list.toArray(new Integer[list.size()]);

        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[minIndex] > numbers[i]) {
                minIndex = i;
            }

            if (numbers[maxIndex] < numbers[i]) {
                maxIndex = i;
            }
        }

        System.out.println("result: " + (minIndex + maxIndex));
    }


    @Test
    public void 두_정수_사이의_합() {
        /**
         * 두 정수 a, b가 주어졌을 때 a와 b 사이에 속한 모든 정수의 합을 리턴하는 함수, solution을 완성하세요.
         * 예를 들어 a = 3, b = 5인 경우, 3 + 4 + 5 = 12이므로 12를 리턴합니다.
         *
         * 제한 조건
         * - a와 b가 같은 경우는 둘 중 아무 수나 리턴하세요.
         * - a와 b는 -10,000,000 이상 10,000,000 이하인 정수입니다.
         * - a와 b의 대소관계는 정해져있지 않습니다.
         */

        int a = 3;
        int b = 5;

        System.out.println(두_정수_사이의_합1(a, b));

    }

    // 두_정수_사이의_합 private 함수 start
    private long 두_정수_사이의_합1(int a, int b) {
        long answer = 0;

        int startIndex = a < b ? a : b;
        int endIndex = a > b ? a: b;

        for (; startIndex <= endIndex; startIndex++) {
            answer += startIndex;
        }

        return answer;
    }
    // 두_정수_사이의_합 private 함수 end



    @Test
    public void 문자열_내_마음대로_정렬하기() {
        /**
         * 문제 설명
         * 문자열로 구성된 리스트 strings와, 정수 n이 주어졌을 때, 각 문자열의 인덱스 n번째 글자를 기준으로 오름차순 정렬하려 합니다.
         * 예를 들어 strings가 [sun, bed, car]이고 n이 1이면 각 단어의 인덱스 1의 문자 u, e, a로 strings를 정렬합니다.
         *
         * 제한 조건
         * - strings는 길이 1 이상, 50이하인 배열입니다.
         * - strings의 원소는 소문자 알파벳으로 이루어져 있습니다.
         * - strings의 원소는 길이 1 이상, 100이하인 문자열입니다.
         * - 모든 strings의 원소의 길이는 n보다 큽니다.
         * - 인덱스 1의 문자가 같은 문자열이 여럿 일 경우, 사전순으로 앞선 문자열이 앞쪽에 위치합니다.
         *
         * 입출력 예
         * strings	           n	return
         * [sun, bed, car]	   1	[car, bed, sun]
         * [abce, abcd, cdx]   2	[abcd, abce, cdx]
         */

        String[] strings = {"sun", "bed", "car"};
        int n = 1;
//        String[] strings = {"abce", "abcd", "cdx"};
//        int n = 1;
//        String[] strings = {"asdf", "fadg", "czas"};
//        int n = 3;
//        String[] strings = {"abzcd","cdzab","abzfg","abzaa","abzbb","bbzaa"};
//        int n = 2;

        System.out.println("result: " + 문자열_내_마음대로_정렬하기1(strings, n));

    }

    // 문자열_내_마음대로_정렬하기 private 함수 start TODO
    private String[] 문자열_내_마음대로_정렬하기1(String[] strings, int n) {
        String[] answer = new String[strings.length];
        // Arrays.sort(strings);
        int index = -1;

        for (int i = 0; i < strings.length; i++) {

            for (int j = 0; j < strings.length; j++) {
                if (!"".equals(strings[j]) ) {
                    if (index == -1) {
                        index = j;
                    }

                    if (j + 1 != strings.length && !"".equals(strings[j + 1])) {

                        if (strings[index].charAt(n) > strings[j + 1].charAt(n)) {
                            index = j + 1;
                        }

                    }

                }
            }

            answer[i] = strings[index];
            strings[index] = "";
            index = -1;
        }



        for (String s : answer) {
            System.out.println("test: " + s);
        }

        return answer;
    }
    // 문자열_내_마음대로_정렬하기 private 함수 end

    // 문자열_다루기_기본 start
    @Test
    public void 문자열_다루기_기본() {
        String s = "12343";
        System.out.println("result " + 문자열_다루기_기본_함수1(s));
    }

    private boolean 문자열_다루기_기본_함수1(String s) {
        if (s.length() == 4 || s.length() == 6 ) {
            boolean answer = true;

            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                answer = false;
                break;
            }

            return answer;
        } else {
            return false;
        }
    }
    // 문자열_다루기_기본 end

    // 문자열_다루기_기본 start
    @Test
    public void 문자앨_내_p와_y의_개수() {
        String s = "pPoooyY";
        System.out.println("result: " + 문자앨_내_p와_y의_개수_함수(s));
    }

    private boolean 문자앨_내_p와_y의_개수_함수(String s) {
        Pattern pattern = Pattern.compile("[py]");
        Matcher matcher = pattern.matcher(s.toLowerCase());

        int pIndex = 0;
        int yIndex = 0;
        while (matcher.find()) {
            if ("p".equals(matcher.group())) {
                pIndex++;
            } else {
                yIndex++;
            }
        }

        return pIndex == yIndex ? true : false;
    }
    // 문자열_다루기_기본 end

    // 시저암호 start
    @Test
    public void 시저암호() {
        String s = "y";
//        String s = "aAbB";
        int n = 25;

        System.out.println("result: " + 시저암호_함수(s, n));
    }

    private String 시저암호_함수(String s, int n) {
        String answer = "";
        String[] temp = s.split("");

        for (int i = 0; i < temp.length; i++) {
            char charAt = temp[i].charAt(0);
            if (' ' == charAt) {
                answer += String.valueOf(charAt);
                continue;
            }

            char c = (char) (charAt + n);
            if (c > 122) {
                c = (char) ("a".charAt(0) + (c - "z".charAt(0)) - 1);
            } else if (c > 90 && charAt < 97) {
                c = (char) ("A".charAt(0) + (c - "Z".charAt(0)) - 1);
            }

            answer += String.valueOf(c);

        }

        return answer;
    }
    // 시저암호 end

    // 프로그래머스

}
