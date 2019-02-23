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

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import org.junit.Before
import org.junit.Test

/**
 * @author Bram
 */
class PrettyTimeI18n_NO_Test {
  private var locale: Locale? = null
  private val format = SimpleDateFormat("dd/MM/yyyy")

  @Before

  fun setUp() {
    locale = Locale("no")
  }

  @Test
  fun testPrettyTime() {
    val p = PrettyTime(locale)
    assertEquals("straks", p.format(Date()))
  }

  @Test
  fun testPrettyTimeCenturies() {
    var p = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 århundre siden", p.format(Date(0)))

    p = PrettyTime(Date(0), locale)
    assertEquals("3 århundre fra nå", p.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("20/5/2009")
    val ref = format.parse("17/6/2009")
    val t = PrettyTime(ref, locale)
    assertEquals("1 måned siden", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime(locale)
    val date: Date? = null
    assertEquals("straks", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime(locale)
    assertEquals("straks", t.format(Date()))
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
    assertEquals("om 3 dager", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("om 3 uker", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
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
    assertEquals("3 tiår fra nå", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("3 århundre fra nå", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("et øyeblikk siden", t.format(Date(0)))
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
    assertEquals("3 dager siden", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 uker siden", t.format(Date(0)))
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
    assertEquals("3 tiår siden", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 århundre siden", t.format(Date(0)))
  }
}
