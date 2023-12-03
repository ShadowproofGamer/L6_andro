package com.example.l5_andro;

import kotlin.random.Random

class DataItem {
    val humanoids = arrayOf("Human", "NPC", "Orc")

    var text_name : String = "Default person name"
    var text_spec : String = "Default specification"
    var item_strength : Float = Random.nextInt(0, 6).toFloat()
    var item_type : String = humanoids[Random.nextInt(0, 3)]
    var dangerous : Boolean = Random.nextBoolean()

    constructor()
    constructor(num: Int) : this() {
        text_name = "Default person name "+num
        if (item_type=="Orc"){
            dangerous=true
        }
    }
    constructor(name: String, spec:String, strength:Float, type:String, danger:Boolean) : this() {
        text_name = name
        text_spec = spec
        item_strength = strength
        item_type = type
        dangerous = danger

    }
}




