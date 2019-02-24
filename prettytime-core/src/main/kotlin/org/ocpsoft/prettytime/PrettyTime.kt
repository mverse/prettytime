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
@file:Suppress("NAME_SHADOWING")

package org.ocpsoft.prettytime

import kotlinx.collections.immutable.toImmutableList
import org.ocpsoft.prettytime.impl.DurationData
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat
import org.ocpsoft.prettytime.impl.ResourcesTimeUnit
import org.ocpsoft.prettytime.units.Century
import org.ocpsoft.prettytime.units.Day
import org.ocpsoft.prettytime.units.Decade
import org.ocpsoft.prettytime.units.Hour
import org.ocpsoft.prettytime.units.JustNow
import org.ocpsoft.prettytime.units.Millennium
import org.ocpsoft.prettytime.units.Millisecond
import org.ocpsoft.prettytime.units.Minute
import org.ocpsoft.prettytime.units.Month
import org.ocpsoft.prettytime.units.Second
import org.ocpsoft.prettytime.units.TimeUnitComparator
import org.ocpsoft.prettytime.units.Week
import org.ocpsoft.prettytime.units.Year
import java.time.Instant
import java.util.*

/**
 * A utility for creating social-networking style timestamps. (e.g. "just now", "moments ago", "3 days ago",
 * "within 2 months")
 *
 *
 * **Usage:**
 *
 *
 * `
 * PrettyTime t = new PrettyTime();<br></br>
 * String timestamp = t.format(new Date());<br></br>
 * //result: moments from now
` *
 *
 * `
 * String timestamp = t.format(new Date(System.currentTimeMillis() + 1000 * 60 * 10));<br></br>
 * //result: 10 minutes from now
` *
 *
 *
 *
 * @author [Lincoln Baxter, III](mailto:lincolnbaxter@gmail.com)
 */
