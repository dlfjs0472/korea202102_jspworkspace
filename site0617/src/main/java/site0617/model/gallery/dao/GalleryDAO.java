package site0617.model.gallery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site0617.model.domain.Gallery;
import site0617.model.pool.PoolManager;

//이 클래스는 오직 gallery에 테이블에 대한 CRUD만을 담당
public class GalleryDAO {
	PoolManager pool=PoolManager.getInstance();
	
	//등록
	public int insert(Gallery gallery) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=pool.getConnection();
		String sql="insert into gallery(gallery_id, title, writer, content, filename) values(seq_gallery.nextval,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, gallery.getTitle());
			pstmt.setString(2, gallery.getWriter());
			pstmt.setString(3, gallery.getContent());
			pstmt.setString(4, gallery.getFilename());
			result=pstmt.executeUpdate();//쿼리수행
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
	//목록
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		ArrayList<Gallery> list = new ArrayList<Gallery>();
		
		con=pool.getConnection();
		String sql = "select * from gallery";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			//rs가 곧 소멸되므로, 최대한 비슷한 형태의 데이터로 옮겨놓자
			while(rs.next()) { //VO에 결과 담기
				Gallery gallery = new Gallery();
				gallery.setGallery_id(rs.getInt("gallery_id"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setFilename(rs.getString("filename"));
				gallery.setHit(rs.getInt("hit"));
				
				list.add(gallery); //VO를 리스트에 담기
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
	
	//레코드 한건 가져오기
	public Gallery select(int gallery_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		Gallery gallery=null; //레코드가 존재할때만 new 할꺼임
		
		con=pool.getConnection();
		String sql="select * from gallery where gallery_id=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, gallery_id);
			rs=pstmt.executeQuery(); //한건의 레코드가 들어있다!!
			
			if(rs.next()) { //레코드가 있다면..VO 하나에 레코드 1건 옮기기
				gallery = new Gallery();
				gallery.setGallery_id(rs.getInt("gallery_id"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setFilename(rs.getString("filename"));
				gallery.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return gallery;
	}
	
	//레코드 한건 삭제
	public int delete(int gallery_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //반환할 용도로
		
		con=pool.getConnection();
		String sql ="delete from gallery where gallery_id";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, gallery_id); //바인드 변수값 지정
			result=pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
}


















