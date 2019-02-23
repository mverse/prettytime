package org.ocpsoft.prettytime.i18n

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.TimeFormat
import org.ocpsoft.prettytime.TimeUnit
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import org.ocpsoft.prettytime.impl.TimeFormatProvider
import org.ocpsoft.prettytime.units.Day
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer

open class Resources_fi : ListResourceBundle(), TimeFormatProvider {

  private val formatMap = ConcurrentHashMap<TimeUnit, TimeFormat>()

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    if (!formatMap.containsKey(t)) {
      (formatMap as java.util.Map<TimeUnit, TimeFormat>).putIfAbsent(t, FiTimeFormat(this, t))
    }
    return formatMap[t]
  }

  override fun getContents() = CONTENTS

  companion object {
    private val tolerance = 50
    private val CONTENTS = arrayOf(
        arrayOf("JustNowPattern", "%u"),
        arrayOf("JustNowPastSingularName", "hetki"),
        arrayOf("JustNowFutureSingularName", "hetken"),
        arrayOf("JustNowPastSuffix", "sitten"),
        arrayOf("JustNowFutureSuffix", "päästä"),
        arrayOf("MillisecondPattern", "%u"),
        arrayOf("MillisecondPluralPattern", "%n %u"),
        arrayOf("MillisecondPastSingularName", "millisekunti"),
        arrayOf("MillisecondPastPluralName", "millisekuntia"),
        arrayOf("MillisecondFutureSingularName", "millisekunnin"),
        arrayOf("MillisecondPastSuffix", "sitten"),
        arrayOf("MillisecondFutureSuffix", "päästä"),
        arrayOf("SecondPattern", "%u"),
        arrayOf("SecondPluralPattern", "%n %u"),
        arrayOf("SecondPastSingularName", "sekunti"),
        arrayOf("SecondPastPluralName", "sekuntia"),
        arrayOf("SecondFutureSingularName", "sekunnin"),
        arrayOf("SecondPastSuffix", "sitten"),
        arrayOf("SecondFutureSuffix", "päästä"),
        arrayOf("MinutePattern", "%u"),
        arrayOf("MinutePluralPattern", "%n %u"),
        arrayOf("MinutePastSingularName", "minuutti"),
        arrayOf("MinutePastPluralName", "minuuttia"),
        arrayOf("MinuteFutureSingularName", "minuutin"),
        arrayOf("MinutePastSuffix", "sitten"),
        arrayOf("MinuteFutureSuffix", "päästä"),
        arrayOf("HourPattern", "%u"),
        arrayOf("HourPluralPattern", "%n %u"),
        arrayOf("HourPastSingularName", "tunti"),
        arrayOf("HourPastPluralName", "tuntia"),
        arrayOf("HourFutureSingularName", "tunnin"),
        arrayOf("HourPastSuffix", "sitten"),
        arrayOf("HourFutureSuffix", "päästä"),
        arrayOf("DayPattern", "%u"),
        arrayOf("DayPluralPattern", "%n %u"),
        arrayOf("DayPastSingularName", "eilen"),
        arrayOf("DayPastPluralName", "päivää"),
        arrayOf("DayFutureSingularName", "huomenna"),
        arrayOf("DayFuturePluralName", "päivän"),
        arrayOf("DayPastSuffix", "sitten"),
        arrayOf("DayFutureSuffix", "päästä"),
        arrayOf("WeekPattern", "%u"),
        arrayOf("WeekPluralPattern", "%n %u"),
        arrayOf("WeekPastSingularName", "viikko"),
        arrayOf("WeekPastPluralName", "viikkoa"),
        arrayOf("WeekFutureSingularName", "viikon"),
        arrayOf("WeekFuturePluralName", "viikon"),
        arrayOf("WeekPastSuffix", "sitten"),
        arrayOf("WeekFutureSuffix", "päästä"),
        arrayOf("MonthPattern", "%u"),
        arrayOf("MonthPluralPattern", "%n %u"),
        arrayOf("MonthPastSingularName", "kuukausi"),
        arrayOf("MonthPastPluralName", "kuukautta"),
        arrayOf("MonthFutureSingularName", "kuukauden"),
        arrayOf("MonthPastSuffix", "sitten"),
        arrayOf("MonthFutureSuffix", "päästä"),
        arrayOf("YearPattern", "%u"),
        arrayOf("YearPluralPattern", "%n %u"),
        arrayOf("YearPastSingularName", "vuosi"),
        arrayOf("YearPastPluralName", "vuotta"),
        arrayOf("YearFutureSingularName", "vuoden"),
        arrayOf("YearPastSuffix", "sitten"),
        arrayOf("YearFutureSuffix", "päästä"),
        arrayOf("DecadePattern", "%u"),
        arrayOf("DecadePluralPattern", "%n %u"),
        arrayOf("DecadePastSingularName", "vuosikymmen"),
        arrayOf("DecadePastPluralName", "vuosikymmentä"),
        arrayOf("DecadeFutureSingularName", "vuosikymmenen"),
        arrayOf("DecadePastSuffix", "sitten"),
        arrayOf("DecadeFutureSuffix", "päästä"),
        arrayOf("CenturyPattern", "%u"),
        arrayOf("CenturyPluralPattern", "%n %u"),
        arrayOf("CenturyPastSingularName", "vuosisata"),
        arrayOf("CenturyPastPluralName", "vuosisataa"),
        arrayOf("CenturyFutureSingularName", "vuosisadan"),
        arrayOf("CenturyPastSuffix", "sitten"),
        arrayOf("CenturyFutureSuffix", "päästä"),
        arrayOf("MillenniumPattern", "%u"),
        arrayOf("MillenniumPluralPattern", "%n %u"),
        arrayOf("MillenniumPastSingularName", "vuosituhat"),
        arrayOf("MillenniumPastPluralName", "vuosituhatta"),
        arrayOf("MillenniumFutureSingularName", "vuosituhannen"),
        arrayOf("MillenniumPastSuffix", "sitten"),
        arrayOf("MillenniumFutureSuffix", "päästä"))
  }

  class FiTimeFormat(private val bundle: ResourceBundle, unit: TimeUnit) : SimpleTimeFormat() {
    private var pastName = ""
    private var futureName = ""
    override var pastPluralName = ""
    override var futurePluralName = ""
    var pluralPattern = ""

    init {

      if (bundle.containsKey(getUnitName(unit) + "PastSingularName")) {
        this.configureFi(Consumer { builder ->
          builder.setPastName(bundle.getString(getUnitName(unit) + "PastSingularName"))
          builder.setFutureName(bundle.getString(getUnitName(unit) + "FutureSingularName"))
          builder.pastPluralName = bundle.getString(getUnitName(unit) + "PastSingularName")
          builder.futurePluralName = bundle.getString(getUnitName(unit) + "FutureSingularName")
          builder.pluralPattern = bundle.getString(getUnitName(unit) + "Pattern")
        })


        if (bundle.containsKey(getUnitName(unit) + "PastPluralName")) {
          this.pastPluralName = bundle.getString(getUnitName(unit) + "PastPluralName")
        }

        if (bundle.containsKey(getUnitName(unit) + "FuturePluralName")) {
          this.futurePluralName = bundle.getString(getUnitName(unit) + "FuturePluralName")
        }

        if (bundle.containsKey(getUnitName(unit) + "PluralPattern")) {
          this.pluralPattern = bundle.getString(getUnitName(unit) + "PluralPattern")
        }

        this.configure { format ->
          format.pattern = bundle.getString(getUnitName(unit) + "Pattern")
          format.pastSuffix = bundle.getString(getUnitName(unit) + "PastSuffix")
          format.futureSuffix = bundle.getString(getUnitName(unit) + "FutureSuffix")
          format.futurePrefix = ""
          format.pastPrefix = ""
          format.singularName = ""
          format.pluralName = ""
          Unit
        }
      }
    }

    fun configureFi(configurer: Consumer<FiTimeFormat>) {
      configurer.accept(this)
    }

    fun getPastName(): String {
      return pastName
    }

    fun setPastName(pastName: String): FiTimeFormat {
      this.pastName = pastName
      return this
    }

    fun getFutureName(): String {
      return futureName
    }

    fun setFutureName(futureName: String): FiTimeFormat {
      this.futureName = futureName
      return this
    }

    override fun decorate(duration: Duration, time: String): String {
      var result = ""
      result = if (duration.unit is Day && Math.abs(duration.getQuantityRounded(tolerance)) == 1L) {
        time
      } else {
        super.decorate(duration, time)
      }
      return result
    }

    override fun getPattern(quantity: Long): String {
      return when {
        Math.abs(quantity) == 1L -> pattern
        else -> pluralPattern
      }
    }

    override fun getGramaticallyCorrectName(d: Duration, round: Boolean): String {
      var result = if (d.isInPast) getPastName() else getFutureName()
      if (Math.abs(getQuantity(d, round)) == 0L || Math.abs(getQuantity(d, round)) > 1) {
        result = if (d.isInPast) pastPluralName else futurePluralName
      }
      return result
    }

    private fun getUnitName(unit: TimeUnit): String {
      return unit.javaClass.simpleName
    }
  }
}
