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
package org.ocpsoft.prettytime

import org.junit.Assert.assertEquals

import java.util.ArrayList
import java.util.Date
import java.util.Locale

import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.units.JustNow

class PrettyTimeI18n_IT_Test {
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("it")
  }

  private fun newPrettyTimeWOJustNow(ref: Date, locale: Locale?): PrettyTime {
    val t = PrettyTime(ref, locale)
    val units = t.unitKeys
    val formats = ArrayList<TimeFormat>()
    for (timeUnit in units) {
      if (timeUnit !is JustNow) {
        formats.add(t.getFormatOrNull(timeUnit)!!)
      }
    }
    var index = 0
    t.clearUnits()
    for (timeUnit in units) {
      if (timeUnit !is JustNow) {
        t.registerUnit(timeUnit, formats[index])
        index++
      }
    }
    return t
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra poco", t.format(Date(6000)))
  }

  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("poco fa", t.format(Date(0)))
  }

  @Test

  fun testMilliSecondsFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("fra 13 millisecondi", t.format(Date(13)))
  }

  @Test

  fun testMilliSecondsAgo() {
    val t = newPrettyTimeWOJustNow(Date(13), locale)
    assertEquals("13 millisecondi fa", t.format(Date(0)))
  }

  @Test

  fun testMilliSecondFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("fra 1 millisecondo", t.format(Date(1)))
  }

  @Test

  fun testMilliSecondAgo() {
    val t = newPrettyTimeWOJustNow(Date(1), locale)
    assertEquals("1 millisecondo fa", t.format(Date(0)))
  }

  @Test

  fun testSecondsFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("fra 13 secondi", t.format(Date((1000 * 13).toLong())))
  }

  @Test

  fun testSecondsAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 13).toLong()), locale)
    assertEquals("13 secondi fa", t.format(Date(0)))
  }

  @Test

  fun testSecondFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("fra 1 secondo", t.format(Date((1000 * 1).toLong())))
  }

  @Test

  fun testSecondAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 1).toLong()), locale)
    assertEquals("1 secondo fa", t.format(Date(0)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 13 minuti", t.format(Date((1000 * 60 * 13).toLong())))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 13).toLong()), locale)
    assertEquals("13 minuti fa", t.format(Date(0)))
  }

  @Test

  fun testMinuteFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("fra 1 minuto", t.format(Date((1000 * 60 * 1).toLong())))
  }

  @Test

  fun testMinuteAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 60 * 1).toLong()), locale)
    assertEquals("1 minuto fa", t.format(Date(0)))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 ore", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 ore fa", t.format(Date(0)))
  }

  @Test

  fun testHoursFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 ora", t.format(Date((1000 * 60 * 60 * 1).toLong())))
  }

  @Test

  fun testHoursAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 1).toLong()), locale)
    assertEquals("1 ora fa", t.format(Date(0)))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 giorni", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 giorni fa", t.format(Date(0)))
  }

  @Test

  fun testDaysFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 giorno", t.format(Date((1000 * 60 * 60 * 24 * 1).toLong())))
  }

  @Test

  fun testDaysAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 1).toLong()), locale)
    assertEquals("1 giorno fa", t.format(Date(0)))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 settimane", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 settimane fa", t.format(Date(0)))
  }

  @Test

  fun testWeeksFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 settimana", t.format(Date((1000 * 60 * 60 * 24 * 7 * 1).toLong())))
  }

  @Test

  fun testWeeksAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 1).toLong()), locale)
    assertEquals("1 settimana fa", t.format(Date(0)))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 mesi", t.format(Date(1000L * 60 * 60 * 24 * 30 * 3)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 30 * 3), locale)
    assertEquals("3 mesi fa", t.format(Date(0)))
  }

  @Test

  fun testMonthFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 mese", t.format(Date(1000L * 60 * 60 * 24 * 30 * 1)))
  }

  @Test

  fun testMonthAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 30 * 1), locale)
    assertEquals("1 mese fa", t.format(Date(0)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 anni", t.format(Date(1000L * 60 * 60 * 24 * 365 * 3)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 3), locale)
    assertEquals("3 anni fa", t.format(Date(0)))
  }

  @Test

  fun testYearFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 anno", t.format(Date(1000L * 60 * 60 * 24 * 366 * 1)))
  }

  @Test

  fun testYearAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 366 * 1), locale)
    assertEquals("1 anno fa", t.format(Date(0)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 decenni", t.format(Date(1000L * 60 * 60 * 24 * 365 * 10 * 3)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 10 * 3), locale)
    assertEquals("3 decenni fa", t.format(Date(0)))
  }

  @Test

  fun testDecadeFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 decennio", t.format(Date(1000L * 60 * 60 * 24 * 365 * 11 * 1)))
  }

  @Test

  fun testDecadeAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 11), locale)
    assertEquals("1 decennio fa", t.format(Date(0)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 secoli", t.format(Date(1000L * 60 * 60 * 24 * 365 * 100 * 3)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 100 * 3), locale)
    assertEquals("3 secoli fa", t.format(Date(0)))
  }

  @Test

  fun testCenturyFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 secolo", t.format(Date(1000L * 60 * 60 * 24 * 365 * 101)))
  }

  @Test

  fun testCenturyAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 101), locale)
    assertEquals("1 secolo fa", t.format(Date(0)))
  }

  @Test

  fun testMillenniaFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 3 millenni", t.format(Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3)))
  }

  @Test

  fun testMillenniaAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3), locale)
    assertEquals("3 millenni fa", t.format(Date(0)))
  }

  @Test

  fun testMillenniumFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("fra 1 millennio", t.format(Date(1000L * 60 * 60 * 24 * 365 * 1001)))
  }

  @Test

  fun testMillenniumAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 1001), locale)
    assertEquals("1 millennio fa", t.format(Date(0)))
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()), locale)
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("3 giorni 15 ore 38 minuti fa", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0), locale)
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("fra 3 giorni 15 ore 38 minuti", t.format(durations))
  }
}
