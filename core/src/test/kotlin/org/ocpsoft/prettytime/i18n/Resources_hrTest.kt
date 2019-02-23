package org.ocpsoft.prettytime.i18n

import org.junit.Assert
import org.junit.Test
import org.ocpsoft.prettytime.PrettyTime
import java.time.OffsetDateTime
import java.util.*

class Resources_hrTest {

  @Test
  fun testFormatMinute() {
    val prettyTime = PrettyTime(defaultLocale = Locale("hr"))
    Assert.assertEquals("za 1 minutu", prettyTime.format(OffsetDateTime.now().plusMinutes(1).toInstant()))
    Assert.assertEquals("za 2 minute", prettyTime.format(OffsetDateTime.now().plusMinutes(2).toInstant()))
    Assert.assertEquals("za 3 minute", prettyTime.format(OffsetDateTime.now().plusMinutes(3).toInstant()))
    Assert.assertEquals("za 4 minute", prettyTime.format(OffsetDateTime.now().plusMinutes(4).toInstant()))
    Assert.assertEquals("za 5 minuta", prettyTime.format(OffsetDateTime.now().plusMinutes(5).toInstant()))
    Assert.assertEquals("za 6 minuta", prettyTime.format(OffsetDateTime.now().plusMinutes(6).toInstant()))

    Assert.assertEquals("prije 1 minutu", prettyTime.format(OffsetDateTime.now().minusMinutes(1).toInstant()))
    Assert.assertEquals("prije 2 minute", prettyTime.format(OffsetDateTime.now().minusMinutes(2).toInstant()))
    Assert.assertEquals("prije 3 minute", prettyTime.format(OffsetDateTime.now().minusMinutes(3).toInstant()))
    Assert.assertEquals("prije 4 minute", prettyTime.format(OffsetDateTime.now().minusMinutes(4).toInstant()))
    Assert.assertEquals("prije 5 minuta", prettyTime.format(OffsetDateTime.now().minusMinutes(5).toInstant()))
    Assert.assertEquals("prije 6 minuta", prettyTime.format(OffsetDateTime.now().minusMinutes(6).toInstant()))
  }
}
