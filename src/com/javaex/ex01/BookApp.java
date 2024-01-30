package com.javaex.ex01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		int cnt = authorDao.authorInsert("이효리", "제주도민");
		System.out.println(cnt +"success");
		List<AuthorVo> authorList = authorDao.authorList();
		for(AuthorVo authorVo : authorList) {
			int id = authorVo.getAuthorId();
			String name = authorVo.getAuthorName();
			String desc = authorVo.getAuthorDesc();
			System.out.println(id + ",  " + name + ",  " + desc);
			
		}
		
		/*List<AuthorVo> authorList = authorDao.authorList();
		for(int i=0; i<authorList.size(); i++) {
			
			int id =authorList.get(i).getAuthorId();
			String name = authorList.get(i).getAuthorName();
			String desc = authorList.get(i).getAuthorDesc();
			System.out.println(i + ", " + name + ", "+ desc);
			
			System.out.println(i);
		}
		*/
		System.out.println(authorList.size() + "명의 작가가 등록되어 있습니다.");
		System.out.println(authorList.toString());
		//authorDao.authorDelete();
				
		 /*AuthorDao authorDao = new AuthorDao();
		 
		 authorDao.authorInsert("이문열", "경북영양");
		 authorDao.authorInsert("박경리", "토지작가");
		 
		 authorDao.authorDelete(2);
		 authorDao.authorUpdate(1, "기안84", "웹툰작가");
		 List<AuthorVo> authorList = authorList();
		 
		 for() */
		
	}

}
