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
package org.ocpsoft.prettytime.i18n

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.TimeFormat
import org.ocpsoft.prettytime.TimeUnit
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import org.ocpsoft.prettytime.impl.TimeFormatProvider
import org.ocpsoft.prettytime.units.Day
import org.ocpsoft.prettytime.units.Hour
import org.ocpsoft.prettytime.units.Millennium
import org.ocpsoft.prettytime.units.Minute
import org.ocpsoft.prettytime.units.Month
import org.ocpsoft.prettytime.units.Week
import org.ocpsoft.prettytime.units.Year
import java.util.*

class Resources_hr : ListResourceBundle(), TimeFormatProvider {
  companion object {
    private var OBJECTS = arrayOf(
        arrayOf("CenturyPattern", "%n %u"),
        arrayOf("CenturyFuturePrefix", "za "),
        arrayOf("CenturyFutureSuffix", ""),
        arrayOf("CenturyPastPrefix", ""),
        arrayOf("CenturyPastSuffix", " unatrag"),
        arrayOf("CenturySingularName", "stoljeće"),
        arrayOf("CenturyPluralName", "stoljeća"),
        arrayOf("DayPattern", "%n %u"),
        arrayOf("DayFuturePrefix", "za "),
        arrayOf("DayFutureSuffix", ""),
        arrayOf("DayPastPrefix", "prije "),
        arrayOf("DayPastSuffix", ""),
        arrayOf("DaySingularName", "dan"),
        arrayOf("DayPluralName", "dana"),
        arrayOf("DecadePattern", "%n %u"),
        arrayOf("DecadeFuturePrefix", "za "),
        arrayOf("DecadeFutureSuffix", ""),
        arrayOf("DecadePastPrefix", "prije "),
        arrayOf("DecadePastSuffix", ""),
        arrayOf("DecadeSingularName", "desetljeće"),
        arrayOf("DecadePluralName", "desetljeća"),
        arrayOf("HourPattern", "%n %u"),
        arrayOf("HourFuturePrefix", "za "),
        arrayOf("HourFutureSuffix", ""),
        arrayOf("HourPastPrefix", "prije "),
        arrayOf("HourPastSuffix", ""),
        arrayOf("HourSingularName", "sat"),
        arrayOf("HourPluralName", "sati"),
        arrayOf("JustNowPattern", "%u"),
        arrayOf("JustNowFuturePrefix", "za nekoliko trenutaka"),
        arrayOf("JustNowFutureSuffix", ""),
        arrayOf("JustNowPastPrefix", "prije nekoliko trenutaka"),
        arrayOf("JustNowPastSuffix", ""),
        arrayOf("JustNowSingularName", ""),
        arrayOf("JustNowPluralName", ""),
        arrayOf("MillenniumPattern", "%n %u"),
        arrayOf("MillenniumFuturePrefix", "za "),
        arrayOf("MillenniumFutureSuffix", ""),
        arrayOf("MillenniumPastPrefix", "prije "),
        arrayOf("MillenniumPastSuffix", ""),
        arrayOf("MillenniumSingularName", "tisućljeće"),
        arrayOf("MillenniumPluralName", "tisućljeća"),
        arrayOf("MillisecondPattern", "%n %u"),
        arrayOf("MillisecondFuturePrefix", "za "),
        arrayOf("MillisecondFutureSuffix", ""),
        arrayOf("MillisecondPastPrefix", "prije "),
        arrayOf("MillisecondPastSuffix", ""),
        arrayOf("MillisecondSingularName", "milisekunda"),
        arrayOf("MillisecondPluralName", "milisekunda"),
        arrayOf("MinutePattern", "%n %u"),
        arrayOf("MinuteFuturePrefix", "za "),
        arrayOf("MinuteFutureSuffix", ""),
        arrayOf("MinutePastPrefix", "prije "),
        arrayOf("MinutePastSuffix", ""),
        arrayOf("MinuteSingularName", "minuta"),
        arrayOf("MinutePluralName", "minuta"),
        arrayOf("MonthPattern", "%n %u"),
        arrayOf("MonthFuturePrefix", "za "),
        arrayOf("MonthFutureSuffix", ""),
        arrayOf("MonthPastPrefix", "prije "),
        arrayOf("MonthPastSuffix", ""),
        arrayOf("MonthSingularName", "mjesec"),
        arrayOf("MonthPluralName", "mjeseca"),
        arrayOf("SecondPattern", "%n %u"),
        arrayOf("SecondFuturePrefix", "za "),
        arrayOf("SecondFutureSuffix", ""),
        arrayOf("SecondPastPrefix", "prije "),
        arrayOf("SecondPastSuffix", ""),
        arrayOf("SecondSingularName", "sekunda"),
        arrayOf("SecondPluralName", "sekundi"),
        arrayOf("WeekPattern", "%n %u"),
        arrayOf("WeekFuturePrefix", "za "),
        arrayOf("WeekFutureSuffix", ""),
        arrayOf("WeekPastPrefix", "prije "),
        arrayOf("WeekPastSuffix", ""),
        arrayOf("WeekSingularName", "tjedan"),
        arrayOf("WeekPluralName", "tjedna"),
        arrayOf("YearPattern", "%n %u"),
        arrayOf("YearFuturePrefix", "za "),
        arrayOf("YearFutureSuffix", ""),
        arrayOf("YearPastPrefix", "prije "),
        arrayOf("YearPastSuffix", ""),
        arrayOf("YearSingularName", "godina"),
        arrayOf("YearPluralName", "godina"),
        arrayOf("AbstractTimeUnitPattern", ""),
        arrayOf("AbstractTimeUnitFuturePrefix", ""),
        arrayOf("AbstractTimeUnitFutureSuffix", ""),
        arrayOf("AbstractTimeUnitPastPrefix", ""),
        arrayOf("AbstractTimeUnitPastSuffix", ""),
        arrayOf("AbstractTimeUnitSingularName", ""),
        arrayOf("AbstractTimeUnitPluralName", ""))
  }

