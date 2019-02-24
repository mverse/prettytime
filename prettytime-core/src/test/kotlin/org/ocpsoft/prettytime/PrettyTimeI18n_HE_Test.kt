/**
 * @author ranmeirman
 */
package org.ocpsoft.prettytime

import org.junit.AfterClass
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import org.ocpsoft.prettytime.units.Minute
import java.text.SimpleDateFormat
import java.util.*

class PrettyTimeI18n_HE_Test {
  internal var format = SimpleDateFormat("dd/mm/yyyy")

  private lateinit var locale: Locale

  @Before
  fun setUp() {
    locale = Locale("he")
    Locale.setDefault(locale)
  }

  @Test fun testRightNow() {
    val t = PrettyTime()
    Assert.assertEquals("עוד רגע", t.format(Date()))
  }

  @Test fun testCalculatePreciseDuration3() {
    val t = PrettyTime()
    val preciseDuration = t.calculatePreciseDuration(
        Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000).toLong() - (2 * 60 * 1000).toLong()))
    Assert.assertEquals("לפני 2 שעות 2 דקות", t.format(preciseDuration))
    Assert.assertEquals("2 שעות 2 דקות", t.formatDuration(preciseDuration))
    Assert.assertEquals("עוד רגע", t.format(t.calculatePreciseDuration(Date())))
  }

  @Test

  fun testCalculatePreciseDurationMillenia() {
    val t = PrettyTime(Date(2014, 8, 15, 0, 0))
    val durations = t.calculatePreciseDuration(Date(0))
    Assert.assertEquals("לפני 1 מילניום 9 מאות 4 עשורים 4 שנים 8 חודשים 1 שבוע 6 ימים 20 שעות 5 דקות",
        t.format(durations))
    Assert.assertEquals("1 מילניום 9 מאות 4 עשורים 4 שנים 8 חודשים 1 שבוע 6 ימים 20 שעות 5 דקות",
        t.formatDuration(durations))
  }

  @Test
  fun testCalculatePreciseDuration2() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("לפני 40 דקות", prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
        Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("עוד רגע", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 12 דקות", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 3 שעות", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 3 ימים", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 3 שבועות", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 3 חודשים", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 3 שנים", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 3 עשורים", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("בעוד 3 מאות", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    Assert.assertEquals("לפני רגע", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    Assert.assertEquals("לפני 12 דקות", t.format(Date(0)))
  }

  @Test

  fun testMinutesFromNowDefaultReference() {
    val t = PrettyTime()
    Assert.assertEquals("בעוד 12 דקות", t.format(Date(System.currentTimeMillis() + 1000 * 60 * 12)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    Assert.assertEquals("לפני 3 שעות", t.format(Date(0)))
  }

  @Test

  fun testHoursAgoDefaultReference() {
    val t = PrettyTime()
    Assert.assertEquals("לפני 3 שעות", t.format(Date(System.currentTimeMillis() - 1000 * 60 * 60 * 3)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    Assert.assertEquals("לפני 3 ימים", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    Assert.assertEquals("לפני 3 שבועות", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    Assert.assertEquals("לפני 3 חודשים", t.format(Date(0)))
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
    t.registerUnit(unit, SimpleTimeFormat().apply {
      singularName = "tick"
      pluralName = "ticks"
      pattern = "%n %u"
      roundingTolerance = 20
      futureSuffix = "... RUN!"
      futurePrefix = "self destruct in: "
      pastPrefix = "self destruct was: "
      pastSuffix = " ago..."
    })

    Assert.assertEquals("self destruct in: 5 ticks ... RUN!", t.format(Date(25000)))
    t.reference = Date(25000).toInstant()
    Assert.assertEquals("self destruct was: 5 ticks ago...", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    Assert.assertEquals("לפני 3 שנים", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    Assert.assertEquals("לפני 3 עשורים", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    Assert.assertEquals("לפני 3 מאות", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    Assert.assertEquals("לפני 2 שעות", t.format(Date(Date().time - 6543990)))
  }

  @Test

  fun testPreciseInTheFuture() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(Date().time + 1000 * (10 * 60 + 5 * 60 * 60)))
    Assert.assertTrue(durations.size >= 2)
    Assert.assertEquals(5, durations[0].quantity)
    Assert.assertEquals(10, durations[1].quantity)
  }

  @Test

  fun testPreciseInThePast() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(Date().time - 1000 * (10 * 60 + 5 * 60 * 60)))
    Assert.assertTrue(durations.size >= 2)
    Assert.assertEquals(-5, durations[0].quantity)
    Assert.assertEquals(-10, durations[1].quantity)
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    val durations = t.calculatePreciseDuration(Date(0))
    Assert.assertEquals("לפני 3 ימים 15 שעות 38 דקות", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    Assert.assertEquals("בעוד 3 ימים 15 שעות 38 דקות", t.format(durations))
  }

  @Test

  fun testSetLocale() {
    val t = PrettyTime(Date(315569259747L * 3L))
    Assert.assertEquals("לפני 3 עשורים", t.format(Date(0)))
    t.locale = Locale.GERMAN
    Assert.assertEquals("vor 3 Jahrzehnten", t.format(Date(0)))
  }

  /**
   * Tests formatApproximateDuration and by proxy, formatDuration.
   *
   * @throws Exception
   */
  @Test
  fun testFormatDuration() {
    val tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10)
    val tenMinAgo = Date(System.currentTimeMillis() - tenMinMillis)
    val t = PrettyTime()
    val result = t.formatDuration(tenMinAgo)
    Assert.assertTrue(result == "10 דקות")
  }

  @Test
  fun testFormatDurationWithRounding() {
    val tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10) + 1000 * 40
    val tenMinAgo = Date(System.currentTimeMillis() - tenMinMillis)
    val t = PrettyTime()
    val result = t.formatDuration(tenMinAgo)
    Assert.assertTrue(result == "11 דקות")
  }

  @Test
  fun testFormatDurationUnrounded() {
    val tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10) + 1000 * 40
    val tenMinAgo = Date(System.currentTimeMillis() - tenMinMillis)
    val t = PrettyTime()
    val result = t.formatDurationUnrounded(tenMinAgo)
    Assert.assertTrue(result == "10 דקות")
  }

  @Test

  fun testFormatList() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("לפני 41 דקות",
        prettyTime.format(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testFormatListUnrounded() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("לפני 40 דקות",
        prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testFormatDurationList() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("41 דקות",
        prettyTime.formatDuration(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testFormatDurationListUnrounded() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("40 דקות",
        prettyTime.formatDurationUnrounded(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test
  fun testCalculatePreciseDuration() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("לפני 41 דקות",
        prettyTime.format(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test
  fun testCalculatePreciseDurationUnrounded() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("לפני 40 דקות",
        prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  companion object {
    @BeforeClass @AfterClass fun resetLocale() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}

