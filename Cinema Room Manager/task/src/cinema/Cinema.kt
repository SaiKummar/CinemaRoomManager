package cinema

fun showSeats(cinemaArray: Array<CharArray>) {
    println("Cinema:")
    print("  ") //print two spaces
    for (i in 1..cinemaArray[0].lastIndex + 1) { //print seat numbers
        print("$i ")
    }
    println()
    for (i in cinemaArray.indices) { //print the rows
        print("${i + 1} ") //print row number
        for (j in cinemaArray[i].indices) {
            print("${cinemaArray[i][j]} ")
        }
        println()
    }
}

fun showStatistics(cinemaArray: Array<CharArray>, currentIncome: Int, rows: Int, seats: Int) {
    var purchasedTickets = 0
    for (eachArray in cinemaArray) {
        for (eachItem in eachArray) {
            if (eachItem == 'B') {
                ++purchasedTickets
            }
        }
    }
    println("Number of purchased tickets: $purchasedTickets")
    val totalTickets = rows * seats
    val ticketSalePercent = purchasedTickets / (totalTickets).toDouble() * 100
    println("Percentage: %.2f".format(ticketSalePercent) + "%")
    println("Current income: $$currentIncome")
    var totalIncome = 0
    if (totalTickets < 60) {
        totalIncome = totalTickets * 10
    } else {
        totalIncome = (rows / 2 * seats * 10) + (rows - (rows / 2)) * seats * 8
    }
    println("Total income: $$totalIncome")
}

fun buyTicket(cinemaArray: Array<CharArray>, rows: Int, seats: Int): Int {
    var rowNumber = 0
    var seatNumber = 0
    do {
        println("Enter a row number:")
        rowNumber = readLine()!!.toInt()
        println("Enter a seat number in that row:")
        seatNumber = readLine()!!.toInt()
        var repeatTheLoop = false
        if (rowNumber > rows || seatNumber > seats) {
            println("Wrong input!")
            repeatTheLoop = true
        }
        if (!repeatTheLoop) {
            if (cinemaArray[rowNumber - 1][seatNumber - 1] == 'B') {
                println("That ticket has already been purchased!")
                repeatTheLoop = true
            } else {
                cinemaArray[rowNumber - 1][seatNumber - 1] = 'B'
            }
        }
    } while (repeatTheLoop)
    val totalSeats = rows * seats
    var ticketPrice = 0
    if (totalSeats < 60) {
        ticketPrice = 10
    } else {
        if (rowNumber <= rows/2) {
            ticketPrice = 10
        } else {
            ticketPrice = 8
        }
    }
    println("Ticket price: $$ticketPrice")
    return ticketPrice
}

fun main() {
    var currentIncome = 0
    //print seating arrangement
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()
    val cinemaArray = Array<CharArray>(rows) { CharArray(seats) } // declare the 2d array
    for (i in cinemaArray.indices) { //populate the 2d array with 'S' characters
        for (j in cinemaArray[i].indices) {
            cinemaArray[i][j] = 'S'
        }
    }
    do {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val input = readLine()!!.toInt()
        when (input) {
            1 -> showSeats(cinemaArray)
            2 -> currentIncome += buyTicket(cinemaArray, rows, seats)
            3 -> showStatistics(cinemaArray, currentIncome, rows, seats)
        }
    } while (input != 0)
}
