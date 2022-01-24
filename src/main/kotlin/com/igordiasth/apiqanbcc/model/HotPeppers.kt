package com.igordiasth.apiqanbcc.model

import javax.persistence.*

@Entity
@Table(name = "HotPeppers")
data class HotPeppers(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long = 0,
    val brandName : String = "",
    val sauceName : String = "",
    @Lob
    val description : String = "",
    @Lob
    val url : String,
    val heat : Int = 0
)