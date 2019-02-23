package org.ocpsoft.prettytime.i18n

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.TimeFormat
import org.ocpsoft.prettytime.TimeUnit
import org.ocpsoft.prettytime.impl.TimeFormatProvider
import org.ocpsoft.prettytime.units.Decade
import org.ocpsoft.prettytime.units.Millennium
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class Resources_ja : ListResourceBundle(), TimeFormatProvider {
  private val formatMap = ConcurrentHashMap<TimeUnit, TimeFormat>()
  override fun getContents() = PATTERNS
  companion object {
    private var PATTERNS = arrayOf(
        arrayOf("CenturyPattern", "%n%u"),
        arrayOf("CenturyFuturePrefix", "今から"),
        arrayOf("CenturyFutureSuffix", "後"),
        arrayOf("CenturyPastPrefix", ""),
        arrayOf("CenturyPastSuffix", "前"),
        arrayOf("CenturySingularName", "世紀"),
        arrayOf("CenturyPluralName", "世紀"),
        arrayOf("DayPattern", "%n%u"),
        arrayOf("DayFuturePrefix", "今から"),
        arrayOf("DayFutureSuffix", "後"),
        arrayOf("DayPastPrefix", ""),
        arrayOf("DayPastSuffix", "前"),
        arrayOf("DaySingularName", "日"),
        arrayOf("DayPluralName", "日"),
        arrayOf("DecadePattern", "%n%u"),
        arrayOf("DecadeFuturePrefix", "今から"),
        arrayOf("DecadeFutureSuffix", "後"),
        arrayOf("DecadePastPrefix", ""),
        arrayOf("DecadePastSuffix", "前"),
        arrayOf("DecadeSingularName", "年"),
        arrayOf("DecadePluralName", "年"),
        arrayOf("HourPattern", "%n%u"),
        arrayOf("HourFuturePrefix", "今から"),
        arrayOf("HourFutureSuffix", "後"),
        arrayOf("HourPastPrefix", ""),
        arrayOf("HourPastSuffix", "前"),
        arrayOf("HourSingularName", "時間"),
        arrayOf("HourPluralName", "時間"),
        arrayOf("JustNowPattern", "%u"),
        arrayOf("JustNowFuturePrefix", "今から"),
        arrayOf("JustNowFutureSuffix", "すぐ"),
        arrayOf("JustNowPastPrefix", ""),
        arrayOf("JustNowPastSuffix", "たった今"),
        arrayOf("JustNowSingularName", ""),
        arrayOf("JustNowPluralName", ""),
        arrayOf("MillenniumPattern", "%n%u"),
        arrayOf("MillenniumFuturePrefix", "今から"),
        arrayOf("MillenniumFutureSuffix", "後"),
        arrayOf("MillenniumPastPrefix", ""),
        arrayOf("MillenniumPastSuffix", "前"),
        arrayOf("MillenniumSingularName", "年"),
        arrayOf("MillenniumPluralName", "年"),
        arrayOf("MillisecondPattern", "%n%u"),
        arrayOf("MillisecondFuturePrefix", "今から"),
        arrayOf("MillisecondFutureSuffix", "後"),
        arrayOf("MillisecondPastPrefix", ""),
        arrayOf("MillisecondPastSuffix", "前"),
        arrayOf("MillisecondSingularName", "ミリ秒"),
        arrayOf("MillisecondPluralName", "ミリ秒"),
        arrayOf("MinutePattern", "%n%u"),
        arrayOf("MinuteFuturePrefix", "今から"),
        arrayOf("MinuteFutureSuffix", "後"),
        arrayOf("MinutePastPrefix", ""),
        arrayOf("MinutePastSuffix", "前"),
        arrayOf("MinuteSingularName", "分"),
        arrayOf("MinutePluralName", "分"),
        arrayOf("MonthPattern", "%n%u"),
        arrayOf("MonthFuturePrefix", "今から"),
        arrayOf("MonthFutureSuffix", "後"),
        arrayOf("MonthPastPrefix", ""),
        arrayOf("MonthPastSuffix", "前"),
        arrayOf("MonthSingularName", "ヶ月"),
        arrayOf("MonthPluralName", "ヶ月"),
        arrayOf("SecondPattern", "%n%u"),
        arrayOf("SecondFuturePrefix", "今から"),
        arrayOf("SecondFutureSuffix", "後"),
        arrayOf("SecondPastPrefix", ""),
        arrayOf("SecondPastSuffix", "前"),
        arrayOf("SecondSingularName", "秒"),
        arrayOf("SecondPluralName", "秒"),
        arrayOf("WeekPattern", "%n%u"),
        arrayOf("WeekFuturePrefix", "今から"),
        arrayOf("WeekFutureSuffix", "後"),
        arrayOf("WeekPastPrefix", ""),
        arrayOf("WeekPastSuffix", "前"),
        arrayOf("WeekSingularName", "週間"),
        arrayOf("WeekPluralName", "週間"),
        arrayOf("YearPattern", "%n%u"),
        arrayOf("YearFuturePrefix", "今から"),
        arrayOf("YearFutureSuffix", "後"),
        arrayOf("YearPastPrefix", ""),
        arrayOf("YearPastSuffix", "前"),
        arrayOf("YearSingularName", "年"),
        arrayOf("YearPluralName", "年"),
        arrayOf("AbstractTimeUnitPattern", ""),
        arrayOf("AbstractTimeUnitFuturePrefix", ""),
        arrayOf("AbstractTimeUnitFutureSuffix", ""),
        arrayOf("AbstractTimeUnitPastPrefix", ""),
        arrayOf("AbstractTimeUnitPastSuffix", ""),
        arrayOf("AbstractTimeUnitSingularName", ""),
        arrayOf("AbstractTimeUnitPluralName", ""))
  }

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    if (!formatMap.containsKey(t)) {
      (formatMap as java.util.Map<TimeUnit, TimeFormat>).putIfAbsent(t, JaTimeFormat(this, t))
    }
    return formatMap[t]
  }

  private class JaTimeFormat(private val bundle: ResourceBundle, unit: TimeUnit) : TimeFormat {
    private var singularName = ""
    private var pluralName = ""
    private var futureSingularName: String? = ""
    private var futurePluralName: String? = ""
    private var pastSingularName: String? = ""
    private var pastPluralName: String? = ""

    private var pattern = ""
    private var futurePrefix = ""
    private var futureSuffix = ""
    private var pastPrefix = ""
    private var pastSuffix = ""
    private var roundingTolerance = 50

    init {

      setPattern(bundle.getString(getUnitName(unit) + "Pattern"))
      setFuturePrefix(bundle.getString(getUnitName(unit) + "FuturePrefix"))
      setFutureSuffix(bundle.getString(getUnitName(unit) + "FutureSuffix"))
      setPastPrefix(bundle.getString(getUnitName(unit) + "PastPrefix"))
      setPastSuffix(bundle.getString(getUnitName(unit) + "PastSuffix"))

      setSingularName(bundle.getString(getUnitName(unit) + "SingularName"))
      setPluralName(bundle.getString(getUnitName(unit) + "PluralName"))

      try {
        setFuturePluralName(bundle.getString(getUnitName(unit) + "FuturePluralName"))
      } catch (e: Exception) {
      }

      try {
        setFutureSingularName(bundle.getString(getUnitName(unit) + "FutureSingularName"))
      } catch (e: Exception) {
      }

      try {
        setPastPluralName(bundle.getString(getUnitName(unit) + "PastPluralName"))
      } catch (e: Exception) {
      }

      try {
        setPastSingularName(bundle.getString(getUnitName(unit) + "PastSingularName"))
      } catch (e: Exception) {
      }
    }

    override fun format(duration: Duration): String {
      return format(duration, true)
    }

    override fun formatUnrounded(duration: Duration): String {
      return format(duration, false)
    }

    override fun decorate(duration: Duration, time: String): String {
      val result = StringBuilder()
      if (duration.isInPast) {
        result.append(pastPrefix).append(time).append(pastSuffix)
      } else {
        result.append(futurePrefix).append(time).append(futureSuffix)
      }
      return result.toString().replace("\\s+".toRegex(), " ").trim { it <= ' ' }
    }

    override fun decorateUnrounded(duration: Duration, time: String): String {
      return decorate(duration, time)
    }

    fun getPattern(): String {
      return pattern
    }

    /*
     * Builder Setters
     */
    fun setPattern(pattern: String): JaTimeFormat {
      this.pattern = pattern
      return this
    }

    fun setFuturePrefix(futurePrefix: String): JaTimeFormat {
      this.futurePrefix = futurePrefix.trim { it <= ' ' }
      return this
    }

    fun setFutureSuffix(futureSuffix: String): JaTimeFormat {
      this.futureSuffix = futureSuffix.trim { it <= ' ' }
      return this
    }

    fun setPastPrefix(pastPrefix: String): JaTimeFormat {
      this.pastPrefix = pastPrefix.trim { it <= ' ' }
      return this
    }

    fun setPastSuffix(pastSuffix: String): JaTimeFormat {
      this.pastSuffix = pastSuffix.trim { it <= ' ' }
      return this
    }

    /**
     * The percentage of the current [TimeUnit].getMillisPerUnit() for which the quantity may be rounded up by
     * one.
     */
    fun setRoundingTolerance(roundingTolerance: Int): JaTimeFormat {
      this.roundingTolerance = roundingTolerance
      return this
    }

    fun setSingularName(name: String): JaTimeFormat {
      this.singularName = name
      return this
    }

    fun setPluralName(pluralName: String): JaTimeFormat {
      this.pluralName = pluralName
      return this
    }

    fun setFutureSingularName(futureSingularName: String): JaTimeFormat {
      this.futureSingularName = futureSingularName
      return this
    }

    fun setFuturePluralName(futurePluralName: String): JaTimeFormat {
      this.futurePluralName = futurePluralName
      return this
    }

    fun setPastSingularName(pastSingularName: String): JaTimeFormat {
      this.pastSingularName = pastSingularName
      return this
    }

    fun setPastPluralName(pastPluralName: String): JaTimeFormat {
      this.pastPluralName = pastPluralName
      return this
    }

    override fun toString(): String {
      return ("JaTimeFormat [pattern=" + pattern + ", futurePrefix=" + futurePrefix + ", futureSuffix="
          + futureSuffix + ", pastPrefix=" + pastPrefix + ", pastSuffix=" + pastSuffix +
          ", roundingTolerance="
          + roundingTolerance + "]")
    }

    protected fun getPattern(quantity: Long): String {
      return pattern
    }

    protected fun getQuantity(duration: Duration, round: Boolean): Long {
      return Math.abs(if (round) duration.getQuantityRounded(roundingTolerance) else duration.quantity)
    }

    protected fun getGramaticallyCorrectName(d: Duration, round: Boolean): String {
      var result = getSingularName(d)
      if (Math.abs(getQuantity(d, round)) == 0L || Math.abs(getQuantity(d, round)) > 1) {
        result = getPluralName(d)
      }
      return result
    }

    private fun getUnitName(unit: TimeUnit): String {
      return unit.javaClass.simpleName
    }

    private fun format(duration: Duration, round: Boolean): String {
      val sign = getSign(duration)
      val unit = getGramaticallyCorrectName(duration, round)
      var quantity = getQuantity(duration, round)
      if (duration.unit is Decade) {
        quantity *= 10
      }
      if (duration.unit is Millennium) {
        quantity *= 1000
      }

      return applyPattern(sign, unit, quantity)
    }

    private fun applyPattern(sign: String, unit: String, quantity: Long): String {
      var result = getPattern(quantity).replace(SIGN.toRegex(), sign)
      result = result.replace(QUANTITY.toRegex(), quantity.toString())
      result = result.replace(UNIT.toRegex(), unit)

      return result
    }

    private fun getSign(d: Duration): String {
      return if (d.quantity < 0) {
        NEGATIVE
      } else ""
    }

    private fun getSingularName(duration: Duration): String {
      return when {
        duration.isInFuture && futureSingularName != null && futureSingularName!!.isNotEmpty() -> futureSingularName!!
        duration.isInPast && pastSingularName != null && pastSingularName!!.isNotEmpty() -> pastSingularName!!
        else -> singularName
      }
    }

    private fun getPluralName(duration: Duration): String {
      return when {
        duration.isInFuture && futurePluralName != null && futureSingularName!!.length > 0 -> futurePluralName!!
        duration.isInPast && pastPluralName != null && pastSingularName!!.length > 0 -> pastPluralName!!
        else -> pluralName
      }
    }

    companion object {

      val SIGN = "%s"
      val QUANTITY = "%n"
      val UNIT = "%u"
      private val NEGATIVE = "-"
    }
  }
}
