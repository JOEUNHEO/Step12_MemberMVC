package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
	private static MemberDao dao;
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	public boolean insert(MemberDto dto) {
		boolean isSuccess = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new DbcpBean().getConn();
			String sql = "INSERT INTO member(num, name, addr) VALUES(member_seq.NEXTVAL,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return isSuccess;
	}
	
	public boolean delete(int num) {
		boolean isSuccess = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new DbcpBean().getConn();
			String sql = "DELETE FROM member WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return isSuccess;
	}
	
	public boolean update(MemberDto dto) {
		boolean isSuccess = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new DbcpBean().getConn();
			String sql = "UPDATE member SET name=?, addr=? WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return isSuccess;
	}
	
	public List<MemberDto> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<>();

		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DbcpBean().getConn();

			String sql = "SELECT num, name, addr FROM member ORDER BY num ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String addr = rs.getString("addr");
				
				MemberDto dto = new MemberDto();
				dto.setNum(num);
				dto.setName(name);
				dto.setAddr(addr);
				
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				// Connection 객체 반납하기
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		//글 목록을 리턴해준다.
		return list;
	}
	
	public MemberDto getData(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto dto = null;

		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DbcpBean().getConn();

			String sql = "SELECT name, addr FROM member WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String addr = rs.getString("addr");
				
				dto = new MemberDto();
				dto.setNum(num);
				dto.setName(name);
				dto.setAddr(addr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				// Connection 객체 반납하기
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		
		return dto;
	}
}
