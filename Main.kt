package parking

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var parkingLot = emptyArray<String>()

    while (true) {
        val input = scanner.nextLine().split(" ")
        val command = input[0]

        when (command) {
            "create" -> {
                val size = input[1].toInt()
                parkingLot = Array(size) { " " }
                println("Created a parking lot with $size spots.")
            }

            "park" -> {
                if (parkingLot.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                } else if (!parkingLot.contains(" ")) {
                    println("Sorry, the parking lot is full.")
                } else {
                    val carNumber = input[1]
                    val carColor = input[2]
                    val spotNumber = parkingLot.indexOf(" ") + 1
                    parkingLot[spotNumber - 1] = "$carNumber $carColor"
                    println("$carColor car parked in spot $spotNumber.")
                }
            }

            "leave" -> {
                val spotNumber = input[1].toInt()
                if (parkingLot.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                } else if (parkingLot[spotNumber - 1] == " ") {
                    println("There is no car in spot $spotNumber.")
                } else {
                    parkingLot[spotNumber - 1] = " "
                    println("Spot $spotNumber is free.")
                }
            }

            "status" -> {
                if (parkingLot.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                } else if (parkingLot.all { it.trim().isEmpty() }) {
                    println("Parking lot is empty.")
                } else {
                    for (spot in parkingLot.indices) {
                        if (parkingLot[spot] != " ") {
                            println("${spot + 1} ${parkingLot[spot]}")
                        }
                    }
                }
            }

            "reg_by_color" -> {
                if (parkingLot.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    val color = input[1].uppercase()
                    var found = false
                    val regNumbers = mutableListOf<String>()
                    for (spot in parkingLot.indices) {
                        if (parkingLot[spot].isNotBlank() && parkingLot[spot].split(" ")[1].toUpperCase() == color) {
                            regNumbers.add(parkingLot[spot].split(" ")[0])
                            found = true
                        }
                    }
                    if (found) {
                        println(regNumbers.joinToString(", "))
                    } else {
                        println("No cars with color $color were found.")
                    }
                }

            }

            "spot_by_color" -> {
                if (parkingLot.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    val color = input[1].uppercase()
                    var found = false
                    val spotNumbers = mutableListOf<Int>()
                    for (spot in parkingLot.indices) {
                        if (parkingLot[spot].isNotBlank() && parkingLot[spot].split(" ")[1].uppercase() == color) {
                            spotNumbers.add(spot + 1)
                            found = true
                        }
                    }
                    if (found) {
                        println(spotNumbers.joinToString(", "))
                    } else {
                        println("No cars with color $color were found.")
                    }
                }
            }

            "spot_by_reg" -> {
                if (parkingLot.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    val regNumber = input[1]
                    var found = false
                    for (spot in parkingLot.indices) {
                        if (parkingLot[spot].isNotBlank() && parkingLot[spot].split(" ")[0] == regNumber) {
                            println(spot + 1)
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        println("No cars with registration number $regNumber were found.")
                    }
                }
            }

            "exit" -> {
                break
            }
        }
    }
}
