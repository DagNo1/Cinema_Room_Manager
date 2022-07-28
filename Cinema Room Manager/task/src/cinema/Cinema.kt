package cinema

fun main() {
    println("Enter the number of rows:")
    val r = readln().toInt()
    println("Enter the number of seats in each row:")
    val s = readln().toInt()
    val seats = MutableList(r){MutableList(s){"S"}}

    var cont = true
    var income = 0
    var no_t = 0
    var per : Double
    var total =   if (r % 2 == 0 && s * r > 60) ((r / 2) * s * 10) + ((r / 2) * s * 8)
    else if (r % 2 != 0 && s * r > 60) ((r / 2) * s * 10) + (((r / 2) + 1) * s * 8)
    else  r * s * 10
    while(cont) {
        // menu
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val inp = readln().toInt()
        when(inp) {
            1 -> {
                //print all the seats
                print("Cinema: \n ")
                for (i in 1..s) print(" $i")
                print("\n")
                for (i in 1..r) {
                    print("$i ")
                    for (j in 1..s) print(seats[i-1][j-1] + " ")
                    print("\n")
                }
            }
            2 -> {
                var form = true
                //chosse seat
                var cr : Int
                var cs : Int
                do {
                    println("Enter a row number:")
                    cr = readln().toInt()
                    println("Enter a seat number in that row:")
                    cs = readln().toInt()
                    try{
                        seats[cr-1][cs-1]
                    } catch (e: IndexOutOfBoundsException) {
                        println("Wrong input!")
                        continue
                    }
                    if (seats[cr-1][cs-1] == "B") println("That ticket has already been purchased!")
                    else {
                        form = false
                        seats[cr-1][cs-1] = "B"
                    }
                } while (form);

                no_t++
                //calculate price for that seat
                var pr = 0
                if (s * r <= 60) pr = 10
                else {
                    if (r % 2 == 0 && cr <= (r / 2)) pr = 10
                    else if (r % 2 == 0 && cr > (r / 2)) pr = 8
                    else if (r % 2 != 0 && cr <= (r / 2)) pr = 10
                    else if (r % 2 != 0 && cr > (r / 2)) pr = 8
                }
                income = income + pr
                println("Ticket price: $" + pr)
            }
            3 -> {
                per = try {(no_t.toDouble() / (s * r)) * 100} catch (e: ArithmeticException) { 0.0 }
                println("Number of purchased tickets: $no_t")
                println("Percentage: " + "%.2f".format(per) + "%")
                println("Current income: $" + income)
                println("Total income: $" + total)
            }
            0 -> cont = false

        }
    }
}