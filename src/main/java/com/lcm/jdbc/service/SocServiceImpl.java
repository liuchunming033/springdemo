package com.lcm.jdbc.service;

import com.lcm.jdbc.dao.SocDao;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring 的事务处理位于service层，它提供了针对事务的解决方案。
 *Spring 的事务属性分别为传播行为、隔离级别、只读和超时属性，
 * 事务的传播行为是指在同一个方法中，不同操作前后所使用的事务。
 * 在事务管理过程中，传播行为可以控制是否需要创建事务以及如何创建事务。
 * 通常情况下，数据的查询不会改变原数据，所以不需要进行事务管理，而对于数据的增加、修改和删除等操作，必须进行事务管理。
 * 如果没有指定事务的传播行为，则 Spring3 默认的传播行为是 required。
 * 这里将SocServiceImpl定义成事务。
 * 在需要使用事务的业务类或者方法中添加注解 @Transactional，并配置 @Transactional 的参数。
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class SocServiceImpl implements SocService {
    private SocDao socDao;

    public void setSocDao(SocDao socDao) {
        this.socDao = socDao;
    }

    @Override
    public void transfer(String vid, int btry_pak_sum) {
        this.socDao.out(vid, btry_pak_sum);
        int i = 1 / 0;  //会导致事务中断
        this.socDao.in(vid, btry_pak_sum);
    }
}
