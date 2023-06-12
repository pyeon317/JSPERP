package com.erp.announcement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class announcementDAO {

	//1. 나자신의 객체를 스태틱으로 선언
	private static announcementDAO instance = new announcementDAO();
	//2. 직접 생성하지 못하도록 생성자 제한
	private announcementDAO() {
		//생성자에서 드라이버클래스 호출
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			
		}
				
	}
	
	//3. getter를 통해서 객체를 반환
	public static announcementDAO getInstance() {
		return instance;
	}
	

	//데이터베이스 연결 주소
	//+오라클 커넥터
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "ERP";
	private String upw = "1234";	
	
	//글 등록
	public void regist(String writer,String announcement_title, String announcement_content) {
				
		String sql = "INSERT INTO ANNOUNCEMENT(announcement_number, WRITER, TITLE, CONTENT) VALUES(ANN_SEQ.NEXTVAL, ?, ?, ?)";
				
				
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
					
			conn = DriverManager.getConnection(url, uid, upw);
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, announcement_title);
			pstmt.setString(3, announcement_content);
					
			pstmt.executeUpdate(); //끝
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	//목록을 조회
	public List <announcementVO> getList() {

		List <announcementVO> list = new ArrayList<>();
				
		String sql = "SELECT * FROM ANNOUNCEMENT ORDER BY BNO DESC";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
					
			conn = DriverManager.getConnection(url, uid, upw);
					
			pstmt = conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery(); //끝
					
			/*
			 * 조회된 데이터를 순서대로 vo에 담고 리스트에 추가하는 프로그램 코드
			 */
			while(rs.next()) {
				//1행에 대한 처리
				int announcement_number = rs.getInt("announcement_number");
				String writer = rs.getString("writer");
				String title = rs.getString("announcement_title");
				int hit = rs.getInt("hit");
				String content = rs.getString("announcement_content");
				Timestamp regdate = rs.getTimestamp("regdate");
						
				announcementVO vo = new announcementVO(announcement_number, writer, title, hit, content, regdate);
					
				list.add(vo);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
						
			}
		}
	return list;
	}	
	
	//글내용을 조회
	public announcementVO getContent(String announcement_number) {
		announcementVO vo = null;
		String sql = "select * from ANNOUNCEMENT where announcement_number = ?";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
					
			conn = DriverManager.getConnection(url, uid, upw);
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, announcement_number);
					
			rs = pstmt.executeQuery(); //끝
					
			if(rs.next()) {
				int announcement_number2 = rs.getInt("announcement_number");
				String writer = rs.getString("writer");
				String title = rs.getString("announcement_title");
				int hit = rs.getInt("hit");
				String content = rs.getString("announcement_content");
				Timestamp regdate = rs.getTimestamp("regdate");
						
				vo = new announcementVO(announcement_number2, writer, title, hit, content, regdate);
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
						
			}
		}
				
		return vo;
	}
			
			
	public void update(String announcement_number,
					   String announcement_title,
					   String announcement_content) {
				
		String sql = "update ANNOUNCEMENT set announcement_title = ?, announcement_content = ? where announcement_number = ?";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = DriverManager.getConnection(url,uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, announcement_title);
			pstmt.setString(2, announcement_content);
			pstmt.setString(3, announcement_number);
					
			pstmt.executeUpdate(); //끝
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
						
			}
		}
				
	}

	public void delete(String announcement_number) {
		String sql = "delete from ANNOUNCEMENT where announcement_number = ?";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = DriverManager.getConnection(url,uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, announcement_number);
					
			pstmt.executeUpdate(); //끝
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
						
			}
		}
	}
}
