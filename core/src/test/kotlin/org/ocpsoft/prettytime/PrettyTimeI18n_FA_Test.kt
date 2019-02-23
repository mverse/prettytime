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

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat

class PrettyTimeI18n_FA_Test {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  // Stores current locale so that it can be restored
  private var locale: Locale? = null

  // Method setUp() is called automatically before every test method
  @Before

  fun setUp() {
    locale = Locale("fa")
    Locale.setDefault(locale!!)
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("5/20/2012")
    val ref = format.parse("6/17/2012")
    val t = PrettyTime(ref)
    assertEquals("1 ماه پیش", t.format(then))
  }

  fun testNullDate() {
    val t = PrettyTime()
    val date: Date? = null
    assertEquals("چند لحظه دیگر", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    assertEquals("چند لحظه دیگر", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    assertEquals("چند لحظه دیگر", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("12 دقیقه دیگر", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 ساعت دیگر", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 روز دیگر", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 هفته دیگر", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 ماه دیگر", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 سال دیگر", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 دهه دیگر", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("3 قرن دیگر", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    assertEquals("چند لحظه پیش", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    assertEquals("12 دقیقه پیش", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    assertEquals("3 ساعت پیش", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    assertEquals("3 روز پیش", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    assertEquals("3 هفته پیش", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    assertEquals("3 ماه پیش", t.format(Date(0)))
  }

  @Test

  fun testCustomFormat() {
    val t = PrettyTime(Date(0))
    val unit = object : TimeUnit {
      override val maxQuantity: Long
        get() = 0

      override val millisPerUnit: Long get() = 5000

      override val isPrecise: Boolean get() = false
    }
    t.clearUnits()
    t.registerUnit(unit, (SimpleTimeFormat()
        .setSingularName("tick").setPluralName("ticks")
        .setPattern("%n %u").setRoundingTolerance(20)
        .setFutureSuffix("... RUN!")
        .setFuturePrefix("self destruct in: ").setPastPrefix("self destruct was: ").setPastSuffix(
            " ago...")))

    assertEquals("self destruct in: 5 ticks ... RUN!", t.format(Date(25000)))
    t.reference = Date(25000).toInstant()
    assertEquals("self destruct was: 5 ticks ago...", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    assertEquals("3 سال پیش", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("3 دهه پیش", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    assertEquals("3 قرن پیش", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    assertEquals("2 ساعت پیش", t.format(Date(Date().time - 6543990)))
  }

  @Test

  fun testPreciseInTheFuture() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(Date().time + 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2)
    assertEquals(5, durations[0].quantity)
    assertEquals(10, durations[1].quantity)
  }

  @Test

  fun testPreciseInThePast() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(Date().time - 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2)
    assertEquals(-5, durations[0].quantity)
    assertEquals(-10, durations[1].quantity)
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("3 روز 15 ساعت 38 دقیقه پیش", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("3 روز 15 ساعت 38 دقیقه دیگر", t.format(durations))
  }

  @Test

  fun testSetLocale() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("3 دهه پیش", t.format(Date(0)))
  }

  // Method tearDown() is called automatically after every test method
  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
