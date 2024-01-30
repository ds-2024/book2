package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		authorDao.authorInsert("서장훈", "농구선수");
		authorDao.authorInsert("안정환", "축구선수");
		
		AuthorVo authorVo = new AuthorVo("ds", "학생");
		authorDao.authorInsert(authorVo);
		
		
		authorDao.authorDelete(3);
		authorDao.authorDelete(4);
		authorDao.authorDelete(5);
		
		List<AuthorVo> authorList = authorDao.authorList();
		for(AuthorVo vo : authorList) {
			System.out.println(authorVo.getAuthorId() + "," 
							 + authorVo.getAuthorName() + ", " 
							 + authorVo.getAuthorDesc());
		}
		
		/*
		authorDao.getConnection();
		authorDao.close();
		*/
	}

}
