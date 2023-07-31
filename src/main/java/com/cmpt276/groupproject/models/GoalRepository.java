package com.cmpt276.groupproject.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal,Integer> {
    List<Goal> findByUid(int uid);
    List<Goal> findByGid(int gid);
}
