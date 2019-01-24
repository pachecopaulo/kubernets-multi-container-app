package com.study.ks8.repository

import com.study.ks8.entity.Values
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ValuesRepository : JpaRepository<Values, Long>