package utilities

object SortGrid {

    // Enumeration to represent the sort order
    object SortOrder extends Enumeration {
        type SortOrder = Value
        val Ascending, Descending = Value
    }
    import SortOrder._

    def sortGrid(sortOrder: SortOrder)(a: String, b: String): Int = {
        val (aValue, bValue) = (parseValue(a), parseValue(b))

        val result = (aValue, bValue) match {
            case (aInt: Int, bInt: Int) => aInt.compareTo(bInt)
            case (aStr: String, bStr: String) =>
                aStr.toLowerCase.compareTo(bStr.toLowerCase)
            case _ =>
                throw new IllegalArgumentException("Unsupported comparison")
        }

        if (sortOrder == Descending) -result else result
    }

    // Parse the value as either Int or String
    def parseValue(value: String): Any = {
        try {
            value.trim.toInt
        } catch {
            case _: NumberFormatException => value.trim
        }
    }
}
