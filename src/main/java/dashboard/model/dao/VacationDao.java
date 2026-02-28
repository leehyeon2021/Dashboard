package dashboard.model.dao;

import dashboard.model.dto.VacationDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class VacationDao {


    public VacationDao(){ connect(); }
    // ======= 데이터베이스 연동 =======
    // 1. 연동한 db서버의 정보
    private String url = "jdbc:mysql://localhost:3306/practice4DB";
    private String user = "root";   private String password = "1234";
    // 2. 연동 인터페이스 변수 선언
    private Connection conn; //선언 만! (아래 메소드 들에서 사용 많이 함.)
    // 3. 연동 함수 선언
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 라이브러리 객체 메모리 할당/불러오기
            conn = DriverManager.getConnection( url , user , password ); // mysql 구현체로 db 연동 후 연동 인터페이스에 반환.
            System.out.println("[성공] vacation 연동 성공");
        }catch (Exception e){ System.out.println("[경고] 데이터베이스 연동 실패: 관리자에게 문의"); }
    }

    // 1. 휴가신청
    public boolean vPost(VacationDto vacationDto){
        try{
            String sql = "insert into vacation(scode, vstart, vend, vreason) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vacationDto.getScode());
            ps.setString(2, vacationDto.getVstart());
            ps.setString(3, vacationDto.getVend());
            ps.setString(4, vacationDto.getVreason());
            int count = ps.executeUpdate();
            if(count == 1) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 2. 휴가 전체 목록
    public List<VacationDto> vFindAll() {
        List<VacationDto> list = new ArrayList<>();
        try {
            String sql = "select * from vacation";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                VacationDto vacationDto = new VacationDto(rs.getInt("vcode"),rs.getString("vstart"),rs.getString("vend"),rs.getString("vreason"),rs.getInt("scode"));
                list.add(vacationDto);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // 3. 휴가 삭제
    public boolean vDelete(int vcode){
        try{
            String sql = "delete from vacation where vcode = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vcode);
            int count = ps.executeUpdate();
            if (count ==1 ) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}

