package org.ocpsoft.prettytime

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import org.junit.Assert.assertEquals

class PrettyTimeI18n_TH_Test {
  internal var format = SimpleDateFormat("MM/dd/yyyy")
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("TH")
    Locale.setDefault(locale!!)
  }

  @Test
  fun testLocaleISOCorrectness() {
    assertEquals("th", this.locale!!.language)
    assertEquals("ไทย", this.locale!!.displayLanguage)
  }

  @Test
  fun testFromNow() {
    val prettyTime = PrettyTime(locale)
    assertEquals("ชั่วขณะต่อจากนี้้ี้้", prettyTime.format(Date()))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime()
    val date: Date? = null
    assertEquals("ชั่วขณะต่อจากนี้้ี้้", t.format(date))
  }

  @Test
  fun testPrettyTimeDefault() {
    val p = PrettyTime(locale)
    assertEquals(p.format(Date()), "ชั่วขณะต่อจากนี้้ี้้")
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("12 นาที ต่อจากนี้ี้", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 ชั่วโมง ต่อจากนี้", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 วัน ต่อจากนี้ี้",
        t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 อาทิตย์ ต่อจากนี้ี้",
        t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 เดือน ต่อจากนี้", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 ศตวรรษ ต่อจากนี้",
        t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    assertEquals("ชั่วขณะก่อน", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    assertEquals("12 นาที ก่อน", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    assertEquals("3 ชั่วโมง ก่อน", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    assertEquals("3 วัน ก่อน", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    assertEquals("3 อาทิตย์ ก่อน", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    assertEquals("3 เดือน ก่อน", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("3 ทศวรรษ ก่อน", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    assertEquals("3 ศตวรรษ ก่อน", t.format(Date(0)))
  }

  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
