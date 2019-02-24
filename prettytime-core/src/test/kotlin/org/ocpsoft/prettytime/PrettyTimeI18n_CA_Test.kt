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
import org.junit.BeforeClass
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class PrettyTimeI18n_CA_Test {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  @Test
  fun testCeilingInterval() {
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(ref, Locale("ca"))
    assertEquals("fa 1 mes", t.format(then))
  }

  @Test
  fun testRightNow() {
    val t = PrettyTime()
    assertEquals("en un instant", t.format(Instant.now()))
  }

  @Test
  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    assertEquals("en un instant", t.format(Date(600)))
  }

  @Test
  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 12 minuts", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test
  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 3 hores", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test
  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 3 dies", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test
  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 3 setmanes", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test
  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 3 mesos", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 3 anys", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 3 desenis", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("d'aquí a 3 segles", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    assertEquals("fa uns instants", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    assertEquals("fa 12 minuts", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    assertEquals("fa 3 hores", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    assertEquals("fa 3 dies", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    assertEquals("fa 3 setmanes", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    assertEquals("fa 3 mesos", t.format(Date(0)))
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
    assertEquals("fa 3 anys", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("fa 3 desenis", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    assertEquals("fa 3 segles", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    assertEquals("fa 2 hores", t.format(Date(Date().time - 6543990)))
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
    assertEquals("fa 3 dies 15 hores 38 minuts", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("d'aquí a 3 dies 15 hores 38 minuts", t.format(durations))
  }

  @Test
  fun testSetLocale() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("fa 3 desenis", t.format(Date(0)))
    t.locale = Locale.GERMAN
    assertEquals("vor 3 Jahrzehnten", t.format(Date(0)))
  }

  @Before
  fun setUp() {
    Locale.setDefault(Locale("ca"))
  }

  companion object {
    @BeforeClass @AfterClass fun resetLocale() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}
