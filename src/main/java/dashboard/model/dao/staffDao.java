package dashboard.model.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class staffDao {

    // ======= 데이터베이스 연동 =======
    // 1. 연동한 db서버의 정보
    private String url = "jdbc:mysql://localhost:3306/boardservice9";
    private String user = "root";   private String password = "1234";
    // 2. 연동 인터페이스 변수 선언
    private Connection conn; //선언 만! (아래 메소드 들에서 사용 많이 함.)
    // 3. 연동 함수 선언
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 라이브러리 객체 메모리 할당/불러오기
            conn = DriverManager.getConnection( url , user , password ); // mysql 구현체로 db 연동 후 연동 인터페이스에 반환.
            System.out.println("[성공] 데이터베이스 연동 성공");
        }catch (Exception e){ System.out.println("[경고] 데이터베이스 연동 실패: 관리자에게 문의"); }
    }

    // 1. 사원 등록

    // 2. 사원 전체 목록

    // 3. 사원 수정

    // 4. 사원 삭제



}
