package dashboard.model.dao;

import dashboard.model.dto.StaffDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class StaffDao {

    public StaffDao(){ connect(); }
    // ======= 데이터베이스 연동 =======
    // 1. 연동한 db서버의 정보
    private String url = "jdbc:mysql://localhost:3306/practice4DB";
    private String user = "root";   private String password = "1234";
    // 2. 연동 인터페이스 변수 선언
    private Connection conn;
    // 3. 연동 함수 선언
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( url , user , password );
            System.out.println("[성공] staff 연동 성공");
        }catch (Exception e){ System.out.println("[경고] 데이터베이스 연동 실패: 관리자에게 문의"); }
    }

    // 1. 사원 등록
    public boolean sPost( StaffDto staffDto ){
        try{
            String sql = "INSERT INTO staff( sname , srank , dcode ) VALUE(? , ? , ?)";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setString(1 , staffDto.getSname());
            ps.setString( 2, staffDto.getSname());
            ps.setInt( 3, staffDto.getDcode());
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){System.out.println(e);}
        return false;
    }

    // 2. 사원 전체 목록
    public List<StaffDto> sFindAll(){
        List<StaffDto> list = new ArrayList<>();
        try{
            String sql = "SELECT s.scode , s.sname , s.srank , s.dcode , d.dname FROM staff s " +
                    "LEFT JOIN department d ON s.dcode = d.dcode";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                StaffDto staffDto1 = new StaffDto(
                        rs.getInt("scode"), rs.getString("sname"),
                        rs.getString("srank"), rs.getInt("dcode"), rs.getString("dname")
                );
                list.add(staffDto1);
            }
        }catch (Exception e){System.out.println(e);}
        return list;
    }

    // 3. 사원 수정
    public boolean sUpdate( StaffDto staffDto ){
        try{
            String sql = "UPDATE staff SET sname = ? , srank = ? WHERE scode = ? ";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setString(1, staffDto.getSname());
            ps.setString(2, staffDto.getSrank());
            ps.setInt(3, staffDto.getScode());
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){System.out.println(e);}
        return false;
    }

    // 4. 사원 삭제
    public boolean sDelete( int scode ){
        try{
            String sql = "DELETE FROM staff WHERE scode = ?";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setInt(1, scode);
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){System.out.println(e);}
        return false;
    }



}
