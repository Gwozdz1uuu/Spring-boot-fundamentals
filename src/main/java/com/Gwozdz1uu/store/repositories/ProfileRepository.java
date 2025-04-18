package com.Gwozdz1uu.store.repositories;

import com.Gwozdz1uu.store.dtos.UserSummary;
import com.Gwozdz1uu.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}