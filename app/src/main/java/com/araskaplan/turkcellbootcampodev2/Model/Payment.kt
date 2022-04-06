package com.araskaplan.turkcellbootcampodev2.Model

import kotlin.properties.Delegates

class Payment {
    var Id by Delegates.notNull<Int>()
    var Amount :Int?=null
    var Date :String?=null
    var PaymentTypeID by Delegates.notNull<Int>()
}