package pairmatching;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private final String selectFunctionMsg = "기능을 선택하세요.\r\n1. 페어 매칭\r\n2. 페어 조회\r\n3. 페어 초기화\r\nQ. 종료";
    private final String ectMsg = "#############################################";
    private final String[] course = new String[]{"백엔드", "프론트엔드"};
    private final String[] level = new String[]{"레벨1", "레벨2", "레벨3", "레벨4", "레벨5"};
    private final String[] level1Mission = new String[]{"자동차경주", "로또", "숫자야구게임"};
    private final String[] level2Mission = new String[]{"장바구니", "결제", "지하철노선도"};
    private final String[] level4Mission = new String[]{"성능개선", "배포"};

    private final String resultMsg = "페어매칭 결과입니다.";

    //초기화면
    public void showFunctionMsg() throws IOException {
        System.out.println(selectFunctionMsg);

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        if(str.equals("1")){
            pairMatchingSelect();
        }

        if(str.equals("2")){
            return;
        }

        if(str.equals("3")){
            return;
        }

        if(str.equals("Q")){
           return;
        }

        scanner.close();
    }

    //기능 입력받기
    public void pairMatchingSelect() throws IOException {
        System.out.println(ectMsg);
        System.out.println("과정: " + String.join(" | ", course));
        System.out.println("미션:");
        System.out.println("\t - " + level[0] + ": " + String.join(" | ", level1Mission));
        System.out.println("\t - " + level[1] + ": " + String.join(" | ", level2Mission));
        System.out.println("\t - " + level[2] + ": ");
        System.out.println("\t - " + level[3] + ": " + String.join(" | ", level4Mission));
        System.out.println("\t - " + level[4] + ": ");
        System.out.println(ectMsg);
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        List<String> result = pairMatching(str);

        //결과 출력...
        System.out.println(resultMsg);
        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
        }

        System.out.println();
        this.showFunctionMsg();
    }

    //페어매칭
    public List<String> pairMatching(String str) throws IOException {
        List<String> result = new ArrayList<>();

        String[] inputValue = str.split(", ");
        List<String> crewList = new ArrayList<>();

        if(inputValue[0].equals("백엔드")){
            crewList = crewListLoad("backend-crew.md");
        }

        if(inputValue[0].equals("프론트엔드")){
            crewList = crewListLoad("frontend-crew.md");
        }

        Collections.shuffle(crewList);

        for(int i = 0; i < crewList.size(); i += 2){
            String member = crewList.get(i) + " : " + crewList.get(i + 1);

            if(i == crewList.size() - 3 && crewList.size() % 2 == 1){
                member += " : " + crewList.get(i + 2);
                i += 2;
            }

            result.add(member);
        }

        return result;
    }

    //크루 파일 읽기
    public List<String> crewListLoad(String fileName) throws IOException {
        List<String> result = new ArrayList<>();

        URL resource = getClass().getClassLoader().getResource(fileName);
        if(resource != null) {
            Path path = new File(resource.getPath()).toPath();
            result = Files.readAllLines(path);
            return result;
        }

        return result;
    }
}

