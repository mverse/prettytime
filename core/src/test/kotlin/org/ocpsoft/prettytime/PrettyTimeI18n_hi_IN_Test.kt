package org.ocpsoft.prettytime

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import java.text.SimpleDateFormat
import java.util.*

class PrettyTimeI18n_hi_IN_Test {

  internal var format = SimpleDateFormat("MM/dd/yyyy")
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("hi", "IN")
    Locale.setDefault(locale!!)
  }

  @Test
  fun testLocaleISOCorrectness() {
    assertEquals("hi", this.locale!!.language)
    assertEquals("IN", this.locale!!.country)
    assertEquals("हिंदी", this.locale!!.displayLanguage)
    assertEquals("भारत", this.locale!!.displayCountry)
  }

  @Test
  fun testNow() {
    val prettyTime = PrettyTime(locale)
    prettyTime.format(Date())
    assertEquals("अभी", prettyTime.format(Date()))
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(ref)
    assertEquals("1 महीना पहले", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime()
    val date: Date? = null
    assertEquals("अभी", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    assertEquals("अभी", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    assertEquals("अभी", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("12 मिनट बाद", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 घंटे बाद", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 दिन बाद",
        t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 सप्ताह बाद",
        t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 महीने बाद", t.format(Date(2629743830L * 3L)))
    // assertEquals("अभी से 3 महीने बाद", t.format(new Date(1000 * 60 * 60 * 24 * 365 * 3L)));
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 वर्ष बाद",
        t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 दशक बाद",
        t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 सदियों बाद",
        t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    assertEquals("अभी", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    assertEquals("12 मिनट पहले", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    assertEquals("3 घंटे पहले", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    assertEquals("3 दिन पहले", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    assertEquals("3 सप्ताह पहले", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    assertEquals("3 महीने पहले", t.format(Date(0)))
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
        SimpleTimeFormat().apply {
          singularName = "खेल"
        }
            .setPluralName("खेल")
            .setPattern("%n %u")
            .setRoundingTolerance(20)
            .setFutureSuffix("होंगे ")
            .setFuturePrefix("भविष्य में ")
            .setPastPrefix("पहले ")
            .setPastSuffix("थे"))

    assertEquals("भविष्य में 5 खेल होंगे",
        t.format(Date(25000)))
    t.reference = Date(25000).toInstant()
    assertEquals("पहले 5 खेल थे", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    assertEquals("3 वर्ष पहले", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("3 दशक पहले", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    assertEquals("3 सदियों पहले", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    assertEquals("2 घंटे पहले",
        t.format(Date(Date().time - 6543990)))
  }

  @Test

  fun testPreciseInTheFuture() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(
        Date().time + 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2) // might be more because of
    // milliseconds between date
    // capturing and result
    // calculation
    assertEquals(5, durations[0].quantity)
    assertEquals(10, durations[1].quantity)
  }

  @Test

  fun testPreciseInThePast() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(
        Date().time - 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2) // might be more because of
    // milliseconds between date
    // capturing and result
    // calculation
    assertEquals(-5, durations[0].quantity)
    assertEquals(-10, durations[1].quantity)
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + (1000
        * 60 * 60 * 15) + 1000 * 60 * 38).toLong()))
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("3 दिन 15 घंटे 38 मिनट पहले", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date(((1000
        * 60 * 60 * 24 * 3) + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    assertEquals("3 दिन 15 घंटे 38 मिनट बाद", t.format(durations))
  }

  // End of duplicate test -- //

  @After

  fun tearDown() {
    Locale.setDefault(Locale.ENGLISH)
  }
}

