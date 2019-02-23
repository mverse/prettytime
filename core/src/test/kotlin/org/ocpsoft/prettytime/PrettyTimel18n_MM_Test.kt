package org.ocpsoft.prettytime

import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.Date
import java.util.Locale

import org.junit.Assert.assertEquals

/**
 * Created by vincentpaing on 5/17/17.
 */
class PrettyTimel18n_MM_Test {

  /*
    * A note when you want to use the YourKit profiler: To use the YourKit
    * profiler (http://yourkit.com), run with VM argument for profiling:
    * -agentlib:yjpagent=onexit=snapshot,tracing
    */

  // Stores current locale so that it can be restored
  private var locale: Locale? = null

  // Method setUp() is called automatically before every test method
  @Before

  fun setUp() {
    locale = Locale("MM")
    Locale.setDefault(locale!!)
  }

  fun testNullDate() {
    val t = PrettyTime()
    val date: Date? = null
    assertEquals("ခေတ္တ မကြာမီ", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    assertEquals("ခေတ္တ မကြာမီ", t.format(Date()))
  }

  @Test

  fun testMinutes() {
    var t = PrettyTime(Date((1000 * 60 * 12).toLong()), locale)
    assertEquals("12 မိနစ် အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 12 မိနစ် အကြာ", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHours() {
    var t = PrettyTime(Date((1000 * 60 * 60 * 5).toLong()), locale)
    assertEquals("5 နာရီ အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 5 နာရီ အကြာ", t.format(Date((1000 * 60 * 60 * 5).toLong())))
  }

  @Test

  fun testDays() {
    var t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()), locale)
    assertEquals("3 ရက် အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 3 ရက် အကြာ", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeks() {
    var t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 2).toLong()), locale)
    assertEquals("2 ရက်သတ္တပတ် အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 2 ရက်သတ္တပတ် အကြာ", t.format(Date((1000 * 60 * 60 * 24 * 7 * 2).toLong())))
  }

  @Test

  fun testMonths() {
    var t = PrettyTime(Date(2629743830L * 3L), locale)
    assertEquals("3 လ အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 3 လ အကြာ", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYears() {
    var t = PrettyTime(Date(2629743830L * 12L * 3L), locale)
    assertEquals("3 နှစ် အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 3 နှစ် အကြာ", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecades() {
    var t = PrettyTime(Date(315569259747L * 3L), locale)
    assertEquals("ဆယ်စုနှစ် 3 နှစ်အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ ဆယ်စုနှစ် 3 နှစ်အကြာ", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturies() {
    var t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 ရာစု အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 3 ရာစု အကြာ", t.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testCentury() {
    var t = PrettyTime(Date(3155692597470L * 3L), locale)
    assertEquals("3 ရာစု အကြာက", t.format(Date(0)))

    t = PrettyTime(Date(0))
    assertEquals("ယခုမှ 3 ရာစု အကြာ", t.format(Date(3155692597470L * 3L)))
  }

  @Test

  fun testFormattingDurationList() {
    var t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38).toLong()))
    var durations = t.calculatePreciseDuration(Date(0))
    assertEquals("3 ရက် 15 နာရီ 38 မိနစ် အကြာက", t.format(durations))

    t = PrettyTime(Date(0))
    durations = t.calculatePreciseDuration(Date((1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
        + 1000 * 60 * 38).toLong()))
    assertEquals("ယခုမှ 3 ရက် 15 နာရီ 38 မိနစ် အကြာ", t.format(durations))
  }
}
