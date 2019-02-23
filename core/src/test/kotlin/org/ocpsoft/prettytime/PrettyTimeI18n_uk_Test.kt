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

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * User: Ihor Lavrynuk Date: 2013-01-05 Time: 16:57
 */
class PrettyTimeI18n_uk_Test {
  private val format = SimpleDateFormat("dd/MM/yyyy")
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale("uk")
    Locale.setDefault(locale!!)
  }

  @Test
  fun testPrettyTime() {
    val p = PrettyTime(locale)
    assertEquals("зараз", p.format(Date()))
  }

  @Test
  fun testPrettyTimeCenturies() {
    var p = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 століття тому", p.format(Date(0)))

    p = PrettyTime(Date(0), locale)
    assertEquals("через 3 століття", p.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("20/5/2009")
    val ref = format.parse("17/6/2009")
    val t = PrettyTime(ref, locale)
    assertEquals("1 місяць тому", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime(locale)
    val date: Date? = null
    assertEquals("зараз", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime(locale)
    assertEquals("зараз", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("зараз", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 12 хвилин", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 3 години", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 3 дні", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 3 тижні", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 3 місяці", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 3 роки", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 3 десятиліття", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0), locale)
    assertEquals("через 3 століття", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000), locale)
    assertEquals("щойно", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()), locale)
    assertEquals("12 хвилин тому", t.format(Date(0)))
  }

  @Test

  fun test1HourAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 1).toLong()), locale)
    assertEquals("1 годину тому", t.format(Date(0)))
  }

  @Test

  fun test3HoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()), locale)
    assertEquals("3 години тому", t.format(Date(0)))
  }

  @Test

  fun test6HoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 6).toLong()), locale)
    assertEquals("6 годин тому", t.format(Date(0)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 дні тому", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()), locale)
    assertEquals("3 тижні тому", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L), locale)
    assertEquals("3 місяці тому", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L), locale)
    assertEquals("3 роки тому", t.format(Date(0)))
  }

  @Test

  fun test8YearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 8L), locale)
    assertEquals("8 років тому", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L), locale)
    assertEquals("3 десятиліття тому", t.format(Date(0)))
  }

  @Test

  fun test8DecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 8L), locale)
    assertEquals("8 десятиліть тому", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 століття тому", t.format(Date(0)))
  }

  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
