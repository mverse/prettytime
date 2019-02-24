package org.ocpsoft.prettytime

import org.ocpsoft.prettytime.format.SimpleTimeFormat
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat
import org.ocpsoft.prettytime.impl.ResourcesTimeUnit
import java.time.ZoneId
import java.util.*
import java.time.Duration as JavaDuration

object zones {
  init {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.systemDefault()))
  }
}


fun PrettyTime.calculatePreciseDuration(then: Date): List<Duration> {
  return calculatePreciseDuration(then.toInstant())
}

fun PrettyTime.format(date: Date?): String {
  val dates = date ?: Date()
  return format(dates.toInstant())
}

fun secondsOf(seconds: Long): JavaDuration {
  return JavaDuration.ofSeconds(seconds)
}

fun PrettyTime(date: Date): PrettyTime {
  return PrettyTime(reference = date.toInstant())
}

fun PrettyTime(date: Date, testLocale: Locale?): PrettyTime =
    PrettyTime(testLocale = testLocale).apply {
      reference = date.toInstant()
    }

fun PrettyTime(testLocale: Locale?): PrettyTime {
  return when (testLocale) {
    null -> PrettyTime()
    else -> PrettyTime(defaultLocale = testLocale)
  }
}

fun PrettyTime.formatDuration(date: Date): Any? =
    formatDuration(date.toInstant())

fun PrettyTime.formatUnrounded(date: Date): Any? =
    formatUnrounded(date.toInstant())

fun ResourcesTimeFormat(theUnit: ResourcesTimeUnit): ResourcesTimeFormat =
    ResourcesTimeFormat(unit = theUnit)

fun PrettyTime.formatDurationUnrounded(date: Date): Any =
    formatDurationUnrounded(date.toInstant())

fun SimpleTimeFormat.setRoundingTolerance(roundingTolerance: Int): SimpleTimeFormat {
  this.roundingTolerance = roundingTolerance
  return this
}

fun SimpleTimeFormat.setPattern(pattern: String): SimpleTimeFormat {
  this.pattern = pattern
  return this
}

fun SimpleTimeFormat.setPluralName(value: String): SimpleTimeFormat {
  this.pluralName = value
  return this
}

fun SimpleTimeFormat.setFutureSuffix(value: String): SimpleTimeFormat {
  this.futureSuffix = value
  return this
}

fun SimpleTimeFormat.setSingularName(value: String): SimpleTimeFormat {
  this.singularName = value
  return this
}

fun SimpleTimeFormat.setFuturePrefix(value: String): SimpleTimeFormat {
  this.futurePrefix = value
  return this
}

fun SimpleTimeFormat.setPastSuffix(value: String): SimpleTimeFormat {
  this.pastSuffix = value
  return this
}

fun SimpleTimeFormat.setPastPrefix(value: String): SimpleTimeFormat {
  this.pastPrefix = value
  return this
}
