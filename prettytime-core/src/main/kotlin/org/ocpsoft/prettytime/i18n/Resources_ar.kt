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

class Resources_ar : ListResourceBundle() {
  public override fun getContents() = OBJECTS

  companion object {
    private val OBJECTS = arrayOf(
        arrayOf("CenturyPattern", "%n %u"),
        arrayOf("CenturyFuturePrefix", "بعد "),
        arrayOf("CenturyFutureSuffix", ""),
        arrayOf("CenturyPastPrefix", "منذ "),
        arrayOf("CenturyPastSuffix", ""),
        arrayOf("CenturySingularName", "قرن"),
        arrayOf("CenturyPluralName", "قرون"),
        arrayOf("DayPattern", "%n %u"),
        arrayOf("DayFuturePrefix", "بعد "),
        arrayOf("DayFutureSuffix", ""),
        arrayOf("DayPastPrefix", "منذ "),
        arrayOf("DayPastSuffix", ""),
        arrayOf("DaySingularName", "يوم"),
        arrayOf("DayPluralName", "ايام"),
        arrayOf("DecadePattern", "%n %u"),
        arrayOf("DecadeFuturePrefix", "بعد "),
        arrayOf("DecadeFutureSuffix", ""),
        arrayOf("DecadePastPrefix", "منذ "),
        arrayOf("DecadePastSuffix", ""),
        arrayOf("DecadeSingularName", "عقد"),
        arrayOf("DecadePluralName", "عقود"),
        arrayOf("HourPattern", "%n %u"),
        arrayOf("HourFuturePrefix", "بعد "),
        arrayOf("HourFutureSuffix", ""),
        arrayOf("HourPastPrefix", "منذ "),
        arrayOf("HourPastSuffix", ""),
        arrayOf("HourSingularName", "ساعة"),
        arrayOf("HourPluralName", "ساعات"),
        arrayOf("JustNowPattern", "%u"),
        arrayOf("JustNowFuturePrefix", ""),
        arrayOf("JustNowFutureSuffix", " بعد لحظات"),
        arrayOf("JustNowPastPrefix", " منذ لحظات"),
        arrayOf("JustNowPastSuffix", ""),
        arrayOf("JustNowSingularName", ""),
        arrayOf("JustNowPluralName", ""),
        arrayOf("MillenniumPattern", "%n %u"),
        arrayOf("MillenniumFuturePrefix", "بعد "),
        arrayOf("MillenniumFutureSuffix", ""),
        arrayOf("MillenniumPastPrefix", "منذ "),
        arrayOf("MillenniumPastSuffix", ""),
        arrayOf("MillenniumSingularName", "جيل"),
        arrayOf("MillenniumPluralName", "اجيال"),
        arrayOf("MillisecondPattern", "%n %u"),
        arrayOf("MillisecondFuturePrefix", "بعد "),
        arrayOf("MillisecondFutureSuffix", ""),
        arrayOf("MillisecondPastPrefix", "منذ "),
        arrayOf("MillisecondPastSuffix", ""),
        arrayOf("MillisecondSingularName", "جزء من الثانية"),
        arrayOf("MillisecondPluralName", "اجزاء من الثانية"),
        arrayOf("MinutePattern", "%n %u"),
        arrayOf("MinuteFuturePrefix", "بعد "),
        arrayOf("MinuteFutureSuffix", ""),
        arrayOf("MinutePastPrefix", "منذ "),
        arrayOf("MinutePastSuffix", ""),
        arrayOf("MinuteSingularName", "دقيقة"),
        arrayOf("MinutePluralName", "دقائق"),
        arrayOf("MonthPattern", "%n %u"),
        arrayOf("MonthFuturePrefix", "بعد "),
        arrayOf("MonthFutureSuffix", ""),
        arrayOf("MonthPastPrefix", "منذ "),
        arrayOf("MonthPastSuffix", ""),
        arrayOf("MonthSingularName", "شهر"),
        arrayOf("MonthPluralName", "أشهر"),
        arrayOf("SecondPattern", "%n %u"),
        arrayOf("SecondFuturePrefix", "بعد "),
        arrayOf("SecondFutureSuffix", ""),
        arrayOf("SecondPastPrefix", "منذ "),
        arrayOf("SecondPastSuffix", ""),
        arrayOf("SecondSingularName", "ثانية"),
        arrayOf("SecondPluralName", "ثوان"),
        arrayOf("WeekPattern", "%n %u"),
        arrayOf("WeekFuturePrefix", "بعد "),
        arrayOf("WeekFutureSuffix", ""),
        arrayOf("WeekPastPrefix", "منذ "),
        arrayOf("WeekPastSuffix", ""),
        arrayOf("WeekSingularName", "أسبوع"),
        arrayOf("WeekPluralName", "أسابيع"),
        arrayOf("YearPattern", "%n %u"),
        arrayOf("YearFuturePrefix", "بعد "),
        arrayOf("YearFutureSuffix", ""),
        arrayOf("YearPastPrefix", "منذ "),
        arrayOf("YearPastSuffix", ""),
        arrayOf("YearSingularName", "سنة"),
        arrayOf("YearPluralName", "سنوات"),
        arrayOf("AbstractTimeUnitPattern", ""),
        arrayOf("AbstractTimeUnitFuturePrefix", ""),
        arrayOf("AbstractTimeUnitFutureSuffix", ""),
        arrayOf("AbstractTimeUnitPastPrefix", ""),
        arrayOf("AbstractTimeUnitPastSuffix", ""),
        arrayOf("AbstractTimeUnitSingularName", ""),
        arrayOf("AbstractTimeUnitPluralName", ""))
  }
}
