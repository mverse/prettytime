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

import org.junit.After
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import java.text.SimpleDateFormat
import java.util.*

class PrettyTimeI18n_AR_Test {
  internal var format = SimpleDateFormat("MM/dd/yyyy")
  lateinit var locale: Locale

  @Before
  fun setUp() {
    locale = Locale("ar")
    Locale.setDefault(locale)
  }

  @Test fun testCeilingInterval() {
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(reference = ref.toInstant())
    assertEquals("منذ 1 شهر", t.format(then.toInstant()))
  }

  @Test
  fun testFromNow() {
    val prettyTime = PrettyTime(defaultLocale = locale)
    assertEquals("بعد لحظات", prettyTime.format(Date()))
  }

  @Test
  fun testRightNow() {
    val t = PrettyTime()
    assertEquals("بعد لحظات", t.format(Date()))
  }

  @Test
  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد لحظات", t.format(Date(600)))
  }

  @Test
  fun testPrettyTimeDefault() {
    val p = PrettyTime(locale)
    assertEquals(p.format(Date()), "بعد لحظات")
  }

  @Test
  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 12 دقائق", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test
  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 3 ساعات", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 3 ايام",
        t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test
  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 3 أسابيع",
        t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 3 أشهر", t.format(Date(2629743830L * 3L)))
  }

  @Test
  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 3 سنوات", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test
  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 3 عقود", t.format(Date(315569259747L * 3L)))
  }

  @Test
  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("بعد 3 قرون",
        t.format(Date(3155692597470L * 3L)))
  }

  /*
   * Past
   */
  @Test
  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    assertEquals("منذ لحظات", t.format(Date(0)))
  }

  @Test
  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    assertEquals("منذ 12 دقائق", t.format(Date(0)))
  }

  @Test
  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    assertEquals("منذ 3 ساعات", t.format(Date(0)))
  }

  @Test
  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    assertEquals("منذ 3 ايام", t.format(Date(0)))
  }

  @Test
  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    assertEquals("منذ 3 أسابيع", t.format(Date(0)))
  }

  @Test
  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    assertEquals("منذ 3 أشهر", t.format(Date(0)))
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

    assertEquals("self destruct in: 5 ticks ... RUN!", t.format(Date(25000)))
    t.reference = Date(25000).toInstant()
    assertEquals("self destruct was: 5 ticks ago...", t.format(Date(0)))
  }

  @Test
  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    assertEquals("منذ 3 سنوات", t.format(Date(0)))
  }

  @Test
  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("منذ 3 عقود", t.format(Date(0)))
  }

  @Test
  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    assertEquals("منذ 3 قرون", t.format(Date(0)))
  }

  @Test
  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    assertEquals("منذ 2 ساعات", t.format(Date(Date().time - 6543990)))
  }

  @Test
  fun testPreciseInTheFuture() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(Date().time + 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2) // might be more because of milliseconds between date capturing and result
    // calculation
    assertEquals(5, durations[0].quantity)
    assertEquals(10, durations[1].quantity)
  }

  @Test
  fun testPreciseInThePast() {
    val t = PrettyTime()
    val durations = t.calculatePreciseDuration(Date(Date().time - 1000 * (10 * 60 + 5 * 60 * 60)))
    assertTrue(durations.size >= 2) // might be more because of milliseconds between date capturing and result
    // calculation
    assertEquals(-5, durations[0].quantity)
    assertEquals(-10, durations[1].quantity)
  }

  @Test
  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("منذ 3 ايام 15 ساعات 38 دقائق", t.format(durations))
  }

  @Test
  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("بعد 3 ايام 15 ساعات 38 دقائق", t.format(durations))
  }

  @Test
  fun testSetLocale() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("منذ 3 عقود", t.format(Date(0)))
  }

  @After
  fun tearDown() {
    Locale.setDefault(locale)
  }

  companion object {
    @AfterClass fun resetLocale() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}


