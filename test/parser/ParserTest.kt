package parser

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class ParserTest {
    @Test
    fun dataTest() {
        val data = File("./out/test/TextRulesParser/testData1.txt")
        val parser = Parser(data.reader())
        val titles = parser.parse()
        val totalEps = titles.sumBy { it.episodes + it.time }
        val totalCount = parser.getCount()
        assertEquals(totalEps, totalCount)
    }
}
