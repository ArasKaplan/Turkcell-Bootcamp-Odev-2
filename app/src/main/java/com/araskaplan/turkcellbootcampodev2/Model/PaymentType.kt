package com.araskaplan.turkcellbootcampodev2.Model

import kotlin.properties.Delegates

class PaymentType {
    var Id by Delegates.notNull<Int>()
    var Title :String?=null
    var Period :Int?=null
    var PeriodType:String?=null
}