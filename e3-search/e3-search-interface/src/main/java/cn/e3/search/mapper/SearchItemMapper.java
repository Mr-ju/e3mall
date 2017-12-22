package cn.e3.search.mapper;

import cn.e3.search.pojo.SearchItem;

import java.util.List;

/**
 * Created by GliangJu on 2017/12/13.
 */
public interface SearchItemMapper {
    /**
     * 需求:查询索引库域字段对应数据库值写入索引库
     * @return
     */
    public List<SearchItem> findDatabaseToSolrIndex();
}
