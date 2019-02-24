package org.ocpsoft.prettytime

import org.junit.Assert.*

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test

class PrettyTimeI18n_BG_Test {

  internal var format = SimpleDateFormat("dd/MM/yyyy")

  @Before fun setUp() {
    Locale.setDefault(Locale("bg"))
  }

  @Test fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 3 века", t.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    assertEquals("преди 3 века", t.format(Date(0)))
  }

  @Test

  fun testCenturySingular() {
    val t = PrettyTime(Date(3155692597470L))
    assertEquals("преди 1 век", t.format(Date(0)))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 3 дни", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    assertEquals("преди 3 дни", t.format(Date(0)))
  }

  @Test

  fun testDaySingular() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24).toLong()))
    assertEquals("преди 1 ден", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("преди 3 десетилетия", t.format(Date(0)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 3 десетилетия", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testDecadeSingular() {
    val t = PrettyTime(Date(0))
    assertEquals("след 1 десетилетие", t.format(Date(315569259747L)))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 3 часа", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    assertEquals("преди 3 часа", t.format(Date(0)))
  }

  @Test

  fun testHourSingular() {
    val t = PrettyTime(Date((1000 * 60 * 60).toLong()))
    assertEquals("преди 1 час", t.format(Date(0)))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    assertEquals("в момента", t.format(Date()))
  }

  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    assertEquals("току що", t.format(Date(0)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 12 минути", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    assertEquals("преди 12 минути", t.format(Date(0)))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 3 месеца", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    assertEquals("преди 3 месеца", t.format(Date(0)))
  }

  @Test

  fun testMonthSingular() {
    val t = PrettyTime(Date(2629743830L))
    assertEquals("преди 1 месец", t.format(Date(0)))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 3 седмици", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    assertEquals("преди 3 седмици", t.format(Date(0)))
  }

  @Test

  fun testWeekSingular() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7).toLong()))
    assertEquals("преди 1 седмица", t.format(Date(0)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("след 3 години", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    assertEquals("преди 3 години", t.format(Date(0)))
  }

  @Test

  fun testYearSingular() {
    val t = PrettyTime(Date(2629743830L * 12L))
    assertEquals("преди 1 година", t.format(Date(0)))
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("преди 3 дни 15 часа 38 минути", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("след 3 дни 15 часа 38 минути", t.format(durations))
  }

  companion object {
    @AfterClass fun resetLocale() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}
