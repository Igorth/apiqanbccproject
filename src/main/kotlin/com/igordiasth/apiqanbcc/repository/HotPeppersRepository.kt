package com.igordiasth.apiqanbcc.repository

import com.igordiasth.apiqanbcc.model.HotPeppers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HotPeppersRepository : JpaRepository<HotPeppers, Long>