package org.ocpsoft.prettytime

import org.junit.Assert.assertEquals

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by edward_chiang on 13/6/27.
 */
class PrettyTimeI18n_MS_Test {
  private val format = SimpleDateFormat("dd/MM/yyyy")
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("ms")
    Locale.setDefault(Locale("ms"))
  }

  @Test
  fun testPrettyTime() {
    val p = PrettyTime(locale)
    assertEquals("tadi", p.format(Date()))
  }

  @Test
  fun testPrettyTimeCenturies() {
    var p = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 abad yang lalu", p.format(Date(0)))

    p = PrettyTime(Date(0), locale)
    assertEquals("3 abad kemudian", p.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("20/5/2009")
    val ref = format.parse("17/6/2009")
    val t = PrettyTime(ref, locale)
    assertEquals("1 bulan yang lalu", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime(locale)
    val date: Date? = null
    assertEquals("tadi", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime(locale)
    assertEquals("tadi", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("tadi", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("12 minit kemudian", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 jam kemudian", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 hari kemudian", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 minggu kemudian", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 bulan kemudian", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 tahun kemudian", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("30 tahun kemudian", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 abad kemudian", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("tadi", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()), locale)
    assertEquals("12 minit yang lalu", t.format(Date(0)))
  }

  @Test

  fun test1HourAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 1).toLong()), locale)
    assertEquals("1 jam yang lalu", t.format(Date(0)))
  }

  @Test

  fun test3HoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 jam yang lalu", t.format(Date(0)))
  }

  @Test

  fun test6HoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 6).toLong()), locale)
    assertEquals("6 jam yang lalu", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 hari yang lalu", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 minggu yang lalu", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L), locale)
    assertEquals("3 bulan yang lalu", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L), locale)
    assertEquals("3 tahun yang lalu", t.format(Date(0)))
  }

  @Test

  fun test8YearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 8L), locale)
    assertEquals("8 tahun yang lalu", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L), locale)
    assertEquals("30 tahun yang lalu", t.format(Date(0)))
  }

  @Test

  fun test8DecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 8L), locale)
    assertEquals("80 tahun yang lalu", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 abad yang lalu", t.format(Date(0)))
  }

  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
