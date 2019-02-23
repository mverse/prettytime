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

import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Duration.*
import java.time.Instant
import java.util.*

/**
 * @author Bram
 */
class PrettyTimeI18n_DA_Test {
  private lateinit var locale: Locale
  private val format = SimpleDateFormat("dd/MM/yyyy")

  @Before
  fun setUp() {
    locale = Locale("da")
  }

  @Test
  fun testPrettyTime() {
    val p = PrettyTime(locale)
    assertEquals("straks", p.format(Date()))
  }

  @Test
  fun testPrettyTimeCenturies() {
    var p = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 århundreder siden", p.format(Date(0)))

    p = PrettyTime(Date(0), locale)
    assertEquals("3 århundreder fra nu", p.format(Date(3155692597470L * 3L)))
  }

  @Test
  fun testCeilingInterval() {
    val then = format.parse("20/5/2009")
    val ref = format.parse("17/6/2009")
    val t = PrettyTime(ref, locale)
    assertEquals("1 måned siden", t.format(then))
  }


  @Test
  fun testRightNow() {
    val now = Instant.now()
    val t = PrettyTime(locale = locale, reference = now)
    assertEquals("straks", t.format(now + ofMillis(600)))
  }

  @Test
  fun testRightNowVariance() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("straks", t.format(Date(600)))
  }

  @Test
  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("om 12 minutter", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test
  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("om 3 timer", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test
  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("om 3 dage", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test
  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("om 3 uger", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test
  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("om 3 måneder", t.format(Date(2629743830L * 3L)))
  }

  @Test
  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("om 3 år", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test
  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 årtier fra nu", t.format(Date(315569259747L * 3L)))
  }

  @Test
  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 århundreder fra nu", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test
  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("et øjeblik siden", t.format(Date(0)))
  }

  @Test
  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()), locale)
    assertEquals("12 minutter siden", t.format(Date(0)))
  }

  @Test
  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 timer siden", t.format(Date(0)))
  }

  @Test
  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 dage siden", t.format(Date(0)))
  }

  @Test
  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 uger siden", t.format(Date(0)))
  }

  @Test
  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L), locale)
    assertEquals("3 måneder siden", t.format(Date(0)))
  }

  @Test
  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L), locale)
    assertEquals("3 år siden", t.format(Date(0)))
  }

  @Test
  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L), locale)
    assertEquals("3 årtier siden", t.format(Date(0)))
  }

  @Test
  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 århundreder siden", t.format(Date(0)))
  }

  companion object {
    @BeforeClass @AfterClass fun resetLocale() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}
