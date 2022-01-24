package com.igordiasth.apiqanbcc.repository

import com.igordiasth.apiqanbcc.model.Gadget
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GadgetRepository : JpaRepository<Gadget, Long>