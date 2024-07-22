package dev.mvc.bookmark;

import java.util.ArrayList;

public interface BookmarkDAOInter {
  
  public int create(BookmarkVO bookmarkVO);
  
  public int bookmark_by_visitor_cnt(BookmarkVO bookmarkVO);
  
  public int bookmark_cnt(int substancesno);
  
  public ArrayList<Integer> substances_by_visitor(int visitorno);
  
  public int delete(BookmarkVO bookmarkVO);
  
  public int delete_by_substancesno(int substancesno);
}
