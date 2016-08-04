package iii_conventions

import java.util.*
import kotlin.comparisons.compareBy

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return compareBy(
            { dt: MyDate -> dt.year },
            { dt: MyDate -> dt.month },
            { dt: MyDate -> dt.dayOfMonth }
        ).compare(this, other)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval) = this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(interval: RepeatedInterval) = this.addTimeIntervals(interval.interval, interval.times)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(times: Int): RepeatedInterval = RepeatedInterval(this, times)

data class RepeatedInterval(val interval: TimeInterval, val times: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(d: MyDate): Boolean =  d >= start && d <= endInclusive

    operator fun iterator(): Iterator<MyDate> =
        object: Iterator<MyDate> {
            var curr: MyDate = start
            
            override fun hasNext(): Boolean =  curr <= endInclusive

            override fun next(): MyDate {
                if (hasNext()) {
                    val c = curr
                    curr = curr.nextDay()
                    return c
                }
                
                throw NoSuchElementException()
            }
        }
}
