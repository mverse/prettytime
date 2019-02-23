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
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.units.JustNow
import org.ocpsoft.prettytime.units.Month
import java.text.SimpleDateFormat
import java.util.*

class PrettyTimeI18n_CS_Test {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  @Test
  fun testCeilingInterval() {
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(ref)
    assertEquals("před 1 měsícem", t.format(then))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    assertEquals("za chvíli", t.format(Date()))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    assertEquals("za chvíli", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    for (u in t.unitKeys) {
      if (u is JustNow) {
        u.maxQuantity = 1000L
      }
    }
    assertEquals("za 1 minutu", t.format(Date((1000 * 60 * 1).toLong())))
    assertEquals("za 3 minuty", t.format(Date((1000 * 60 * 3).toLong())))
    assertEquals("za 12 minut", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {

    val t = PrettyTime(Date(0))
    assertEquals("za 1 hodinu", t.format(Date((1000 * 60 * 60 * 1).toLong())))
    assertEquals("za 3 hodiny", t.format(Date((1000 * 60 * 60 * 3).toLong())))
    assertEquals("za 10 hodin", t.format(Date((1000 * 60 * 60 * 10).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("za 1 den", t.format(Date((1000 * 60 * 60 * 24 * 1).toLong())))
    assertEquals("za 3 dny", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
    assertEquals("za 5 dní", t.format(Date((1000 * 60 * 60 * 24 * 5).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    for (u in t.unitKeys) {
      if (u is Month) {
        t.removeUnit(u)
      }
    }
    assertEquals("za 1 týden", t.format(Date(1000 * 60 * 60 * 24 * 7 * 1L)))
    assertEquals("za 3 týdny", t.format(Date(1000 * 60 * 60 * 24 * 7 * 3L)))
    assertEquals("za 5 týdnů", t.format(Date(1000 * 60 * 60 * 24 * 7 * 5L)))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("za 1 měsíc", t.format(Date(2629743830L * 1L)))
    assertEquals("za 3 měsíce", t.format(Date(2629743830L * 3L)))
    assertEquals("za 6 měsíců", t.format(Date(2629743830L * 6L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("za 1 rok", t.format(Date(2629743830L * 12L * 1L)))
    assertEquals("za 3 roky", t.format(Date(2629743830L * 12L * 3L)))
    assertEquals("za 9 let", t.format(Date(2629743830L * 12L * 9L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("za 3 desetiletí", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    assertEquals("za 3 století", t.format(Date(3155692597470L * 3L)))
  }

  /*
    * Past
    */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    assertEquals("před chvílí", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    assertEquals("před 12 minutami", t.format(Date(0)))
  }

  @Test

  fun testHoursAgo() {
    val base = Date()
    val t = PrettyTime(base)

    assertEquals("před 1 hodinou", t.format(addTime(base, -1, Calendar.HOUR_OF_DAY)))
    assertEquals("před 3 hodinami", t.format(addTime(base, -3, Calendar.HOUR_OF_DAY)))
  }

  @Test

  fun testDaysAgo() {
    val base = Date()
    val t = PrettyTime(base)

    assertEquals("před 1 dnem", t.format(addTime(base, -1, Calendar.DAY_OF_MONTH)))
    assertEquals("před 3 dny", t.format(addTime(base, -3, Calendar.DAY_OF_MONTH)))
  }

  @Test

  fun testWeeksAgo() {
    val base = Date()
    val t = PrettyTime(base)

    assertEquals("před 1 týdnem", t.format(addTime(base, -1, Calendar.WEEK_OF_MONTH)))
    assertEquals("před 3 týdny", t.format(addTime(base, -3, Calendar.WEEK_OF_MONTH)))
  }

  @Test

  fun testMonthsAgo() {
    val base = Date()
    val t = PrettyTime(base)

    assertEquals("před 1 měsícem", t.format(addTime(base, -1, Calendar.MONTH)))
    assertEquals("před 3 měsíci", t.format(addTime(base, -3, Calendar.MONTH)))
  }

  @Test

  fun testYearsAgo() {
    val base = Date()
    val t = PrettyTime(base)
    for (u in t.unitKeys) {
      if (u is Month) {
        t.removeUnit(u)
      }
    }
    assertEquals("před 1 rokem", t.format(addTime(base, -1, Calendar.YEAR)))
    assertEquals("před 3 roky", t.format(addTime(base, -3, Calendar.YEAR)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    assertEquals("před 3 desetiletími", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    assertEquals("před 3 stoletími", t.format(Date(0)))
  }

  @Test

  fun testFormattingDurationListInThePast() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    val durations = t.calculatePreciseDuration(Date(0))
    assertEquals("před 3 dny 15 hodinami 38 minutami", t.format(durations))
  }

  @Test

  fun testFormattingDurationListInTheFuture() {
    val t = PrettyTime(Date(0))
    val durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("za 3 dny 15 hodin 38 minut", t.format(durations))
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
    Assert.assertEquals("10 minutami", result)
  }

  private fun addTime(time: Date, amount: Int, field: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = time
    calendar.add(field, amount)
    return calendar.time
  }

  private var locale: Locale? = null

  @Before
  fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale("cs"))
  }

  @After
  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}

