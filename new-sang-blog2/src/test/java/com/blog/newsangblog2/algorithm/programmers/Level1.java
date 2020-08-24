package com.blog.newsangblog2.algorithm.programmers;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 프로그래머스 알고리즘 문제 풀어보자.
 */
public class Level1 {

    @Test
    public void 체육복() {
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
//        String[] strings = {"sun", "bed", "car"};
//        int n = 1;
//        String[] strings = {"abce", "abcd", "cdx"};
//        int n = 2;
//        String[] strings = {"asdf", "fadg", "czas"};
//        int n = 3;
        String[] strings = {"abzcd","cdzab","abzfg","abzaa","abzbb","bbzaa"};
        int n = 2;

        for (String s : 문자열_내_마음대로_정렬하기1(strings, n)) {
            System.out.println("test: " + s);
        }
    }

    // 문자열_내_마음대로_정렬하기 private 함수 start TODO
    private String[] 문자열_내_마음대로_정렬하기1(String[] strings, int n) {
        String[] answer = new String[strings.length];

        Comparator<String> comp = new Comparator<String>() {
            public int compare(String s1, String s2) {
                char str1 = s1.charAt(n);
                char str2 = s2.charAt(n);

                if(str1 > str2)
                    return 1;
                else if(str1 < str2)
                    return -1;
                else
                    return 0;
            }
        };

        Arrays.sort(strings);
        Arrays.sort(strings, comp);

        for(int i = 0; i < strings.length; i++)
            answer[i] = strings[i];

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

    // 이상한_문자_만들기 start
    @Test
    public void 이상한_문자_만들기() {
        String s = "try hello world";
        System.out.println("result: " + 이상한_문자_만들기_함수(s));
    }

    private String 이상한_문자_만들기_함수(String s) {
        String[] tempArray = s.split(" ", -1);
        String answer = "";

        for (int i = 0; i < tempArray.length; i++) {
            String tempStr = "";
            for (int j = 0; j < tempArray[i].length(); j++) {
                char charAt = tempArray[i].charAt(j);
                tempStr += j % 2 == 0 ? String.valueOf(charAt).toUpperCase() : String.valueOf(charAt).toLowerCase();
            }

            tempArray[i] = tempStr;
        }

        answer = String.join(" ", tempArray);

        return answer;
    }
    // 이상한_문자_만들기 end

    // 자릿수_더하기 start
    @Test
    public void 자릿수_더하기() {
        int n = 123;
        System.out.println("result: " + 자릿수_더하기_함수(n));
    }

    private int 자릿수_더하기_함수(int n) {
        int answer = 0;
        String strN = String.valueOf(n);

        for (int i = 0; i < strN.length(); i++) {
            answer += Integer.valueOf(String.valueOf(strN.charAt(i)));
        }

        return answer;
    }
    // 자릿수_더하기 end

    // 자연수_뒤집어_배열로_만들기 start
    @Test
    public void 자연수_뒤집어_배열로_만들기() {
        long n = 12345;

        for (int i : 자연수_뒤집어_배열로_만들기_함수(n)) {
            System.out.println("result:" + i);
        }
    }

    private int[] 자연수_뒤집어_배열로_만들기_함수(long n) {
        String[] tempN = String.valueOf(n).split("");
        int[] answer = Arrays.stream(tempN).mapToInt(Integer::parseInt).toArray();
        int nLength = tempN.length;
        int middleLength = nLength % 2 == 0 ? nLength / 2 : nLength / 2 + 1;

        for (int i = 0; i < middleLength; i++) {
            answer[i] = Integer.valueOf(tempN[(nLength - 1) - i]);
            answer[(nLength - 1) - i] = Integer.valueOf(tempN[i]);
        }

        return answer;
    }
    // 자연수_뒤집어_배열로_만들기 end

    // 정수_제곱근_판별 start
    @Test
    public void 정수_제곱근_판별() {
        long n = 121;
        System.out.println("result: " + 정수_제곱근_판별_함수(n));
    }

    private long 정수_제곱근_판별_함수(long n) {
        long answer = -1;

        long i = 1;
        while(n > i || n == 1) {
            if ((n / i == i && n % i == 0) || n == 1) {
                System.out.println("i: " + i);
                break;
            }

            i++;
        }

        if (i != n || n == 1) {
            i += 1;
            answer  = i * i;
        }

        return answer;
    }
    // 정수_제곱근_판별 end

    // 제일_작은_수_제거하기 start
    @Test
    public void 제일_작은_수_제거하기() {
        int[] arr = {10,8,9};

        for (int i : 제일_작은_수_제거하기_함수(arr)) {
            System.out.println("result: " + i);;
        }
    }

    private int[] 제일_작은_수_제거하기_함수(int[] arr) {
        int checkLength= arr.length - 1;
        int[] answer;
        if (checkLength > 0) {
            answer = new int[checkLength];

            int minIndex = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[minIndex] > arr[i]) {
                    minIndex = i;
                }
            }

            int j = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i == minIndex) {
                    continue;
                }

