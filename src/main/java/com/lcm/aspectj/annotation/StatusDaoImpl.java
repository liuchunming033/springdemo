package com.lcm.aspectj.annotation;

import org.springframework.stereotype.Repository;

@Repository("statusDao")
public class StatusDaoImpl implements StatusDao {
    @Override
    public void add() {
        System.out.println("添加状态...");
    }

    @Override
    public void update() {
        System.out.println("更新状态...");
        int i = 1 / 0;
    }

    @Override
    public void delete() {
        System.out.println("删除状态...");

    }

    @Override
    public void find() {
        System.out.println("查找状态...");

    }
}
