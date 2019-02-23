/*
 * Copyright 2013 Martin Kouba
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

import java.util.ArrayList
import java.util.Collections
import java.util.ListResourceBundle
import java.util.ResourceBundle
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

/**
 * @author Martin Kouba
 */
class Resources_cs : ListResourceBundle(), TimeFormatProvider {

  public override fun getContents(): Array<Array<Any>> {
    return OBJECTS
  }

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    if (t is Minute) {
      return CsTimeFormatBuilder("Minute")
          .addFutureName("minutu", 1)
          .addFutureName("minuty", 4)
          .addFutureName("minut", java.lang.Long.MAX_VALUE)
          .addPastName("minutou", 1)
          .addPastName("minutami", java.lang.Long.MAX_VALUE)
          .build(this)
    } else if (t is Hour) {
      return CsTimeFormatBuilder("Hour")
          .addFutureName("hodinu", 1)
          .addFutureName("hodiny", 4)
          .addFutureName("hodin", java.lang.Long.MAX_VALUE)
          .addPastName("hodinou", 1)
          .addPastName("hodinami", java.lang.Long.MAX_VALUE)
          .build(this)
    } else if (t is Day) {
      return CsTimeFormatBuilder("Day")
          .addFutureName("den", 1)
          .addFutureName("dny", 4)
          .addFutureName("dní", java.lang.Long.MAX_VALUE)
          .addPastName("dnem", 1)
          .addPastName("dny", java.lang.Long.MAX_VALUE)
          .build(this)
    } else if (t is Week) {
      return CsTimeFormatBuilder("Week")
          .addFutureName("týden", 1)
          .addFutureName("týdny", 4)
          .addFutureName("týdnů", java.lang.Long.MAX_VALUE)
          .addPastName("týdnem", 1)
          .addPastName("týdny", java.lang.Long.MAX_VALUE)
          .build(this)
    } else if (t is Month) {
      return CsTimeFormatBuilder("Month")
          .addFutureName("měsíc", 1)
          .addFutureName("měsíce", 4)
          .addFutureName("měsíců", java.lang.Long.MAX_VALUE)
          .addPastName("měsícem", 1)
          .addPastName("měsíci", java.lang.Long.MAX_VALUE)
          .build(this)
    } else if (t is Year) {
      return CsTimeFormatBuilder("Year")
          .addFutureName("rok", 1)
          .addFutureName("roky", 4)
          .addFutureName("let", java.lang.Long.MAX_VALUE)
          .addPastName("rokem", 1)
          .addPastName("roky", java.lang.Long.MAX_VALUE)
          .build(this)
    }
    // Don't override format for other time units
    return null
  }

  private class CsTimeFormatBuilder internal constructor(private val resourceKeyPrefix: String) {

    private val names = ArrayList<Resources_cs.CsName>()

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
    private val futureNames = ArrayList<Resources_cs.CsName>()
    private val pastNames = ArrayList<Resources_cs.CsName>()

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
      Collections.sort(futureNames)
      Collections.sort(pastNames)
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

    override fun compareTo(other: CsName): Int {
      return threshold!!.compareTo(other.getThreshold())
    }
  }

  companion object {
    private val OBJECTS = arrayOf(

        arrayOf<Any>("CenturyPattern", "%n %u"), arrayOf<Any>("CenturyFuturePrefix", "za "), arrayOf<Any>("CenturyFutureSuffix", ""), arrayOf<Any>("CenturyPastPrefix", "před "), arrayOf<Any>("CenturyPastSuffix", ""), arrayOf<Any>("CenturySingularName", "století"), arrayOf<Any>("CenturyPluralName", "století"), arrayOf<Any>("CenturyPastSingularName", "stoletím"), arrayOf<Any>("CenturyPastPluralName", "stoletími"), arrayOf<Any>("CenturyFutureSingularName", "století"), arrayOf<Any>("CenturyFuturePluralName", "století"),

        arrayOf<Any>("DayPattern", "%n %u"), arrayOf<Any>("DayFuturePrefix", "za "), arrayOf<Any>("DayFutureSuffix", ""), arrayOf<Any>("DayPastPrefix", "před "), arrayOf<Any>("DayPastSuffix", ""), arrayOf<Any>("DaySingularName", "den"), arrayOf<Any>("DayPluralName", "dny"),

        arrayOf<Any>("DecadePattern", "%n %u"), arrayOf<Any>("DecadeFuturePrefix", "za "), arrayOf<Any>("DecadeFutureSuffix", ""), arrayOf<Any>("DecadePastPrefix", "před "), arrayOf<Any>("DecadePastSuffix", ""), arrayOf<Any>("DecadeSingularName", "desetiletí"), arrayOf<Any>("DecadePluralName", "desetiletí"), arrayOf<Any>("DecadePastSingularName", "desetiletím"), arrayOf<Any>("DecadePastPluralName", "desetiletími"), arrayOf<Any>("DecadeFutureSingularName", "desetiletí"), arrayOf<Any>("DecadeFuturePluralName", "desetiletí"),

        arrayOf<Any>("HourPattern", "%n %u"), arrayOf<Any>("HourFuturePrefix", "za "), arrayOf<Any>("HourFutureSuffix", ""), arrayOf<Any>("HourPastPrefix", "před"), arrayOf<Any>("HourPastSuffix", ""), arrayOf<Any>("HourSingularName", "hodina"), arrayOf<Any>("HourPluralName", "hodiny"),

        arrayOf<Any>("JustNowPattern", "%u"), arrayOf<Any>("JustNowFuturePrefix", ""), arrayOf<Any>("JustNowFutureSuffix", "za chvíli"), arrayOf<Any>("JustNowPastPrefix", "před chvílí"), arrayOf<Any>("JustNowPastSuffix", ""), arrayOf<Any>("JustNowSingularName", ""), arrayOf<Any>("JustNowPluralName", ""),

        arrayOf<Any>("MillenniumPattern", "%n %u"), arrayOf<Any>("MillenniumFuturePrefix", "za "), arrayOf<Any>("MillenniumFutureSuffix", ""), arrayOf<Any>("MillenniumPastPrefix", "před "), arrayOf<Any>("MillenniumPastSuffix", ""), arrayOf<Any>("MillenniumSingularName", "tisíciletí"), arrayOf<Any>("MillenniumPluralName", "tisíciletí"),

        arrayOf<Any>("MillisecondPattern", "%n %u"), arrayOf<Any>("MillisecondFuturePrefix", "za "), arrayOf<Any>("MillisecondFutureSuffix", ""), arrayOf<Any>("MillisecondPastPrefix", "před "), arrayOf<Any>("MillisecondPastSuffix", ""), arrayOf<Any>("MillisecondSingularName", "milisekunda"), arrayOf<Any>("MillisecondPluralName", "milisekundy"), arrayOf<Any>("MillisecondPastSingularName", "milisekundou"), arrayOf<Any>("MillisecondPastPluralName", "milisekundami"), arrayOf<Any>("MillisecondFutureSingularName", "milisekundu"), arrayOf<Any>("MillisecondFuturePluralName", "milisekund"),

        arrayOf<Any>("MinutePattern", "%n %u"), arrayOf<Any>("MinuteFuturePrefix", "za "), arrayOf<Any>("MinuteFutureSuffix", ""), arrayOf<Any>("MinutePastPrefix", "před "), arrayOf<Any>("MinutePastSuffix", ""), arrayOf<Any>("MinuteSingularName", "minuta"), arrayOf<Any>("MinutePluralName", "minuty"),

        arrayOf<Any>("MonthPattern", "%n %u"), arrayOf<Any>("MonthFuturePrefix", "za "), arrayOf<Any>("MonthFutureSuffix", ""), arrayOf<Any>("MonthPastPrefix", "před "), arrayOf<Any>("MonthPastSuffix", ""), arrayOf<Any>("MonthSingularName", "měsíc"), arrayOf<Any>("MonthPluralName", "měsíce"),

        arrayOf<Any>("SecondPattern", "%n %u"), arrayOf<Any>("SecondFuturePrefix", "za "), arrayOf<Any>("SecondFutureSuffix", ""), arrayOf<Any>("SecondPastPrefix", "před "), arrayOf<Any>("SecondPastSuffix", ""), arrayOf<Any>("SecondSingularName", "sekunda"), arrayOf<Any>("SecondPluralName", "sekundy"),

        arrayOf<Any>("WeekPattern", "%n %u"), arrayOf<Any>("WeekFuturePrefix", "za "), arrayOf<Any>("WeekFutureSuffix", ""), arrayOf<Any>("WeekPastPrefix", "před "), arrayOf<Any>("WeekPastSuffix", ""), arrayOf<Any>("WeekSingularName", "týden"), arrayOf<Any>("WeekPluralName", "týdny"),

        arrayOf<Any>("YearPattern", "%n %u"), arrayOf<Any>("YearFuturePrefix", "za "), arrayOf<Any>("YearFutureSuffix", ""), arrayOf<Any>("YearPastPrefix", "před "), arrayOf<Any>("YearPastSuffix", ""), arrayOf<Any>("YearSingularName", "rok"), arrayOf<Any>("YearPluralName", "roky"),

        arrayOf<Any>("AbstractTimeUnitPattern", ""), arrayOf<Any>("AbstractTimeUnitFuturePrefix", ""), arrayOf<Any>("AbstractTimeUnitFutureSuffix", ""), arrayOf<Any>("AbstractTimeUnitPastPrefix", ""), arrayOf<Any>("AbstractTimeUnitPastSuffix", ""), arrayOf<Any>("AbstractTimeUnitSingularName", ""), arrayOf<Any>("AbstractTimeUnitPluralName", ""))
  }
}
