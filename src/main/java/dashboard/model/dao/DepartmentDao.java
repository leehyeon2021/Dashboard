package dashboard.model.dao;

import dashboard.model.dto.DepartmentDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentDao {
    public DepartmentDao(){connect();}
    private String url = "jdbc:mysql://localhost:3306/practice4DB";
    private String user = "root"; private String pw = "0950";
    // 연동 인터페이스 변수 선언
    private Connection conn;
    // 연동 함수 정의, dao에 생성자에서 함수실행{dao 실글톤이 생성되면서 db연동 실행}
    private void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 메모리할당
            conn = DriverManager.getConnection(url,user,pw); // mysql 구현체로 db연동후 연동 인터페이스에 반환
            System.out.println("데이터베이스 연동 성공");
        } catch (Exception e) {
            System.out.println("연동실패"+e);
        }
    }

    public boolean dPost(String dname){
        try{
            String sql = "insert into department(dname) values(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            int count = ps.executeUpdate();
            if(count == 1) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public List<DepartmentDto> dFindAll() {
        List<DepartmentDto> list = new ArrayList<>();
        try {
            String sql = "select * from department";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                DepartmentDto departmentDto = new DepartmentDto(rs.getInt("dcode"),rs.getString("dname"));
                list.add(departmentDto);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean dUpdate(DepartmentDto departmentDto) {
        try{
            String sql = "update department set dname = ? where dcode = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, departmentDto.getDname());
            ps.setInt(2, departmentDto.getDcode());
            int count = ps.executeUpdate();
            if (count == 1) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean dDelete(int dcode) {
        try{
            String sql = "delete from department where dcode = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dcode);
            int count = ps.executeUpdate();
            if (count ==1 ) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
