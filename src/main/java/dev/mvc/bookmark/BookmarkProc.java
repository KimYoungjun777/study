package dev.mvc.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.bookmark.BookmarkProc")
public class BookmarkProc implements BookmarkProcInter {
  @Autowired
  private BookmarkDAOInter bookmarkDAO;
  
  @Override
  public int create(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkDAO.create(bookmarkVO);
    return cnt;    
  }

  @Override
  public int bookmark_by_visitor_cnt(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkDAO.bookmark_by_visitor_cnt(bookmarkVO);
    return cnt;
  }

  @Override
  public int bookmark_cnt(int substancesno) {
    int cnt = this.bookmarkDAO.bookmark_cnt(substancesno);
    return cnt;
  }

  @Override
  public int delete(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkDAO.delete(bookmarkVO);
    return cnt;
  }

  @Override
  public int delete_by_substancesno(int substancesno) {
    int cnt = this.bookmarkDAO.delete_by_substancesno(substancesno);
    return cnt;
  }

  
}
