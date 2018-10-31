package parser

import java.io.File
import java.io.PrintWriter
import java.io.Reader
import java.text.SimpleDateFormat
import java.util.*

class Parser(private val reader: Reader) {
    private val fsm = StateMachine()

    fun parse(): Collection<TitleRecord> {
        reader
                .readLines()
                .forEach { fsm.handleString(it) }
        return fsm.getTitles()
    }

    fun getCount() = fsm.totalCount()

    fun getDatesCount(): Map<Date, Int> = fsm.datesCount.map { Pair(it.date, it.dayCount) }.toMap()

    fun printDates(writer: PrintWriter, datesCount:Map<Date, Int>) {
        for ((key, value) in datesCount) {
            writer.println("date: ${key.format()} count: $value")
        }

        val avg = "%.2f".format(Locale.ROOT, getCount().toDouble() / datesCount.count())
        writer.println("total: ${getCount()} average: $avg")
    }

    companion object {
        fun printList(writer: PrintWriter, titles:Collection<TitleRecord>) {
            for (title in titles) {
                writer.println(title.title)
                writer.println(getAttributes(title))
                if (!title.tags.isEmpty()) {
                    writer.println("tags: ${title.tags.joinToString()}")
                }
                writer.println()
            }
        }

        private fun getAttributes(title: TitleRecord) =
                "start: ${title.startDate.format()} end: ${title.endDate.format()} eps: ${title.episodes} score: ${title.score}"

    }
}

private fun Date.format(): String = SimpleDateFormat("yyyy-MM-dd").format(this)

fun main(args:Array<String>) {
    val file = File(args[0]).reader()
    val writer = if (args.size >= 2 && args[1] != "") {
        val outfile = File(args[1])
        outfile.createNewFile()
        outfile.printWriter()
    }
    else {
        PrintWriter(System.out.writer())
    }

    val parser = Parser(file)
    val titles = parser.parse()
    Parser.printList(writer, titles)
    writer.println()
    val dateCount = parser.getDatesCount()
    parser.printDates(writer, dateCount)
    writer.flush()
    writer.close()
}
