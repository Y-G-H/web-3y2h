package cn.y3h2.blog.web.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页分装类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {
  /**
   * 当前页记录
   */
  private List<T> data;
  /**
   * 记录总数
   */
  private Long total;
  /**
   * 页记录数
   */
  private Integer pageSize;
  /**
   * 页码
   */
  private Integer pageNum;

  public Page(List<T> data, Long total) {
    this.data = data;
    this.total = total;
  }

  public void init(){
    if(pageNum == null){
      this.pageNum = 1;
    }
    if(pageSize == null){
      this.setPageSize(10);
    }
  }


  public static <T> Page emptyPage(){
    Page<T> emptyPage= new Page<>();
    emptyPage.setTotal(0L);
    emptyPage.setData(new ArrayList<>(0));
    return emptyPage;
  }
  public static <T> Page emptyPage(Long total){
    Page<T> emptyPage= new Page<>();
    emptyPage.setTotal(total);
    emptyPage.setData(new ArrayList<>(0));
    return emptyPage;
  }

  public static <T> Page emptyPage(Integer pageSize,Integer pageNum){
    Page<T> emptyPage= emptyPage();
    emptyPage.setPageSize(pageSize);
    emptyPage.setPageNum(pageNum);
    return emptyPage;
  }
}