                answer[j++] = arr[i];
            }

        } else {
            answer = new int[]{-1};
        }

        return answer;
    }
    // 제일_작은_수_제거하기 end

    // 핸드폰_번호_가리기 start
    @Test
    public void 핸드폰_번호_가리기() {
        String phoneNumber = "01033334444";
        System.out.println("result: " + 핸드폰_번호_가리기_함수(phoneNumber));
    }

    private String 핸드폰_번호_가리기_함수(String phone_number) {
        int endNum = phone_number.length() - 4;
        String target = phone_number.substring(endNum);
        String answer = "";

        for (int i = 0; i < endNum; i++) {
            answer += "*";
        }

        return answer + target;
    }
    // 핸드폰_번호_가리기 end

    // x만큼_간격이_있는_n개의_숫자 start
    @Test
    public void x만큼_간격이_있는_n개의_숫자() {
        int x = -4; int n = 3;
        for (long o : x만큼_간격이_있는_n개의_숫자_함수(x, n)) {
            System.out.println("result: " + o);
        }
    }

    private long[] x만큼_간격이_있는_n개의_숫자_함수(int x, int n) {
        long[] answer = new long[n];
        for (int i = 0; i < n; i++) {
            answer[i] = x * (i + 1);
        }
        return answer;
    }
    // x만큼_간격이_있는_n개의_숫자 end

    // 직사각형_별찍기 start
    @Test
    public void 직사각형_별찍기() {
        Scanner sc = new Scanner(System.in);
        int a = 5;
        int b = 3;

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println(a + b);
    }
    // 직사각형_별찍기 end

    // 2019 카카오 개발자 겨울 인턴십 - 크레미_인형뽑기_게임 start
    @Test
    public void 크레미_인형뽑기_게임() {
        //[[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]];
        //[1,5,3,5,1,2,1,4];
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };
        // 4, 3, 1, 1, 3, 2, 0, 4
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};


        System.out.println("result: " + 크레미_인형뽑기_게임_함수(board, moves));
    }

    private int 크레미_인형뽑기_게임_함수(int[][] board, int[] moves) {
        // 터지는 인형 개수
        int answer = 0;
        // 바구니
        List<Integer> bucket = new ArrayList<>();
        // 크레인 위치
        int crane = 0;

        for (int i = 0; i < moves.length; i++) {
            crane = moves[i] - 1;

            for (int j = 0; j < board.length; j++) {
                if (board[j][crane] == 0) {
                    continue;
                }

                bucket.add(board[j][crane]);
                board[j][crane] = 0;
                break;
            }

            while (bucket.size() > 1) {
                int size = bucket.size();
                int checkIndex1 = size - 1;
                int checkIndex2 = size - 2;

                if (bucket.get(checkIndex1) != bucket.get(checkIndex2)) {
                    break;
                }

                answer += 2;
                bucket.remove(checkIndex1);
                bucket.remove(checkIndex2);
            }

        }

        return answer;
    }
    // 2019 카카오 개발자 겨울 인턴십 - 크레미_인형뽑기_게임 end

    // 예산 start
    @Test
    public void 예산() {
        int[] d  = {1,3,2,5,4};
        int budget = 9;
        System.out.println("result: " + 예산_함수(d, budget));
    }

    private int 예산_함수(int[] d, int budget) {
        int result = 0;
        Arrays.sort(d);

        int tempBudget = budget;

        for (int i = 0; i < d.length; i++) {
            if (tempBudget < d[i]) {
                break;
            }

            tempBudget -= d[i];
            result++;
        }

        return result;
    }

    // 예산 end

    // 2019 KAKAO BLIND RECRUITMENT - 실패율 start
    @Test
    public void 실패율() {
        int n = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};

        for (int i : 실패율_함수(n, stages)) {
            System.out.println("result: " + i);
        }
    }

    private int[] 실패율_함수(int N, int[] stages) {
        Map<Integer, Double> tempResult = new HashMap<>();

        double tempVal = 0.0;
        for (int i = 1; i <= N; i++) {
            double fPeople = 0.0;
            double sPeople = 0.0;

            for (int j = 0; j < stages.length; j++) {
                if (i <= stages[j]) {
                    sPeople++;
                    if (i == stages[j]) {
                        fPeople++;
                    }
                }
            }
            tempVal = sPeople > 0.0 ? fPeople / sPeople : 0.0;
            tempResult.put(i, tempVal);
        }

        int[] result = new int[tempResult.size()];

        List<Integer> collect = tempResult.keySet().stream()
                .sorted((o1, o2) -> {
                    if (tempResult.get(o1) < tempResult.get(o2)) {
                        return 1;
                    } else if (tempResult.get(o1) > tempResult.get(o2)) {
                        return -1;
                    }
                    return 0;
                })
                .collect(Collectors.toList());

        int j = 0;
        for (int i : collect) {
            result[j++] = i;
        }

        return result;
    }
    // 2019 KAKAO BLIND RECRUITMENT - 실패율 end


    // 2019 2018 KAKAO BLIND RECRUITMENT - [1차] 비밀지도 start
    @Test
    public void 비밀지도() {
        int n = 5;
        int[] arr1 = {9,20,28,18,11};
        int[] arr2 = {30,1,21,17,28};
        for (String s : 비밀지도_함수(n, arr1, arr2)) {
            System.out.println("result: " + s);
        }
    }

    private String[] 비밀지도_함수(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        String[] temp1 = decimalToBinaryNum(arr1, n);
        String[] temp2 = decimalToBinaryNum(arr2, n);

        for (int i = 0 ; i < n; i++) {
            String value = "";
            for (int j = 0; j < n; j++) {
                boolean check = false;
                if (temp1[i].charAt(j) == '1') {
                    check = true;
                }

                if (temp2[i].charAt(j) == '1' && !check) {
                    check = true;
                }

                if (check) {
                    value += "#";
                } else {
                    value += " ";
                }
            }

            answer[i] = value;
        }

        return answer;
    }
    
    private String[] decimalToBinaryNum(int[] arr, int n) {
        String[] returnStr = new String[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            StringBuilder binaryNum = new StringBuilder();
            int val = arr[i];

            int j = n;
            while (j-- > 0) {
                if (val % 2 == 0) {
                    binaryNum.append(0);
                } else {
                    binaryNum.append(val % 2);
                }
                val = val / 2;
            }

            returnStr[i] = binaryNum.reverse().toString();
        }

        return returnStr;
    }

    // 2019 2018 KAKAO BLIND RECRUITMENT - [1차] 비밀지도 end



    // 2018 KAKAO BLIND RECRUITMENT - [1차] 다트 게임 start
    @Test
    public void 다트_게임() {
//        String dartResult = "1S2D*3T"; // 37
//        String dartResult = "1D2S#10S"; // 9
//        String dartResult = "1D2S0T"; // 3
//        String dartResult = "1S*2T*3S"; // 23
//        String dartResult = "1D#2S*3S"; // 5
//        String dartResult = "1T2D3D#"; // -4
        String dartResult = "1D2S3T*"; // 59 x

        System.out.println("result: " + 다트_게임_함수(dartResult));
    }

    private int 다트_게임_함수(String dartResult) {
        int result = 0;
        Pattern pattern = Pattern.compile("[0-9]+[S,D,T]+[*,#]?");
        Matcher matcher = pattern.matcher(dartResult);

        int preVal = 0;
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group();
            String[] divideData = group.replaceAll("(\\d+|.)", "$1\n").split("\n");

            int num = Integer.parseInt(divideData[0]);
            int squareOfNum = 0;
            String option = "";

            if ("S".equals(divideData[1])) {
                squareOfNum = 1;
            } else if ("D".equals(divideData[1])) {
                squareOfNum = 2;
            } else if ("T".equals(divideData[1])) {
                squareOfNum = 3;
            }

            if (divideData.length == 3) {
                option = divideData[2];

                if ("*".equals(option)) {
                    if (i != 0) {
                        //result = result * 2;
                        result -= preVal;
                        result += preVal * 2;
                    }

                    option = 2 + "";
                } else if ("#".equals(option)) {
                    option = -1 + "";
                }


                result += (Math.pow(num, squareOfNum)) * Integer.parseInt(option);
                preVal = (int) (Math.pow(num, squareOfNum)) * Integer.parseInt(option);

            } else {
                result += (Math.pow(num, squareOfNum));
                preVal = (int) (Math.pow(num, squareOfNum));
            }

            i++;
        }

        return result;
    }
    // 2018 KAKAO BLIND RECRUITMENT - [1차] 다트 게임 end
}
