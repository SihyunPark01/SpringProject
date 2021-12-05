package kr.co.farmstory.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.FileVo;

@Repository
public interface BoardDao {
	
	public int insertArticle(ArticleVo vo);
	public void insertFile(FileVo vo);
	public void insertComment(ArticleVo vo);
	
	public ArticleVo selectArticle(int seq); 
	public List<ArticleVo> selectArticles(int start, String cate);
	public FileVo selectFile(int fseq);
	public List<ArticleVo> selectComments(int seq);
	public ArticleVo selectComment(int seq);
	public int selectCountTotal(String cate);
	
	public int updateArticle(ArticleVo vo);
    public void updateFileDownload(int fseq);
	public void updateArticleHit(int seq);
	public int updateComment(int seq, String content);
	
	public void deleteArticle(int seq);
	public void deleteComment(int seq);
}
