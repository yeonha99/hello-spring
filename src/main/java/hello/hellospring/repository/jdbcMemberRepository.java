package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public jdbcMemberRepository(DataSource dataSource){
        this.dataSource=dataSource;
    }
    private Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn,PreparedStatement pst,ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(pst!=null){
                pst.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null){
                close(conn);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException{
        DataSourceUtils.releaseConnection(conn,dataSource);
    }
    @Override
    public Member save(Member member) {
        String sql="insert into member(name) values(?)";
        Connection conn =null;
        PreparedStatement pst = null;
        ResultSet rs=null;

        try{
            conn=getConnection();
            pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, member.getName());
            pst.executeUpdate();
            rs=pst.getGeneratedKeys();
            if(rs.next()){
                member.setId(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패");
            }
            return member;
        }catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn,pst,rs);
        }

    }


    @Override
    public Optional<Member> findByid(long id) {
        String sql="select * from member where id=?";
        Connection conn =null;
        PreparedStatement pst = null;
        ResultSet rs=null;

        try{
            conn=getConnection();
            pst=conn.prepareStatement(sql);
            pst.setLong(1, id);
            rs=pst.executeQuery();
            if(rs.next()){
                Member member=new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }
        }catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn,pst,rs);
        }

    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql="select * from member where name=?";
        Connection conn =null;
        PreparedStatement pst = null;
        ResultSet rs=null;

        try{
            conn=getConnection();
            pst=conn.prepareStatement(sql);
            pst.setString(1,name);
            rs=pst.executeQuery();
            if(rs.next()){
                Member member=new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }
        }catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn,pst,rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql="select * from member";
        Connection conn =null;
        PreparedStatement pst = null;
        ResultSet rs=null;

        try{
            conn=getConnection();
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            List<Member> members=new ArrayList<>();
            while(rs.next()){
                Member member= new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        }catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn,pst,rs);
        }
    }
}
