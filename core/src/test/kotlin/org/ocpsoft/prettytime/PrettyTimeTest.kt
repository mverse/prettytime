/*
 * Copyright 2012 <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ocpsoft.prettytime

import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import org.ocpsoft.prettytime.units.Day
import org.ocpsoft.prettytime.units.Hour
import org.ocpsoft.prettytime.units.Minute
import org.ocpsoft.prettytime.units.Second
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*

class PrettyTimeTest {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale.ENGLISH)
  }

  @Test
  fun testCeilingInterval() {
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(ref)
    Assert.assertEquals("1 month ago", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime()
    val date: Date? = null
    Assert.assertEquals("moments from now", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    Assert.assertEquals("moments from now", t.format(Date()))
  }

  @Test

  fun testCalculatePreciceDuration() {
    val t = PrettyTime()
    val preciseDuration = t.calculatePreciseDuration(
        Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000).toLong() - (2 * 60 * 1000).toLong()))
    Assert.assertEquals("2 hours 2 minutes ago", t.format(preciseDuration))
    Assert.assertEquals("2 hours 2 minutes", t.formatDuration(preciseDuration))
    Assert.assertEquals("moments from now", t.format(t.calculatePreciseDuration(Date())))
  }

  @Test
  fun testCalculatePreciceDurationMillenia() {
    val date = Date(2014, 8, 15, 0, 0)
    val t = PrettyTime(date)
    val durations = t.calculatePreciseDuration(Date(0))
    Assert.assertEquals("1 millennium 9 centuries 4 decades 4 years 8 months 1 week 6 days 20 hours 5 minutes ago",
        t.format(durations))
    Assert.assertEquals("1 millennium 9 centuries 4 decades 4 years 8 months 1 week 6 days 20 hours 5 minutes",
        t.formatDuration(durations))
  }

  @Test
  fun testCalculatePreciceDurationSeconds() {
    val now = Instant.now()
    val plusFiveHours = now.plusSeconds(60 * 60 * 5 + (60 * 10))

    val t = PrettyTime(reference = now)
    val durations = t.calculatePreciseDuration(plusFiveHours)
    assertk.assertAll {
      assertk.assert(durations).hasSize(2)
      assertk.assert(durations[0].unit).isInstanceOf(Hour::class)
      assertk.assert(durations[0].quantity).isEqualTo(5L)
      assertk.assert(durations[1].unit).isInstanceOf(Minute::class)
      assertk.assert(durations[1].quantity).isEqualTo(10L)
    }
  }

  @Test
  fun testCalculatePreciceDurationDays() {
    val now = OffsetDateTime.now()
    val plusFiveHours = now.plusDays(2).plusHours(10).plusMinutes(4).plusSeconds(45)

    val t = PrettyTime(reference = now.toInstant())
    val durations = t.calculatePreciseDuration(plusFiveHours.toInstant())
    assertk.assert(durations).hasSize(3)
    assertk.assertAll {
      assertk.assert(durations[0].unit).isInstanceOf(Day::class)
      assertk.assert(durations[0].quantity).isEqualTo(2L)
      assertk.assert(durations[1].unit).isInstanceOf(Hour::class)
      assertk.assert(durations[1].quantity).isEqualTo(10L)
      assertk.assert(durations[2].unit).isInstanceOf(Minute::class)
      assertk.assert(durations[2].quantity).isEqualTo(4L)
    }
  }

  @Test
  fun testCalculatePreciseDuration2() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("40 minutes ago", prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
        Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("moments from now", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("12 minutes from now", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 hours from now", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 days from now", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 weeks from now", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 months from now", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 years from now", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 decades from now", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 centuries from now", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    Assert.assertEquals("moments ago", t.format(Date(0)))
  }

  @Test
  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    Assert.assertEquals("12 minutes ago", t.format(Date(0)))
  }

  @Test

  fun testMinutesFromNowDefaultReference() {
    val t = PrettyTime()
    Assert.assertEquals("12 minutes from now", t.format(Date(System.currentTimeMillis() + 1000 * 60 * 12)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    Assert.assertEquals("3 hours ago", t.format(Date(0)))
  }

  @Test

  fun testHoursAgoDefaultReference() {
    val t = PrettyTime()
    Assert.assertEquals("3 hours ago", t.format(Date(System.currentTimeMillis() - 1000 * 60 * 60 * 3)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    Assert.assertEquals("3 days ago", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    Assert.assertEquals("3 weeks ago", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    Assert.assertEquals("3 months ago", t.format(Date(0)))
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
    t.registerUnit(unit, (SimpleTimeFormat()
        .setSingularName("tick").setPluralName("ticks")
        .setPattern("%n %u").setRoundingTolerance(20)
        .setFutureSuffix("... RUN!")
        .setFuturePrefix("self destruct in: ").setPastPrefix("self destruct was: ").setPastSuffix(
            " ago...")))

    Assert.assertEquals("self destruct in: 5 ticks ... RUN!", t.format(Date(25000)))
    t.reference = Date(25000).toInstant()
    Assert.assertEquals("self destruct was: 5 ticks ago...", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    Assert.assertEquals("3 years ago", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    Assert.assertEquals("3 decades ago", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    Assert.assertEquals("3 centuries ago", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    Assert.assertEquals("2 hours ago", t.format(Date(Date().time - 6543990)))
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
    Assert.assertEquals("3 days 15 hours 38 minutes ago", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    Assert.assertEquals("3 days 15 hours 38 minutes from now", t.format(durations))
  }

  @Test

  fun testSetLocale() {
    val t = PrettyTime(Date(315569259747L * 3L))
    Assert.assertEquals("3 decades ago", t.format(Date(0)))
    t.locale = Locale.GERMAN
    Assert.assertEquals("vor 3 Jahrzehnten", t.format(Date(0)))
  }

  /**
   * Tests formatApproximateDuration and by proxy, formatDuration.
   *
   * @throws Exception
   */
  @Test fun testFormatDuration() {
    val tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10)
    val tenMinAgo = Date(System.currentTimeMillis() - tenMinMillis)
    val t = PrettyTime()
    val result = t.formatDuration(tenMinAgo)
    Assert.assertTrue(result == "10 minutes")
  }

  @Test

  fun testFormatDurationWithRounding() {
    val tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10) + 1000 * 40
    val tenMinAgo = Date(System.currentTimeMillis() - tenMinMillis)
    val t = PrettyTime()
    val result = t.formatDuration(tenMinAgo)
    Assert.assertTrue(result == "11 minutes")
  }

  @Test

  fun testFormatDurationUnrounded() {
    val tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10) + 1000 * 40
    val tenMinAgo = Date(System.currentTimeMillis() - tenMinMillis)
    val t = PrettyTime()
    val result = t.formatDurationUnrounded(tenMinAgo)
    Assert.assertTrue(result == "10 minutes")
  }

  @Test

  fun testFormatList() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("41 minutes ago",
        prettyTime.format(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testFormatListUnrounded() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("40 minutes ago",
        prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testFormatDurationList() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("41 minutes",
        prettyTime.formatDuration(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testFormatDurationListUnrounded() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("40 minutes",
        prettyTime.formatDurationUnrounded(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test
  fun testCalculatePreciseDuration() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("41 minutes ago",
        prettyTime.format(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test
  fun testCalculatePreciseDurationUnrounded() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute()
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("40 minutes ago",
        prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
            Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  companion object {
    @BeforeClass @AfterClass fun resetLocale() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}
