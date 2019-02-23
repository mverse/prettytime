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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import java.time.Duration.ofSeconds
import java.time.Instant
import java.util.*
import java.time.Duration as JavaDuration

class SimpleTimeFormatTest {
  // Stores current locale so that it can be restored
  private lateinit var locale: Locale

  // Method setUp() is called automatically before every test method
  @Before fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale.ROOT)
  }

  @Test
  fun testRounding() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3 + 1000 * 60 * 45).toLong()))
    val duration = t.approximateDuration(Date(0).toInstant())

    assertEquals("4 hours ago", t.format(duration))
    assertEquals("3 hours ago", t.formatUnrounded(duration))
  }

  @Test

  fun testDecorating() {
    val t = PrettyTime()
    val format = SimpleTimeFormat().setFuturePrefix("from now").setPastSuffix("ago")

    var duration = t.approximateDuration(Instant.now() - ofSeconds(1000))
    assertEquals("some time from now", format.decorate(duration, "some time"))

    duration = t.approximateDuration(Instant.now() + ofSeconds(10000))
    assertEquals("some time ago", format.decorate(duration, "some time"))
  }

  // Method tearDown() is called automatically after every test method
  @After fun tearDown() {
    Locale.setDefault(locale)
  }
}
