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
package org.ocpsoft.prettytime.i18n

import java.util.*

open class Resources_en : ListResourceBundle() {

  public override fun getContents() = OBJECTS

  companion object {
    private val OBJECTS = arrayOf(arrayOf("CenturyPattern", "%n %u"),
        arrayOf("CenturyFuturePrefix", ""),
        arrayOf("CenturyFutureSuffix", " from now"),
        arrayOf("CenturyPastPrefix", ""),
        arrayOf("CenturyPastSuffix", " ago"),
        arrayOf("CenturySingularName", "century"),
        arrayOf("CenturyPluralName", "centuries"),
        arrayOf("DayPattern", "%n %u"),
        arrayOf("DayFuturePrefix", ""),
        arrayOf("DayFutureSuffix", " from now"),
        arrayOf("DayPastPrefix", ""),
        arrayOf("DayPastSuffix", " ago"),
        arrayOf("DaySingularName", "day"),
        arrayOf("DayPluralName", "days"),
        arrayOf("DecadePattern", "%n %u"),
        arrayOf("DecadeFuturePrefix", ""),
        arrayOf("DecadeFutureSuffix", " from now"),
        arrayOf("DecadePastPrefix", ""),
        arrayOf("DecadePastSuffix", " ago"),
        arrayOf("DecadeSingularName", "decade"),
        arrayOf("DecadePluralName", "decades"),
        arrayOf("HourPattern", "%n %u"),
        arrayOf("HourFuturePrefix", ""),
        arrayOf("HourFutureSuffix", " from now"),
        arrayOf("HourPastPrefix", ""),
        arrayOf("HourPastSuffix", " ago"),
        arrayOf("HourSingularName", "hour"),
        arrayOf("HourPluralName", "hours"),
        arrayOf("JustNowPattern", "%u"),
        arrayOf("JustNowFuturePrefix", ""),
        arrayOf("JustNowFutureSuffix", "moments from now"),
        arrayOf("JustNowPastPrefix", "moments ago"),
        arrayOf("JustNowPastSuffix", ""),
        arrayOf("JustNowSingularName", ""),
        arrayOf("JustNowPluralName", ""),
        arrayOf("MillenniumPattern", "%n %u"),
        arrayOf("MillenniumFuturePrefix", ""),
        arrayOf("MillenniumFutureSuffix", " from now"),
        arrayOf("MillenniumPastPrefix", ""),
        arrayOf("MillenniumPastSuffix", " ago"),
        arrayOf("MillenniumSingularName", "millennium"),
        arrayOf("MillenniumPluralName", "millennia"),
        arrayOf("MillisecondPattern", "%n %u"),
        arrayOf("MillisecondFuturePrefix", ""),
        arrayOf("MillisecondFutureSuffix", " from now"),
        arrayOf("MillisecondPastPrefix", ""),
        arrayOf("MillisecondPastSuffix", " ago"),
        arrayOf("MillisecondSingularName", "millisecond"),
        arrayOf("MillisecondPluralName", "milliseconds"),
        arrayOf("MinutePattern", "%n %u"),
        arrayOf("MinuteFuturePrefix", ""),
        arrayOf("MinuteFutureSuffix", " from now"),
        arrayOf("MinutePastPrefix", ""),
        arrayOf("MinutePastSuffix", " ago"),
        arrayOf("MinuteSingularName", "minute"),
        arrayOf("MinutePluralName", "minutes"),
        arrayOf("MonthPattern", "%n %u"),
        arrayOf("MonthFuturePrefix", ""),
        arrayOf("MonthFutureSuffix", " from now"),
        arrayOf("MonthPastPrefix", ""),
        arrayOf("MonthPastSuffix", " ago"),
        arrayOf("MonthSingularName", "month"),
        arrayOf("MonthPluralName", "months"),
        arrayOf("SecondPattern", "%n %u"),
        arrayOf("SecondFuturePrefix", ""),
        arrayOf("SecondFutureSuffix", " from now"),
        arrayOf("SecondPastPrefix", ""),
        arrayOf("SecondPastSuffix", " ago"),
        arrayOf("SecondSingularName", "second"),
        arrayOf("SecondPluralName", "seconds"),
        arrayOf("WeekPattern", "%n %u"),
        arrayOf("WeekFuturePrefix", ""),
        arrayOf("WeekFutureSuffix", " from now"),
        arrayOf("WeekPastPrefix", ""),
        arrayOf("WeekPastSuffix", " ago"),
        arrayOf("WeekSingularName", "week"),
        arrayOf("WeekPluralName", "weeks"),
        arrayOf("YearPattern", "%n %u"),
        arrayOf("YearFuturePrefix", ""),
        arrayOf("YearFutureSuffix", " from now"),
        arrayOf("YearPastPrefix", ""),
        arrayOf("YearPastSuffix", " ago"),
        arrayOf("YearSingularName", "year"),
        arrayOf("YearPluralName", "years"),
        arrayOf("AbstractTimeUnitPattern", ""),
        arrayOf("AbstractTimeUnitFuturePrefix", ""),
        arrayOf("AbstractTimeUnitFutureSuffix", ""),
        arrayOf("AbstractTimeUnitPastPrefix", ""),
        arrayOf("AbstractTimeUnitPastSuffix", ""),
        arrayOf("AbstractTimeUnitSingularName", ""),
        arrayOf("AbstractTimeUnitPluralName", ""))
  }
}
