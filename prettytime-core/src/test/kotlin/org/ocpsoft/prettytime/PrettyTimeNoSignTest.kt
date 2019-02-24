package org.ocpsoft.prettytime

import java.text.SimpleDateFormat
import java.util.Locale

import org.junit.Assert
import org.junit.Test
import org.ocpsoft.prettytime.format.SimpleTimeFormat

class PrettyTimeNoSignTest {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  @Test

  fun testNoSuffixes() {
    val then = format.parse("8/20/2009")
    val ref = format.parse("5/17/2009")
    val p = PrettyTime(ref, Locale.ENGLISH)

    val units = p.unitKeys
    for (unit in units) {
      val fmt = p.getFormatOrNull(unit)
      if (fmt is SimpleTimeFormat) {
        fmt.setFuturePrefix("").setFutureSuffix("").setPastPrefix("").setPastSuffix("")
      }
    }

    Assert.assertEquals("3 months", p.format(then))
  }
}
