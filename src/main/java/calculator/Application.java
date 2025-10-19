package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        // 변수 선언
        String rawInputString; // 입력 문자열
        String sep; // 구분자
        String inputString; // 구분자 지정부를 제외한 입력 문자열

        // 1. 문자열 입력 기능
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        rawInputString = Console.readLine();
        System.out.println("입력한 문자열 : " + rawInputString); // 테스트 출력

        // 2. 입력받은 문자열로부터 구분자를 식별해 문자열 배열을 생성하는 기능
        // 커스텀 구분자가 지정된 경우, 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자와 기본 구분자(쉼표, 콜론)를 구분자로 사용.
        if (rawInputString.matches("^//.*\\\\n.*$")) {
            int sepDeclarationStartIndex = rawInputString.indexOf("//");
            int sepDeclarationEndIndex = rawInputString.indexOf("\\n");
            sep = rawInputString.substring(sepDeclarationStartIndex + 2, sepDeclarationEndIndex) + "|,|:";
            inputString = rawInputString.substring(sepDeclarationEndIndex+2);
        }
        // 커스텀 구분자가 지정되지 않은 경우, 쉼표(,) 또는 콜론(:)을 구분자로 사용.
        else {
            sep = ",|:";
            inputString = rawInputString;
        }
        System.out.println("구분자 = " + sep); // 테스트 출력

        // 구분자를 기준으로 문자열을 분리해 문자열 배열 생성.
        String[] strArr = inputString.split(sep);
        // 테스트 출력
        for (String str : strArr) System.out.println(str);
    }
}
