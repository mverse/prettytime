package org.ocpsoft.prettytime.nlp

import java.util.Calendar
import java.util.Date
import java.util.Locale

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.ocpsoft.prettytime.PrettyTime
import org.ocpsoft.prettytime.nlp.parse.DateGroup

class PrettyTimeParserTest {

  @Test
  fun testParseTimes() {
    val parse = PrettyTimeParser().parse("let's get lunch at two pm")
    Assert.assertFalse(parse.isEmpty())
    val calendar = Calendar.getInstance()
    calendar.time = parse[0]
    Assert.assertEquals(14, calendar.get(Calendar.HOUR_OF_DAY).toLong())
  }

  @Test
  @Ignore
  fun testParseAmbiguousTimes() {
    val parse = PrettyTimeParser().parseSyntax("let's get lunch at two")
    Assert.assertFalse(parse.isEmpty())
    val calendar = Calendar.getInstance()
    calendar.time = parse[0].dates[0]
    val hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    if (hourOfDay >= 2 && hourOfDay < 14)
      Assert.assertEquals(14, calendar.get(Calendar.HOUR_OF_DAY).toLong())
    else
      Assert.assertEquals(2, calendar.get(Calendar.HOUR_OF_DAY).toLong())
  }

  @Test
  fun testParsePrettyTimeTime() {
    val parse = PrettyTimeParser().parse("I did it three days ago")
    Assert.assertFalse(parse.isEmpty())
    val formatted = PrettyTime(defaultLocale = Locale.ENGLISH).format(parse[0].toInstant())
    Assert.assertEquals("3 days ago", formatted)
  }

  @Test
  fun testParseSyntax() {
    val parse = PrettyTimeParser().parseSyntax("I did it three days ago")
    Assert.assertFalse(parse.isEmpty())
    val formatted = PrettyTime(defaultLocale = Locale.ENGLISH).format(parse[0].dates[0].toInstant())
    Assert.assertEquals("3 days ago", formatted)
    Assert.assertEquals(1, parse[0].line.toLong())
    Assert.assertEquals(9, parse[0].position.toLong())
    Assert.assertEquals(1, parse[0].dates.size.toLong())
    Assert.assertNull(parse[0].recursUntil)
    Assert.assertFalse(parse[0].isRecurring)
    Assert.assertEquals(-1, parse[0].recurInterval)
  }

  @Test
  @Ignore
  fun testParseSyntaxRecurring() {
    val parse = PrettyTimeParser().parseSyntax("I do it every three days")
    Assert.assertFalse(parse.isEmpty())
    val formatted = PrettyTime(defaultLocale = Locale.ENGLISH).format(parse[0].dates[0].toInstant())
    Assert.assertEquals("3 days from now", formatted)
    Assert.assertEquals(1, parse[0].line.toLong())
    Assert.assertEquals(14, parse[0].position.toLong())
    Assert.assertEquals(1, parse[0].dates.size.toLong())
    Assert.assertNull(parse[0].recursUntil)
    Assert.assertTrue(parse[0].isRecurring)
    Assert.assertEquals(1000 * 60 * 60 * 24 * 3L, parse[0].recurInterval)
  }

  @Test
  fun testParseYesterday() {
    val yesterday = Calendar.getInstance()
    yesterday.time = Date()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)

    val parse = PrettyTimeParser().parse("yesterday")
    Assert.assertFalse(parse.isEmpty())

    val parsedDate = Calendar.getInstance()
    parsedDate.time = parse[0]

    Assert.assertEquals(yesterday.get(Calendar.DAY_OF_MONTH).toLong(), parsedDate.get(Calendar.DAY_OF_MONTH).toLong())
    Assert.assertEquals(yesterday.get(Calendar.MONTH).toLong(), parsedDate.get(Calendar.MONTH).toLong())
    Assert.assertEquals(yesterday.get(Calendar.YEAR).toLong(), parsedDate.get(Calendar.YEAR).toLong())
  }
}
