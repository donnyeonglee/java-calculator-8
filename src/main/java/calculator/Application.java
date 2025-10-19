package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        // 변수 선언
        String rawInputString; // 입력 문자열
        String sep; // 구분자
        String inputString; // 구분자 지정부를 제외한 입력 문자열
        BigDecimal num; // 입력받은 문자열에서 추출한 각 숫자
        BigDecimal sum = new BigDecimal("0"); // 입력받은 각 숫자의 합. 0으로 초기화

        // 1. 문자열 입력 기능
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        rawInputString = Console.readLine();
        //System.out.println("입력한 문자열 : " + rawInputString); // 테스트 출력

        // 2. 입력받은 문자열로부터 구분자를 식별해 문자열 배열을 생성하는 기능
        // 커스텀 구분자가 지정된 경우, 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자와 기본 구분자(쉼표, 콜론)를 구분자로 사용.
        if (rawInputString.matches("^//.*\\\\n.*$")) {
            int sepDeclStartIndex = rawInputString.indexOf("//");
            int sepDeclEndIndex = rawInputString.indexOf("\\n");
            sep = rawInputString.substring(sepDeclStartIndex + 2, sepDeclEndIndex) + "|,|:";
            inputString = rawInputString.substring(sepDeclEndIndex+2);
        }
        // 커스텀 구분자가 지정되지 않은 경우, 쉼표(,) 또는 콜론(:)을 구분자로 사용.
        else {
            sep = ",|:";
            inputString = rawInputString;
        }
        //System.out.println("구분자 = " + sep); // 테스트 출력

        // 구분자를 기준으로 문자열을 분리해 문자열 배열 생성.
        String[] strArr = inputString.split(sep);
        // 테스트 출력
        //for (String str : strArr) System.out.println("입력받은 숫자 : " + str);

        // 3.문자열 배열의 각 배열 요소를 형변환 후 덧셈 연산하는 기능
        for (String str : strArr) {
            if (!str.isBlank()) {
                try {
                    if (str.startsWith("-") || str.equals("0")) { // 배열 요소가 양수가 아닌 경우, 예외처리를 위해 숫자가 아닌 값을 str에 대입
                        str = "nonPositiveNumber";
                    }
                    num = new BigDecimal(str); // 배열 요소가 숫자가 아닌 경우 예외 발생
                    sum = sum.add(num); // 배열 요소가 정상적인 경우 sum에 더함
                    //System.out.println("입력받은 숫자 : " + num + ", 현재까지 합 : " + sum); //테스트 출력
                } catch (Exception e) {
                    throw new IllegalArgumentException("잘못된 입력입니다." + e.getMessage());
                }
            }
            else { // 배열의 요소가 비어 있는 경우 덧셈에서 제외
                continue;
            }
        }

        // 4.덧셈 결과 출력 기능
        System.out.println("결과 : " + sum.stripTrailingZeros().toPlainString());
    }
}