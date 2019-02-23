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
package org.ocpsoft.prettytime.format

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.TimeFormat

/**
 * Represents a simple method of formatting a specific [Duration] of time
 *
 * @author [Lincoln Baxter, III](mailto:lincolnbaxter@gmail.com)
 */
open class SimpleTimeFormat : TimeFormat {
  open var singularName = ""
  open var pluralName = ""
  open var futureSingularName: String = ""
  open var futurePluralName: String = ""
  open var pastSingularName: String = ""
  open var pastPluralName: String = ""
  open var pattern = ""
  open var futurePrefix = ""
    set(value) {
      field = value.trim { it <= ' ' }
    }
  open var futureSuffix = ""
    set(value) {
      field = value.trim { it <= ' ' }
    }
  open var pastPrefix = ""
    set(value) {
      field = value.trim { it <= ' ' }
    }
  open var pastSuffix = ""
    set(value) {
      field = value.trim { it <= ' ' }
    }

  fun configure(block:(SimpleTimeFormat)->Unit) {
    block(this)
  }

  open var roundingTolerance = 50

  override fun format(duration: Duration): String {
    return format(duration, true)
  }

  override fun formatUnrounded(duration: Duration): String {
    return format(duration, false)
  }

  override fun decorate(duration: Duration, time: String): String {
    val result = when {
      duration.isInPast -> "$pastPrefix $time $pastSuffix"
      else -> "$futurePrefix $time $futureSuffix"
    }
    return result.replace("\\s+".toRegex(), " ").trim()
  }

  override fun decorateUnrounded(duration: Duration, time: String): String {
    // This format does not need to know about rounding during decoration.
    return decorate(duration, time)
  }

  private fun format(duration: Duration, round: Boolean): String {
    val sign = getSign(duration)
    val unit = getGramaticallyCorrectName(duration, round)
    val quantity = getQuantity(duration, round)

    return applyPattern(sign, unit, quantity)
  }

  private fun applyPattern(sign: String, unit: String, quantity: Long): String {
    var result = getPattern(quantity).replace(SIGN.toRegex(), sign)
    result = result.replace(QUANTITY.toRegex(), quantity.toString())
    result = result.replace(UNIT.toRegex(), unit)
    return result
  }

  protected open fun getPattern(quantity: Long): String {
    return pattern
  }

  protected fun getQuantity(duration: Duration, round: Boolean): Long {
    return Math.abs(if (round) duration.getQuantityRounded(roundingTolerance) else duration.quantity)
  }

  protected open fun getGramaticallyCorrectName(d: Duration, round: Boolean): String {
    var result = getSingularName(d)
    if (Math.abs(getQuantity(d, round)) == 0L || Math.abs(getQuantity(d, round)) > 1) {
      result = getPluralName(d)
    }
    return result
  }

  private fun getSign(d: Duration): String {
    return when {
      d.quantity < 0 -> NEGATIVE
      else -> ""
    }
  }

  private fun getSingularName(duration: Duration): String {
    return when {
      duration.isInFuture && futureSingularName.isNotEmpty() -> futureSingularName
      duration.isInPast && pastSingularName.isNotEmpty() -> pastSingularName
      else -> singularName
    }
  }

  private fun getPluralName(duration: Duration): String {
    return when {
      duration.isInFuture && futureSingularName.isNotEmpty() -> futurePluralName
      duration.isInPast && pastSingularName.isNotEmpty() -> pastPluralName
      else -> pluralName
    }
  }

  override fun toString(): String {
    return ("SimpleTimeFormat [pattern=" + pattern + ", futurePrefix=" + futurePrefix + ", futureSuffix="
        + futureSuffix + ", pastPrefix=" + pastPrefix + ", pastSuffix=" + pastSuffix + ", roundingTolerance="
        + roundingTolerance + "]")
  }

  companion object {
    private const val NEGATIVE = "-"
    const val SIGN = "%s"
    const val QUANTITY = "%n"
    const val UNIT = "%u"
  }
}
