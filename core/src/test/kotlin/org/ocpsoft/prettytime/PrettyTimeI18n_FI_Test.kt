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

class PrettyTimeI18n_FI_Test {
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("fi")
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
    assertEquals("hetken päästä", t.format(Date(6000)))
  }

  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("hetki sitten", t.format(Date(0)))
  }

  @Test

  fun testMilliSecondsFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("13 millisekunnin päästä", t.format(Date(13)))
  }

  @Test

  fun testMilliSecondsAgo() {
    val t = newPrettyTimeWOJustNow(Date(13), locale)
    assertEquals("13 millisekuntia sitten", t.format(Date(0)))
  }

  @Test

  fun testMilliSecondFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("millisekunnin päästä", t.format(Date(1)))
  }

  @Test

  fun testMilliSecondAgo() {
    val t = newPrettyTimeWOJustNow(Date(1), locale)
    assertEquals("millisekunti sitten", t.format(Date(0)))
  }

  @Test

  fun testSecondsFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("13 sekunnin päästä", t.format(Date((1000 * 13).toLong())))
  }

  @Test

  fun testSecondsAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 13).toLong()), locale)
    assertEquals("13 sekuntia sitten", t.format(Date(0)))
  }

  @Test

  fun testSecondFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("sekunnin päästä", t.format(Date((1000 * 1).toLong())))
  }

  @Test

  fun testSecondAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 1).toLong()), locale)
    assertEquals("sekunti sitten", t.format(Date(0)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("13 minuutin päästä", t.format(Date((1000 * 60 * 13).toLong())))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 13).toLong()), locale)
    assertEquals("13 minuuttia sitten", t.format(Date(0)))
  }

  @Test

  fun testMinuteFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("minuutin päästä", t.format(Date((1000 * 60 * 1).toLong())))
  }

  @Test

  fun testMinuteAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 60 * 1).toLong()), locale)
    assertEquals("minuutti sitten", t.format(Date(0)))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 tunnin päästä", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 tuntia sitten", t.format(Date(0)))
  }

  @Test

  fun testHoursFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("tunnin päästä", t.format(Date((1000 * 60 * 60 * 1).toLong())))
  }

  @Test

  fun testHoursAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 1).toLong()), locale)
    assertEquals("tunti sitten", t.format(Date(0)))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 päivän päästä", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testNear2DaysAgo() {
    val hour = 1000 * 60 * 60
    var t = PrettyTime(Date((hour * 24 * 2 - 11 * hour).toLong()), locale)
    assertEquals("2 päivää sitten", t.format(Date(0)))
    t = PrettyTime(Date((hour * 24 * 2 - 13 * hour).toLong()), locale)
    assertEquals("eilen", t.format(Date(0)))
  }

  @Test

  fun test3DaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 päivää sitten", t.format(Date(0)))
  }

  @Test

  fun testDaysFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("huomenna", t.format(Date((1000 * 60 * 60 * 24 * 1).toLong())))
  }

  @Test

  fun testDaysAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 1).toLong()), locale)
    assertEquals("eilen", t.format(Date(0)))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 viikon päästä", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 viikkoa sitten", t.format(Date(0)))
  }

  @Test

  fun testWeeksFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("viikon päästä", t.format(Date((1000 * 60 * 60 * 24 * 7 * 1).toLong())))
  }

  @Test

  fun testWeeksAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 1).toLong()), locale)
    assertEquals("viikko sitten", t.format(Date(0)))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 kuukauden päästä", t.format(Date(1000L * 60 * 60 * 24 * 30 * 3)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 30 * 3), locale)
    assertEquals("3 kuukautta sitten", t.format(Date(0)))
  }

  @Test

  fun testMonthFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("kuukauden päästä", t.format(Date(1000L * 60 * 60 * 24 * 30 * 1)))
  }

  @Test

  fun testMonthAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 30 * 1), locale)
    assertEquals("kuukausi sitten", t.format(Date(0)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 vuoden päästä", t.format(Date(1000L * 60 * 60 * 24 * 365 * 3)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 3), locale)
    assertEquals("3 vuotta sitten", t.format(Date(0)))
  }

  @Test

  fun testYearFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("vuoden päästä", t.format(Date(1000L * 60 * 60 * 24 * 366 * 1)))
  }

  @Test

  fun testYearAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 366 * 1), locale)
    assertEquals("vuosi sitten", t.format(Date(0)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 vuosikymmenen päästä", t.format(Date(1000L * 60 * 60 * 24 * 365 * 10 * 3)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 10 * 3), locale)
    assertEquals("3 vuosikymmentä sitten", t.format(Date(0)))
  }

  @Test

  fun testDecadeFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("vuosikymmenen päästä", t.format(Date(1000L * 60 * 60 * 24 * 365 * 11 * 1)))
  }

  @Test

  fun testDecadeAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 11), locale)
    assertEquals("vuosikymmen sitten", t.format(Date(0)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 vuosisadan päästä", t.format(Date(1000L * 60 * 60 * 24 * 365 * 100 * 3)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 100 * 3), locale)
    assertEquals("3 vuosisataa sitten", t.format(Date(0)))
  }

  @Test

  fun testCenturyFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("vuosisadan päästä", t.format(Date(1000L * 60 * 60 * 24 * 365 * 101)))
  }

  @Test

  fun testCenturyAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 101), locale)
    assertEquals("vuosisata sitten", t.format(Date(0)))
  }

  @Test

  fun testMillenniaFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 vuosituhannen päästä", t.format(Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3)))
  }

  @Test

  fun testMillenniaAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3), locale)
    assertEquals("3 vuosituhatta sitten", t.format(Date(0)))
  }

  @Test

  fun testMillenniumFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("vuosituhannen päästä", t.format(Date(1000L * 60 * 60 * 24 * 365 * 1001)))
  }

  @Test

  fun testMillenniumAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 1001), locale)
    assertEquals("vuosituhat sitten", t.format(Date(0)))
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()), locale)
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("3 päivää 15 tuntia 38 minuuttia sitten", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0), locale)
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("3 päivän 15 tunnin 38 minuutin päästä", t.format(durations))
  }
}
