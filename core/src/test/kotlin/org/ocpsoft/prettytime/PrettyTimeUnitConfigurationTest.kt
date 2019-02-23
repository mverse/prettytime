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
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.PrettyTime
import org.ocpsoft.prettytime.TimeUnit
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import org.ocpsoft.prettytime.units.Hour
import org.ocpsoft.prettytime.units.JustNow
import org.ocpsoft.prettytime.units.Minute

class PrettyTimeUnitConfigurationTest {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  // Stores current locale so that it can be restored
  private var locale: Locale? = null

  // Method setUp() is called automatically before every test method
  @Before

  fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale.ROOT)
  }

  @Test

  fun testRightNow() {
    val ref = Date(0)
    val then = Date(2)

    val t = PrettyTime(ref)
    val format = t.removeUnit(JustNow::class.java)
    Assert.assertNotNull(format)
    assertEquals("2 milliseconds from now", t.format(then))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    val format = t.removeUnit(Minute::class.java)
    Assert.assertNotNull(format)
    assertEquals("720 seconds from now", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    val format = t.removeUnit(Hour::class.java)
    Assert.assertNotNull(format)
    assertEquals("180 minutes from now", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  // Method tearDown() is called automatically after every test method
  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
