package com.igordiasth.apiqanbcc.model

import javax.persistence.*

@Entity
@Table(name = "HotPeppers")
data class HotPeppers(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long = 0,
    var brandName : String = "",
    var sauceName : String = "",
    @Lob
    var description : String = "",
    @Lob
    var url : String,
    var heat : Int = 0
)