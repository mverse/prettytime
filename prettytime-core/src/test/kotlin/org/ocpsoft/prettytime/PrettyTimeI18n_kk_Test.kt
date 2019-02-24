package org.ocpsoft.prettytime

import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import java.time.Duration.ofSeconds
import java.time.Instant

/**
 * Created by Azimkhan Yerzhan on 5/8/2017
 */
class PrettyTimeI18n_kk_Test {
  private val format = SimpleDateFormat("dd/MM/yyyy")
  private lateinit var locale: Locale

  @Before fun setUp() {
    locale = Locale("kk")
    Locale.setDefault(locale)
  }

  @Test
  fun testPrettyTime() {
    val p = PrettyTime(locale)
    assertEquals("дәл қазір", p.format(Date()))
  }

  @Test
  fun testPrettyTimeCenturies() {
    var p = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 ғасыр бұрын", p.format(Date(0)))

    p = PrettyTime(Date(0), locale)
    assertEquals("3 ғасырдан кейін", p.format(Date(3155692597470L * 3L)))
  }

  @Test fun testCeilingInterval() {
    val then = format.parse("20/5/2009")
    val ref = format.parse("17/6/2009")
    val t = PrettyTime(ref, locale)
    assertEquals("1 ай бұрын", t.format(then))
  }

  @Test fun testRightNow() {
    val t = PrettyTime(locale)
    assertEquals("дәл қазір", t.format(Instant.now()))
  }

  @Test fun testRightNowVariance() {
    val now = Instant.now()
    val t = PrettyTime(locale = locale, reference = now)
    assertEquals("дәл қазір", t.format(now + ofSeconds(1)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("12 минуттан кейін", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 сағаттан кейін", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 күннен кейін", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 аптадан кейін", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 айдан кейін", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 жылдан кейін", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 онжылдықтан кейін", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 ғасырдан кейін", t.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testMillenniaFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 мыңжылдықтан кейін", t.format(Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3)))
  }

  /*
     * Past
     */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("жана ғана", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()), locale)
    assertEquals("12 минут бұрын", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 сағат бұрын", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 күн бұрын", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 апта бұрын", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L), locale)
    assertEquals("3 ай бұрын", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L), locale)
    assertEquals("3 жыл бұрын", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L), locale)
    assertEquals("3 онжылдық бұрын", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 ғасыр бұрын", t.format(Date(0)))
  }

  @Test

  fun testMillenniaAgo() {
    val t = PrettyTime(Date(3155692597470L * 10 * 3L), locale)
    // 3 millennia ago
    assertEquals("3 мыңжылдық бұрын", t.format(Date(0)))
  }

  companion object {
    // Method setUp() is called automatically before every test method
    @BeforeClass @AfterClass fun setUp() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}
