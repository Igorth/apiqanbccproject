package com.igordiasth.apiqanbcc.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "HotPeppers")
data class HotPeppers(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long,
    val brandName : String,
    val sauceName : String?,
    val description : String,
    val url : String,
    val heat : String
)