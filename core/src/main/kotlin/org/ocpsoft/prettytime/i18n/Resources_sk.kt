/*
 * Copyright 2015 Michal Ursíny & Stefan Kostelny
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
import org.ocpsoft.prettytime.units.Minute
import org.ocpsoft.prettytime.units.Month
import org.ocpsoft.prettytime.units.Week
import org.ocpsoft.prettytime.units.Year
import java.util.*

/**
 * @author Michal Ursíny & Stefan Kostelny based on cs variant by Martin Kouba
 */
class Resources_sk : ListResourceBundle(), TimeFormatProvider {

  override fun getContents() = OBJECTS

  companion object {
    private var OBJECTS = arrayOf(
        arrayOf("CenturyPattern", "%n %u"),
        arrayOf("CenturyFuturePrefix", "o "),
        arrayOf("CenturyFutureSuffix", ""),
        arrayOf("CenturyPastPrefix", "pred "),
        arrayOf("CenturyPastSuffix", ""),
        arrayOf("CenturySingularName", "storočie"),
        arrayOf("CenturyPluralName", "storočia"),
        arrayOf("CenturyPastSingularName", "storočím"),
        arrayOf("CenturyPastPluralName", "storočiami"),
        arrayOf("CenturyFutureSingularName", "storočí"),
        arrayOf("CenturyFuturePluralName", "storočia"),

        arrayOf("DayPattern", "%n %u"),
        arrayOf("DayFuturePrefix", "o "),
        arrayOf("DayFutureSuffix", ""),
        arrayOf("DayPastPrefix", "pred "),
        arrayOf("DayPastSuffix", ""),
        arrayOf("DaySingularName", "deň"),
        arrayOf("DayPluralName", "dni"),

        arrayOf("DecadePattern", "%n %u"),
        arrayOf("DecadeFuturePrefix", "o "),
        arrayOf("DecadeFutureSuffix", ""),
        arrayOf("DecadePastPrefix", "pred "),
        arrayOf("DecadePastSuffix", ""),
        arrayOf("DecadeSingularName", "desaťročie"),
        arrayOf("DecadePluralName", "desaťročia"),
        arrayOf("DecadePastSingularName", "desaťročím"),
        arrayOf("DecadePastPluralName", "desaťročiami"),
        arrayOf("DecadeFutureSingularName", "desaťročí"),
        arrayOf("DecadeFuturePluralName", "desaťročia"),

        arrayOf("HourPattern", "%n %u"),
        arrayOf("HourFuturePrefix", "o "),
        arrayOf("HourFutureSuffix", ""),
        arrayOf("HourPastPrefix", "pred"),
        arrayOf("HourPastSuffix", ""),
        arrayOf("HourSingularName", "hodina"),
        arrayOf("HourPluralName", "hodiny"),

        arrayOf("JustNowPattern", "%u"),
        arrayOf("JustNowFuturePrefix", ""),
        arrayOf("JustNowFutureSuffix", "o chvíľu"),
        arrayOf("JustNowPastPrefix", "pred chvíľou"),
        arrayOf("JustNowPastSuffix", ""),
        arrayOf("JustNowSingularName", ""),
        arrayOf("JustNowPluralName", ""),

        arrayOf("MillenniumPattern", "%n %u"),
        arrayOf("MillenniumFuturePrefix", "o "),
        arrayOf("MillenniumFutureSuffix", ""),
        arrayOf("MillenniumPastPrefix", "pred "),
        arrayOf("MillenniumPastSuffix", ""),
        arrayOf("MillenniumSingularName", "tísícročie"),
        arrayOf("MillenniumPluralName", "tisícročia"),

        arrayOf("MillisecondPattern", "%n %u"),
        arrayOf("MillisecondFuturePrefix", "o "),
        arrayOf("MillisecondFutureSuffix", ""),
        arrayOf("MillisecondPastPrefix", "pred "),
        arrayOf("MillisecondPastSuffix", ""),
        arrayOf("MillisecondSingularName", "milisekunda"),
        arrayOf("MillisecondPluralName", "milisekundy"),
        arrayOf("MillisecondPastSingularName", "milisekundou"),
        arrayOf("MillisecondPastPluralName", "milisekundami"),
        arrayOf("MillisecondFutureSingularName", "milisekundu"),
        arrayOf("MillisecondFuturePluralName", "milisekúnd"),

        arrayOf("MinutePattern", "%n %u"),
        arrayOf("MinuteFuturePrefix", "o "),
        arrayOf("MinuteFutureSuffix", ""),
        arrayOf("MinutePastPrefix", "pred "),
        arrayOf("MinutePastSuffix", ""),
        arrayOf("MinuteSingularName", "minúta"),
        arrayOf("MinutePluralName", "minúty"),

        arrayOf("MonthPattern", "%n %u"),
        arrayOf("MonthFuturePrefix", "o "),
        arrayOf("MonthFutureSuffix", ""),
        arrayOf("MonthPastPrefix", "pred "),
        arrayOf("MonthPastSuffix", ""),
        arrayOf("MonthSingularName", "mesiac"),
        arrayOf("MonthPluralName", "mesiace"),

        arrayOf("SecondPattern", "%n %u"),
        arrayOf("SecondFuturePrefix", "o "),
        arrayOf("SecondFutureSuffix", ""),
        arrayOf("SecondPastPrefix", "pred "),
        arrayOf("SecondPastSuffix", ""),
        arrayOf("SecondSingularName", "sekunda"),
        arrayOf("SecondPluralName", "sekundy"),

        arrayOf("WeekPattern", "%n %u"),
        arrayOf("WeekFuturePrefix", "o "),
        arrayOf("WeekFutureSuffix", ""),
        arrayOf("WeekPastPrefix", "pred "),
        arrayOf("WeekPastSuffix", ""),
        arrayOf("WeekSingularName", "týždeň"),
        arrayOf("WeekPluralName", "týždne"),

        arrayOf("YearPattern", "%n %u"),
        arrayOf("YearFuturePrefix", "o "),
        arrayOf("YearFutureSuffix", ""),
        arrayOf("YearPastPrefix", "pred "),
        arrayOf("YearPastSuffix", ""),
        arrayOf("YearSingularName", "rok"),
        arrayOf("YearPluralName", "roky"),

        arrayOf("AbstractTimeUnitPattern", ""),
        arrayOf("AbstractTimeUnitFuturePrefix", ""),
        arrayOf("AbstractTimeUnitFutureSuffix", ""),
        arrayOf("AbstractTimeUnitPastPrefix", ""),
        arrayOf("AbstractTimeUnitPastSuffix", ""),
        arrayOf("AbstractTimeUnitSingularName", ""),
        arrayOf("AbstractTimeUnitPluralName", ""))
  }

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    when (t) {
      is Minute -> return CsTimeFormatBuilder("Minute")
          .addFutureName("minútu", 1)
          .addFutureName("minúty", 4)
          .addFutureName("minút", java.lang.Long.MAX_VALUE)
          .addPastName("minútou", 1)
          .addPastName("minútami", java.lang.Long.MAX_VALUE)
          .build(this)
      is Hour -> return CsTimeFormatBuilder("Hour")
          .addFutureName("hodinu", 1)
          .addFutureName("hodiny", 4)
          .addFutureName("hodín", java.lang.Long.MAX_VALUE)
          .addPastName("hodinou", 1)
          .addPastName("hodinami", java.lang.Long.MAX_VALUE)
          .build(this)
      is Day -> return CsTimeFormatBuilder("Day")
          .addFutureName("deň", 1)
          .addFutureName("dni", 4)
          .addFutureName("dní", java.lang.Long.MAX_VALUE)
          .addPastName("dňom", 1)
          .addPastName("dňami", java.lang.Long.MAX_VALUE)
          .build(this)
      is Week -> return CsTimeFormatBuilder("Week")
          .addFutureName("týždeň", 1)
          .addFutureName("týždne", 4)
          .addFutureName("týždňov", java.lang.Long.MAX_VALUE)
          .addPastName("týždňom", 1)
          .addPastName("týždňami", java.lang.Long.MAX_VALUE)
          .build(this)
      is Month -> return CsTimeFormatBuilder("Month")
          .addFutureName("mesiac", 1)
          .addFutureName("mesiace", 4)
          .addFutureName("mesiacov", java.lang.Long.MAX_VALUE)
          .addPastName("mesiacom", 1)
          .addPastName("mesiacmi", java.lang.Long.MAX_VALUE)
          .build(this)
      is Year -> return CsTimeFormatBuilder("Year")
          .addFutureName("rok", 1)
          .addFutureName("roky", 4)
          .addFutureName("rokov", java.lang.Long.MAX_VALUE)
          .addPastName("rokom", 1)
          .addPastName("rokmi", java.lang.Long.MAX_VALUE)
          .build(this)
      // Don't override format for other time units
      else -> return null
    }
  }

  private class CsTimeFormatBuilder internal constructor(private val resourceKeyPrefix: String) {

    private val names = ArrayList<Resources_sk.CsName>()

    internal fun addFutureName(name: String, limit: Long): CsTimeFormatBuilder {
      return addName(true, name, limit)
    }

    internal fun addPastName(name: String, limit: Long): CsTimeFormatBuilder {
      return addName(false, name, limit)
    }

    internal fun build(bundle: ResourceBundle): CsTimeFormat {
      return CsTimeFormat(resourceKeyPrefix, bundle, names)
    }

    private fun addName(isFuture: Boolean, name: String?, limit: Long): CsTimeFormatBuilder {
      if (name == null) {
        throw IllegalArgumentException()
      }
      names.add(CsName(isFuture, name, limit))
      return this
    }
  }

  private class CsTimeFormat(resourceKeyPrefix: String, bundle: ResourceBundle, names: Collection<CsName>) : SimpleTimeFormat(), TimeFormat {

    private val futureNames = ArrayList<Resources_sk.CsName>()

    private val pastNames = ArrayList<Resources_sk.CsName>()

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

    private fun getGramaticallyCorrectName(quantity: Long, names: List<CsName>): String {
      for (name in names) {
        if (name.getThreshold() >= quantity) {
          return name.get()
        }
      }
      throw IllegalStateException("Invalid resource bundle configuration")
    }
  }

  private class CsName(val isFuture: Boolean, private val value: String, private val threshold: Long?) : Comparable<CsName> {

    fun get(): String {
      return value
    }

    fun getThreshold(): Long {
      return threshold!!
    }

    override fun compareTo(o: CsName): Int {
      return threshold!!.compareTo(o.getThreshold())
    }
  }


}
