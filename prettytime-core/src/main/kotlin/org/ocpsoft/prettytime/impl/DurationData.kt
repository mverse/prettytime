/*
 * Copyright 2012 <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ocpsoft.prettytime.impl

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.TimeUnit

//data class dDurationImpl(override var quantity: Long = 0,
//                         override var delta: Long = 0,
//                         override var unit: TimeUnit) : Duration {

//  override val isInPast get() = quantity < 0
//  override val isInFuture get() = !isInPast
//
//  override fun getQuantityRounded(tolerance: Int): Long {
//    var quantity = Math.abs(quantity)
//
//    if (delta != 0L) {
//      val threshold = Math
//          .abs(delta.toDouble() / unit.millisPerUnit.toDouble() * 100)
//      if (threshold > tolerance) {
//        quantity += 1
//      }
//    }
//    return quantity
//  }
//
//  override fun toString(): String = "DurationImpl [$quantity $unit, delta=$delta]"
//
//  override fun hashCode(): Int {
//    val prime = 31
//    var result = 1
//    result = prime * result + (delta xor delta.ushr(32)).toInt()
//    result = prime * result + (quantity xor quantity.ushr(32)).toInt()
//    result = prime * result + unit.hashCode()
//    return result
//  }
//
//  override fun equals(other: Any?): Boolean {
//    when {
//      other !is DurationImpl -> return false
//      this === other -> return true
//      other == null -> return false
//      javaClass != other.javaClass -> return false
//      delta != other.delta -> return false
//      quantity != other.quantity -> return false
//      unit != other.unit -> return false
//      else -> return true
//    }
//  }
//}

data class DurationData(
    override var quantity: Long = 0,
    override var delta: Long = 0,
    private var internalTimeUnit: TimeUnit? = null) : Duration {

  override var unit
    get() = internalTimeUnit ?: throw NullPointerException("unit has not been set")
    set(value) {
      internalTimeUnit = value
    }

  override val isInPast: Boolean get() = quantity < 0
  override val isInFuture: Boolean get() = !isInPast

  override fun getQuantityRounded(tolerance: Int): Long {
    var quantity = Math.abs(quantity)

    if (delta != 0L) {
      val threshold = Math
          .abs(delta.toDouble() / unit.millisPerUnit.toDouble() * 100)
      if (threshold > tolerance) {
        quantity += 1
      }
    }
    return quantity
  }

  override fun toString(): String = "DurationImpl [$quantity $unit, delta=$delta]"
}
