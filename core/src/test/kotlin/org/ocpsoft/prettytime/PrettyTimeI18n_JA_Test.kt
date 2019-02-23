package org.ocpsoft.prettytime

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Most languages (using the standard %n %u pattern) will render something like: {prefix} {number} {unitName} {suffix}.
 */
class PrettyTimeI18n_JA_Test {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  private lateinit var locale: Locale

  @Before
  fun setUp() {
    locale = Locale("ja")
    Locale.setDefault(locale)
  }

  fun testNullDate() {
    val t = PrettyTime(locale)
    val date: Date? = null
    // moments from now
    assertEquals("今からすぐ", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime(locale)
    // moments from now
    assertEquals("今からすぐ", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0), locale)
    // moments from now
    assertEquals("今からすぐ", t.format(Date(600)))
  }

  @Test

  fun testMillisecondsFromNow() {
    val t = PrettyTime(Date(0), locale)
    t.removeUnit(org.ocpsoft.prettytime.units.JustNow::class.java)
    // 450 milliseconds from now
    assertEquals("450 milliseconds from now", "今から450ミリ秒後", t.format(Date(450L)))
  }

  @Test

  fun testSecondsFromNow() {
    val t = PrettyTime(Date(0), locale)
    t.removeUnit(org.ocpsoft.prettytime.units.JustNow::class.java)
    // 36 seconds from now
    assertEquals("36 seconds from now", "今から36秒後", t.format(Date(1000L * 36L)))
    t.removeUnit(org.ocpsoft.prettytime.units.Millisecond::class.java)
    // 1 second from now
    assertEquals("1 second from now", "今から1秒後", t.format(Date(10)))
    // 1 second from now
    assertEquals("1 second from now", "今から1秒後", t.formatUnrounded(Date(10)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 12 minutes from now
    assertEquals("12 minutes from now", "今から12分後", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 3 hours from now
    assertEquals("3 hours from now", "今から3時間後", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 3 days from now
    assertEquals("3 days from now", "今から3日後", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 3 weeks from now
    assertEquals("3 weeks from now", "今から3週間後", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 3 months from now
    assertEquals("3 months from now", "今から3ヶ月後", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 3 years from now
    assertEquals("3 years from now", "今から3年後", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testOneDecadeFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 1 decade from now
    assertEquals("今から10年後", t.format(Date(315569259747L * 1L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 3 centuries from now
    assertEquals("3 centuries from now", "今から3世紀後", t.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testMillenniumFromNow() {
    val t = PrettyTime(Date(0), locale)
    // 3 millennia from now
    assertEquals("3 millennia from now", "今から3000年後", t.format(Date(3155692597470L * 10L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    // moments ago
    assertEquals("たった今", t.format(Date(0)))
  }

  @Test

  fun testMillisecondsAgo() {
    val t = PrettyTime(Date(450L), locale)
    t.removeUnit(org.ocpsoft.prettytime.units.JustNow::class.java)
    // 450 milliseconds ago
    assertEquals("450 milliseconds ago", "450ミリ秒前", t.format(Date(0)))
  }

  @Test

  fun testSecondsAgo() {
    val t = PrettyTime(Date((1000 * 36).toLong()), locale)
    t.removeUnit(org.ocpsoft.prettytime.units.JustNow::class.java)
    // 36 seconds ago
    assertEquals("36 seconds ago", "36秒前", t.format(Date(0)))
    t.reference = Date(10).toInstant()
    t.removeUnit(org.ocpsoft.prettytime.units.Millisecond::class.java)
    // 1 second ago
    assertEquals("1 second ago", "1秒前", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()), locale)
    // 12 minutes ago
    assertEquals("12分前", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    // 3 hours ago
    assertEquals("3時間前", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    // 3 days ago
    assertEquals("3日前", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    // 3 weeks ago
    assertEquals("3週間前", t.format(Date(0)))
  }

  @Test

  fun testOneMonthAgo() {
    val t = PrettyTime(Date(2629743830L * 1L), locale)
    // 3 months ago
    assertEquals("1 months ago", "1ヶ月前", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L), locale)
    // 3 months ago
    assertEquals("3 months ago", "3ヶ月前", t.format(Date(0)))
  }

  @Test

  fun testCustomFormat() {
    val t = PrettyTime(Date(0), locale)
    val unit = object : TimeUnit {
      override val maxQuantity: Long
        get() = 0

      override val millisPerUnit: Long
        get() = 5000

      override val isPrecise: Boolean
        get() = false
    }
    t.clearUnits()
    t.registerUnit(unit, SimpleTimeFormat()
        .setSingularName("tick")
        .setPluralName("ticks")
        .setPattern("%n %u").setRoundingTolerance(20)
        .setFutureSuffix("... RUN!")
        .setFuturePrefix("self destruct in: ").setPastPrefix("self destruct was: ").setPastSuffix(
            " ago..."))

    assertEquals("self destruct in: 5 ticks ... RUN!", t.format(Date(25000)))
    t.reference = Date(25000).toInstant()
    assertEquals("self destruct was: 5 ticks ago...", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L), locale)
    // 3 years ago
    assertEquals("3年前", t.format(Date(0)))
  }

  @Test

  fun testDecadeAgo() {
    val t = PrettyTime(Date(315569259747L * 1L), locale)
    // 1 decade ago
    assertEquals("10年前", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L), locale)
    // 3 centuries ago
    assertEquals("3 centuries ago", "3世紀前", t.format(Date(0)))
  }

  @Test

  fun testMilleniumAgo() {
    val t = PrettyTime(Date(3155692597470L * 10 * 3L), locale)
    // 3 millennia ago
    assertEquals("3 millennia ago", "3000年前", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime(locale)
    // 2 hours ago
    assertEquals("2時間前", t.format(Date(Date().time - 6543990)))
  }

  @Test

  fun testPreciseInTheFuture() {
    val t = PrettyTime(locale)
    val durations = t.calculatePreciseDuration(Date(Date().time + 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2)
    assertEquals(5, durations[0].quantity)
    assertEquals(10, durations[1].quantity)
  }

  @Test

  fun testPreciseInThePast() {
    val t = PrettyTime(locale)
    val durations = t.calculatePreciseDuration(Date(Date().time - 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2)
    assertEquals(-5, durations[0].quantity)
    assertEquals(-10, durations[1].quantity)
  }

  @Test

  fun testSetLocale() {
    val t = PrettyTime(Date(315569259747L * 1L), locale)
    // 1 decade ago
    assertEquals("10年前", t.format(Date(0)))
    t.locale = Locale.GERMAN
    assertEquals("vor 1 Jahrzehnt", t.format(Date(0)))
  }

  /**
   * Tests formatApproximateDuration and by proxy, formatDuration.
   *
   * @throws Exception
   */
  @Test

  fun testFormatApproximateDuration() {
    val tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10)
    val tenMinAgo = Date(System.currentTimeMillis() - tenMinMillis)
    val t = PrettyTime()
    val result = t.formatDuration(tenMinAgo)
    // 10 minutes
    assert(result == "10分")
  }
}
