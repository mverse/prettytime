package org.ocpsoft.prettytime

import org.junit.Assert.assertEquals

import java.util.ArrayList
import java.util.Date
import java.util.Locale

import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.units.JustNow

class PrettyTimeI18n_ET_Test {
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("et")
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
    assertEquals("hetke pärast", t.format(Date(6000)))
  }

  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("hetk tagasi", t.format(Date(0)))
  }

  @Test

  fun testMilliSecondsFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("13 millisekundi pärast", t.format(Date(13)))
  }

  @Test

  fun testMilliSecondsAgo() {
    val t = newPrettyTimeWOJustNow(Date(13), locale)
    assertEquals("13 millisekundit tagasi", t.format(Date(0)))
  }

  @Test

  fun testMilliSecondFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("millisekundi pärast", t.format(Date(1)))
  }

  @Test

  fun testMilliSecondAgo() {
    val t = newPrettyTimeWOJustNow(Date(1), locale)
    assertEquals("millisekund tagasi", t.format(Date(0)))
  }

  @Test

  fun testSecondsFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("13 sekundi pärast", t.format(Date((1000 * 13).toLong())))
  }

  @Test

  fun testSecondsAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 13).toLong()), locale)
    assertEquals("13 sekundit tagasi", t.format(Date(0)))
  }

  @Test

  fun testSecondFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("sekundi pärast", t.format(Date((1000 * 1).toLong())))
  }

  @Test

  fun testSecondAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 1).toLong()), locale)
    assertEquals("sekund tagasi", t.format(Date(0)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("13 minuti pärast", t.format(Date((1000 * 60 * 13).toLong())))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 13).toLong()), locale)
    assertEquals("13 minutit tagasi", t.format(Date(0)))
  }

  @Test

  fun testMinuteFromNow() {
    val t = newPrettyTimeWOJustNow(Date(0), locale)
    assertEquals("minuti pärast", t.format(Date((1000 * 60 * 1).toLong())))
  }

  @Test

  fun testMinuteAgo() {
    val t = newPrettyTimeWOJustNow(Date((1000 * 60 * 1).toLong()), locale)
    assertEquals("minut tagasi", t.format(Date(0)))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 tunni pärast", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 tundi tagasi", t.format(Date(0)))
  }

  @Test

  fun testHoursFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("tunni pärast", t.format(Date((1000 * 60 * 60 * 1).toLong())))
  }

  @Test

  fun testHoursAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 1).toLong()), locale)
    assertEquals("tund tagasi", t.format(Date(0)))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 päeva pärast", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 päeva tagasi", t.format(Date(0)))
  }

  @Test

  fun testDaysFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("homme", t.format(Date((1000 * 60 * 60 * 24 * 1).toLong())))
  }

  @Test

  fun testDaysAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 1).toLong()), locale)
    assertEquals("eile", t.format(Date(0)))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 nädala pärast", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 nädalat tagasi", t.format(Date(0)))
  }

  @Test

  fun testWeeksFromNowSingle() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("nädala pärast", t.format(Date((1000 * 60 * 60 * 24 * 7 * 1).toLong())))
  }

  @Test

  fun testWeeksAgoSingle() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 1).toLong()), locale)
    assertEquals("nädal tagasi", t.format(Date(0)))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 kuu pärast", t.format(Date(1000L * 60 * 60 * 24 * 30 * 3)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 30 * 3), locale)
    assertEquals("3 kuud tagasi", t.format(Date(0)))
  }

  @Test

  fun testMonthFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("kuu pärast", t.format(Date(1000L * 60 * 60 * 24 * 30 * 1)))
  }

  @Test

  fun testMonthAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 30 * 1), locale)
    assertEquals("kuu tagasi", t.format(Date(0)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 aasta pärast", t.format(Date(1000L * 60 * 60 * 24 * 365 * 3)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 3), locale)
    assertEquals("3 aastat tagasi", t.format(Date(0)))
  }

  @Test

  fun testYearFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("aasta pärast", t.format(Date(1000L * 60 * 60 * 24 * 366 * 1)))
  }

  @Test

  fun testYearAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 366 * 1), locale)
    assertEquals("aasta tagasi", t.format(Date(0)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 aastakümne pärast", t.format(Date(1000L * 60 * 60 * 24 * 365 * 10 * 3)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 10 * 3), locale)
    assertEquals("3 aastakümmet tagasi", t.format(Date(0)))
  }

  @Test

  fun testDecadeFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("aastakümne pärast", t.format(Date(1000L * 60 * 60 * 24 * 365 * 11 * 1)))
  }

  @Test

  fun testDecadeAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 11), locale)
    assertEquals("aastakümme tagasi", t.format(Date(0)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 sajandi pärast", t.format(Date(1000L * 60 * 60 * 24 * 365 * 100 * 3)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 100 * 3), locale)
    assertEquals("3 sajandit tagasi", t.format(Date(0)))
  }

  @Test

  fun testCenturyFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("sajandi pärast", t.format(Date(1000L * 60 * 60 * 24 * 365 * 101)))
  }

  @Test

  fun testCenturyAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 101), locale)
    assertEquals("sajand tagasi", t.format(Date(0)))
  }

  @Test

  fun testMillenniaFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 aastatuhande pärast", t.format(Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3)))
  }

  @Test

  fun testMillenniaAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3), locale)
    assertEquals("3 aastatuhandet tagasi", t.format(Date(0)))
  }

  @Test

  fun testMillenniumFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("aastatuhande pärast", t.format(Date(1000L * 60 * 60 * 24 * 365 * 1001)))
  }

  @Test

  fun testMillenniumAgo() {
    val t = PrettyTime(Date(1000L * 60 * 60 * 24 * 365 * 1001), locale)
    assertEquals("aastatuhat tagasi", t.format(Date(0)))
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()), locale)
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("3 päeva 15 tundi 38 minutit tagasi", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0), locale)
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("3 päeva 15 tunni 38 minuti pärast", t.format(durations))
  }
}
