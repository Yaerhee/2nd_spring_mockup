package hello.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 private, static으로 "객체를 1개만" 선언(진짜 싱글톤!)
    private static final SingletonService instance = new SingletonService();
    //실행할 경우 new를 통해 내부 실행을 하고, 객체를 생성해서 instance에 참조를 넣어 둠

    //public으로 열 때 객체 인스턴스가 필요할 경우, 해당 static method를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    //추가 생성을 막기 위해 private 선언
    //Test 파일 psvm에 new SingletonService 객체 생성을 시도하면, private라 접근 권한이 없다고 나옴
    private SingletonService() {
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
