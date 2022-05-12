package com.zd.esearch.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2022/5/12 0012 8:28
 */
public class PageInfoUtil {
    /**
     * pageInfo泛型转换
     *
     * @param pageInfoE pageInfo<E>类对象
     * @param Dclz      DTO类的class对象
     * @param <E>       entity类
     * @param <D>       DTO类
     * @return
     */
    public static <E, D> PageInfo<D> pageInfo2DTO(PageInfo<E> pageInfoE, Class<D> Dclz) {
        //创建page对象，传入当前页，和每页数量进行初始化（page对象是ArrayList的子类，在ArrayList的基础上添加了分页的信息）
        Page<D> page = new Page<>(pageInfoE.getPageNum(), pageInfoE.getPageSize());
        //传入总记录数
        page.setTotal(pageInfoE.getTotal());
        //遍历原entity的列表
        for (E e : pageInfoE.getList()) {
            try {
                //通过class对象生成DTO的对象
                D d = Dclz.getConstructor().newInstance();
                //使用BeanUtils将entity中与dto相同的属性拷贝到dto中并放入page列表
                BeanUtils.copyProperties(e, d);
                page.add(d);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //通过page对象以及pageInfoE中的导航页码数创建要返回的pageInfo<D>对象
        return new PageInfo<D>(page, pageInfoE.getNavigatePages());
    }
}
