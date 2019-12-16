package cn.com.wjqhuaxia.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * java内存分页工具类
 * @author wjqhuaxia
 *
 */
public class JavaMemoryPageUtil {

	/**
	 * 获取分页数据
	 * @param dataList	进行分页的数据集合
	 * @param pageNum	第几页
	 * @param pageSize	每页显示多少条
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getPageLimit(List dataList, int pageNum, int pageSize){
		if(CollectionUtils.isEmpty(dataList)){
			return dataList;
		}
		List resultList = new ArrayList();
		// 所有dataList数据中的第几条
        int currIdx = pageNum > 1 ? (pageNum -1) * pageSize : 0;
        for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
            resultList.add(dataList.get(currIdx + i));
        }
        return resultList;
	}
}
