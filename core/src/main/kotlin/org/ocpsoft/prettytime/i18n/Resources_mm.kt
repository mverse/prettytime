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
package org.ocpsoft.prettytime.i18n;

import java.util.*

class Resources_mm : ListResourceBundle() {
  companion object {
    private var OBJECTS = arrayOf(
        arrayOf("CenturyPattern", "%n %u"),
        arrayOf("CenturyFuturePrefix", " ယခုမှ"),
        arrayOf("CenturyFutureSuffix", " အကြာ"),
        arrayOf("CenturyPastPrefix", ""),
        arrayOf("CenturyPastSuffix", " အကြာက"),
        arrayOf("CenturySingularName", "ရာစု "),
        arrayOf("CenturyPluralName", " ရာစု "),
        arrayOf("DayPattern", "%n %u"),
        arrayOf("DayFuturePrefix", " ယခုမှ"),
        arrayOf("DayFutureSuffix", " အကြာ"),
        arrayOf("DayPastPrefix", ""),
        arrayOf("DayPastSuffix", " အကြာက"),
        arrayOf("DaySingularName", "ရက်"),
        arrayOf("DayPluralName", "ရက်"),
        arrayOf("DecadePattern", "%u %n"),
        arrayOf("DecadeFuturePrefix", " ယခုမှ"),
        arrayOf("DecadeFutureSuffix", " နှစ်အကြာ"),
        arrayOf("DecadePastPrefix", ""),
        arrayOf("DecadePastSuffix", " နှစ်အကြာက"),
        arrayOf("DecadeSingularName", "ဆယ်စုနှစ်"),
        arrayOf("DecadePluralName", "ဆယ်စုနှစ်"),
        arrayOf("HourPattern", "%n %u"),
        arrayOf("HourFuturePrefix", " ယခုမှ"),
        arrayOf("HourFutureSuffix", " အကြာ"),
        arrayOf("HourPastPrefix", ""),
        arrayOf("HourPastSuffix", " အကြာက"),
        arrayOf("HourSingularName", "နာရီ"),
        arrayOf("HourPluralName", "နာရီ"),
        arrayOf("JustNowPattern", "%u"),
        arrayOf("JustNowFuturePrefix", " ခေတ္တ"),
        arrayOf("JustNowFutureSuffix", " မကြာမီ"),
        arrayOf("JustNowPastPrefix", " အခု"),
        arrayOf("JustNowPastSuffix", " လေးတင်"),
        arrayOf("JustNowSingularName", ""),
        arrayOf("JustNowPluralName", ""),
        arrayOf("MillenniumPattern", "%u %n"),
        arrayOf("MillenniumFuturePrefix", " ယခုမှ"),
        arrayOf("MillenniumFutureSuffix", " နှစ်အကြာ"),
        arrayOf("MillenniumPastPrefix", ""),
        arrayOf("MillenniumPastSuffix", " နှစ်အကြာက"),
        arrayOf("MillenniumSingularName", "ထောင်စုနှစ်"),
        arrayOf("MillenniumPluralName", "ထောင်စုနှစ်"),
        arrayOf("MillisecondPattern", "%n %u"),
        arrayOf("MillisecondFuturePrefix", " ယခုမှ"),
        arrayOf("MillisecondFutureSuffix", " အကြာ"),
        arrayOf("MillisecondPastPrefix", ""),
        arrayOf("MillisecondPastSuffix", " အကြာက"),
        arrayOf("MillisecondSingularName", "မီလီစက္ကန့်"),
        arrayOf("MillisecondPluralName", "မီလီစက္ကန့်"),
        arrayOf("MinutePattern", "%n %u"),
        arrayOf("MinuteFuturePrefix", " ယခုမှ"),
        arrayOf("MinuteFutureSuffix", " အကြာ"),
        arrayOf("MinutePastPrefix", ""),
        arrayOf("MinutePastSuffix", " အကြာက"),
        arrayOf("MinuteSingularName", "မိနစ်"),
        arrayOf("MinutePluralName", "မိနစ်"),
        arrayOf("MonthPattern", "%n %u"),
        arrayOf("MonthFuturePrefix", " ယခုမှ"),
        arrayOf("MonthFutureSuffix", " အကြာ"),
        arrayOf("MonthPastPrefix", ""),
        arrayOf("MonthPastSuffix", " အကြာက"),
        arrayOf("MonthSingularName", "လ"),
        arrayOf("MonthPluralName", "လ"),
        arrayOf("SecondPattern", "%n %u"),
        arrayOf("SecondFuturePrefix", " ယခုမှ"),
        arrayOf("SecondFutureSuffix", " အကြာ"),
        arrayOf("SecondPastPrefix", ""),
        arrayOf("SecondPastSuffix", " အကြာက"),
        arrayOf("SecondSingularName", "စက္ကန့်"),
        arrayOf("SecondPluralName", "စက္ကန့်"),
        arrayOf("WeekPattern", "%n %u"),
        arrayOf("WeekFuturePrefix", " ယခုမှ"),
        arrayOf("WeekFutureSuffix", " အကြာ"),
        arrayOf("WeekPastPrefix", ""),
        arrayOf("WeekPastSuffix", " အကြာက"),
        arrayOf("WeekSingularName", "ရက်သတ္တပတ်"),
        arrayOf("WeekPluralName", "ရက်သတ္တပတ်"),
        arrayOf("YearPattern", "%n %u"),
        arrayOf("YearFuturePrefix", " ယခုမှ"),
        arrayOf("YearFutureSuffix", " အကြာ"),
        arrayOf("YearPastPrefix", ""),
        arrayOf("YearPastSuffix", " အကြာက"),
        arrayOf("YearSingularName", "နှစ်"),
        arrayOf("YearPluralName", "နှစ်"),
        arrayOf("AbstractTimeUnitPattern", ""),
        arrayOf("AbstractTimeUnitFuturePrefix", " ယခုမှ"),
        arrayOf("AbstractTimeUnitFutureSuffix", " အကြာ"),
        arrayOf("AbstractTimeUnitPastPrefix", ""),
        arrayOf("AbstractTimeUnitPastSuffix", ""),
        arrayOf("AbstractTimeUnitSingularName", ""),
        arrayOf("AbstractTimeUnitPluralName", ""))
  }

  override fun getContents() = OBJECTS
}
