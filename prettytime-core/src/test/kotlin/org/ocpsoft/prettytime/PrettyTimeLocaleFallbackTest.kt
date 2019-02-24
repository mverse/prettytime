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
import org.ocpsoft.prettytime.PrettyTime

class PrettyTimeLocaleFallbackTest {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  // Stores current locale so that it can be restored
  private var locale: Locale? = null

  // Method setUp() is called automatically before every test method
  @Before

  fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale("Foo", "Bar"))
  }

  @Test

  fun testCeilingInterval() {
    assertEquals(Locale("Foo", "Bar"), Locale.getDefault())
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(ref)
    assertEquals("1 month ago", t.format(then))
  }

  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
