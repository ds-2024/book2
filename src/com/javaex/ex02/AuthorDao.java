package com.javaex.ex02;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";
	//생성자
	//기본생성자 사용
	
	//메소드-gs
	
	//메소드-일반
	private void getConnection() {
		
		
		try {	
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
					
			// 2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url, id, pw);
		
		} catch (ClassNotFoundException e) {
	    System.out.println("error: 드라이버 로딩 실패 - " + e);
	    
		} catch (SQLException e) {
	    System.out.println("error:" + e);
		
		}

	}//getConnection()
	
	private void close() {
		// 5. 자원정리
				try {
					if (rs != null) {
				        rs.close();
				    }
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}
	
		
	}
	
	//작가등록
	
		
		public int authorInsert(AuthorVo authorVo) {
		
			int count = -1;
			//1.book_db 데이터베이스에 접속
			//book/book
			//author 테이블에
			//작가를 insert
			//메모리에 짤 수 없으니 틀짜줘야지
			
			this.getConnection();


	try {

	 // 3. SQL문 준비 / 바인딩 / 실행
	 // -SQL문 준비
	 String query = "";
	 query += " insert into author ";
	 query += " values(null, ?, ? )";
	 
	 //바인딩
	 pstmt = conn.prepareStatement(query);
	 pstmt.setString(1, authorVo.getAuthorName() );
	 pstmt.setString(2, authorVo.getAuthorDesc());
	 
	 //-실행
	 count = pstmt.executeUpdate();
	 // 4.결과처리
	 System.out.println(count + "건 등록 되었습니다.");
	 
	
	} catch (SQLException e) {
			System.out.println("error:" + e);
	} 

		this.close();
		
		return count;
		
	}//authorInsert()
	
		//작가리스트
		public List<AuthorVo> authorList() {
		
			this.getConnection();
		
		//리스트만들고
		//db에서 데이터 가져오고
		//리스트에 추가하기	
		//리스트 주소 전달하기
		
		//리스트 준비
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		//DB에서 가져오기
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    //SQL문 준비
			String query = "";
			query += "select author_id,";
			query += "		 author_name,";
			query += "		 author_desc,";
			query += "	from author";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(no, name, desc);
				
				authorList.add(authorVo);
			}

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		
		return authorList;
		
	}//authorList()
	
		// 작가수정
		// 예제
		
		// 작가삭제
		public int authorDelete(int no) {
			
			int count = -1;
			
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// 2. Connection 얻어오기
				String url = "jdbc:mysql://localhost:3306/book_db";
				conn = DriverManager.getConnection(url, "book", "book");
				
				// 3. SQL문 준비 / 바인딩 / 실행
				// -SQL문 준비
				String query = "";
				query += " delete from author ";
				query += " where author_id = ? ";
				
				// -바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				
				// -실행
				count = pstmt.executeUpdate();
				
				// 4.결과처리
				System.out.println(count + "건이 삭제되었습니다.");
				

			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {

				// 5. 자원정리
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}

			}

			return count;
		}//authorDelete()
		
		//작가수정
		public int authorUpdate(AuthorVo authorVo) {
			int count = -1;
			
			return count;
			
			
		}

		public void authorInsert(String string, String string2) {
			// TODO Auto-generated method stub
			
		}

		
}
	
	
