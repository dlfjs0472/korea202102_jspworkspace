package com.koreait.springmvc0714.model.board.service;

import java.util.List;

import com.koreait.springmvc0714.model.domain.Board;

public interface BoardService {
	public List selectAll(); //목록
	public void insert(Board board);//글쓰기
	public Board select(int baord_id);//상세보기
	public void update(Board board); //수정
	public void delete(int board_id);//삭제
	
}