  override fun getContents() = OBJECTS

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    when (t) {
      is Minute -> return HrTimeFormatBuilder("Minute").addNames("minutu", 1)
          .addNames("minute", 4).addNames("minuta", java.lang.Long.MAX_VALUE)
          .build(this)
      is Hour -> return HrTimeFormatBuilder("Hour").addNames("sat", 1)
          .addNames("sata", 4).addNames("sati", java.lang.Long.MAX_VALUE)
          .build(this)
      is Day -> return HrTimeFormatBuilder("Day").addNames("dan", 1)
          .addNames("dana", 4).addNames("dana", java.lang.Long.MAX_VALUE)
          .build(this)
      is Week -> return HrTimeFormatBuilder("Week").addNames("tjedan", 1)
          .addNames("tjedna", 4).addNames("tjedana", java.lang.Long.MAX_VALUE)
          .build(this)
      is Month -> return HrTimeFormatBuilder("Month").addNames("mjesec", 1)
          .addNames("mjeseca", 4).addNames("mjeseci", java.lang.Long.MAX_VALUE)
          .build(this)
      is Year -> return HrTimeFormatBuilder("Year").addNames("godinu", 1)
          .addNames("godine", 4).addNames("godina", java.lang.Long.MAX_VALUE)
          .build(this)
      is Millennium -> return HrTimeFormatBuilder("Millennium")
          .addNames("tisućljeće", 1).addNames("tisućljeća", java.lang.Long.MAX_VALUE)
          .build(this)
      // Don't override format for other time units
      else -> return null
    }
  }

  private class HrName(val isFuture: Boolean, private val value: String, private val threshold: Long?) : Comparable<HrName> {

    override fun compareTo(other: HrName): Int {
      return threshold!!.compareTo(other.getThreshold())
    }

    fun get(): String {
      return value
    }

    fun getThreshold(): Long {
      return threshold!!
    }
  }

  private class HrTimeFormat(resourceKeyPrefix: String, bundle: ResourceBundle, names: Collection<HrName>) : SimpleTimeFormat(), TimeFormat {

    private val futureNames = ArrayList<Resources_hr.HrName>()

    private val pastNames = ArrayList<Resources_hr.HrName>()

    init {
      pattern = bundle.getString(resourceKeyPrefix + "Pattern")
      futurePrefix = bundle.getString(resourceKeyPrefix + "FuturePrefix")
      futureSuffix = bundle.getString(resourceKeyPrefix + "FutureSuffix")
      pastPrefix = bundle.getString(resourceKeyPrefix + "PastPrefix")
      pastSuffix = bundle.getString(resourceKeyPrefix + "PastSuffix")
      singularName = bundle.getString(resourceKeyPrefix + "SingularName")
      pluralName = bundle.getString(resourceKeyPrefix + "PluralName")

      try {
        futurePluralName = bundle.getString(resourceKeyPrefix + "FuturePluralName")
      } catch (e: Exception) {
      }

      try {
        futureSingularName = bundle.getString(resourceKeyPrefix + "FutureSingularName")
      } catch (e: Exception) {
      }

      try {
        pastPluralName = bundle.getString(resourceKeyPrefix + "PastPluralName")
      } catch (e: Exception) {
      }

      try {
        pastSingularName = bundle.getString(resourceKeyPrefix + "PastSingularName")
      } catch (e: Exception) {
      }

      for (name in names) {
        if (name.isFuture) {
          futureNames.add(name)
        } else {
          pastNames.add(name)
        }
      }
      futureNames.sort()
      pastNames.sort()
    }

    override fun getGramaticallyCorrectName(d: Duration, round: Boolean): String {
      val quantity = Math.abs(getQuantity(d, round))
      return if (d.isInFuture) {
        getGramaticallyCorrectName(quantity, futureNames)
      } else getGramaticallyCorrectName(quantity, pastNames)
    }

    private fun getGramaticallyCorrectName(quantity: Long, names: List<HrName>): String {
      for (name in names) {
        if (name.getThreshold() >= quantity) {
          return name.get()
        }
      }
      throw IllegalStateException("Invalid resource bundle configuration")
    }
  }

  private class HrTimeFormatBuilder internal constructor(private val resourceKeyPrefix: String) {

    private val names = mutableListOf<HrName>()

    internal fun addNames(name: String, limit: Long): HrTimeFormatBuilder {
      return addName(true, name, limit).addName(false, name, limit)
    }

    internal fun build(bundle: ResourceBundle): HrTimeFormat {
      return HrTimeFormat(resourceKeyPrefix, bundle, names)
    }

    private fun addName(isFuture: Boolean, name: String?, limit: Long): HrTimeFormatBuilder {
      if (name == null) {
        throw IllegalArgumentException()
      }
      names += HrName(isFuture, name, limit)
      return this
    }
  }
}
