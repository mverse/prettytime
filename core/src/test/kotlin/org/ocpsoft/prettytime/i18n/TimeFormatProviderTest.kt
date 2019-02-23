package org.ocpsoft.prettytime.i18n

import java.util.Date
import java.util.Locale
import java.util.ResourceBundle

import org.junit.Assert
import org.junit.Test
import org.ocpsoft.prettytime.PrettyTime
import org.ocpsoft.prettytime.impl.TimeFormatProvider

class TimeFormatProviderTest {
  @Test
  fun test() {
    val bundle = ResourceBundle.getBundle("org.ocpsoft.prettytime.i18n.Resources", Locale("xx"))
    Assert.assertTrue(bundle is TimeFormatProvider)
  }

  @Test

  fun testFormatFromDirectFormatOverride() {
    val prettyTime = PrettyTime(defaultLocale = Locale("xx"))
    val result = prettyTime.format(Date(System.currentTimeMillis() + 1000 * 60 * 6).toInstant())
    Assert.assertEquals("6 minuti from now", result)
  }
}
