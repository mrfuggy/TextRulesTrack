package parser

import java.util.*

class TitleRecord(
        val title: String,
        val startDate: Date,
        val endDate: Date,
        val episodes: Int,
        val score: Int,
        val time: Int,
        val tags: List<String>,
        val drop: Boolean,
        val continues: Boolean) {
    fun change(
            currentDate: Date,
            eps: Int,
            score: Int,
            addTime: Int,
            tags: List<String>,
            drop: Boolean,
            cont: Boolean): TitleRecord {
        return TitleRecord(
                title,
                startDate,
                currentDate,
                if (cont) episodes + eps else eps,
                score,
                time + addTime,
                tags,
                drop,
                cont)
    }

    fun getTotal(): Int {
        return episodes + time
    }

    fun getTotal(tr: TitleRecord): Int {
        return episodes + time - tr.episodes - tr.time
    }
}