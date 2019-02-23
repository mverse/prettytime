package org.ocpsoft.prettytime

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat

class PrettyTimeI18n_in_ID_Test {

  internal var format = SimpleDateFormat("MM/dd/yyyy")
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("in", "ID")
    Locale.setDefault(locale!!)
  }

  @Test
  fun testLocaleISOCorrectness() {
    Assert.assertEquals("in", this.locale!!.language)
    Assert.assertEquals("ID", this.locale!!.country)
    Assert.assertEquals("Bahasa Indonesia", this.locale!!.displayLanguage)
    Assert.assertEquals("Indonesia", this.locale!!.displayCountry)
  }

  @Test
  fun testNow() {
    val prettyTime = PrettyTime(locale)
    prettyTime.format(Date())
    Assert.assertEquals("dari sekarang", prettyTime.format(Date()))
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(ref)
    Assert.assertEquals("1 bulan yang lalu", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime()
    val date: Date? = null
    Assert.assertEquals("dari sekarang", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    Assert.assertEquals("dari sekarang", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("dari sekarang", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("12 menit dari sekarang", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 jam dari sekarang", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 hari dari sekarang",
        t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 minggu dari sekarang",
        t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 bulan dari sekarang", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 tahun dari sekarang",
        t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 dekade dari sekarang",
        t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 abad dari sekarang",
        t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    Assert.assertEquals("yang lalu", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    Assert.assertEquals("12 menit yang lalu", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    Assert.assertEquals("3 jam yang lalu", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    Assert.assertEquals("3 hari yang lalu", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    Assert.assertEquals("3 minggu yang lalu", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    Assert.assertEquals("3 bulan yang lalu", t.format(Date(0)))
  }

  @Test

  fun testCustomFormat() {
    val t = PrettyTime(Date(0))
    val unit = object : TimeUnit {
      override val maxQuantity: Long
        get() = 0

      override val millisPerUnit: Long
        get() = 5000

      override val isPrecise: Boolean
        get() = false
    }
    t.clearUnits()
    t.registerUnit(
        unit,
        SimpleTimeFormat()
            .setPluralName("hitungan").setPattern("%n %u")
            .setRoundingTolerance(20).setFutureSuffix("... LARI!")
            .setFuturePrefix("hancur dalam: ")
            .setPastPrefix("telah hancur dalam: ")
            .setPastSuffix(""))

    Assert.assertEquals("hancur dalam: 5 hitungan ... LARI!",
        t.format(Date(25000)))
    t.reference = Date(25000).toInstant()
    Assert.assertEquals("telah hancur dalam: 5 hitungan", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    Assert.assertEquals("3 tahun yang lalu", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    Assert.assertEquals("3 dekade yang lalu", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    Assert.assertEquals("3 abad yang lalu", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    Assert.assertEquals("2 jam yang lalu",
        t.format(Date(Date().time - 6543990)))
  }

  @Test

  fun testPreciseInTheFuture() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(
        Date().time + 1000 * (10 * 60 + 5 * 60 * 60)))
    Assert.assertTrue(durations.size >= 2)
    Assert.assertEquals(5, durations[0].quantity)
    Assert.assertEquals(10, durations[1].quantity)
  }

  @Test

  fun testPreciseInThePast() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(
        Date().time - 1000 * (10 * 60 + 5 * 60 * 60)))
    Assert.assertTrue(durations.size >= 2)
    Assert.assertEquals(-5, durations[0].quantity)
    Assert.assertEquals(-10, durations[1].quantity)
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + (1000
        * 60 * 60 * 15) + 1000 * 60 * 38).toLong()))
    val durations = t.calculatePreciseDuration(Date(0))
    Assert.assertEquals("3 hari 15 jam 38 menit yang lalu", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date(((1000
        * 60 * 60 * 24 * 3) + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    Assert.assertEquals("3 hari 15 jam 38 menit dari sekarang", t.format(durations))
  }

  @After

  fun tearDown() {
    Locale.setDefault(Locale.ENGLISH)
  }
}
