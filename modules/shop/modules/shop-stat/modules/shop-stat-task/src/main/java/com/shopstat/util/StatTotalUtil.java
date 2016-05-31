package com.shopstat.util;

import com.shopstat.model.vo.stat.Dimen;
import com.shopstat.model.vo.stat.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * 统计汇总工具类
 */
public class StatTotalUtil {

    private static Logger logger= LoggerFactory.getLogger(StatTotalUtil.class);

    public interface StatCallBack<T>
    {
        public void deal(List<T> perList);
    }

    /**
     * 汇总统计
     * @param stat   统计实体
     */
    public static <T> void statTotal(Stat stat,Class<T> clazz,JdbcTemplate jdbcTemplate,StatCallBack<T> callBack)
    {
        List<Dimen> dimenList = stat.getDimenList();
        int n = dimenList.size();
        //把每个维度可能值的组合查找出来,
        //这里优化成每个为维度全部/非全部组合查找出来,0--全部,1--非全部
        List<Integer []> combins =null;
        try {
            combins=StatNfor.nfor(2,n);
        }catch(Exception e)
        {
            logger.error("StatTotalUtil invoke StatNfor.nfor error,",e);
            return;
        }
        //对组合过过滤
        Iterator<Integer []> it = combins.iterator();
        while(it.hasNext())
        {
            Integer [] per=it.next();
            boolean hasTotal= totalFilter(per,0);
            //0表示全部
            if(!hasTotal)
            {
                it.remove();
            }
        }
        //遍历组合,进行组合查询汇总
        for(Integer [] combin:combins)
        {
            logger.info("StatTotalUtil combin-->"+getArrayStr(combin));
            String querySql =stat.combinSql(stat, combin);
            logger.info("StatTotalUtil combinSql querySql-->\n" + querySql);
            List<T> reultList = DbUtil.queryList(querySql,clazz,jdbcTemplate);
            callBack.deal(reultList);
            if(reultList!=null) {
                reultList.clear();
            }
        }
    }

    public static boolean totalFilter(Integer [] combin,int totalValue)
    {
        boolean hasTotal=false;
        int n =combin.length;
        for(int i=0;i<n;i++)
        {
            if( combin[i]==totalValue)
            {
                hasTotal=true;
                break;
            }
        }
        return hasTotal;
    }

    public static String getArrayStr(Integer [] as)
    {
        StringBuffer buf = new StringBuffer("");
        for(Integer i:as)
        {
             buf.append(i).append(" ");
        }
        return buf.toString();
    }

//
//    public static boolean totalFilter(Integer [] combin,Map<Integer,Integer> totalMap)
//    {
//        boolean hasTotal=false;
//        int n =combin.length;
//        for(int i=0;i<n;i++)
//        {
//            if(totalMap.get(i)!=null && totalMap.get(i)==combin[i].intValue())
//            {
//                hasTotal=true;
//                break;
//            }
//        }
//        return hasTotal;
//    }
//    public static int getPosition(List<String> list,String value)
//    {
//        int pos =-1;
//        if(value==null || list==null)
//        {
//            return pos;
//        }
//        for(int i=0;i<list.size();i++)
//        {
//            if(value.equals(list.get(i)))
//            {
//                pos=i;
//                break;
//            }
//        }
//        return pos;
//    }

}
