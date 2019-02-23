package org.ocpsoft.prettytime

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat
import org.ocpsoft.prettytime.units.Minute

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PrettyTimeI18n_EL_Test {
  internal var format = SimpleDateFormat("MM/dd/yyyy")

  private var locale: Locale? = null

  @Before
  fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale.forLanguageTag("el"))
  }

  @Test

  fun testCeilingInterval() {
    val then = format.parse("5/20/2009")
    val ref = format.parse("6/17/2009")
    val t = PrettyTime(ref)
    Assert.assertEquals("1 μήνας Πριν από", t.format(then))
  }

  @Test

  fun testNullDate() {
    val t = PrettyTime()
    val date: Date? = null
    Assert.assertEquals("στιγμές από τώρα", t.format(date))
  }

  @Test

  fun testRightNow() {
    val t = PrettyTime()
    Assert.assertEquals("στιγμές από τώρα", t.format(Date()))
  }

  @Test

  fun testCalculatePreciceDuration() {
    val t = PrettyTime()
    val preciseDuration = t.calculatePreciseDuration(
        Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000).toLong() - (2 * 60 * 1000).toLong()))
    Assert.assertEquals("2 ώρες 2 λεπτά Πριν από", t.format(preciseDuration))
    Assert.assertEquals("2 ώρες 2 λεπτά", t.formatDuration(preciseDuration))
    Assert.assertEquals("στιγμές από τώρα", t.format(t.calculatePreciseDuration(Date())))
  }

  @Test
  fun testCalculatePreciseDuration2() {
    val prettyTime = PrettyTime()
    prettyTime.clearUnits()
    val minutes = Minute
    prettyTime.registerUnit(minutes, ResourcesTimeFormat(minutes))
    Assert.assertEquals("40 λεπτά Πριν από", prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
        Date(Date().time - (40 * 60 * 1000).toLong() - (40 * 1000).toLong()))))
  }

  @Test

  fun testRightNowVariance() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("στιγμές από τώρα", t.format(Date(600)))
  }

  @Test

  fun testMinutesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("12 λεπτά από τώρα", t.format(Date((1000 * 60 * 12).toLong())))
  }

  @Test

  fun testHoursFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 ώρες από τώρα", t.format(Date((1000 * 60 * 60 * 3).toLong())))
  }

  @Test

  fun testDaysFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 ημέρες από τώρα", t.format(Date((1000 * 60 * 60 * 24 * 3).toLong())))
  }

  @Test

  fun testWeeksFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 εβδομάδες από τώρα", t.format(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong())))
  }

  @Test

  fun testMonthsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 μήνες από τώρα", t.format(Date(2629743830L * 3L)))
  }

  @Test

  fun testYearsFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 έτη από τώρα", t.format(Date(2629743830L * 12L * 3L)))
  }

  @Test

  fun testDecadesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 δεκαετίες από τώρα", t.format(Date(315569259747L * 3L)))
  }

  @Test

  fun testCenturiesFromNow() {
    val t = PrettyTime(Date(0))
    Assert.assertEquals("3 αιώνες από τώρα", t.format(Date(3155692597470L * 3L)))
  }

  /*
     * Past
     */
  @Test

  fun testMomentsAgo() {
    val t = PrettyTime(Date(6000))
    Assert.assertEquals("πριν από στιγμές", t.format(Date(0)))
  }

  @Test

  fun testMinutesAgo() {
    val t = PrettyTime(Date((1000 * 60 * 12).toLong()))
    Assert.assertEquals("12 λεπτά Πριν από", t.format(Date(0)))
  }

  @Test

  fun testMinutesFromNowDefaultReference() {
    val t = PrettyTime()
    Assert.assertEquals("12 λεπτά από τώρα", t.format(Date(System.currentTimeMillis() + 1000 * 60 * 12)))
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 3).toLong()))
    Assert.assertEquals("3 ώρες Πριν από", t.format(Date(0)))
  }

  @Test

  fun testHoursAgoDefaultReference() {
    val t = PrettyTime()
    Assert.assertEquals("3 ώρες Πριν από", t.format(Date(System.currentTimeMillis() - 1000 * 60 * 60 * 3)))
  }

  @Test

  fun testDaysAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 3).toLong()))
    Assert.assertEquals("3 ημέρες Πριν από", t.format(Date(0)))
  }

  @Test

  fun testWeeksAgo() {
    val t = PrettyTime(Date((1000 * 60 * 60 * 24 * 7 * 3).toLong()))
    Assert.assertEquals("3 εβδομάδες Πριν από", t.format(Date(0)))
  }

  @Test

  fun testMonthsAgo() {
    val t = PrettyTime(Date(2629743830L * 3L))
    Assert.assertEquals("3 μήνες Πριν από", t.format(Date(0)))
  }

  @Test

  fun testYearsAgo() {
    val t = PrettyTime(Date(2629743830L * 12L * 3L))
    Assert.assertEquals("3 έτη Πριν από", t.format(Date(0)))
  }

  @Test

  fun testDecadesAgo() {
    val t = PrettyTime(Date(315569259747L * 3L))
    Assert.assertEquals("3 δεκαετίες Πριν από", t.format(Date(0)))
  }

  @Test

  fun testCenturiesAgo() {
    val t = PrettyTime(Date(3155692597470L * 3L))
    Assert.assertEquals("3 αιώνες Πριν από", t.format(Date(0)))
  }

  @Test

  fun testWithinTwoHoursRounding() {
    val t = PrettyTime()
    Assert.assertEquals("2 ώρες Πριν από", t.format(Date(Date().time - 6543990)))
  }

  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