data class PrettyTime(
    /**
     * Set the reference [Date]. If `null`, [PrettyTime] will always use the current value of
     * [System.currentTimeMillis] as the reference [Date].
     *
     *
     * If the [Date] formatted is before the reference [Date], the format command will produce a
     * [String] that is in the past tense. If the [Date] formatted is after the reference [Date], the
     * format command will produce a [String] that is in the future tense.
     */
    var referenceFn: () -> Instant = { Instant.now() },
    private var defaultLocale: Locale = Locale.getDefault(),
    private val units: MutableMap<TimeUnit, TimeFormat> = mutableMapOf(),
    private var overrideResourceBundle: String? = null) {

  constructor(reference: Instant,
              locale: Locale = Locale.getDefault(),
              units: MutableMap<TimeUnit, TimeFormat> = mutableMapOf(),
              overrideResourceBundle: String? = null) : this(
      referenceFn = { reference },
      defaultLocale = locale,
      units = units,
      overrideResourceBundle = overrideResourceBundle)



  private var cachedUnitKeys: List<TimeUnit>? = null
  var reference: Instant
    get() = referenceFn()
    set(value) {
      referenceFn = { value }
    }

  /**
   * Get the unmodifiable [List] of the current configured [TimeUnit] instances in calculations.
   */
  val unitKeys: List<TimeUnit>
    get() {
      if (cachedUnitKeys == null) {
        cachedUnitKeys = units.keys.sortedWith(TimeUnitComparator).toImmutableList()
      }
      return cachedUnitKeys!!
    }

  var locale: Locale
    get() = defaultLocale
    /**
     * Set the the [Locale] for this [PrettyTime] object. This may be an expensive operation, since this
     * operation calls [TimeUnit.locale] for each [TimeUnit] in [.getUnits].
     */
    set(value) {
      defaultLocale = value
      units.flatMap { (key, value) -> listOfNotNull(key, value) }
          .mapNotNull { it as? LocaleAware<*> }
          .forEach { it.locale = value }
    }

  init {
    this.locale = defaultLocale
    initTimeUnits()
  }

  /**
   * Calculate the approximate [Duration] between the reference [Date] and given [Date]
   *
   * See `PrettyTime#reference`.
   */
  fun approximateDuration(then: Instant): Duration {
    val difference = then.toEpochMilli() - reference.toEpochMilli()
    return calculateDuration(difference)
  }

  /**
   * Calculate to the precision of the smallest provided [TimeUnit], the exact [Duration] represented by
   * the difference between the reference [Date], and the given [Date]. If the given [Date] is
   * `null`, the current value of [System.currentTimeMillis] will be used instead.
   *
   *
   * **Note**: Precision may be lost if no supplied [TimeUnit] is granular enough to represent the remainder
   * of time (in milliseconds).
   *
   * @param then The [Date] to be compared against the reference timestamp, or *now* if no reference
   * timestamp was provided
   * @return A sorted [List] of [Duration] objects, from largest to smallest. Each element in the list
   * represents the approximate duration (number of times) that [TimeUnit] to fit into the previous
   * element's delta. The first element is the largest [TimeUnit] to fit within the total difference
   * between compared dates.
   */
  fun calculatePreciseDuration(then: Instant): List<Duration> {
    val result = mutableListOf<Duration>()
    val difference = then.toEpochMilli() - reference.toEpochMilli()
    var duration = calculateDuration(difference)
    result += duration
    while (duration.delta != 0L) {
      duration = calculateDuration(duration.delta)
      if (result.size > 0) {
        val last = result.last()
        if (last.unit == duration.unit) {
          break
        }
      }

      if (duration.unit.isPrecise) result += duration
    }
    return result
  }

  /**
   * Format the given [Date] object. If the given [Date] is `null`, the current value of
   * [System.currentTimeMillis] will be used instead.
   *
   * @param then the [Date] to be formatted
   * @return A formatted string representing `then`
   */
  fun format(then: Instant): String {
    val d = approximateDuration(then)
    return format(d)
  }

  /**
   * Format the given [Duration] object, using the [TimeFormat] specified by the [TimeUnit] contained
   * within. If the given [Duration] is `null`, the current value of
   * [System.currentTimeMillis] will be used instead.
   *
   * @param duration the [Duration] to be formatted
   * @return A formatted string representing `duration`
   */
  fun format(duration: Duration): String {
    val format = getFormat(duration.unit)
    val time = format.format(duration)
    return format.decorate(duration, time)
  }

  /**
   * Format the given [Duration] objects, using the [TimeFormat] specified by the [TimeUnit]
   * contained within. Rounding rules are ignored for all but the last [Duration] element. If the given
   * [Duration] [List] is `null` or empty, the current value of
   * [System.currentTimeMillis] will be used instead.
   *
   * @param durations the [Duration]s to be formatted
   * @return A list of formatted strings representing `durations`
   */
  fun format(durations: List<Duration>): String {
    assert(durations.isNotEmpty()) { "parameter [durations] cannot be empty" }

    var lastDuration: Duration? = null
    var lastFormat: TimeFormat? = null
    val result = durations.mapIndexed { i, duration ->
      lastDuration = duration
      lastFormat = getFormatOrNull(duration.unit)
      return@mapIndexed when {
        i < durations.size - 1 -> lastFormat!!.formatUnrounded(duration)
        else -> lastFormat!!.format(duration)
      }
    }.joinToString(" ")

    return lastFormat!!.decorateUnrounded(lastDuration!!, result)
  }

  /**
   * Format the given [Date] object. Rounding rules are ignored. If the given [Date] is `null`,
   * the current value of [System.currentTimeMillis] will be used instead.
   *
   * @param then the [Date] to be formatted
   * @return A formatted string representing `then`
   */
  fun formatUnrounded(then: Instant): String {
    val d = approximateDuration(then)
    return formatUnrounded(d)
  }

  /**
   * Format the given [Duration] object, using the [TimeFormat] specified by the [TimeUnit] contained
   * within. Rounding rules are ignored. If the given [Duration] is `null`, the current value of
   * [System.currentTimeMillis] will be used instead.
   *
   * @param duration the [Duration] to be formatted
   * @return A formatted string representing `duration`
   */
  fun formatUnrounded(duration: Duration): String {
    val format = getFormat(duration.unit)
    val time = format.formatUnrounded(duration)
    return format.decorateUnrounded(duration, time)
  }

  /**
   * Format the given [Duration] objects, using the [TimeFormat] specified by the [TimeUnit]
   * contained within. Rounding rules are ignored. If the given [Duration] [List] is `null` or
   * empty, the current value of [System.currentTimeMillis] will be used instead.
   *
   * @param durations the [Duration]s to be formatted
   * @return A list of formatted strings representing `durations`
   */
  fun formatUnrounded(durations: List<Duration>): String {
    assert(durations.isNotEmpty()) { "parameter [durations] cannot be empty" }

    var duration: Duration? = null
    var format: TimeFormat? = null
    val result = durations.joinToString { d ->
      duration = d
      format = getFormatOrNull(d.unit)
      return@joinToString format!!.formatUnrounded(d)
    }

    return format!!.decorateUnrounded(duration!!, result)
  }

  /**
   * Format the given [Date] and return a non-relative (not decorated with past or future tense) [String]
   * for the approximate duration of its difference between the reference [Date]. If the given [Date] is
   * `null`, the current value of [System.currentTimeMillis] will be used instead.
   *
   *
   *
   * @param then the date to be formatted
   * @return A formatted string of the given [Date]
   */
  fun formatDuration(then: Instant): String {
    val duration = approximateDuration(then)
    return formatDuration(duration)
  }

  /**
   * Format the given [Duration] and return a non-relative (not decorated with past or future tense)
   * [String] for the approximate duration of the difference between the reference [Date] and the given
   * [Duration]. If the given [Duration] is `null`, the current value of
   * [System.currentTimeMillis] will be used instead.
   *
   * @param duration the duration to be formatted
   * @return A formatted string of the given [Duration]
   */
  fun formatDuration(duration: Duration): String {
    val timeFormat = getFormatOrNull(duration.unit)
    return timeFormat!!.format(duration)
  }

  /**
   * Format the given [Duration] [List] and return a non-relative (not decorated with past or future tense)
   * [String] for the approximate duration of its difference between the reference [Date]. If the given
   * [Duration] is `null`, the current value of [System.currentTimeMillis] will be used
   * instead.
   *
   * @param duration the duration to be formatted
   * @return A formatted string of the given [Duration]
   */
  fun formatDuration(durations: List<Duration>): String {
    assert(durations.isNotEmpty()) { "parameter [durations] cannot be empty" }

    return durations.mapIndexed { i, duration ->
      val format = getFormatOrNull(duration.unit)
      /*
       * Round only the last element
       */
      return@mapIndexed when {
        i < durations.lastIndex -> format!!.formatUnrounded(duration)
        else -> format!!.format(duration)
      }
    }.joinToString(" ")
  }

  /**
   * Format the given [Date] and return a non-relative (not decorated with past or future tense) [String]
   * for the approximate duration of its difference between the reference [Date]. Rounding rules are ignored. If
   * the given [Date] is `null`, the current value of [System.currentTimeMillis] will be used
   * instead.
   *
   *
   *
   * @param then the date to be formatted
   * @return A formatted string of the given [Date]
   */
  fun formatDurationUnrounded(then: Instant): String {
    val duration = approximateDuration(then)
    return formatDurationUnrounded(duration)
  }

  /**
   * Format the given [Duration] and return a non-relative (not decorated with past or future tense)
   * [String] for the approximate duration of its difference between the reference [Date]. Rounding rules
   * are ignored. If the given [Duration] is `null`, the current value of
   * [System.currentTimeMillis] will be used instead.
   *
   * @param duration the duration to be formatted
   * @return A formatted string of the given [Duration]
   */
  fun formatDurationUnrounded(duration: Duration): String {
    val timeFormat = getFormatOrNull(duration.unit)
    return timeFormat!!.formatUnrounded(duration)
  }

  /**
   * Format the given [Duration] [List] and return a non-relative (not decorated with past or future tense)
   * [String] for the approximate duration of its difference between the reference [Date]. Rounding rules
   * are ignored. If the given [Duration] is `null`, the current value of
   * [System.currentTimeMillis] will be used instead.
   *
   * @param duration the duration to be formatted
   * @return A formatted string of the given [Duration]
   */
  fun formatDurationUnrounded(durations: List<Duration>): String {
    if (durations.isEmpty()) throw IllegalArgumentException("parameter [durations] cannot be empty")
    return durations.joinToString(" ") { duration ->
      val format = getFormatOrNull(duration.unit)
      return@joinToString format!!.formatUnrounded(duration)
    }
  }

  /**
   * Get the registered [TimeFormat] for the given [TimeUnit] or `null` if none exists.
   */
  fun getFormatOrNull(unit: TimeUnit): TimeFormat? {
    return units[unit]
  }

  fun getFormat(unit: TimeUnit): TimeFormat = getFormatOrNull(unit)
      ?: throw IllegalArgumentException("No format found for key [unit]")

  operator fun get(unit: TimeUnit): TimeFormat = getFormat(unit)
  operator fun contains(unit: TimeUnit): Boolean = unit in units

  /**
   * Get the registered [TimeUnit] for the given [TimeUnit] type or `null` if none exists.
   */
  @Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")
  fun <UNIT : TimeUnit> getUnit(unitType: Class<UNIT>): UNIT? {
    return units.keys.asSequence()
        .mapNotNull { unit -> unit as? UNIT }
        .firstOrNull()
  }

  /**
   * Get the registered [TimeUnit] for the given [TimeUnit] type or `null` if none exists.
   */
  @Suppress("UNCHECKED_CAST")
  inline fun <reified UNIT : TimeUnit> getUnit(): UNIT? = getUnit(UNIT::class.java)

  /**
   * Register the given [TimeUnit] and corresponding [TimeFormat] instance to be used in calculations. If
   * an entry already exists for the given [TimeUnit], its [TimeFormat] will be overwritten with the given
   * [TimeFormat]. ([TimeUnit] and [TimeFormat] must not be `null`.)
   */
  fun registerUnit(unit: TimeUnit, format: TimeFormat): PrettyTime = apply {
    cachedUnitKeys = null
    if (unit is LocaleAware<*>) unit.locale = this.locale
    if (format is LocaleAware<*>) format.locale = this.locale
    units[unit] = format
  }

  /**
   * Removes the mapping for the given [TimeUnit] type. This effectively de-registers the [TimeUnit] so it
   * will not be used in formatting. Returns the [TimeFormat] that was removed, or `null` if no unit
   * of the given type was registered.
   */
  fun <UNIT : TimeUnit> removeUnit(unitType: Class<UNIT>): TimeFormat? {
    for (unit in units.keys) {
      if (unitType.isAssignableFrom(unit::class.java)) {
        cachedUnitKeys = null
        return units.remove(unit)
      }
    }
    return null
  }

  /**
   * Removes the mapping for the given [TimeUnit]. This effectively de-registers the [TimeUnit] so it will
   * not be used in formatting. Returns the [TimeFormat] that was removed, or null if no such unit was
   * registered.
   */
  fun removeUnit(unit: TimeUnit): TimeFormat? {
    cachedUnitKeys = null
    return units.remove(unit)
  }

  override fun toString(): String = "PrettyTime [reference=$reference, locale=$locale]"

  /**
   * Remove all registered [TimeUnit] instances. Returns all [TimeUnit] instances that were removed.
   */
  fun clearUnits(): List<TimeUnit>? {
    val result = unitKeys
    cachedUnitKeys = null
    units.clear()
    return result
  }

  private fun initTimeUnits() {
    addUnit(JustNow())
    addUnit(Millisecond())
    addUnit(Second())
    addUnit(Minute())
    addUnit(Hour())
    addUnit(Day())
    addUnit(Week())
    addUnit(Month())
    addUnit(Year())
    addUnit(Decade())
    addUnit(Century())
    addUnit(Millennium())
  }

  private fun addUnit(unit: ResourcesTimeUnit) {
    registerUnit(unit, ResourcesTimeFormat(unit = unit, overrideResourceBundle = overrideResourceBundle))
  }

  private fun calculateDuration(difference: Long): Duration {
    val absoluteDifference = Math.abs(difference)

    /*
     * Required for thread-safety
     */
    val localUnits = unitKeys
    val result = DurationData()

    var i = 0
    for (unit in localUnits) {
      val millisPerUnit = Math.abs(unit.millisPerUnit)
      var quantity = Math.abs(unit.maxQuantity)
      val isLastUnit = i == localUnits.lastIndex

      if (quantity == 0L && !isLastUnit) {
        quantity = localUnits[i + 1].millisPerUnit / unit.millisPerUnit
      }

      /*
       * Does our unit encompass the time duration?
       */
      if (millisPerUnit * quantity > absoluteDifference || isLastUnit) {
        result.unit = unit
        if (millisPerUnit > absoluteDifference) {
          result.quantity = getSign(difference)
          result.delta = 0
        } else {
          result.quantity = difference / millisPerUnit
          result.delta = difference - result.quantity * millisPerUnit
        }
        break
      }
      i++
    }
    return result
  }

  private fun getSign(difference: Long): Long {
    return if (0 > difference) {
      -1
    } else {
      1
    }
  }
}
