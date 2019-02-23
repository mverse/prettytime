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
class PrettyTimeI18n_zh_TW_Test {
  private val format = SimpleDateFormat("dd/MM/yyyy")
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale.TRADITIONAL_CHINESE
    Locale.setDefault(Locale.TRADITIONAL_CHINESE)
  }

  @Test
  fun testPrettyTime() {
    val p = PrettyTime(locale)
    assertEquals("剛剛", p.format(Date()))
  }

  @Test
  fun testPrettyTimeCenturies() {
    var p = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 世紀 前", p.format(Date(0)))

    p = PrettyTime(Date(0), locale)
    assertEquals("3 世紀 後", p.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("20/5/2009")
    val ref = format.parse("17/6/2009")
    val t = PrettyTime(ref, locale)
    assertEquals("1 個月 前", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime(locale)
    val date: Date? = null
    assertEquals("剛剛", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime(locale)
    assertEquals("剛剛", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("剛剛", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("12 分鐘 後", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 小時 後", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 天 後", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 週 後", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 個月 後", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 年 後", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("30 年 後", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 世紀 後", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("片刻之前", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()), locale)
    assertEquals("12 分鐘 前", t.format(Date(0)))
  }

  @Test

  fun test1HourAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 1).toLong()), locale)
    assertEquals("1 小時 前", t.format(Date(0)))
  }

  @Test

  fun test3HoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 小時 前", t.format(Date(0)))
  }

  @Test

  fun test6HoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 6).toLong()), locale)
    assertEquals("6 小時 前", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 天 前", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 週 前", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L), locale)
    assertEquals("3 個月 前", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L), locale)
    assertEquals("3 年 前", t.format(Date(0)))
  }

  @Test

  fun test8YearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 8L), locale)
    assertEquals("8 年 前", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L), locale)
    assertEquals("30 年 前", t.format(Date(0)))
  }

  @Test

  fun test8DecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 8L), locale)
    assertEquals("80 年 前", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 世紀 前", t.format(Date(0)))
  }

  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
